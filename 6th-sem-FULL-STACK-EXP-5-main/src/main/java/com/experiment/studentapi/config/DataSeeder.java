package com.experiment.studentapi.config;

import com.experiment.studentapi.model.Student;
import com.experiment.studentapi.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataSeeder {

    @Bean
    CommandLineRunner seedDatabase(StudentRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                log.info("Seeding database with sample students...");

                repository.save(Student.builder()
                        .firstName("Alice")
                        .lastName("Johnson")
                        .email("alice.johnson@university.edu")
                        .age(20)
                        .department("Computer Science")
                        .gpa(3.5)
                        .build());

                repository.save(Student.builder()
                        .firstName("Bob")
                        .lastName("Smith")
                        .email("bob.smith@university.edu")
                        .age(22)
                        .department("Data Science")
                        .gpa(3.7)
                        .build());

                repository.save(Student.builder()
                        .firstName("Carol")
                        .lastName("Williams")
                        .email("carol.williams@university.edu")
                        .age(21)
                        .department("Computer Science")
                        .gpa(3.4)
                        .build());

                repository.save(Student.builder()
                        .firstName("David")
                        .lastName("Brown")
                        .email("david.brown@university.edu")
                        .age(19)
                        .department("Software Engineering")
                        .gpa(3.3)
                        .build());

                repository.save(Student.builder()
                        .firstName("Eve")
                        .lastName("Davis")
                        .email("eve.davis@university.edu")
                        .age(23)
                        .department("Data Science")
                        .gpa(3.9)
                        .build());

                log.info("Seeded 5 students into the database");
            }
        };
    }
}