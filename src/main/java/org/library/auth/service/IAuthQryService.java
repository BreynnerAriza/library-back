package org.library.auth.service;

import org.library.auth.dto.LoginRequest;
import org.library.auth.dto.LoginSuccess;

public interface IAuthQryService {

    LoginSuccess login(LoginRequest loginRequest);

}
