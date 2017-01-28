package project.model.dao;

import java.util.Optional;

import project.model.entity.Client;

public interface ClientDAO {

    Optional<Client> getById(int id);

    void insert(Client client);

}
