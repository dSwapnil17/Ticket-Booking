package com.application.booktheshow.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.booktheshow.entity.Student;
import com.application.booktheshow.service.StudentService;

@RestController
@RequestMapping("/booktheshow")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping("/saveStudent")
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        Student saveStudent = studentService.addStudent(student);
        return ResponseEntity.ok(saveStudent);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/validate/{studentId}")
    public ResponseEntity<String> validateStudentId(@PathVariable String studentId){
        Long studentIdLong = Long.parseLong(studentId);
        Optional<Student> student = studentService.getStudentByStudentId(studentIdLong);
        if(student.isPresent()){
            return ResponseEntity.ok("StudentId is valid");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid student id");
        }
    }

    @PostMapping("/check-dob")
    public ResponseEntity<Map<String, Boolean>> checkDateOfBirth(@RequestBody Map<String, String> request) {
        String dob = request.get("dateOfBirth");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateOfBirth = LocalDate.parse(dob, formatter);

        boolean exists = studentService.checkDateOfBirth(dateOfBirth);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/validate-student")
    public ResponseEntity<Map<String, Boolean>> validateStudent(@RequestBody Map<String, String> request){
        Long studentId = Long.parseLong(request.get("studentId"));
        String dob = request.get("dateOfBirth");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateOfBirth = LocalDate.parse(dob, formatter);

        boolean isValid = studentService.validateStudent(studentId, dateOfBirth);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isValid", isValid);

        return ResponseEntity.ok(response);
    }
}
