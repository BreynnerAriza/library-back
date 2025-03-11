package org.library.user.service;

import org.library.user.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface IUserQryService {

    Optional<User> getUserByUsername(String username);
    Optional<User> getUserById(UUID userId);

}
