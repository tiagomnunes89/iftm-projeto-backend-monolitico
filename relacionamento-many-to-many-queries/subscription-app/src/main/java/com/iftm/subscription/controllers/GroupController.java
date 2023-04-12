package com.iftm.subscription.controllers;

import com.iftm.subscription.data.vo.GroupVO;
import com.iftm.subscription.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/group")
public class GroupController {
    @Autowired
    GroupService service;

    // READ - HTTP GET
    // Endpoint: http://localhost:8080/api/v1/group
    @GetMapping
    public List<GroupVO> findAll() {
        return service.findAll();
    }

    // READ - HTTP GET
    // Endpoint: http://localhost:8080/api/v1/group/ID
    @GetMapping("/{id}")
    public GroupVO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    // CREATE - HTTP POST
    // Endpoint: http://localhost:8080/api/v1/group
    @PostMapping
    public GroupVO save(@RequestBody GroupVO groupVO) {
        return service.save(groupVO);
    }

    //    // INSERT USERS - HTTP POST
    //// Endpoint: http://localhost:8080/api/v1/group/insert-users
        @PostMapping("insert-users")
        public GroupVO insertUsers(@RequestBody GroupVO groupVO) {
            return service.insertUsers(groupVO);
        }

    // UPDATE - HTTP PUT
    // Endpoint: http://localhost:8080/api/v1/group
    @PutMapping
    public GroupVO update(@RequestBody GroupVO groupVO) {
        return service.update(groupVO);
    }

    // DELETE - HTTP DELETE
     // Endpoint: http://localhost:8080/api/v1/group/ID
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }
}