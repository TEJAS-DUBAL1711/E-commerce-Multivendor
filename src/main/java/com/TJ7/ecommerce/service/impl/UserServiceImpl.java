package com.TJ7.ecommerce.service.impl;

import com.TJ7.ecommerce.config.JwtProvider;
import com.TJ7.ecommerce.model.User;
import com.TJ7.ecommerce.repository.UserRepository;
import com.TJ7.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {

        String email = jwtProvider.getEmailFromJwtToken(jwt);


        return this.findUserByEmail(email);
    }


    @Override
    public User findUserByEmail(String email) throws Exception {

        User user = userRepository.findByEmail(email);

        if (user == null) {

            throw new Exception("User not found with email - " + email);

        }
        return user;
    }


}
