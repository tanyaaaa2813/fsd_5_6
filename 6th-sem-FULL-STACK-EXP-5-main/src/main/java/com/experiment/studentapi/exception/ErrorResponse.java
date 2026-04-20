package com.experiment.studentapi.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Standard error response")
public class ErrorResponse {

    @Schema(description = "HTTP status code", example = "404")
    private int status;

    @Schema(description = "Error type", example = "NOT_FOUND")
    private String error;

    @Schema(description = "Error message", example = "Student not found with ID: 99")
    private String message;

    @Schema(description = "Request path", example = "/api/students/99")
    private String path;

    @Schema(description = "Timestamp of the error")
    private LocalDateTime timestamp;

    @Schema(description = "Validation field errors (present only on 400 validation errors)")
    private Map<String, String> fieldErrors;
}
