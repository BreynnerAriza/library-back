package org.library.auth.security.service;

import lombok.RequiredArgsConstructor;
import org.library.user.entity.User;
import org.library.user.repository.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    //Permite buscar usuario
    private final IUserRepository userRepository;

    //Permite obtener un user details service a partir de su username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("El usuario no fue encontrado")
        );
        return new UserDetailsImpl(user);
    }

}
