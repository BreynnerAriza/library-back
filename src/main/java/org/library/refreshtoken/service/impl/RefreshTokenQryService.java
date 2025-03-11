package org.library.refreshtoken.service.impl;

import lombok.RequiredArgsConstructor;
import org.library.refreshtoken.entity.RefreshToken;
import org.library.refreshtoken.repository.IRefreshTokenRepository;
import org.library.refreshtoken.service.IRefreshTokenQryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenQryService implements IRefreshTokenQryService {

    private final IRefreshTokenRepository refreshTokenRepository;

    //Permite buscar un refresh token por el token
    @Transactional(readOnly = true)
    @Override
    public Optional<RefreshToken> getRefreshTokenByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

}
