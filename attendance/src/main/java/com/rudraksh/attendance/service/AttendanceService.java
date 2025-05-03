package com.rudraksh.attendance.service;

import com.rudraksh.attendance.dto.AttendanceDTO;
import com.rudraksh.attendance.entity.Attendance;
import com.rudraksh.attendance.entity.Student;
import com.rudraksh.attendance.exception.StudentNotFoundException;
import com.rudraksh.attendance.repository.AttendanceRepository;
import com.rudraksh.attendance.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Method to save attendance
    public Attendance saveAttendance(AttendanceDTO dto) {
        // Validate DTO fields (e.g., studentId, date, status)
        if (dto.getStudentId() == null || dto.getDate() == null || dto.getStatus() == null) {
            throw new IllegalArgumentException("Invalid data in AttendanceDTO.");
        }

        // Fetch student from DB
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + dto.getStudentId() + " not found"));  // ✅
        // Throw exception if not found

        // Create and populate Attendance object
        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setDate(dto.getDate());
        attendance.setStatus(dto.getStatus());

        // Save and return attendance
        return attendanceRepository.save(attendance);
    }

    // Optional: You can add more methods for fetching attendance, updating, etc.
}
