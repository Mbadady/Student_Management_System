package com.mbadady.studentManagementSystem.service.impl;

import com.mbadady.studentManagementSystem.customException.ResourceNotFoundException;
import com.mbadady.studentManagementSystem.entity.Student;
import com.mbadady.studentManagementSystem.payload.StudentCustomDto;
import com.mbadady.studentManagementSystem.payload.StudentDto;
import com.mbadady.studentManagementSystem.repository.StudentRepository;
import com.mbadady.studentManagementSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentCustomDto getAllStudents(int pageNo, int pageSize, String sortBy, String sortDir) {

        // Sort if it is by ascending or descending order
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).ascending();

        // Create a pageable instance
        PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Student> students = studentRepository.findAll(pageable);

        List<Student> listOfStudents = students.getContent();

        StudentDto studentDto = new StudentDto();

    List<StudentDto> studentContents = listOfStudents.stream().map(student -> mapToDto(student)).collect(Collectors.toList());

        StudentCustomDto customDto = new StudentCustomDto();
        customDto.setContent(studentContents);
        customDto.setLast(students.isLast());
        customDto.setPageNo(students.getNumber());
        customDto.setPageSize(students.getSize());
        customDto.setTotalElements(students.getTotalElements());
        customDto.setTotalPages(students.getTotalPages());

        return customDto;
    }

    @Override
    public StudentDto addNewStudent(StudentDto studentDto) {
        Student student = mapToEntity(studentDto);
       Student newStudent =  studentRepository.save(student);

        return mapToDto(newStudent);
    }

    @Override
    public StudentDto updateStudentInfo(StudentDto studentDto, Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(()->
                new ResourceNotFoundException("Student", "id", studentId));
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
        student.setFirstName(studentDto.getFirstName());
        studentRepository.save(student);

        studentDto.setId(student.getId());
        studentDto.setEmail(student.getEmail());
        studentDto.setLastName(student.getLastName());
        studentDto.setFirstName(student.getFirstName());

        return studentDto;
    }

    @Override
    public StudentDto getStudentById(StudentDto studentDto, Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(()->
                new ResourceNotFoundException("Student", "id", studentId));

        return mapToDto(student);
    }

    @Override
    public String deleteStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(()->
                new ResourceNotFoundException("Student", "id", studentId));
        studentRepository.delete(student);

        return "Student deleted successfully";
    }

    private Student mapToEntity(StudentDto studentDto){
        Student student = new Student();
        student.setEmail(studentDto.getEmail());
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());

        return student;
    }

    private StudentDto mapToDto(Student student){
        StudentDto studentDto = new StudentDto();

        studentDto.setId(student.getId());
        studentDto.setEmail(student.getEmail());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());

        return studentDto;
    }
}
