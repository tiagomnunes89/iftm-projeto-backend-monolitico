package com.iftm.exercicio03.services;

import com.iftm.exercicio03.controllers.UserController;
import com.iftm.exercicio03.data.vo.UserVO;
import com.iftm.exercicio03.mapper.DozerMapper;
import com.iftm.exercicio03.models.User;
import com.iftm.exercicio03.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<UserVO> findAll() {
        var userDbList = repository.findAll();
        var users = DozerMapper.parseListObject(userDbList, UserVO.class);
        users.forEach(user -> user.add(linkTo(methodOn(UserController.class).findById(user.getId()))
                .withSelfRel()
        ));
        return users;
    }

    public UserVO findById(Long id) {
        Optional<User> userDb = repository.findById(id);
        if (userDb.isPresent()) {
            var user = DozerMapper.parseObject(userDb.get(), UserVO.class);
            user.add(linkTo(methodOn(UserController.class).findById(id)).withSelfRel());
            return user;
        }
        return null;
    }

    public List<UserVO> findUsersByGroupName(String groupName) {
        List<User> userDbList = repository.findUsersByGroupName(groupName);
        var users = DozerMapper.parseListObject(userDbList, UserVO.class);
        users.forEach(user -> user.add(linkTo(methodOn(UserController.class).findById(user.getId()))
                .withSelfRel()
        ));
        return users;
    }

    public List<UserVO> findUsersByName(String name) {
        List<User> userDbList = repository.findUsersByName(name);
        var users = DozerMapper.parseListObject(userDbList, UserVO.class);
        users.forEach(user -> user.add(linkTo(methodOn(UserController.class).findById(user.getId()))
                .withSelfRel()
        ));
        return users;
    }

    public List<UserVO> findUsersByPhone(String phone) {
        List<User> userDbList = repository.findUsersByPhone(phone);
        var users = DozerMapper.parseListObject(userDbList, UserVO.class);
        users.forEach(user -> user.add(linkTo(methodOn(UserController.class).findById(user.getId()))
                .withSelfRel()
        ));
        return users;
    }

    public List<UserVO> findUsersByGroupCode(String groupCode) {
        List<User> userDbList = repository.findUsersByGroupCode(groupCode);
        var users = DozerMapper.parseListObject(userDbList, UserVO.class);
        users.forEach(user -> user.add(linkTo(methodOn(UserController.class).findById(user.getId()))
                .withSelfRel()
        ));
        return users;
    }

    public UserVO save(UserVO userVO) {
        if (verifyUser(userVO)) {
            User user = DozerMapper.parseObject(userVO, User.class);
            var userDb = repository.save(user);
            userVO = DozerMapper.parseObject(userDb, UserVO.class);
            userVO.add(linkTo(methodOn(UserController.class)
                    .findById(userVO.getId())).withSelfRel());
            return userVO;
        }
        return null;
    }

    public UserVO update(UserVO userVO) {
        Optional<User> dbUser = repository.findById(userVO.getId());
        if (dbUser.isPresent() && verifyUser(userVO)) {
            User user = DozerMapper.parseObject(userVO, User.class);
            user = repository.save(user);
            userVO = DozerMapper.parseObject(user, UserVO.class);
            userVO.add(linkTo(methodOn(UserController.class)
                    .findById(userVO.getId())).withSelfRel());
            return userVO;
        }
        return null;
    }

    public String delete(Long id) {
        var dbUser = repository.findById(id);
        if (dbUser.isPresent()) {
            repository.deleteById(id);

            return "User with id " + id + " has been deleted!";
        } else
            return "ID " + id + " not found!";
    }

    private boolean verifyUser(UserVO userVO) {
        return !userVO.getFirstName().isBlank() && !userVO.getFirstName().isEmpty() &&
                !userVO.getLastName().isBlank() && !userVO.getLastName().isEmpty();
    }
}