package com.test.demo.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BusinessException extends Exception {

    public BusinessException(String message) {
        super(message);
    }
}
