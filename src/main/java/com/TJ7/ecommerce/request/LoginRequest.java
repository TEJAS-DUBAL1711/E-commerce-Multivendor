package com.TJ7.ecommerce.request;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String otp;
}
