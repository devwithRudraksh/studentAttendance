package com.rudraksh.attendance.service;

import com.rudraksh.attendance.dto.StudentDTO;
import com.rudraksh.attendance.dto.StudentResponseDTO;

import java.util.List;

public interface StudentService {
    StudentResponseDTO addStudent(StudentDTO studentDTO);
    void deleteStudent(Long id);
    StudentResponseDTO getStudentById(Long id);
    List<StudentResponseDTO> getAllStudentsWithAge10();
}
