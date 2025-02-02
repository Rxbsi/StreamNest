package de.rxbsi.streamnest.user.resource;

import de.rxbsi.streamnest.user.service.User;
import de.rxbsi.streamnest.user.service.UserEntity;
import de.rxbsi.streamnest.user.service.UserService;
import de.rxbsi.streamnest.user.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/users")
    List<UserEntity> listAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/byId/{id}")
    User getUserById(@PathVariable Long id) {
        return userService.findUser(id);
    }

    @PutMapping("/edit/{id}")
    User updateUser(@RequestBody User newUser,
                    @PathVariable Long id){
        return userService.updateUser(id, newUser);
    }

    @DeleteMapping("/delete/{id}")
    String deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

}
