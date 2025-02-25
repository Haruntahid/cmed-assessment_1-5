package com.health.cmed_task_01.repository;

import com.health.cmed_task_01.entity.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String username);
    Users findByEmail(String email);
}
