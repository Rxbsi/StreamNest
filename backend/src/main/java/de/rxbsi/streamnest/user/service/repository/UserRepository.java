package de.rxbsi.streamnest.user.service.repository;

import de.rxbsi.streamnest.user.service.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserDetails> findByUsername(String username);

    boolean existsByUsername(String username);


}
