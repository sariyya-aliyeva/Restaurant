package com.matrix.spring1.jwt;

import com.matrix.spring1.entity.user.User;
import com.matrix.spring1.entity.user.UserGrantedAuthority;
import com.matrix.spring1.property.JwtProperty;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtServiceImp implements JwtService {
    private final JwtProperty jwtProperty;

    @Override
    public String createJwt(User user) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(String.valueOf(user.getId()))
                .setSubject(user.getFirstName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperty.getExpiration() * 5000L))  // add 5 minutes
                .addClaims(Map.of("roles", user.getAuthorities()
                        .stream()
                        .map(UserGrantedAuthority::getAuthority).collect(Collectors.toList())))
                .setHeader(Map.of("type", "JWT"))
                .signWith(SignatureAlgorithm.HS512, jwtProperty.getSecretKey().getBytes(StandardCharsets.UTF_8));
        return jwtBuilder.compact();
    }

    @Override
    public Claims verifyJwt(String token) {
        var claims = Jwts.parser()
                .setSigningKey(jwtProperty.getSecretKey().getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();

        if (claims.getExpiration().before(new Date())) {
            throw new RuntimeException("Token expired");
        }
        return claims;
    }
}
