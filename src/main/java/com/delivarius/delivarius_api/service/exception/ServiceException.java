package com.delivarius.delivarius_api.service.exception;

public class ServiceException extends Exception {
    private static final long serialVersionUID = 1L;

    public ServiceException(Throwable e) {
        this.initCause(e);
    }
}