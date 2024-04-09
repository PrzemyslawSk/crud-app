package com.przemyslawsk.crudapp.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum Error {
    INVALID_REQUEST("Invalid request", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus httpStatus;

    public ServiceError getError() {
        return new ServiceError(this);
    }
}
