package com.iftm.firststructure.controllers;

import com.iftm.firststructure.models.Person;
import com.iftm.firststructure.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    //Injeção de dependência
    @Autowired
    private PersonService personService;

    //READ - HTTP GET
    //http://localhost:8080/api/persons
    @GetMapping("persons")
    public List<Person> findAll() {
        return personService.findAll();
    }

    //READ - HTTP GET
    //http://localhost:8080/api/person/ID
    @GetMapping("person/{id}")
    public Optional<Person> findById(@PathVariable Long id) {
        return personService.findById(id);
    }

    //CREATE - HTTP POST
    //http://localhost:8080/api/person
    //JSON do person no Body
    @PostMapping("person")
    public Person save(@RequestBody Person person) {
        return personService.save(person);
    }

    //UPDATE - HTTP PUT
    //http://localhost:8080/api/person
    @PutMapping("person")
    public Person update(@RequestBody Person person) {
        return personService.update(person);
    }

    //DELETE - HTTP DELETE
    //http://localhost:8080/api/person/ID
    @DeleteMapping("person/{id}")
    public String delete(@PathVariable("id") Long id) {
        return personService.delete(id);
    }

}
