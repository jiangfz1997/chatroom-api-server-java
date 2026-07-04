package com.chatroom.controller;

import com.chatroom.dto.LoginRequest;
import com.chatroom.dto.RegisterRequest;
import com.chatroom.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Registers a new user.
     * Returns 409 if the username is already taken.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest req) {
        userService.register(req);
        return ResponseEntity.ok(Map.of("message", "sign up successfully"));
    }

    /**
     * Authenticates a user and returns a JWT token.
     * Returns 401 if credentials are invalid.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req) {
        String token = userService.login(req);
        return ResponseEntity.ok(Map.of(
                "message", "login success",
                "username", req.getUsername(),
                "token", token
        ));
    }
}
