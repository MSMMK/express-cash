package org.example.expresscash.constants;

public enum StatusEnum {
    ACTIVE(100),
    INACTIVE(101),
    ACCESS_DENIED(505),
    UNAUTHORIZED(401),
    BAD_REQUEST(403),
    INTERNAL_SERVER_ERROR(500),
    DELETED(102),
    TOKEN_EXPIRED(409);

    int code;
    StatusEnum(int code) {
        this.code = code;
    }
}
