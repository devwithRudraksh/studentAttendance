package com.rudraksh.attendance.controller;

import com.rudraksh.attendance.dto.RegisterRequest;
import com.rudraksh.attendance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rudraksh.attendance.dto.LoginResponse;
import com.rudraksh.attendance.dto.LoginRequest;
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService; // Now injected by interface!

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        userService.registerUser(request);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        System.out.println("LOGIN HIT: " + request.getUsername());
        return userService.login(request);
    }
}

