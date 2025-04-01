package de.rxbsi.streamnest.auth;

import de.rxbsi.streamnest.user.service.UserEntity;
import de.rxbsi.streamnest.user.service.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AdminInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initAdmin() {
        if (userRepository.findByUsername("admin").isEmpty()) {
            UserEntity admin = new UserEntity();
            admin.setUsername("admin");
            admin.setEmail("admin@admin.net");

            var hashedPassword = passwordEncoder.encode("admin");
            admin.setAdmin(true);
            admin.setPassword(hashedPassword);
            admin.setUserToken(UUID.randomUUID().toString());

            userRepository.save(admin);
            System.out.println("Admin-User erfolgreich erstellt!");
        }
    }

}
