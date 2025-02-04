package de.rxbsi.streamnest.auth.dto;

import lombok.Data;

@Data
public class PasswordResetRequest {
    private String token;
    private String password;
}
