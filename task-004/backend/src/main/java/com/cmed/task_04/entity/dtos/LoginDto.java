package com.cmed.task_04.entity.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    @NotBlank(message = "Username Require")
    private String username;
    @NotBlank(message = "Username Require")
    private String password;
}
