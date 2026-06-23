package com.application.auth.controller;

import com.application.auth.dto.AuthResponse;
import com.application.auth.dto.RegisterRequest;
import com.application.auth.dto.UserDto;
import com.application.auth.service.AuthService;
import com.application.auth.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

   private final AuthService authService;

   @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterRequest registerRequest){
        AuthResponse authResponse= authService.register(registerRequest);
        return ResponseEntity.ok(authResponse);

    }

    @PostMapping("/login")
    public ResponseEntity<String>login(@RequestBody UserDto userDto) {

       // String respons=userService.login(userDto);
        String token = userService.login(userDto);
        System.out.println("i am checking");
        return ResponseEntity.ok(token);
      //  return ResponseEntity.ok(respons);
    }

    @GetMapping("/profile")
    public String profile() {
        return "Welcome";
    }


}
