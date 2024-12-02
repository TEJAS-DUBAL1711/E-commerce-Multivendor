package com.TJ7.ecommerce.response;

import com.TJ7.ecommerce.multivendor.domain.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private USER_ROLE role;
}
