package org.example.springdemo.model;


import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class Person {

    @NotEmpty(message = "name shouldn't be empty")
    @Size(min = 1, max = 15, message = "name should be between 1 and 15 symbols")
    private String name;

    private int id;

    @Min(value = 0, message = "age should be greater than 0")
    private int age;

    @Email(message = "Email should be valid")
    @NotEmpty(message = "email shouldn't be empty")
    private String email;

    public Person() {};

    public Person(int id,  String name, int age, String email) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
