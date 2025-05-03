package com.rudraksh.attendance.controller;

import com.rudraksh.attendance.dto.AttendanceDTO;
import com.rudraksh.attendance.entity.Attendance;
import com.rudraksh.attendance.entity.Student;
import com.rudraksh.attendance.repository.AttendanceRepository;
import com.rudraksh.attendance.repository.StudentRepository;
import com.rudraksh.attendance.exception.StudentNotFoundException;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AttendanceFormController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    // Render form
    @GetMapping("/attendance-form")
    public String showAttendanceForm(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("attendanceDTO", new AttendanceDTO());
        return "attendanceForm"; // This is your HTML template name
    }

    // Handle form submission
    @PostMapping("/attendance")
    @Transactional
    public String saveAttendance(@ModelAttribute AttendanceDTO dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + dto.getStudentId() + " not found"));  // ✅

        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setDate(dto.getDate());
        attendance.setStatus(dto.getStatus());


        try {
            attendanceRepository.save(attendance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/attendance-form"; // Optionally redirect or show a success page
    }
}
