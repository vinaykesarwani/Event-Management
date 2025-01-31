package com.example.event_management.controller;

import com.example.event_management.dtos.LoginUserDto;
import com.example.event_management.dtos.OtpDto;
import com.example.event_management.dtos.RegisterUserDto;
import com.example.event_management.entity.User;
import com.example.event_management.repository.UnverifiedUserRepository;
import com.example.event_management.response.LoginResponse;
import com.example.event_management.service.AuthenticationService;
import com.example.event_management.service.JwtService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtService jwtService;
//    private final OtpDto OtpDto;
//    private final LoginUserDto LoginUserDto;
//    private final RegisterUserDto RegisterUserDto;
    private final AuthenticationService authenticationService;
    private final UnverifiedUserRepository unverifiedUserRepository;

    @PostMapping("/signup/verify")
    public ResponseEntity<User> register(@RequestBody OtpDto request) throws Exception {

        User registeredUser = authenticationService.signup(request);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse response = new LoginResponse();
        response.setToken(jwtToken);
        response.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(response);
    }
    @PostMapping("/signup")
    public ResponseEntity<String> sendOtp(@RequestBody RegisterUserDto request) throws MessagingException {
        authenticationService.sendOtp(request);
        return ResponseEntity.ok("Otp sent successfully");
    }
}