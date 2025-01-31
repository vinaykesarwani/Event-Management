package com.example.event_management.entity;

import com.example.event_management.domain.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class UnverifiedUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String email;

    private String password;

    private String fullName;

    private String otp;

    private Role role;

    private String contactNumber;
}
