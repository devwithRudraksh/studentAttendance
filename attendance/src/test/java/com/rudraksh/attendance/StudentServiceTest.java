package com.rudraksh.attendance;

import com.rudraksh.attendance.dto.StudentResponseDTO;
import com.rudraksh.attendance.entity.Student;
import com.rudraksh.attendance.dto.StudentDTO;
import com.rudraksh.attendance.repository.StudentRepository;
import com.rudraksh.attendance.service.StudentServiceImpl;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;



    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    void shouldReturnStudentsWithAgeGreaterThan10() {
        // Arrange
        Student student1 = new Student();
        student1.setId(1L);
        student1.setName("Amit");
        student1.setEmail("amit@example.com");
        student1.setAge(15);

        Student student2 = new Student();
        student2.setId(2L);
        student2.setName("Ravi");
        student2.setEmail("ravi@example.com");
        student2.setAge(8); // This one should be filtered out

        List<Student> studentsFromRepo = List.of(student1, student2);

        when(studentRepository.findAll()).thenReturn(studentsFromRepo);

        // Act
        List<StudentResponseDTO> result = studentService.getAllStudentsWithAge10();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Amit", result.get(0).getName());
        assertEquals("amit@example.com", result.get(0).getEmail());
        System.out.println(result);
    }

}
