package ru.stepanov.db.mapper;

import ru.stepanov.db.domain.Client;

import java.util.List;

public interface ClientMapper {

    public List<Client> getAll();

    public void deleteById(int id);

    public void insertClient(Client client);

    public void updateById(Client client);
}
