package com.matrix.spring1.jwt;


import com.matrix.spring1.entity.user.User;
import io.jsonwebtoken.Claims;

public interface JwtService {
    String createJwt(User user);

    Claims verifyJwt(String token);
}
