package de.rxbsi.streamnest.user.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class User {
    private Long id;
    private String username;
    private String name;
    private String lastName;
    private String email;
    private Boolean admin;

}
