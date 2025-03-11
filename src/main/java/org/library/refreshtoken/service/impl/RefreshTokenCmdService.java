package org.library.refreshtoken.service.impl;

import lombok.RequiredArgsConstructor;
import org.library.refreshtoken.entity.RefreshToken;
import org.library.refreshtoken.repository.IRefreshTokenRepository;
import org.library.refreshtoken.service.IRefreshTokenCmdService;
import org.library.user.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenCmdService implements IRefreshTokenCmdService {

    @Value("${refresh-token.duration-min}")
    private Long refreshTokenLife;

    private final IRefreshTokenRepository refreshTokenRepository;

    //Permite generar un nuevo refresh token
    @Transactional
    @Override
    public String generateRefreshToken(User user) {
        return refreshTokenRepository.save(createRefreshToken(user)).getToken();
    }

    private RefreshToken createRefreshToken(User user){
        return new RefreshToken(refreshTokenLife, user);
    }

}
