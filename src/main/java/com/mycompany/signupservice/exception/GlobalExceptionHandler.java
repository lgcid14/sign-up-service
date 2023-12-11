package com.mycompany.signupservice.exception;

import com.mycompany.signupservice.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GeneralServiceException.class)
    public ResponseEntity<ErrorResponse> handleEmailValidationError(GeneralServiceException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(new SimpleDateFormat("MMM d, yyyy hh:mm:ss a")
                .format(new Date()).toString());
        errorResponse.setCodigo(10);
        errorResponse.setDetail(e.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
