package com.cmed.assesment_03.entity.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    @NotBlank(message = "Username Require")
    private String username;

    @NotBlank(message = "Password Require")
    private String password;
}
