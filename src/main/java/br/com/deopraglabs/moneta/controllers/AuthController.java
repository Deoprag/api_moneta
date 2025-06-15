package br.com.deopraglabs.moneta.controllers;

import br.com.deopraglabs.moneta.dtos.request.AccountCredentialsRequest;
import br.com.deopraglabs.moneta.services.AuthService;
import br.com.deopraglabs.moneta.utils.StringUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication Endpoint", description = "Endpoints for authenticating users")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Authenticates a user and returns a token")
    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@Valid @RequestBody AccountCredentialsRequest data) {
        return Objects.requireNonNullElseGet(
                authService.signIn(data),
                () -> ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!")
        );
    }

    @Operation(summary = "Refresh token for authenticated user and returns a token")
    @PutMapping("/refresh/{username}")
    public ResponseEntity<?> refreshToken(@PathVariable("username") String username, @RequestHeader("Authorization") String refreshToken) {
        return Objects.requireNonNullElseGet(
                authService.refreshToken(username, refreshToken),
                () -> ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!")
        );
    }
}
