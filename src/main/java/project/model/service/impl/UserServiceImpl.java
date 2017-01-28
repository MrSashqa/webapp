package project.model.service.impl;

import project.model.dao.factory.DAOFactory;
import project.model.dao.UserDAO;
import project.model.dao.connection.ConnectionFactory;
import project.model.dao.connection.ConnectionWrapper;
import project.model.entity.User;
import project.model.service.UserService;
import project.model.service.security.PasswordHasher;

import java.util.Optional;


public class UserServiceImpl implements UserService {
    private ConnectionFactory connectionFactory;
    private DAOFactory daoFactory;


    public UserServiceImpl(ConnectionFactory connectionFactory, DAOFactory daoFactory) {
        this.connectionFactory = connectionFactory;
        this.daoFactory = daoFactory;
    }


    @Override
    public boolean insert(User user) {
        try (ConnectionWrapper connection = connectionFactory.getConnection()) {
            UserDAO userDAO = daoFactory.getUserDAO(connection);
            connection.beginTransaction();
            Optional<User> userOptional = userDAO.getByLogin(user.getLogin());
            if (!userOptional.isPresent()) {
                user.setPassword(PasswordHasher.hash(user.getPassword()));
                userDAO.insert(user);
                connection.commit();
                return true;
            }
            return false;
        }
    }

    public Optional<User> getByLogin(String login) {
        Optional<User> userOptional;
        try (ConnectionWrapper connectionWrapper = connectionFactory.getConnection()) {
            UserDAO userDAO = daoFactory.getUserDAO(connectionWrapper);
            userOptional = userDAO.getByLogin(login);
        }
        return userOptional;
    }


    public boolean authenticate(User user) {
        String login = user.getLogin();
        String password = PasswordHasher.hash(user.getPassword());
        Optional<User> userOptional = getByLogin(login);
        return userOptional.isPresent() && password.equals(userOptional.get().getPassword());
    }


}
