package org.library.refreshtoken.service;

import org.library.user.entity.User;

public interface IRefreshTokenCmdService {

    String generateRefreshToken(User user);

}
