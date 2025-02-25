package com.cmed.task_04.service;


import com.cmed.task_04.entity.dtos.LoginDto;
import com.cmed.task_04.entity.dtos.UsersDto;
import com.cmed.task_04.entity.mapper.UserMapper;
import com.cmed.task_04.entity.model.Users;
import com.cmed.task_04.exceptions.AlreadyExistException;
import com.cmed.task_04.repo.UserRepository;
import com.cmed.task_04.service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(UsersDto dto) {
        Users user = mapper.dtoToUser(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        if (repository.findByUsername(dto.getUsername()).isPresent()) {
            throw new AlreadyExistException("User already exists with this username");
        }
        repository.save(user);
    }


    public String verify(LoginDto dto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );

        if (!authentication.isAuthenticated()) {
            return "User Not Authenticate";
        } else {
            return jwtService.generateToken(dto.getUsername());
        }
    }
}
