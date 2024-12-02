package com.TJ7.ecommerce.service;

import com.TJ7.ecommerce.multivendor.domain.USER_ROLE;
import com.TJ7.ecommerce.request.LoginRequest;
import com.TJ7.ecommerce.request.SignupRequest;
import com.TJ7.ecommerce.response.AuthResponse;

public interface AuthService {

    void sendLoginOtp(String email, USER_ROLE role) throws Exception;

    String createUser(SignupRequest req) throws Exception;

    AuthResponse signin(LoginRequest req) throws Exception;

}
