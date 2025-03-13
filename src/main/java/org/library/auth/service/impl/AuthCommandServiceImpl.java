package org.library.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.library.auth.dto.RefreshTokenDto;
import org.library.auth.dto.LoginSuccessDto;
import org.library.auth.exception.TokenInvalidException;
import org.library.auth.jwt.service.JwtService;
import org.library.auth.service.IAuthCommandService;
import org.library.refreshtoken.entity.RefreshToken;
import org.library.refreshtoken.service.IRefreshTokenCmdService;
import org.library.refreshtoken.service.IRefreshTokenQryService;
import org.library.user.entity.User;
import org.library.user.service.IUserQryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthCommandServiceImpl implements IAuthCommandService {

    private final JwtService jwtService;
    private final IRefreshTokenCmdService refreshTokenCmdService;
    private final IRefreshTokenQryService refreshTokenQryService;
    private final IUserQryService userQryService;

    //Permite generar un nuevo access token a partir de un refresh token
    @Transactional
    @Override
    public LoginSuccessDto refreshToken(RefreshTokenDto refreshTokenDto) {
        User user = validRefreshToken(refreshTokenDto.accessToken());
        return new LoginSuccessDto(
                jwtService.generateToken(user),
                refreshTokenCmdService.generateRefreshToken(user)
        );
    }

    //Valid access token
    private User validRefreshToken(String token){
        RefreshToken refreshToken = refreshTokenQryService.getRefreshTokenByToken(token).orElseThrow(null);
        if(refreshToken == null || !refreshToken.isValid())
            throw new TokenInvalidException("El token proporcionado es invalido");
        return userQryService.getUserById(refreshToken.getUser().getUserId()).orElse(null);
    }

}
