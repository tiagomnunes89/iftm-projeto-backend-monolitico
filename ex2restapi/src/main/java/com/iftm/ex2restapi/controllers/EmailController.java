package com.iftm.ex2restapi.controllers;

import com.iftm.ex2restapi.models.Email;
import com.iftm.ex2restapi.services.EmailService;
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
@RequestMapping("/api/v1/")
public class EmailController {

    //Injeção de dependência
    @Autowired
    private EmailService emailService;

    //READ - HTTP GET
    //http://localhost:8080/api/v1/emails
    @GetMapping("emails")
    public List<Email> findAll() {
        return emailService.findAll();
    }

    //READ - HTTP GET
    //http://localhost:8080/api/v1/email/ID
    @GetMapping("email/{id}")
    public Optional<Email> findById(@PathVariable Long id) {
        return emailService.findById(id);
    }

    //CREATE - HTTP POST
    //http://localhost:8080/api/v1/email
    //JSON do email no Body
    @PostMapping("email")
    public Email save(@RequestBody Email email) {
        return emailService.save(email);
    }

    //UPDATE - HTTP PUT
    //http://localhost:8080/api/v1/email
    @PutMapping("email")
    public Email update(@RequestBody Email email) {
        return emailService.update(email);
    }

    //DELETE - HTTP DELETE
    //http://localhost:8080/api/v1/email/ID
    @DeleteMapping("email/{id}")
    public String delete(@PathVariable("id") Long id) {
        return emailService.delete(id);
    }
}
