package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail =
                studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent())
            throw new IllegalStateException("email taken");
        studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        if (!studentRepository.existsById(id)) throw new
                IllegalStateException("student with id = " + id + " doesn't exist");
        studentRepository.deleteById(id);
    }
    @Transactional
    public void updateStudent(long id, String name, String email) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id = " + id + " not found"
                ))
                ;
        if (name != null && name.length() > 0) {
            student.setName(name);
        }
        if (email != null && email.length() > 0) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) throw new IllegalStateException("email taken");
            student.setEmail(email);
        }
    }
}
