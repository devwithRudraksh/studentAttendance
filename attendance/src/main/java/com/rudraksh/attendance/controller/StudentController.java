package com.rudraksh.attendance.controller;

import com.rudraksh.attendance.dto.StudentDTO;
import com.rudraksh.attendance.dto.StudentResponseDTO;
import com.rudraksh.attendance.entity.Student;
import com.rudraksh.attendance.exception.StudentNotFoundException;
import com.rudraksh.attendance.repository.StudentRepository;
import com.rudraksh.attendance.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;
    private final StudentService studentService;

    public StudentController(StudentRepository studentRepository, StudentService studentService) {
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }



    @PostMapping
    public ResponseEntity<StudentResponseDTO> addStudent(@RequestBody StudentDTO studentDTO) {
        log.info("POST /students called with name: {}, email: {}", studentDTO.getName(), studentDTO.getEmail());

        StudentResponseDTO savedStudent = studentService.addStudent(studentDTO);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);}

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudentsWithAge10(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public StudentResponseDTO getStudentById(@PathVariable Long id) {
       return  studentService.getStudentById(id);
    } //STUDENT RESPONSE DTO


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/debug")
    public String debug() {
        log.info("🔍 Inside /debug endpoint — logging works!");
        List<Student> all = studentRepository.findAll();
        System.out.println("Fetched Students: " + all); // legacy
        return "Check logs";
    }

}