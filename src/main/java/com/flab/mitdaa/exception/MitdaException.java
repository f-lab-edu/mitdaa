package com.flab.mitdaa.exception;

import lombok.Getter;


@Getter
public class MitdaException extends RuntimeException{

    private final ErrorType errorType;

    public MitdaException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}

