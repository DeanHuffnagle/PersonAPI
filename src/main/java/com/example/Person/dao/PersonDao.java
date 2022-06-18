package com.example.Person.dao;


import com.example.Person.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
    void insertPerson(UUID id, Person person);

    default void insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        insertPerson(id, person);
    }

    List<Person> getAllPeople();

    Optional<Person> selectPersonById(UUID id);

    int deletePersonById(UUID id);

    void updatePersonById(UUID id, Person person);

}
