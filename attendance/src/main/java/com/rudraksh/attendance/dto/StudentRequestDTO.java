package com.rudraksh.attendance.dto;

public class StudentRequestDTO {
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
