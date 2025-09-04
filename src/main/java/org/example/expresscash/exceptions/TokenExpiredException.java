package org.example.expresscash.exceptions;

import org.example.expresscash.constants.StatusCodeEnum;

import java.text.MessageFormat;

public class TokenExpiredException extends RuntimeException {
    public final StatusCodeEnum statusCode;
    private final Object[] args;

    public TokenExpiredException(StatusCodeEnum statusCode, String message, Object... args) {
        super(message);
        this.statusCode = statusCode;
        this.args = args;
    }


    public String getFormattedMessage() {
        return MessageFormat.format(super.getMessage(), args);
    }
}
