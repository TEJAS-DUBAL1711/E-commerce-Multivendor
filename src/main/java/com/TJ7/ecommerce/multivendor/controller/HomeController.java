package com.TJ7.ecommerce.multivendor.controller;


import com.TJ7.ecommerce.multivendor.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @GetMapping("home")
    public ApiResponse HomeControllerHandler() {

        ApiResponse response = new ApiResponse();
        response.setMessage("Welcome to Ecommerce Multivendor");
        return  response;
    }

}
