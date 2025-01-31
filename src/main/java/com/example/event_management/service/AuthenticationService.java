package com.example.event_management.service;

import com.example.event_management.domain.Role;
import com.example.event_management.dtos.LoginUserDto;
import com.example.event_management.dtos.OtpDto;
import com.example.event_management.dtos.RegisterUserDto;
import com.example.event_management.entity.UnverifiedUser;
import com.example.event_management.entity.User;
import com.example.event_management.repository.UnverifiedUserRepository;
import com.example.event_management.repository.UserRepository;
import com.example.event_management.util.OtpUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.event_management.domain.Role.ORGANIZER;
import static com.example.event_management.domain.Role.USER;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final UnverifiedUserRepository unverifiedUserRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final EmailService emailService;

    public void sendOtp(RegisterUserDto input) throws MessagingException {

        UnverifiedUser existingUser = unverifiedUserRepository.findByEmail(input.getEmail());
        if(existingUser!=null){
            unverifiedUserRepository.delete(existingUser);
        }
        String otp = OtpUtil.generateOtp();
        String subject = "Otp for signup verification";
        String text= "Otp for verification of your account is - " + otp;
        emailService.sendVerificationOtpEmail(input.getEmail(), otp, subject, text);
        UnverifiedUser user = new UnverifiedUser();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setOtp(otp);
        user.setRole(input.getRole());
        unverifiedUserRepository.save(user);
    }

    public User signup(OtpDto input) throws Exception {

        UnverifiedUser unverifiedUser = unverifiedUserRepository.findByEmail(input.getEmail());
        if(unverifiedUser==null){
            throw new Exception("Invalid Email");
        }
        else if(!unverifiedUser.getOtp().equals(input.getOtp())){
                throw new Exception("Invalid Otp");
        }

        User user = new User();
        user.setPassword(unverifiedUser.getPassword());
        user.setEmail(unverifiedUser.getEmail());
        user.setFullName(unverifiedUser.getFullName());
        user.setContactNumber(unverifiedUser.getContactNumber());
        if (unverifiedUser.getRole()==ORGANIZER){
            user.getRoles().add(USER);
        }
        user.getRoles().add(unverifiedUser.getRole());
        unverifiedUserRepository.delete(unverifiedUser);
        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}