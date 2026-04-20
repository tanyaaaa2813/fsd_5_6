package com.experiment.studentapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Response payload containing student information")
public class StudentResponseDTO {

    @Schema(description = "Unique identifier of the student", example = "1")
    private Long id;

    @Schema(description = "Student's first name", example = "John")
    private String firstName;

    @Schema(description = "Student's last name", example = "Doe")
    private String lastName;

    @Schema(description = "Student's full name", example = "John Doe")
    private String fullName;

    @Schema(description = "Student's email address", example = "john.doe@university.edu")
    private String email;

    @Schema(description = "Student's age", example = "20")
    private Integer age;

    @Schema(description = "Department of study", example = "Computer Science")
    private String department;

    @Schema(description = "Grade Point Average", example = "8.5")
    private Double gpa;
}
