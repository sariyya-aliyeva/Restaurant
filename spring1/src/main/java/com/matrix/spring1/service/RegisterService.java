package com.matrix.spring1.service;

import com.matrix.spring1.dto.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public interface RegisterService {
    String register(RegistrationRequest registrationRequest);
}
