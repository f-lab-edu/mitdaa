package com.flab.mitdaa.exception;

import lombok.Getter;

import java.util.Map;
import java.util.function.Consumer;


@Getter
public class MitdaaException extends RuntimeException{

    private final ErrorType errorType;
    private final Map<String, Object> parameters;
    private final Consumer<String> log;

    public MitdaaException(ErrorType errorType , Map<String, Object> parameters, Consumer<String> log) {
        super(errorType.getMessage());
        this.errorType = errorType;
        this.parameters = parameters;
        this.log = (log != null) ? log : (msg -> {});

        log.accept("Message : " + errorType.getMessage() + " 파라미터 : " + parameters  );
    }
}

