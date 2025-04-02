package de.rxbsi.streamnest.auth;

import de.rxbsi.streamnest.auth.dto.AuthResponse;
import de.rxbsi.streamnest.auth.dto.LoginRequest;
import de.rxbsi.streamnest.auth.dto.PasswordResetRequest;
import de.rxbsi.streamnest.auth.dto.RegisterRequest;
import de.rxbsi.streamnest.auth.jwt.JwtService;
import de.rxbsi.streamnest.user.service.UserEntity;
import de.rxbsi.streamnest.user.service.impl.UserDetailsService;
import de.rxbsi.streamnest.user.service.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:3000")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        UserEntity user = (UserEntity) userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtService.generateToken(user);

        AuthResponse response = AuthResponse.builder()
                .token(token)
                .username(user.getUsername())
                .isAdmin(user.isAdmin())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/set-password")
    public ResponseEntity<?> setPassword(@RequestBody PasswordResetRequest request) {
        String token = request.getToken();
        String newPassword = request.getPassword();

        UserEntity user = userRepository.findByUserToken(token).orElseThrow();

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setUserToken(null);
        userRepository.save(user);

        return ResponseEntity.ok(Map.of("message", "Passwort erfolgreich geändert!"));
    }

}
