package com.health.cmed_task_01.services;

import com.health.cmed_task_01.entity.model.Users;
import com.health.cmed_task_01.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = userRepo.findByUsername(username);

        if(user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }else return new UserPrincipal(user.get());
    }
}
