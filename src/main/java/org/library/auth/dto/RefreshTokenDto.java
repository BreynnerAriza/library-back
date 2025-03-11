package org.library.auth.dto;

import java.io.Serializable;

public record RefreshTokenDto(
        String accessToken
) implements Serializable { }
