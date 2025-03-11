package org.library.refreshtoken.service;

import org.library.refreshtoken.entity.RefreshToken;

import java.util.Optional;

public interface IRefreshTokenQryService {

    Optional<RefreshToken> getRefreshTokenByToken(String token);

}
