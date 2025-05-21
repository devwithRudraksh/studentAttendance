package com.rudraksh.attendance.service;

import com.rudraksh.attendance.dto.RegisterRequest;
import com.rudraksh.attendance.entity.User;
import com.rudraksh.attendance.dto.LoginRequest;
import com.rudraksh.attendance.dto.LoginResponse;


public interface UserService {
   User registerUser(RegisterRequest request);
   LoginResponse login(LoginRequest request);
    // Add other methods like login, getUserByUsername, etc., as needed
}
