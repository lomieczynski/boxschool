package com.andthenew.boxschool.controller;

import com.andthenew.boxschool.exception.ResourceNotFoundException;
import com.andthenew.boxschool.model.Student;
import com.andthenew.boxschool.repository.StudentRepositiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")

public class StudentController {
    @Autowired
    private StudentRepositiry studentRepositiry;

    @CrossOrigin
    @PostMapping("/student")
    public Student createStudent(@Valid @RequestBody Student student){
        Student student1 = studentRepositiry.save(student);
        return student1;
    }
    @CrossOrigin
    @GetMapping("/student")
    public Page<Student> listStudents(Pageable pageable){
        return studentRepositiry.findAll(pageable);
    }
    @CrossOrigin
    @GetMapping("/student/{id}")
    public Student getById(@PathVariable Long id){
        Student student = studentRepositiry.getOne(id);
        return student;
    }
    @CrossOrigin
    @DeleteMapping("/student")
    public ResponseEntity<?> deleteStident(@PathVariable Long id){
        return studentRepositiry.findById(id)
                .map(student -> {
                    studentRepositiry.delete(student);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()-> new ResourceNotFoundException("Student not found with id" + id));
    }
}
