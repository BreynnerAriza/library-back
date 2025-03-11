package org.library.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.library.user.entity.User;
import org.library.user.repository.IUserRepository;
import org.library.user.service.IUserQryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserQryService implements IUserQryService {

    private final IUserRepository userRepository;

    //Permite obtener un usuario a partir de su username
    @Transactional(readOnly = true)
    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username.trim().toLowerCase());
    }

    @Override
    public Optional<User> getUserById(UUID userId) {
        return userRepository.findById(userId);
    }

}
