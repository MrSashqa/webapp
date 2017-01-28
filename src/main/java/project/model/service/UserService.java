package project.model.service;


import project.model.entity.User;

import java.util.Optional;

public interface UserService {

    boolean authenticate(User user) ;

    Optional<User> getByLogin(String login) ;

    boolean insert(User user);


}
