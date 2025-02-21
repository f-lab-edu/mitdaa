package com.flab.mitdaa.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Parameter;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {

    private final int code;
    private final String message;
    private final Map<String,Object> parameters;

}
