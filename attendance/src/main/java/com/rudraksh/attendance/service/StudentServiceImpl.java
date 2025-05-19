package com.rudraksh.attendance.service;

import com.rudraksh.attendance.dto.StudentDTO;
import com.rudraksh.attendance.dto.StudentResponseDTO;
import com.rudraksh.attendance.entity.Student;
import com.rudraksh.attendance.exception.StudentNotFoundException;
import com.rudraksh.attendance.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @PostConstruct
    public void printAppInfo() {
        System.out.println("🚀 App Name: " + appName);
        System.out.println("🔢 App Version: " + appVersion);
    }


    public StudentResponseDTO addStudent(StudentDTO studentDTO) {
        log.info("Received request to add student: {}", studentDTO.getName());

        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setAge(studentDTO.getAge());
        student = studentRepository.save(student);
        log.info("Student saved with ID: {}", student.getId());
        return new StudentResponseDTO(student.getName(), student.getEmail());
    }
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with ID: " + id));

        studentRepository.delete(student);
    }
public StudentResponseDTO getStudentById(Long id){
    Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " not found"));
    return new StudentResponseDTO(student.getName(),student.getEmail());
}
    public List<StudentResponseDTO> getAllStudentsWithAge10() {
        var students = studentRepository.findAll();

        return students.stream()
                .filter(s -> s.getAge() > 10)
                .map(s -> new StudentResponseDTO(s.getName(), s.getEmail()))
                .collect(Collectors.toList());
    }
}