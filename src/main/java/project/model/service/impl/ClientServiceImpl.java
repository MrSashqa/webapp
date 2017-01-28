package project.model.service.impl;

import project.model.dao.ClientDAO;
import project.model.dao.factory.DAOFactory;
import project.model.dao.connection.ConnectionFactory;
import project.model.dao.connection.ConnectionWrapper;
import project.model.entity.Client;
import project.model.service.ClientService;

import java.util.Optional;

public class ClientServiceImpl implements ClientService {
    private ConnectionFactory connectionFactory;
    private DAOFactory daoFactory;

    public ClientServiceImpl(ConnectionFactory connectionFactory, DAOFactory daoFactory) {
        this.connectionFactory = connectionFactory;
        this.daoFactory = daoFactory;
    }

    @Override
    public Optional<Client> getById(int id) {
        Optional<Client> clientOptional;
        try (ConnectionWrapper connectionWrapper = connectionFactory.getConnection()) {
            ClientDAO clientDAO = daoFactory.getClientDAO(connectionWrapper);
            clientOptional = clientDAO.getById(id);
        }
        return clientOptional;
    }

    @Override
    public boolean insert(Client client) {
        try (ConnectionWrapper connection = connectionFactory.getConnection()) {
            connection.beginTransaction();
            ClientDAO clientDAO = daoFactory.getClientDAO(connection);
            Optional<Client> clientOptional = clientDAO.getById(client.getId());
            if (!clientOptional.isPresent()) {
                clientDAO.insert(client);
                connection.commit();
                return true;
            }
            return false;
        }
    }
}
