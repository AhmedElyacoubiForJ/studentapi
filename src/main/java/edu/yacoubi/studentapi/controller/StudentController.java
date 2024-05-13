package edu.yacoubi.studentapi.controller;

import edu.yacoubi.studentapi.model.Student;
import edu.yacoubi.studentapi.servcie.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000") // allowing client app. consuming this backend
@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final IStudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        return new ResponseEntity<List<Student>>(studentService.getStudents(), HttpStatus.FOUND);
    }
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return new ResponseEntity<Student>(studentService.addStudent(student), HttpStatus.CREATED);
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable Long id) {
        return new ResponseEntity<Student>(studentService.updateStudent(student, id), HttpStatus.OK);
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        return new ResponseEntity<Student>(studentService.getStudentById(id), HttpStatus.OK);
    }
}
