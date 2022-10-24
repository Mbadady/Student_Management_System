package com.mbadady.studentManagementSystem.service;

import com.mbadady.studentManagementSystem.entity.Student;
import com.mbadady.studentManagementSystem.payload.StudentCustomDto;
import com.mbadady.studentManagementSystem.payload.StudentDto;

import java.util.List;

public interface StudentService {
    StudentCustomDto getAllStudents(int pageNo, int pageSize, String sortBy, String sortDir);

    StudentDto addNewStudent(StudentDto studentDto);

    StudentDto updateStudentInfo(StudentDto studentDto, Long studentId);

    StudentDto getStudentById(StudentDto studentDto, Long studentId);

    String deleteStudentById(Long studentId);

}
