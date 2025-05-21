package com.rudraksh.attendance.dto;

import com.rudraksh.attendance.entity.Role;
import lombok.Data;



@Data
public class RegisterRequest {
    private String username;
    private String password;
    private Role role; // can default to STUDENT if you want
}

