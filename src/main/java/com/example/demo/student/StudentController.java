package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String greet() {
        return "Hello, world!";
    }

    @GetMapping("/json")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentID}")
    public void deleteStudent(@PathVariable("studentID") long id) {
        studentService.deleteStudent(id);
    }

    @PutMapping(path = "{studentID}")
    public void putStudent(@PathVariable("studentID") long id,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String email) {
        studentService.updateStudent(id, name, email);
    }
}
