package ru.stepanov.mapper;

import ru.stepanov.entity.Client;

import java.util.List;

public interface ClientMapper {

    public List<Client> getAll();

    public void insertClient(Client client);

    public void deleteByID(int id);

    public void updateById(Client client, int id);
}
