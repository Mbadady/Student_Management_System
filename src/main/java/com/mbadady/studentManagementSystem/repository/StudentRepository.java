package com.mbadady.studentManagementSystem.repository;

import com.mbadady.studentManagementSystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
