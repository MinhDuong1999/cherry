package com.example.base_crud.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsCustom extends UserDetails {
    String getCompany();

    String getCompanyName();

    String getUserId();
}
