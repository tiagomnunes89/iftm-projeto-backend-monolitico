package com.iftm.firststructure.services;

import com.iftm.firststructure.models.Person;
import com.iftm.firststructure.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    //Injeção de dependência
    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Optional<Person> findById(Long id) {
        //personRepository.findById(id).orElseThrow(()-> new RuntimeException("Person not found"));
        return personRepository.findById(id);
    }

    public Person save(Person person) {
        if (!person.getFirstName().isBlank() && !person.getFirstName().isEmpty()) {
            return personRepository.save(person);
        }
        return null;
    }

    public Person update(Person person) {
        var dbPerson = personRepository.findById(person.getId());
        if (dbPerson.isPresent()) {
            return personRepository.save(person);
        }
        return null;
    }

    public String delete(Long id) {
        var dbPerson = personRepository.findById(id);
        //personRepository.findById(id).ifPresent(personRepository::delete);
        if (dbPerson.isPresent()) {
            personRepository.deleteById(id);
        }
        return "Person id " + "id" + "has been deleted";
    }
}
