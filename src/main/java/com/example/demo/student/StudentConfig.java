package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student matvey = new Student(
                    "Matvey",
                    "bestadapter@gmail.com",
                    LocalDate.of(2000, Month.OCTOBER, 3)
            );
            Student nikita = new Student(
                    "Nikita",
                    "bestadapter@gmail.com",
                    LocalDate.of(1999, Month.FEBRUARY, 12)
            );
            studentRepository.saveAll(
                    List.of(nikita, matvey)
            );
        };
    }
}
