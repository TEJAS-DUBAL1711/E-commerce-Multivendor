package com.TJ7.ecommerce.request;

import com.TJ7.ecommerce.multivendor.domain.USER_ROLE;
import lombok.Data;

@Data
public class LoginOtpRequest {
    private String email;
    private String otp;
    private USER_ROLE role;

}
