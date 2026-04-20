package com.experiment.studentapi.repository;

import com.experiment.studentapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    List<Student> findByDepartment(String department);

    List<Student> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String first, String last);
}