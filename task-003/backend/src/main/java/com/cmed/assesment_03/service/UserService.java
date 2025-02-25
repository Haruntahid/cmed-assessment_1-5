package com.cmed.assesment_03.service;


import com.cmed.assesment_03.entity.model.UserPrincipal;
import com.cmed.assesment_03.entity.model.Users;
import com.cmed.assesment_03.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional <Users> user = repository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Username not found!");
        } else return new UserPrincipal(user.get());
    }
}
