package com.rudraksh.attendance.controller;

import com.rudraksh.attendance.dto.AttendanceDTO;
import com.rudraksh.attendance.entity.Attendance;
import com.rudraksh.attendance.entity.Student;
import com.rudraksh.attendance.repository.AttendanceRepository;
import com.rudraksh.attendance.repository.StudentRepository;
import com.rudraksh.attendance.exception.StudentNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    // List all attendance records (basic for now)
    @GetMapping
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    // Optional: Fetch attendance for a specific student
    @GetMapping("/student/{id}")
    public List<Attendance> getAttendanceByStudent(@PathVariable Long id) {
        return attendanceRepository.findByStudentId(id);
    }

    // Optional: Add attendance using JSON API
    @PostMapping
    public ResponseEntity<?> addAttendance(@RequestBody AttendanceDTO dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + dto.getStudentId() + " not found"));  // ✅
        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setDate(dto.getDate());
        attendance.setStatus(dto.getStatus());

        Attendance saved = attendanceRepository.save(attendance);

        return ResponseEntity.ok(saved);
    }
}
