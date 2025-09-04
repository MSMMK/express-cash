package org.example.expresscash.services;

import org.example.expresscash.entity.User;
import org.example.expresscash.model.AuthRequest;
import org.example.expresscash.model.AuthResponse;
import org.example.expresscash.model.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<String> register(RegisterRequest request);
    ResponseEntity<AuthResponse> login(AuthRequest request);
    User authUser();
}
