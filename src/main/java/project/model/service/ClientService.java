package project.model.service;

import project.model.entity.Client;

import java.util.Optional;

public interface ClientService {

    Optional<Client> getById(int id);

    boolean insert(Client client);

}
