package com.ayush.SkillTracker.controller;

import com.ayush.SkillTracker.dto.LoginRequestDTO;
import com.ayush.SkillTracker.dto.LoginResponseDTO;
import com.ayush.SkillTracker.dto.RegisterRequestDTO;
import com.ayush.SkillTracker.dto.RegisterResponseDTO;
import com.ayush.SkillTracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginRequestDTO
                                                      loginRequestDTO){

        LoginResponseDTO loginResponse = userService.loginUser(loginRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> registerUser(@RequestBody RegisterRequestDTO
                                                            registerRequestDTO){

        RegisterResponseDTO response = userService.registerUser(registerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
