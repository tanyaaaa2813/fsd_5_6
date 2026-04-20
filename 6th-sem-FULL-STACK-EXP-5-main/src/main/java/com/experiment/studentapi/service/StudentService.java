package com.experiment.studentapi.service;

import com.experiment.studentapi.dto.StudentRequestDTO;
import com.experiment.studentapi.dto.StudentResponseDTO;
import java.util.List;

public interface StudentService {
    StudentResponseDTO createStudent(StudentRequestDTO requestDTO);
    StudentResponseDTO getStudentById(Long id);
    List<StudentResponseDTO> getAllStudents();
    StudentResponseDTO updateStudent(Long id, StudentRequestDTO requestDTO);
    void deleteStudent(Long id);
    List<StudentResponseDTO> getStudentsByDepartment(String department);
    List<StudentResponseDTO> searchStudentsByName(String name);
}
