package com.andthenew.boxschool.repository;

import com.andthenew.boxschool.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepositiry extends JpaRepository<Student, Long> {
}
