package com.example.Person.api;

import com.example.Person.model.Person;
import com.example.Person.service.PersonService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void insertPerson(@Valid @NotNull @RequestBody Person person) {
     personService.insertPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @GetMapping(path = "{id}")
    public Optional<Person> selectPersonById(@PathVariable("id") UUID id) {
        return personService.selectPersonById(id);
    }

    @DeleteMapping(path = "{id}")
    public int deletePersonById(@PathVariable("id") UUID id) {
        return personService.deletePersonById(id);
    }

    @PutMapping(path = "{id}")
    public  void updatePersonById(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Person person) {
         personService.updatePersonById(id, person);
    }





}
