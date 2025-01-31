package com.example.event_management.dtos;

import com.example.event_management.domain.Role;
import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {
    private String email;

    private String password;

    private String fullName;

    private Role role;

    private String contactNumber;
}