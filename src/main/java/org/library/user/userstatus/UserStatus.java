package org.library.user.userstatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor @Getter
public enum UserStatus {

    ACTIVE("Activo"),
    DISABLED("Deshabilitado");

    private final String value;

    //Permite obtener un UserStatus a partir de un String
    public static UserStatus fromValue(String value){
        return Arrays.stream(UserStatus.values()).filter(
                status -> status.value.equals(value)
        ).findAny().orElseThrow(() -> new IllegalArgumentException("UserStatus [ " + value + " ]"));
    }

}
