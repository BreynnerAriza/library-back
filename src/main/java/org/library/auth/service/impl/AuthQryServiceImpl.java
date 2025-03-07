package org.library.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.library.auth.dto.LoginRequest;
import org.library.auth.dto.LoginSuccess;
import org.library.auth.exception.InvalidCredentialException;
import org.library.auth.exception.UserDisabledException;
import org.library.auth.jwt.service.JwtService;
import org.library.auth.service.IAuthQryService;
import org.library.user.entity.User;
import org.library.user.repository.IUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthQryServiceImpl implements IAuthQryService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    //MÉTODO PARA PERMITIR EL LOGUEO DE UN USUARIO
    @Override
    public LoginSuccess login(LoginRequest loginRequest) {
        User user = validateCredentials(loginRequest);
        return generateLoginSuccess(user);
    }

    //Permite saber si las credenciales proporcionadas del usuario para el login están bien
    private User validateCredentials(LoginRequest loginRequest){
        User user = searchUser(loginRequest);
        if(!passwordEncoder.matches(loginRequest.password(), user.getPassword()))
            throw new InvalidCredentialException("Credenciales invalidas");
        if(user.isDisabled())
            throw new UserDisabledException("Usuario deshabilitado");
        return user;
    }

    //Permite obtener un usuario a través de su username
    private User searchUser(LoginRequest loginRequest){
        Optional<User> user = userRepository.findByUsername(loginRequest.username().trim().toLowerCase());
        if(user.isEmpty())
            throw new InvalidCredentialException("Credenciales invalidas");
        return user.get();
    }

    //Genera respuesta de un login exitoso
    public LoginSuccess generateLoginSuccess(User user){
        return new LoginSuccess(
                jwtService.generateToken(user)
        );
    }



}
