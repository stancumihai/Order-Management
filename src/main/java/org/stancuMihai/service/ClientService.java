package org.stancuMihai.service;

import org.stancuMihai.dao.AbstractDao;
import org.stancuMihai.model.Client;

import java.sql.SQLException;
import java.util.List;

public class ClientService {

    public static AbstractDao<Client> clientDataAccessService;
    public static ClientService clientService = null;

    private ClientService() {
        ClientService.clientDataAccessService = new AbstractDao<>(Client.class);
    }

    public static ClientService getInstance() {
        if (clientService == null) {
            clientService = new ClientService();
        }
        return clientService;
    }

    public Client findById(Integer id) throws SQLException {
        return clientDataAccessService.findById(id);
    }

    public Client update(Integer id, Client model) throws SQLException {
        return clientDataAccessService.update(id, model);
    }

    public void create(Client model) throws SQLException {
        clientDataAccessService.create(model);
    }

    public Client delete(Integer id) throws SQLException {
        return clientDataAccessService.delete(id);
    }

    public List<Client> selectAll() throws SQLException {
        return clientDataAccessService.selectAll();
    }
}
