package com.example.Person.model;

import java.util.UUID;

public class Person {
    private final UUID id;
    private final String name;


    public Person(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
