package com.tie_international.multiuserapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class RequestController {

    @GetMapping("getHello")
    public String getHello() {
        return "Hello!";
    }

    @GetMapping("getHello/{username}")
    public String getHelloByUsername(@PathVariable("username")String username) {
        return "Hello " + username + "!";
    }
}