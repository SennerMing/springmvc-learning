package club.musician.service.impl;

import club.musician.dao.UserDao;
import club.musician.entity.User;
import club.musician.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;


    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public User getUser(Integer id) {
        return userDao.getUser(id);
    }
}
