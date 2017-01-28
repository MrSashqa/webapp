package project.model.dao;

import java.util.Optional;

import project.model.entity.User;

public interface UserDAO {


    Optional<User> getByLogin(String login);

    void insert(User user);
}
