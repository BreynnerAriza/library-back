package org.library.auth.service;

import org.library.auth.dto.RefreshTokenDto;
import org.library.auth.dto.LoginSuccessDto;

public interface IAuthCommandService {

    LoginSuccessDto refreshToken(RefreshTokenDto accessToken);

}
