package com.iftm.exercicio02.services;

import com.iftm.exercicio02.data.vo.UserVO;
import com.iftm.exercicio02.mapper.DozerMapper;
import com.iftm.exercicio02.models.User;
import com.iftm.exercicio02.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserVO> findAll() {
        var userDBList = repository.findAll();
        return DozerMapper.parseListObject(userDBList, UserVO.class);
    }

    public UserVO findById(Long id) {
        var userDB = repository.findById(id);
        return userDB.map(user -> DozerMapper.parseObject(user, UserVO.class)).orElse(null);
    }

    public UserVO save(UserVO userVO) {
        if (verifyUser(userVO)) {
            User user = DozerMapper.parseObject(userVO, User.class);
            var userDB = repository.save(user);
            return DozerMapper.parseObject(userDB, UserVO.class);
        }
        return null;
    }

    public UserVO update(UserVO userVO) {
        var dbUser = repository.findById(userVO.getId());
        if (dbUser.isPresent() && verifyUser(userVO)) {
            var user = DozerMapper.parseObject(userVO, User.class);
            user = repository.save(user);
            return DozerMapper.parseObject(user, UserVO.class);
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
        if (!userVO.getFirstName().isBlank() && !userVO.getFirstName().isEmpty() &&
                !userVO.getLastName().isBlank() && !userVO.getLastName().isEmpty()) {
            return true;
        }
        return false;
    }
}
