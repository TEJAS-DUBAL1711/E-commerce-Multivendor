package com.TJ7.ecommerce.service;

import com.TJ7.ecommerce.model.User;


public interface UserService {

    User findUserByJwtToken(String jwt) throws Exception;

    User findUserByEmail(String email) throws Exception;


}


