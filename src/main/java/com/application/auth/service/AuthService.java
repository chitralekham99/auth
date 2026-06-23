package com.application.auth.service;

import com.application.auth.dto.AuthResponse;
import com.application.auth.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest registerRequest);
}
