package com.iftm.subscription.services;

import com.iftm.subscription.data.vo.UserVO;
import com.iftm.subscription.mapper.DozerMapper;
import com.iftm.subscription.models.User;
import com.iftm.subscription.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserVO> findAll() {
        var userDbList = repository.findAll();
        return DozerMapper.parseListObject(userDbList, UserVO.class);
    }

    public UserVO findById(Long id) {
        Optional<User> userDb = repository.findById(id);
        if(userDb.isPresent())
            return DozerMapper.parseObject(userDb.get(), UserVO.class);
        return null;
    }

    public List<UserVO> findByGroupName(String groupName) {
        List<User> users = repository.findUsersByGroupName(groupName);
        return DozerMapper.parseListObject(users, UserVO.class);
    }

    public UserVO save(UserVO userVO) {
        if(verifyUser(userVO)) {
            User user = DozerMapper.parseObject(userVO, User.class);
            var userDb = repository.save(user);
            return DozerMapper.parseObject(userDb, UserVO.class);
        }
        return null;
    }

    public UserVO update(UserVO userVO) {
        Optional<User> dbUser = repository.findById(userVO.getId());
        if(dbUser.isPresent() && verifyUser(userVO))
        {
            User user = DozerMapper.parseObject(userVO, User.class);
            user = repository.save(user);
            return DozerMapper.parseObject(user, UserVO.class);
        }
        return null;
    }

    public String delete(Long id) {
        var dbUser = repository.findById(id);
        if(dbUser.isPresent()) {
            repository.deleteById(id);
            return "User with id " + id + " has been deleted!";
        }else
            return "ID " + id + " not found!";
    }

    private boolean verifyUser(UserVO userVO) {
        if( !userVO.getFirstName().isBlank() && !userVO.getFirstName().isEmpty() &&
            !userVO.getLastName().isBlank() && !userVO.getLastName().isEmpty()) {
            return true;
        }
        return false;
    }
}
