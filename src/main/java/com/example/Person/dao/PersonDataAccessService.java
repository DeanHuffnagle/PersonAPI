package com.example.Person.dao;

import com.example.Person.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository("realDao")
public class PersonDataAccessService implements PersonDao{

    private static final List<Person> DB = new ArrayList<>();

    @Override
    public void insertPerson(UUID id, Person person) {
        System.out.println("real Dao insert");
        DB.add(new Person(id,person.getName()));
    }

    @Override
    public List<Person> getAllPeople() {
        System.out.println("real Dao get all");
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        System.out.println("real Dao select one");
        return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        System.out.println("real Dao delete");
        Optional<Person> person = selectPersonById(id);
        if (person.isEmpty()) {
            return 0;
        }
        DB.remove(person.get());
        return 1;

    }

    @Override
    public void updatePersonById(UUID id, Person person ) {
        System.out.println("real Dao update");
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
