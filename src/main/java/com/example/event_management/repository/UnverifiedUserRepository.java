package com.example.event_management.repository;

import com.example.event_management.entity.UnverifiedUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnverifiedUserRepository extends JpaRepository<UnverifiedUser, Integer> {
    UnverifiedUser findByEmail(String email);
}
