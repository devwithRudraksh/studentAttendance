package com.rudraksh.attendance.repository;

import com.rudraksh.attendance.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // no need to write anything here. Magic!
}