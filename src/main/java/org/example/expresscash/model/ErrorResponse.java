package org.example.expresscash.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.expresscash.constants.StatusCodeEnum;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private StatusCodeEnum status;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse(StatusCodeEnum status,String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
