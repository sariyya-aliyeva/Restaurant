package com.matrix.spring1.controller;

import com.matrix.spring1.dto.LoginResponse;
import com.matrix.spring1.dto.UserDto;
import com.matrix.spring1.service.impl.UserDetailsServiceImp;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "registration")
@RequiredArgsConstructor
@Api(description = "Login info")
public class LoginController {
    private final UserDetailsServiceImp userDetailsService;

    @PostMapping(path = "/login")
    public LoginResponse login(@RequestBody UserDto user) {
        return userDetailsService.loadUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
