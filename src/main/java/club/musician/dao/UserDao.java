package club.musician.dao;

import club.musician.entity.User;

public interface UserDao {

    User getUser(Integer id);

    int addUser(User user);
}
