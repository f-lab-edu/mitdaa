package com.flab.mitdaa.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.logging.Logger;

@RestControllerAdvice
public class MitdaaExceptionHandler {

    private static final Logger logger = Logger.getLogger(MitdaaExceptionHandler.class.getName());

    @ExceptionHandler(MitdaaException.class)
    public ResponseEntity<ErrorResponse> handleMitdaaException(MitdaaException ex) {

        ErrorType errorType = ex.getErrorType();

        Map<String, Object> parameters  = ex.getParameters();

        logError(errorType, parameters );

        ErrorResponse errorResponse = new ErrorResponse(
                errorType.getCode(),
                errorType.getMessage(),
                parameters
        );
        return new ResponseEntity<>(errorResponse, errorType.getHttpStatus());
    }
    // 로그 에러 메소드
    private void logError(ErrorType errorType, Map<String, Object> parameters) {
        logger.info("Error: " + errorType.getMessage() + " with parameters: " + parameters);
    }

}
