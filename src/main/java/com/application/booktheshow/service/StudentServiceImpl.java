package com.application.booktheshow.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.application.booktheshow.entity.Student;
import com.application.booktheshow.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // @Override
    // public Optional<Student> getStudentById(long studentId) {
    //     return studentRepository.findById(studentId);
    // }

    @Override
    public Optional<Student> getStudentByStudentId(Long studentId) {
        return studentRepository.findByStudentId(studentId);
    }

}
