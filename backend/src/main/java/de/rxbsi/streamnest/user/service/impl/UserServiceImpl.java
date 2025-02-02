package de.rxbsi.streamnest.user.service.impl;

import de.rxbsi.streamnest.user.mapping.UserMapper;
import de.rxbsi.streamnest.user.resource.exception.UserNotFoundException;
import de.rxbsi.streamnest.user.service.User;
import de.rxbsi.streamnest.user.service.UserEntity;
import de.rxbsi.streamnest.user.service.UserService;
import de.rxbsi.streamnest.user.service.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public User createUser(User user) {
        var userEntity = userMapper.toEntity(user);
        var savedEntity = repository.save(userEntity);
        return userMapper.toDto(savedEntity);
    }

    @Override
    public User findUser(Long id) {

        return userMapper.toDto(repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id)));
    }

    @Override
    public User updateUser(Long id, User newUser) {

        return repository.findById(id)
                .map(user -> {
                    updateNonNullFields(newUser, user);
                    repository.save(user);
                    return userMapper.toDto(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public String deleteUser(Long id) {

        if (!repository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        repository.deleteById(id);

        return "User with id: " + id + " has been deleted successfully!";
    }

    private void updateNonNullFields(User source, UserEntity target) {
        if (source.getUsername() != null) {
            target.setUsername(source.getUsername());
        }
        if (source.getName() != null) {
            target.setName(source.getName());
        }
        if (source.getLastName() != null) {
            target.setLastName(source.getLastName());
        }
        if (source.getEmail() != null) {
            target.setEmail(source.getEmail());
        }
        if (source.getAdmin() != null) {
            target.setAdmin(source.getAdmin());
        }
    }
}