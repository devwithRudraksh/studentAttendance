package com.rudraksh.attendance.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;

public class StudentRequestDTO {
    @NotBlank(message = "Name is required")
    private String name;
    private int age;

    public StudentRequestDTO() {}

    public StudentRequestDTO(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters & setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
