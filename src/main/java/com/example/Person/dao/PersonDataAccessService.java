package com.example.Person.dao;

import com.example.Person.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository("postgres")
public class PersonDataAccessService implements PersonDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertPerson(UUID id, Person person) {
        final String sqlStatement = "INSERT INTO person (id, name) VALUES (? , ?)";
        jdbcTemplate.update(sqlStatement, id, person.getName());
    }

    @Override
    public List<Person> getAllPeople() {
        final String sqlStatement = "SELECT id, name FROM person";
        return jdbcTemplate.query(sqlStatement, (resultSet, i ) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(id, name);
        });
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sqlStatement = "SELECT id, name FROM person Where id = ?";
        Person person =  jdbcTemplate.queryForObject(sqlStatement,  (resultSet, i ) -> {
            UUID personId = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(personId, name);
        }, id);
        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {
        final String sqlStatement = "DELETE FROM person WHERE id = ?";
        return jdbcTemplate.update(sqlStatement, id);
    }

    @Override
    public void updatePersonById(UUID id, Person person ) {
        final String sqlStatement = "UPDATE person SET name = ? WHERE id = ?";
        jdbcTemplate.update(sqlStatement, person.getName(), id);
    }
}
