package org.example.springdemo.dao;

import org.example.springdemo.model.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
    private int PERSON_COUNT = 0;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++PERSON_COUNT , "Mike", 26, "mike@gmail.com"));
        people.add(new Person(++PERSON_COUNT , "Daniel", 45, "user34@gmail.com"));
        people.add(new Person(++PERSON_COUNT , "Dandy", 34, "Dandy23@mail.ru"));
        people.add(new Person(++PERSON_COUNT , "Jim Halpert", 10, "JimHalpert@mail.ru"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {

        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PERSON_COUNT);
        people.add(person);
    }

    public void update(Person person) {
        Person toBeUpdatedPerson = show(person.getId());
        toBeUpdatedPerson.setName(person.getName());
        toBeUpdatedPerson.setAge(person.getAge());
        toBeUpdatedPerson.setEmail(person.getEmail());

    }

    public void delete(Person person) {
        people.removeIf(per -> per.getId() == person.getId());
    }
}
