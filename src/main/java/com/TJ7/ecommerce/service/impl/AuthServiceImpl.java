package com.TJ7.ecommerce.service.impl;

import com.TJ7.ecommerce.config.JwtProvider;
import com.TJ7.ecommerce.model.Cart;
import com.TJ7.ecommerce.model.Seller;
import com.TJ7.ecommerce.model.User;
import com.TJ7.ecommerce.model.VerificationCode;
import com.TJ7.ecommerce.multivendor.domain.USER_ROLE;
import com.TJ7.ecommerce.repository.CartRepository;
import com.TJ7.ecommerce.repository.SellerRepository;
import com.TJ7.ecommerce.repository.UserRepository;
import com.TJ7.ecommerce.repository.VerificationCodeRepository;
import com.TJ7.ecommerce.request.LoginRequest;
import com.TJ7.ecommerce.request.SignupRequest;
import com.TJ7.ecommerce.response.AuthResponse;
import com.TJ7.ecommerce.service.AuthService;
import com.TJ7.ecommerce.service.EmailService;
import com.TJ7.ecommerce.utils.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final JwtProvider jwtProvider;
    private final VerificationCodeRepository verificationCodeRepository;
    private final EmailService emailService;
    private final SellerRepository sellerRepository;

    private final CustomeUserServiceImpl customeUserServiceImpl;

    @Override
    public void sendLoginOtp(String email, USER_ROLE role) throws Exception {
        String SIGNING_PREFIX = "signing_";


        if (email.startsWith(SIGNING_PREFIX)) {
            email = email.substring(SIGNING_PREFIX.length());

            if (role == USER_ROLE.ROLE_SELLER) {

                Seller seller = sellerRepository.findByEmail(email);
                if (seller == null) {
                    throw new Exception("Seller not exists with provided email - " + email);
                }

            } else {
                System.out.println("email - " + email);
                User user = userRepository.findByEmail(email);
                if (user == null) {
                    throw new Exception("User not exists with provided email - " + email);
                }

            }


        }

        VerificationCode isExist = verificationCodeRepository.findByEmail(email);

        if (isExist != null) {
            verificationCodeRepository.delete(isExist);

        }
        String otp = OtpUtil.generateOtp();

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setEmail(email);
        verificationCode.setOtp(otp);

        verificationCodeRepository.save(verificationCode);
        String subject = " TJ7 bazaarr Login/signup OTP";
        String text = "Your login/signup OTP is - " + otp;

        emailService.sendVerificationOtpEmail(email, otp, subject, text);
    }

    @Override
    public String createUser(SignupRequest req) throws Exception {

        VerificationCode verificationCode = verificationCodeRepository.findByEmail(req.getEmail());
        // System.out.println(verificationCode.getOtp());

        if (verificationCode == null || !verificationCode.getOtp().equals(req.getOtp())) {
            throw new Exception("Wrong OTP...");
        }

        User user = userRepository.findByEmail(req.getEmail());


        if (user == null) {

            User createdUser = new User();
            createdUser.setEmail(req.getEmail());
            createdUser.setFullName(req.getFullName());
            createdUser.setRole(USER_ROLE.ROLE_CUSTOMER);
            createdUser.setMobile("7350966507");
            createdUser.setPassword(passwordEncoder.encode(req.getOtp()));

            user = userRepository.save(createdUser);

            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(USER_ROLE.ROLE_CUSTOMER.toString()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(req.getEmail(), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtProvider.generateToken(authentication);
    }

    @Override
    public AuthResponse signin(LoginRequest req) throws Exception {

        String username = req.getEmail();
        String otp = req.getOtp();

        Authentication authentication = authenticate(username, otp);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Logged in successfully");
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String roleName = authorities.iterator().next().getAuthority();

        authResponse.setRole(USER_ROLE.valueOf(roleName));

        return authResponse;
    }

    private Authentication authenticate(String username, String otp) throws Exception {

        UserDetails userDetails = customeUserServiceImpl.loadUserByUsername(username);

        String SELLER_PREFIX = "seller_";
        if (username.startsWith(SELLER_PREFIX)) {
            username = username.substring(SELLER_PREFIX.length());
        }

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password.");
        }

        VerificationCode verificationCode = verificationCodeRepository.findByEmail(username);
        if (verificationCode == null || !verificationCode.getOtp().equals(otp)) {
            throw new Exception("wrong otp");
        }


        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

    }
}
