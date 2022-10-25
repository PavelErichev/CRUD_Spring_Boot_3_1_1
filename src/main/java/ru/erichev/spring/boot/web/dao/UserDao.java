package ru.erichev.spring.boot.web.dao;


import ru.erichev.spring.boot.web.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    public void saveUser(User user);

    public User getUser(long id);

    public void deleteUser(long id);

    public void updateUser(User user);
}