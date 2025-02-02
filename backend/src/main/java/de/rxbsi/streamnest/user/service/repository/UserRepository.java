package de.rxbsi.streamnest.user.service.repository;

import de.rxbsi.streamnest.user.service.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
