package org.library;

import lombok.RequiredArgsConstructor;
import org.library.role.repository.IRoleRepository;
import org.library.user.entity.User;
import org.library.user.repository.IUserRepository;
import org.library.user.userstatus.UserStatus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@SpringBootApplication
@RequiredArgsConstructor
public class LibraryBackApplication {

    private final PasswordEncoder passwordEncoder;
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(LibraryBackApplication.class, args);
    }

    /*
    @Override
    public void run(String... args) throws Exception {
       userRepository.save(
               new User("MoeruTsuky", passwordEncoder.encode("123456"), UserStatus.ACTIVE, roleRepository.findById(UUID.fromString("83c4d67c-82ba-4e13-abea-19516ede8edb")).get())
       );
    }
    */

}
