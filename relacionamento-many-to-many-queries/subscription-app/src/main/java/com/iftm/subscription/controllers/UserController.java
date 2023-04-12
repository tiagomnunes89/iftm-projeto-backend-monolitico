package com.iftm.subscription.controllers;

import com.iftm.subscription.data.vo.UserVO;
import com.iftm.subscription.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService service;

    // READ - HTTP GET
    // Endpoint: http://localhost:8080/api/v1/user
    @GetMapping
    public List<UserVO> findAll() {
        return service.findAll();
    }

    // READ - HTTP GET
    // Endpoint: http://localhost:8080/api/v1/user/ID
    @GetMapping("/{id}")
    public UserVO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    // USERS BY GROUP NAME - HTTP GET
    // Endpoint: http://localhost:8080/api/v1/user/group/NOME_DO_GRUPO
    @GetMapping("group/{name}")
    public List<UserVO> findUsersByGroupName(@PathVariable("name") String groupName) {
        return service.findByGroupName(groupName);
    }

    // CREATE - HTTP POST
    // Endpoint: http://localhost:8080/api/v1/user
    @PostMapping
    public UserVO save(@RequestBody UserVO userVO) {
        return service.save(userVO);
    }

    // UPDATE - HTTP PUT
    // Endpoint: http://localhost:8080/api/v1/user
    @PutMapping
    public UserVO update(@RequestBody UserVO userVO) {
        return service.update(userVO);
    }

    // DELETE - HTTP DELETE
    // Endpoint: http://localhost:8080/api/v1/user/ID
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }
}
