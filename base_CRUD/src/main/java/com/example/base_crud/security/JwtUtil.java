package com.example.base_crud.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtUtil {
    final JWTProperties jwtProperties;

    private Key getKey() {
        String secretKey = jwtProperties.getSecret();
        if (secretKey.isBlank()) {
            throw new IllegalArgumentException("The secret key for JWT is required !!!");
        }
        return Keys.hmacShaKeyFor(StringUtils.rightPad(secretKey, 64, StringUtils.SPACE).getBytes());
    }

    public String generateRefreshToken(UserDetailsCustom userPrincipal) {
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .claim("companyId", userPrincipal.getCompany())
                .claim("companyName", userPrincipal.getCompanyName())
                .claim("userId", userPrincipal.getUserId())
                .setExpiration(calculateExpireDate(jwtProperties.getExpireTimeAccessToken()))
                .signWith(getKey(), SignatureAlgorithm.HS512)
                .compact();

    }

    private Date calculateExpireDate(long expireTime) {
        return new Date(Date.from(Instant.now()).getTime() + expireTime);
    }

    public String getUserIdFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody().getSubject();
    }
}
