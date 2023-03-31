package com.iftm.ex2restapi.controllers;

import com.iftm.ex2restapi.models.User;
import com.iftm.ex2restapi.services.UserService;
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
public class UserController {

    //Injeção de dependência
    @Autowired
    private UserService userService;

    //READ - HTTP GET
    //http://localhost:8080/api/v1/users
    @GetMapping("users")
    public List<User> findAll() {
        return userService.findAll();
    }

    //READ - HTTP GET
    //http://localhost:8080/api/v1/user/ID
    @GetMapping("user/{id}")
    public Optional<User> findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    //CREATE - HTTP POST
    //http://localhost:8080/api/v1/user
    //JSON do user no Body
    @PostMapping("user")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    //UPDATE - HTTP PUT
    //http://localhost:8080/api/v1/user
    @PutMapping("user")
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

    //DELETE - HTTP DELETE
    //http://localhost:8080/api/v1/user/ID
    @DeleteMapping("user/{id}")
    public String delete(@PathVariable("id") Long id) {
        return userService.delete(id);
    }
}
