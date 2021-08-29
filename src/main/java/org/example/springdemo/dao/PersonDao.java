package org.example.springdemo.dao;

import org.example.springdemo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
    private static int PERSON_COUNT = 12;



    @Autowired
    private JdbcTemplate jdbcTemplate;


    public PersonDao() { }

    public List<Person> index() {
        List<Person> people= jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
        return people;
    }

    public Person show(int id)  {
        Person person = jdbcTemplate.query("SELECT * FROM person WHERE person.id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
        return person;
    }

    public void save(Person person)  {
        jdbcTemplate.update("INSERT INTO person VALUES (?, ?, ?, ?)", ++PERSON_COUNT, person.getName(), person.getAge(), person.getEmail());
    }

    public void update(Person person) {
        jdbcTemplate.update("UPDATE person SET name = ?, age = ?, email = ? WHERE person.id = ? ",
                person.getName(), person.getAge(), person.getEmail(), person.getId());
    }

    public void delete(Person person) {
        jdbcTemplate.update("DELETE FROM person WHERE person.id = ?", person.getId());
    }
}
