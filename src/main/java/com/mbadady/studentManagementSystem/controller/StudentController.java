package com.mbadady.studentManagementSystem.controller;


import com.mbadady.studentManagementSystem.payload.StudentCustomDto;
import com.mbadady.studentManagementSystem.payload.StudentDto;
import com.mbadady.studentManagementSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public StudentCustomDto getAllStudents(
                                           @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                           @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                                           @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                           @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir){
        return studentService.getAllStudents(pageNo, pageSize, sortBy, sortDir);
    }

    @PostMapping("/students")
    public StudentDto addNewStudent(@RequestBody StudentDto studentDto){
       return studentService.addNewStudent(studentDto);
    }

    @PutMapping("/students/{id}")
    public StudentDto updateStudent(@RequestBody StudentDto studentDto,
                                    @PathVariable Long id){
        return studentService.updateStudentInfo(studentDto, id);
    }

    @GetMapping("/students/{id}")
    public StudentDto getStudentById(@RequestBody StudentDto studentDto,
                                     @PathVariable Long id){
        return studentService.getStudentById(studentDto, id);
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudentById(@PathVariable Long id){
       return studentService.deleteStudentById(id);
    }

}
