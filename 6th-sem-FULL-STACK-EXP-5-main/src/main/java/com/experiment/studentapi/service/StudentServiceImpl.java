package com.experiment.studentapi.service;

import com.experiment.studentapi.dto.StudentRequestDTO;
import com.experiment.studentapi.dto.StudentResponseDTO;
import com.experiment.studentapi.exception.EmailAlreadyExistsException;
import com.experiment.studentapi.exception.StudentNotFoundException;
import com.experiment.studentapi.model.Student;
import com.experiment.studentapi.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    // ───────── CREATE STUDENT ─────────

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO dto) {

        log.info("Creating student with email: {}", dto.getEmail());

        if (studentRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException(dto.getEmail());
        }

        Student student = mapToEntity(dto);
        Student saved = studentRepository.save(student);

        log.info("Student created with ID: {}", saved.getId());

        return mapToResponse(saved);
    }

    // ───────── GET STUDENT BY ID ─────────

    @Override
    @Transactional(readOnly = true)
    public StudentResponseDTO getStudentById(Long id) {

        log.info("Fetching student with ID: {}", id);

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        return mapToResponse(student);
    }

    // ───────── GET ALL STUDENTS ─────────

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponseDTO> getAllStudents() {

        log.info("Fetching all students");

        return studentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ───────── UPDATE STUDENT ─────────

    @Override
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto) {

        log.info("Updating student with ID: {}", id);

        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        // Check email conflict
        if (!existing.getEmail().equalsIgnoreCase(dto.getEmail())
                && studentRepository.existsByEmailAndIdNot(dto.getEmail(), id)) {

            throw new EmailAlreadyExistsException(dto.getEmail());
        }

        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setEmail(dto.getEmail());
        existing.setAge(dto.getAge());
        existing.setDepartment(dto.getDepartment());
        existing.setGpa(dto.getGpa());

        Student updated = studentRepository.save(existing);

        log.info("Student updated with ID: {}", updated.getId());

        return mapToResponse(updated);
    }

    // ───────── DELETE STUDENT ─────────

    @Override
    public void deleteStudent(Long id) {

        log.info("Deleting student with ID: {}", id);

        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }

        studentRepository.deleteById(id);

        log.info("Student deleted with ID: {}", id);
    }

    // ───────── GET BY DEPARTMENT ─────────

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponseDTO> getStudentsByDepartment(String department) {

        log.info("Fetching students by department: {}", department);

        return studentRepository.findByDepartment(department)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ───────── SEARCH STUDENTS BY NAME ─────────

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponseDTO> searchStudentsByName(String name) {

        log.info("Searching students by name: {}", name);

        return studentRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ───────── DTO → ENTITY ─────────

    private Student mapToEntity(StudentRequestDTO dto) {

        return Student.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .age(dto.getAge())
                .department(dto.getDepartment())
                .gpa(dto.getGpa())
                .build();
    }

    // ───────── ENTITY → RESPONSE DTO ─────────

    private StudentResponseDTO mapToResponse(Student student) {

        return StudentResponseDTO.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .fullName(student.getFirstName() + " " + student.getLastName())
                .email(student.getEmail())
                .age(student.getAge())
                .department(student.getDepartment())
                .gpa(student.getGpa())
                .build();
    }
}