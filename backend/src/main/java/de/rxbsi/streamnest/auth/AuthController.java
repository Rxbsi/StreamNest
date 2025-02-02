package de.rxbsi.streamnest.auth;

import de.rxbsi.streamnest.auth.dto.AuthResponse;
import de.rxbsi.streamnest.auth.dto.LoginRequest;
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

    /**
     *  WIRD NICHT IM FRONTEND VERWENDET. IST ZUR SICHERHEIT FALLS ALLE ADMIN USER GELÖSCHT WERDEN,
     *  DAMIT MAN ÜBER POSTMAN EINEN ADMIN USER GENERIEREN KANN
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body(new AuthResponse("Username already exists"));
        }

        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setUsername(request.getUsername());
        newUserEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        newUserEntity.setEmail(request.getEmail());
        newUserEntity.setName(request.getName());
        newUserEntity.setLastName(request.getLastName());
        newUserEntity.setAdmin(request.getAdmin());

        userRepository.save(newUserEntity);

        String token = jwtService.generateToken(newUserEntity);

        AuthResponse response = AuthResponse.builder()
                .token(token)
                .username(newUserEntity.getUsername())
                .isAdmin(newUserEntity.isAdmin())
                .build();

        return ResponseEntity.ok(response);
    }

}
