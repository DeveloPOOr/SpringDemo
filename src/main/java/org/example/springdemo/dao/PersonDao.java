package org.example.springdemo.dao;

import org.example.springdemo.model.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
    private static int PERSON_COUNT = 4;
    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "123456";

    private static Class cl;
    private static Connection con;

    static {
        try {
            cl = Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public PersonDao() { }

    public List<Person> index() {
        List<Person> people = new ArrayList<>();
        try {
            Statement all = con.createStatement();
            ResultSet resultSet = all.executeQuery("SELECT * FROM person");
            while (resultSet.next()) {
                Person person = new Person();
                person.setId( resultSet.getInt("id"));
                person.setAge( resultSet.getInt("age"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                people.add(person);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return people;
    }

    public Person show(int id)  {
        Person person = new Person();
        try {
            PreparedStatement st = con.prepareStatement("SELECT * FROM person WHERE person.id = ?");
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                person.setId( resultSet.getInt("id"));
                person.setAge( resultSet.getInt("age"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    public void save(Person person)  {
        try{
            PreparedStatement st = con.prepareStatement("INSERT INTO person VALUES (?, ?, ?, ?)");
            st.setInt(1, person.getId());
            st.setString(2, person.getName());
            st.setInt(3, person.getAge());
            st.setString(4, person.getEmail());

            st.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Person person) {
        try{
            PreparedStatement st = con.prepareStatement("UPDATE person SET name = ?, age = ?, email = ? WHERE person.id = ? ");
            st.setString(1, person.getName());
            st.setInt(2, person.getAge());
            st.setString(3, person.getEmail());
            st.setInt(4, person.getId());
            st.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(Person person) {
        try{
            PreparedStatement st = con.prepareStatement("DELETE FROM person WHERE person.id = ?");
            st.setInt(1, person.getId());
            st.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
