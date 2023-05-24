package com.iftm.exercicio03.services;

import com.iftm.exercicio03.controllers.EmailController;
import com.iftm.exercicio03.data.vo.EmailVO;
import com.iftm.exercicio03.mapper.DozerMapper;
import com.iftm.exercicio03.models.Email;
import com.iftm.exercicio03.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class EmailService {

    @Autowired
    private EmailRepository repository;

    public List<EmailVO> findAll() {
        var emailDbList = repository.findAll();
        var emails = DozerMapper.parseListObject(emailDbList, EmailVO.class);
        emails.forEach(email -> email.add(linkTo(methodOn(EmailController.class).findById(email.getId()))
                .withSelfRel()
        ));
        return emails;
    }

    public EmailVO findById(Long id) {
        Optional<Email> emailDb = repository.findById(id);
        if (emailDb.isPresent()) {
            var email = DozerMapper.parseObject(emailDb.get(), EmailVO.class);
            email.add(linkTo(methodOn(EmailController.class).findById(id)).withSelfRel());
            return email;
        }
        return null;
    }

    public EmailVO save(EmailVO emailVO) {
        if (verifyEmail(emailVO)) {
            var email = repository.save(DozerMapper.parseObject(emailVO, Email.class));
            emailVO = DozerMapper.parseObject(email, EmailVO.class);
            emailVO.add(linkTo(methodOn(EmailController.class).findById(emailVO.getId())).withSelfRel());
            return emailVO;
        }
        return null;
    }

    public EmailVO update(EmailVO emailVO) {
        var dbEmail = repository.findById(emailVO.getId());
        if (dbEmail.isPresent() && verifyEmail(emailVO)) {
            var email = repository.save(DozerMapper.parseObject(emailVO, Email.class));
            emailVO = DozerMapper.parseObject(email, EmailVO.class);
            emailVO.add(linkTo(methodOn(EmailController.class).findById(emailVO.getId())).withSelfRel());
            return emailVO;
        }
        return null;
    }

    public String delete(Long id) {
        var dbEmail = repository.findById(id);
        if (dbEmail.isPresent()) {
            repository.deleteById(id);
            return "Email with id " + id + " has been deleted!";
        }
        return "ID " + id + " not found!";
    }

    private boolean verifyEmail(EmailVO emailVO) {
        if (!emailVO.getFrom().isBlank() && !emailVO.getFrom().isEmpty() &&
                !emailVO.getTo().isBlank() && !emailVO.getTo().isEmpty() &&
                !emailVO.getSubject().isBlank() && !emailVO.getSubject().isEmpty() &&
                !emailVO.getBody().isBlank() && !emailVO.getBody().isEmpty()) {
            return true;
        }
        return false;
    }
}
