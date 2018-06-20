package com.delivarius.delivarius_api.service.exception;

public class HttpConnectionException extends Exception {
    private static final long serialVersionUID = 1L;

    public HttpConnectionException(Throwable e) {
        this.initCause(e);
    }
}