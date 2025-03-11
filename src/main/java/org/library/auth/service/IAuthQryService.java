package org.library.auth.service;

import org.library.auth.dto.LoginRequestDto;
import org.library.auth.dto.LoginSuccessDto;

public interface IAuthQryService {

    LoginSuccessDto login(LoginRequestDto loginRequestDto);

}
