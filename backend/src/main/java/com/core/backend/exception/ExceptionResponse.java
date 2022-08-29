package com.core.backend.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ExceptionResponse {

    private final String message;
}
