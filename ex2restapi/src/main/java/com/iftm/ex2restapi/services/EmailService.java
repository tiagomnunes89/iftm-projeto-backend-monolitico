package com.iftm.ex2restapi.services;

import com.iftm.ex2restapi.models.Email;
import com.iftm.ex2restapi.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    public List<Email> findAll() {
        return emailRepository.findAll();
    }

    public Optional<Email> findById(Long id) {
        return Optional.ofNullable(emailRepository.findById(id).orElseThrow(() -> new RuntimeException("Email not found")));
    }

    public Email save(Email email) {
        return emailRepository.save(email);
    }

    public Email update(Email email) {
        var dbEmail = emailRepository.findById(email.getId());
        if (dbEmail.isPresent()) {
            return emailRepository.save(email);
        }
        return null;
    }

    public String delete(Long id) {
        var dbEmail = emailRepository.findById(id);
        if (dbEmail.isPresent()) {
            emailRepository.deleteById(id);
            return "Email id " + id + " has been deleted";
        } else {
            return "Email id " + id + " has NOT been deleted";
        }
    }
}
