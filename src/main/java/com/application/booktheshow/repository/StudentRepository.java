package com.application.booktheshow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.booktheshow.entity.Student;


public interface StudentRepository extends JpaRepository<Student, Long>{

    Optional<Student> findByStudentId(Long studentId);

}
