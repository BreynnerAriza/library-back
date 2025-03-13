package org.library.auth.service;

import org.library.auth.dto.LoginRequestDto;
import org.library.auth.dto.LoginSuccessDto;
import org.library.user.entity.User;

import java.util.Optional;

public interface IAuthQueryService {

    LoginSuccessDto login(LoginRequestDto loginRequestDto);
    User getUserAuthenticate();

}
