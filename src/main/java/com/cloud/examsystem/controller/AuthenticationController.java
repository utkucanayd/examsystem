package com.cloud.examsystem.controller;

import com.cloud.examsystem.dto.UserDTO;
import com.cloud.examsystem.service.AuthenticationService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login/user")
    @SneakyThrows
    public ResponseEntity<?> login(UserDTO userDto){
        return ResponseEntity.ok(authenticationService.login(userDto));
    }

    @PostMapping ("/session")
    @SneakyThrows
    public ResponseEntity<?> session(String username){
        return ResponseEntity.ok(authenticationService.session(username));
    }

    @PostMapping("/logout")
    @SneakyThrows
    public ResponseEntity<?> logout(String username){
        return ResponseEntity.ok(authenticationService.logout(username));
    }
}
