package org.example.springdemo.dao;

import org.example.springdemo.model.Person;
import org.example.springdemo.model.Role;
import org.example.springdemo.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class PersonDao {
    private static int PERSON_COUNT = 12;



    @Autowired
    private JdbcTemplate jdbcTemplate;




    public PersonDao() { }

    public List<Person> index() {
        List<Person> people = jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
        return people;
    }

    public Person show(int id)  {
        Person person = jdbcTemplate.query("SELECT * FROM person WHERE person.id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
        return person;
    }

    public void save(Person person)  {
        jdbcTemplate.update("INSERT INTO person VALUES (username = ?, email = ?, role = ?, status = ?, age = ?)", person.getUsername(), person.getEmail() , person.getRole().name(), person.getStatus().name(), person.getAge());
        jdbcTemplate.update("INSERT INTO users VALUES (?, ?, true)", person.getUsername(), person.getPassword());
        person.getRole().getGrantedAuthority().forEach(a -> jdbcTemplate.update("INSERT INTO authorities VALUES (?, ?)", person.getUsername(), a.getAuthority() ));
    }

    public void update(Person person) {
        jdbcTemplate.update("UPDATE person SET username = ?, age = ?, email = ? WHERE person.id = ? ",
                person.getUsername(), person.getAge(), person.getEmail(), person.getId());
    }

    public void delete(Person person) {
        jdbcTemplate.update("DELETE FROM person WHERE person.id = ?", person.getId());
    }
}
