package com.application.booktheshow.service;

import java.util.List;
import java.util.Optional;

import com.application.booktheshow.entity.Student;

public interface StudentService {

    public Student addStudent(Student student);
    public List<Student> getAllStudents();
    // public Optional<Student> getStudentById(long Id);
    public Optional<Student> getStudentByStudentId(Long studentId);
}
