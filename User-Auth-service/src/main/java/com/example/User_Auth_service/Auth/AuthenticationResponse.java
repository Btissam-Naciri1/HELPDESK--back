package com.example.User_Auth_service.Auth;

import com.example.User_Auth_service.DTO.UserDto;
import com.example.User_Auth_service.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthenticationResponse {
    private String jwt;
    private UserDto user;
    private Role role;
    private List<String> groups; // Only for Admin N2

    public AuthenticationResponse(String jwt, UserDto user, Role role, List<String> groups) {
        this.jwt = jwt;
        this.user = user;
        this.role = role;
        this.groups = groups;
    }
}
