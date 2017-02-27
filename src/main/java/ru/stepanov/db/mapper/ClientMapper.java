package ru.stepanov.db.mapper;

import ru.stepanov.db.domain.Client;

import java.util.List;

public interface ClientMapper {

    public List<Client> getAll();

    public void insertClient(Client client);

    public void deleteByID(Integer id);

    public void updateById(Client client, int id);
}
