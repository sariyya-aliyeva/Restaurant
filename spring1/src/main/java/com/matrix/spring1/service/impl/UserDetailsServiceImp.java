package com.matrix.spring1.service.impl;

import com.matrix.spring1.dto.LoginResponse;
import com.matrix.spring1.entity.ConfirmationToken;
import com.matrix.spring1.entity.user.User;
import com.matrix.spring1.jwt.JwtService;
import com.matrix.spring1.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class UserDetailsServiceImp implements UserDetailsService {
    private final static String USER_NOT_FOUND_MESSAGE = "User with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;
    private final ConfirmationTokenService confirmationTokenService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username {}", username);
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Email not found"));
    }

    public LoginResponse loadUserByUsernameAndPassword(String email, String password) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, email)));
        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            var token = jwtService.createJwt(user);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setUsername(user.getUsername());
            loginResponse.setFirstName(user.getFirstName());
            loginResponse.setLastName(user.getLastName());
            loginResponse.setToken(token);

            return loginResponse;
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public String signUpUser(User user) {
        boolean userExists = userRepository.findByUsername(user.getUsername()).isPresent();

        if (userExists) {
            throw new IllegalStateException("Email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user);

        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }



    // sonradan elave
//    public Map<String, Object> getUserClaims() {
//        Authentication authentication = SecurityContextHolder.getContext()
//                .getAuthentication();
//        if (authentication.getPrincipal() instanceof OidcUser) {
//            OidcUser principal = ((OidcUser) authentication.getPrincipal());
//            return principal.getClaims();
//        }
//        return Collections.emptyMap();
//    }
    //
}
