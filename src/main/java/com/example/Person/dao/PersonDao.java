package com.example.Person.dao;


import com.example.Person.model.Person;

import java.util.List;
import java.util.UUID;

public interface PersonDao {
    int insertPerson(UUID id, Person person);

    List<Person> getAllPeople();
}
