package com.example.base_crud.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JWTProperties {
    private String secret;
    private String type;
    private String header;
    private String prefix;
    private long expireTimeAccessToken;
    private long expireTimeRefreshToken;
}
