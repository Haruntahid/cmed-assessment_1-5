package com.cmed.task_05.service;

import com.cmed.task_05.exception.AlreadyExistException;
import com.cmed.task_05.model.dtos.LoginDto;
import com.cmed.task_05.model.dtos.UserDto;
import com.cmed.task_05.model.entity.User;
import com.cmed.task_05.model.mapper.UserMapper;
import com.cmed.task_05.repository.UserRepository;
import com.cmed.task_05.security.JwtService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public void register(UserDto dto) {
        Optional<User> userExistWithUsernameOrEmail = repository.findByUsernameOrEmail(dto.getUsername(), dto.getEmail());

        if (userExistWithUsernameOrEmail.isPresent()) {
            throw new AlreadyExistException("User with username or email already exists.");
        }

        User user = mapper.dtoToUser(dto);
        user.setPassword(encoder.encode(dto.getPassword()));

        repository.save(user);
    }

    public String verify(LoginDto dto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

        if(!authentication.isAuthenticated()) {
            return "User Not Authenticated";
        }else return jwtService.generateToken(dto.getUsername());
    }


}
