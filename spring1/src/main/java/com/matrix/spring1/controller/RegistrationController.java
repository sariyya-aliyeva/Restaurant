package com.matrix.spring1.controller;

import com.matrix.spring1.dto.RegistrationRequest;
import com.matrix.spring1.service.impl.RegistrationServiceImp;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "registration")
@RequiredArgsConstructor
@Api(description = "Registration info")
public class RegistrationController {
    private final RegistrationServiceImp registrationServiceImp;

    @PostMapping(value = "/register")
    public String register(@RequestBody RegistrationRequest request) {
        return registrationServiceImp.register(request);
    }

    @GetMapping(value = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationServiceImp.confirmToken(token);
    }
}
