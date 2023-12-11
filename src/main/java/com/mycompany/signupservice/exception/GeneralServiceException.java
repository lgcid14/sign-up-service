package com.mycompany.signupservice.exception;

public class GeneralServiceException extends RuntimeException {

    private int codigo;

    public GeneralServiceException(String message, int codigo) {
        super(message);
        this.codigo = codigo;
    }
}
