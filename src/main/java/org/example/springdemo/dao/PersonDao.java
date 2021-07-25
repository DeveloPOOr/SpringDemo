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
        people.add(new Person(++PERSON_COUNT , "Mike"));
        people.add(new Person(++PERSON_COUNT , "Daniel"));
        people.add(new Person(++PERSON_COUNT , "Dandy"));
        people.add(new Person(++PERSON_COUNT , "Jim Halpert"));
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
}
