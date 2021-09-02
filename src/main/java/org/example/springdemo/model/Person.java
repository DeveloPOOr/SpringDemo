package org.example.springdemo.model;


import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class Person {

    @NotEmpty(message = "name shouldn't be empty")
    @Size(min = 1, max = 15, message = "name should be between 1 and 15 symbols")
    private String username;

    private int id;

    @Min(value = 0, message = "age should be greater than 0")
    private int age;

    @Email(message = "Email should be valid")
    @NotEmpty(message = "email shouldn't be empty")
    private String email;

    private Role role;

    private Status status;

    @NotEmpty(message = "password shouldn't be empty")
    @Size(min = 6, max = 50, message = "password should be between 6 and 50 symbols")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person() {};

    public Person(int id,  String name, int age, String email) {
        this.username = name;
        this.id = id;
        this.age = age;
        this.email = email;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
