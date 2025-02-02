package de.rxbsi.streamnest.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String username;
    private boolean isAdmin;
    private String message;

    public AuthResponse(String message) {
        this.message = message;
    }
}
