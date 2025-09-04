package org.example.expresscash.exceptions;

import org.example.expresscash.constants.StatusCodeEnum;

import java.text.MessageFormat;

public class BusinessException extends RuntimeException{
    private final StatusCodeEnum statusCode;
    private final Object[] args;

    public BusinessException(StatusCodeEnum statusCode, String message, Object... args) {
        super(message);
        this.statusCode = statusCode;
        this.args = args;
    }

    public StatusCodeEnum getStatusCode() {
        return statusCode;
    }

    public Object[] getArgs() {
        return args;
    }

    public String getFormattedMessage() {
        return MessageFormat.format(super.getMessage(), args);
    }
}
