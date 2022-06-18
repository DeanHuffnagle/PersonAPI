package com.example.Person.dao;

import com.example.Person.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao{

    private static final List<Person> DB = new ArrayList<>();

    @Override
    public void insertPerson(UUID id, Person person) {
        DB.add(new Person(id,person.getName()));
    }

    @Override
    public List<Person> getAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
       return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
       Optional<Person> person = selectPersonById(id);
       if (person.isEmpty()) {
           return 0;
       }
       DB.remove(person.get());
       return 1;

    }

    @Override
    public void updatePersonById(UUID id, Person person ) {
        selectPersonById(id)
                .map(p -> {
                    int indexOfPerson = DB.indexOf(p);
                    if (indexOfPerson >= 0) {
                        DB.set(indexOfPerson, new Person(p.getId(), person.getName()));
                        return 1;
                    }
                    return 0;
                });
    }
}
