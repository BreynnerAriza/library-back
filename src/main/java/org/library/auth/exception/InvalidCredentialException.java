package org.library.auth.exception;

public class InvalidCredentialException extends RuntimeException {

    public InvalidCredentialException(String message){
        super(message);
    }

}

