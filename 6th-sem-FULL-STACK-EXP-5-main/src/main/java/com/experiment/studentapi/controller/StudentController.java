package com.experiment.studentapi.controller;

import com.experiment.studentapi.dto.StudentRequestDTO;
import com.experiment.studentapi.dto.StudentResponseDTO;
import com.experiment.studentapi.exception.ErrorResponse;
import com.experiment.studentapi.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@Tag(name = "Student API", description = "CRUD operations for managing students")
public class StudentController {

    private final StudentService studentService;

    // ─── POST /api/students ───────────────────────────────────────────────────
    @Operation(
        summary = "Create a new student",
        description = "Adds a new student to the system. Returns 201 with Location header."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Student created successfully",
            content = @Content(schema = @Schema(implementation = StudentResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Validation failed",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "409", description = "Email already exists",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(
            @Valid @RequestBody StudentRequestDTO requestDTO) {
        StudentResponseDTO created = studentService.createStudent(requestDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    // ─── GET /api/students ────────────────────────────────────────────────────
    @Operation(summary = "Get all students", description = "Returns a list of all registered students.")
    @ApiResponse(responseCode = "200", description = "List of students returned",
        content = @Content(array = @ArraySchema(schema = @Schema(implementation = StudentResponseDTO.class))))
    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    // ─── GET /api/students/{id} ───────────────────────────────────────────────
    @Operation(summary = "Get student by ID", description = "Returns a single student by their unique ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Student found",
            content = @Content(schema = @Schema(implementation = StudentResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Student not found",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(
            @Parameter(description = "Student ID", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    // ─── PUT /api/students/{id} ───────────────────────────────────────────────
    @Operation(summary = "Update a student", description = "Updates all fields of an existing student.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Student updated",
            content = @Content(schema = @Schema(implementation = StudentResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Validation failed",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "Student not found",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "409", description = "Email already exists",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(
            @Parameter(description = "Student ID", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDTO requestDTO) {
        return ResponseEntity.ok(studentService.updateStudent(id, requestDTO));
    }

    // ─── DELETE /api/students/{id} ────────────────────────────────────────────
    @Operation(summary = "Delete a student", description = "Permanently removes a student by ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Student deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Student not found",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @Parameter(description = "Student ID", example = "1")
            @PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    // ─── GET /api/students/department/{dept} ──────────────────────────────────
    @Operation(summary = "Get students by department", description = "Returns all students in a given department.")
    @GetMapping("/department/{department}")
    public ResponseEntity<List<StudentResponseDTO>> getByDepartment(
            @Parameter(description = "Department name", example = "Computer Science")
            @PathVariable String department) {
        return ResponseEntity.ok(studentService.getStudentsByDepartment(department));
    }

    // ─── GET /api/students/search?name=... ────────────────────────────────────
    @Operation(summary = "Search students by name", description = "Searches students by first or last name (case-insensitive partial match).")
    @GetMapping("/search")
    public ResponseEntity<List<StudentResponseDTO>> searchByName(
            @Parameter(description = "Name search term", example = "john")
            @RequestParam String name) {
        return ResponseEntity.ok(studentService.searchStudentsByName(name));
    }
}
