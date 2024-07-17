package com.api.url_shortener.controller;

import com.api.url_shortener.controller.dto.LoginDTO;
import com.api.url_shortener.controller.dto.RegisterDTO;
import com.api.url_shortener.controller.dto.TokenDTO;
import com.api.url_shortener.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class TokenController {

    @Autowired
    private UserService userService;

    @Operation(
            summary = "Login",
            description = "Endpoint that performs a user login and returns a JWT",
            tags = "Auth"
    )
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(loginDTO));
    }

    @Operation(
            summary = "Register",
            description = "Endpoint that register a new user",
            tags = "Auth"
    )
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDTO registerDTO) {
        userService.register(registerDTO);
        return ResponseEntity.status(HttpStatus.OK).body("User registered successfully");
    }

}
