package com.example.Person.service;


import com.example.Person.dao.PersonDao;
import com.example.Person.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("realDao") PersonDao personDao) {
        this.personDao = personDao;
    }

    public void insertPerson(Person person) {
        personDao.insertPerson(person);
    }

    public List<Person> getAllPeople() {
        return personDao.getAllPeople();
    }

    public Optional<Person> selectPersonById(UUID id) {
        return personDao.selectPersonById(id);
    }

    public int deletePersonById(UUID id) {
        return personDao.deletePersonById(id);
    }

    public void updatePersonById(UUID id, Person person) {
        personDao.updatePersonById(id, person);
    }


}
