package org.library.auth.service;

import org.library.auth.dto.RefreshTokenDto;
import org.library.auth.dto.LoginSuccessDto;
import org.library.user.entity.User;

public interface IAuthCmdService {

    LoginSuccessDto refreshToken(RefreshTokenDto accessToken);

}
