package org.example.expresscash.resources;

import lombok.RequiredArgsConstructor;
import org.example.expresscash.model.AuthRequest;
import org.example.expresscash.model.RegisterRequest;
import org.example.expresscash.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthResource {

    private final AuthService authService;



    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody RegisterRequest signUpRequest) {
        authService.register(signUpRequest);
    }
}
