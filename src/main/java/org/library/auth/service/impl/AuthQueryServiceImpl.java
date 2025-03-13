package org.library.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.library.auth.dto.LoginRequestDto;
import org.library.auth.dto.LoginSuccessDto;
import org.library.auth.exception.InvalidCredentialException;
import org.library.auth.exception.UserDisabledException;
import org.library.auth.jwt.service.JwtService;
import org.library.auth.service.IAuthCommandService;
import org.library.auth.service.IAuthQueryService;
import org.library.refreshtoken.service.impl.RefreshTokenCmdService;
import org.library.user.entity.User;
import org.library.user.service.IUserQryService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthQueryServiceImpl implements IAuthQueryService {

    private final IUserQryService userQryService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenCmdService refreshTokenCmdService;

    //MÉTODO PARA PERMITIR EL LOGUEO DE UN USUARIO
    @Transactional
    @Override
    public LoginSuccessDto login(LoginRequestDto loginRequestDto) {
        User user = validateCredentials(loginRequestDto);
        return new LoginSuccessDto(
                jwtService.generateToken(user),
                refreshTokenCmdService.generateRefreshToken(user)
        );
    }

    //METODO QUE PERMITE OBTENER EL USUARIO QUE SE ENCUENTRA LOGUEADO
    @Transactional(readOnly = true)
    @Override
    public User getUserAuthenticate() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userQryService.getUserByUsername(username).get();
        return user;
    }

    //Permite saber si las credenciales proporcionadas del usuario para el login están bien
    private User validateCredentials(LoginRequestDto loginRequestDto){
        User user = searchUser(loginRequestDto);
        if(!passwordEncoder.matches(loginRequestDto.password(), user.getPassword()))
            throw new InvalidCredentialException("Credenciales invalidas");
        if(user.isDisabled())
            throw new UserDisabledException("Usuario deshabilitado");
        return user;
    }

    //Permite obtener un usuario a través de su username
    private User searchUser(LoginRequestDto loginRequestDto){
        Optional<User> user = userQryService.getUserByUsername(loginRequestDto.username());
        if(user.isEmpty())
            throw new InvalidCredentialException("Credenciales invalidas");
        return user.get();
    }

}
