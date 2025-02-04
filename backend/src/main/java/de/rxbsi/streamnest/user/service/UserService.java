package de.rxbsi.streamnest.user.service;

import java.util.List;

public interface UserService {

    /**
     * Creates a User
     *
     * @param user The created User
     * @return     The saved User
     */
    User createUser(User user);

    /**
     * Finds User through theyre id
     *
     * @param id The User ID
     * @return   The User
     */
    User findUser(Long id);

    /**
     * Return all users with a password (All Real users)
     *
     * @return the above
     */
    List<User> findAllUsers();

    /**
     * Updates a User that already exists
     * @param id        The User ID
     * @param newUser   The new User RequestBody
     * @return          The changed User
     */
    User updateUser(Long id, User newUser);

    /**
     * Handles user deletion
     *
     * @param id    The user id
     * @return      Deletion text
     */
    String deleteUser(Long id);

}
