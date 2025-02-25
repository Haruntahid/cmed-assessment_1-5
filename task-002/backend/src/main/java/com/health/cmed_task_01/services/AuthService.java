package com.health.cmed_task_01.services;


import com.health.cmed_task_01.entity.dtos.LoginDto;
import com.health.cmed_task_01.entity.dtos.UsersDto;
import com.health.cmed_task_01.entity.mapper.UserMapper;
import com.health.cmed_task_01.entity.model.Users;
import com.health.cmed_task_01.exceptions.ResourceAlreadyExist;
import com.health.cmed_task_01.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;


    public void register(UsersDto dto) {
        Users user = userMapper.toEntity(dto,passwordEncoder);

        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            throw new ResourceAlreadyExist("Username already exists");
        }
        userRepo.save(user);
    }


    public String verify(LoginDto dto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );

        if (!authentication.isAuthenticated()) {
            return "user not authenticated";
        } else {
            return jwtService.generateToken(dto.getUsername());
        }

    }
}
