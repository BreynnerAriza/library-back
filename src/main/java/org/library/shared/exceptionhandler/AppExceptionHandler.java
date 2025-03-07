package org.library.shared.exceptionhandler;

import org.library.auth.exception.InvalidCredentialException;
import org.library.auth.exception.UserDisabledException;
import org.library.shared.dto.RequestFailed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<RequestFailed> invalidCredentialException(InvalidCredentialException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(
                new RequestFailed(List.of(ex.getMessage()))
        );
    }

    @ExceptionHandler(UserDisabledException.class)
    public ResponseEntity<RequestFailed> userDisabledException(UserDisabledException ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(
                new RequestFailed(List.of(ex.getMessage()))
        );
    }


}
