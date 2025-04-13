package com.ctse.userManagement.Controller;

import com.ctse.userManagement.dto.AuthenticationRequest;
import com.ctse.userManagement.dto.AuthenticationResponse;
import com.ctse.userManagement.dto.PasswordResetRequestDto;
import com.ctse.userManagement.dto.PasswordResetVerifyDto;
import com.ctse.userManagement.dto.RegisterRequest;
import com.ctse.userManagement.dto.UpdatePasswordRequest;
import com.ctse.userManagement.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/update-password")
    public ResponseEntity<Void> updatePassword(
            @RequestBody UpdatePasswordRequest request
    ) {
        authenticationService.updatePassword(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-password-request")
    public ResponseEntity<Void> resetPasswordRequest(
            @RequestBody PasswordResetRequestDto request
    ) {
        authenticationService.initiatePasswordReset(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-password-verify")
    public ResponseEntity<Void> resetPasswordVerify(
            @RequestBody PasswordResetVerifyDto request
    ) {
        authenticationService.completePasswordReset(request);
        return ResponseEntity.ok().build();
    }
} 