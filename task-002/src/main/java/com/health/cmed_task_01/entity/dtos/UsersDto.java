package com.health.cmed_task_01.entity.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UsersDto {
    @NotBlank(message = "Username Must be Required")
    private String username;

    @Email(message = "Provide a valid Email address")
    private String email;

    @NotBlank(message = "Password Must be Required")
    @Size(min = 6, message = "Password must be at least 6 characters long.")
    private String password;

}
