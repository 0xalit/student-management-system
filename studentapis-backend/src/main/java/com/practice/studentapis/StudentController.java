package com.practice.studentapis;

import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5500")
@RestController

@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getStudents()
                .stream()
                .map(student -> new StudentDTO(student.getId(), student.getName(), student.getEmail(), student.getAge()))
                .toList();
    }

    @PostMapping
    public Student createStudent(@RequestBody @Valid StudentDTO studentDTO) {
        Student student = new Student();

        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setAge(studentDTO.getAge());

        return studentService.addStudent(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody @Valid StudentDTO studentDTO) {
        Student updatedData = new Student();

        updatedData.setName(studentDTO.getName());
        updatedData.setEmail(studentDTO.getEmail());
        updatedData.setAge(studentDTO.getAge());

        Student updatedStudent = studentService.updateStudent(id, updatedData);

        StudentDTO responseDTO = new StudentDTO(updatedStudent.getId(), updatedStudent.getName(), updatedStudent.getEmail(), updatedStudent.getAge());
        return ResponseEntity.ok(responseDTO);
    }


    @DeleteMapping("/{id}")
    public void deleteStu(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }


    @GetMapping("/exists/{id}")
    public ResponseEntity<String> studentExists(@PathVariable Long id) {
        boolean exists = studentService.studentExists(id);
        if (exists)
            return ResponseEntity.ok("Student exists in the database.");
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found.");


    }
}
