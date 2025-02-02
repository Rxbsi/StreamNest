package de.rxbsi.streamnest.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String name;
    private String lastName;
    private Boolean admin;
}

