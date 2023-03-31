package com.iftm.ex2restapi.services;

import com.iftm.ex2restapi.models.User;
import com.iftm.ex2restapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found")));
    }

    public User save(User user) {
        if (!user.getFirstName().isBlank() && !user.getFirstName().isEmpty()) {
            return userRepository.save(user);
        }
        return null;
    }

    public User update(User user) {
        var dbUser = userRepository.findById(user.getId());
        if (dbUser.isPresent()) {
            return userRepository.save(user);
        }
        return null;
    }

    public String delete(Long id) {
        var dbUser = userRepository.findById(id);
        if (dbUser.isPresent()) {
            userRepository.deleteById(id);
            return "User id " + id + " has been deleted";
        } else {
            return "User id " + id + " has NOT been deleted";
        }
    }
}
