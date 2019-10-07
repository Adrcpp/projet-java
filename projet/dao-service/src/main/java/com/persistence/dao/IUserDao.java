package com.persistence.dao;

import com.persistence.dao.entities.User;
import java.util.List;

public interface IUserDao {

    public List<User> findAllUsers();

    public User findUserById(int idUser);

    public User findByUsername(String username);

    public User createUser(User user);

    public User updateUser(User user);

    public boolean deleteUser(User user);
}
