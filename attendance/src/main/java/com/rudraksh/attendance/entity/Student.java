package com.rudraksh.attendance.entity;

import jakarta.persistence.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import lombok.NoArgsConstructor;
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")  // Add this!
    private String name;

    @Column(name = "email")  // Add this!
    private String email;

    @Column(name = "age")  // Add this!
    private Integer age;

    @OneToMany


    // Getters and Setters below
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }


}