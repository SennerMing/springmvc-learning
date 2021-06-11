package club.musician.service;

import club.musician.entity.User;

public interface UserService {

    int addUser(User user);

    User getUser(Integer id);

}
