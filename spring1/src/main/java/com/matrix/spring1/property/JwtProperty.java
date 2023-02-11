package com.matrix.spring1.property;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtProperty {
    String header;
    String prefix;
    int expiration;
    String secretKey;
}
