package com.TJ7.ecommerce.controller;

import com.TJ7.ecommerce.multivendor.domain.USER_ROLE;
import com.TJ7.ecommerce.repository.UserRepository;
import com.TJ7.ecommerce.request.LoginOtpRequest;
import com.TJ7.ecommerce.request.LoginRequest;
import com.TJ7.ecommerce.request.SignupRequest;
import com.TJ7.ecommerce.response.ApiResponse;
import com.TJ7.ecommerce.response.AuthResponse;
import com.TJ7.ecommerce.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final AuthService authService;

    /**
     * The @RequiredArgsConstructor annotation in Lombok is used to automatically generate
     * a constructor that initializes all the final fields (those marked with final) and fields
     * with @NonNull annotations in a class. This is particularly useful in classes where you have dependency injections
     * or values that should be set only once, typically in service classes or other classes
     * where some properties are required at object creation.
     * How @RequiredArgsConstructor Works
     * Purpose: It generates a constructor with parameters for each field that’s either final or annotated with @NonNull.
     * Generated Constructor: Lombok will include only those fields in the constructor and skip non-final fields or fields without the @NonNull annotation.
     * Usage: Often used in classes where dependency injection is needed, such as in Spring, to ensure that all necessary dependencies are provided at construction.
     */

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest req) throws Exception {

        String jwt = authService.createUser(req);

        AuthResponse response = new AuthResponse();
        response.setJwt(jwt);
        response.setMessage("Registered successfully");
        response.setRole(USER_ROLE.ROLE_CUSTOMER);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/send/login-signup-otp")
    public ResponseEntity<ApiResponse> sendOtpHandler(@RequestBody LoginOtpRequest req) throws Exception {

        authService.sendLoginOtp(req.getEmail(), req.getRole());

        ApiResponse response = new ApiResponse();

        response.setMessage("otp sent successfully");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest req) throws Exception {

        AuthResponse authResponse = authService.signin(req);


        return ResponseEntity.ok(authResponse);
    }


}
