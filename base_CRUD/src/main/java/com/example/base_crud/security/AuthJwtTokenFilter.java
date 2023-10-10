package com.example.base_crud.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class AuthJwtTokenFilter extends OncePerRequestFilter {

    protected static final Integer TOKEN_AT_POSITION = 7;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }

    protected String parseJwt(HttpServletRequest request) {
        String headAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headAuth) && headAuth.startsWith("Bear ")) {
            return headAuth.substring(TOKEN_AT_POSITION);
        }
        return null;
    }
}
