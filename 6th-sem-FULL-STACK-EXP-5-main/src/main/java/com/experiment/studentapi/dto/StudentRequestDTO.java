package com.experiment.studentapi.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request payload for creating or updating a student")
public class StudentRequestDTO {

    @Schema(description = "Student's first name", example = "John")
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @Schema(description = "Student's last name", example = "Doe")
    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @Schema(description = "Student's email address", example = "john.doe@university.edu")
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid email address")
    private String email;

    @Schema(description = "Student's age", example = "20")
    @NotNull(message = "Age is required")
    @Min(value = 16, message = "Age must be at least 16")
    @Max(value = 100, message = "Age must be at most 100")
    private Integer age;

    @Schema(description = "Department of study", example = "Computer Science")
    @NotBlank(message = "Department is required")
    private String department;

    @Schema(description = "Grade Point Average (0.0 - 10.0)", example = "8.5")
    @DecimalMin(value = "0.0", message = "GPA must be at least 0.0")
    @DecimalMax(value = "10.0", message = "GPA must be at most 10.0")
    private Double gpa;
}
