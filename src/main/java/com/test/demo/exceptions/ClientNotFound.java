package com.test.demo.exceptions;

import com.test.demo.utils.exceptionsUtils.ExceptionUtils;

public class ClientNotFound extends BusinessException {
    public ClientNotFound(Long id) {
        super(ExceptionUtils.subst("Client with ID: {} has not been found", id));
    }
}
