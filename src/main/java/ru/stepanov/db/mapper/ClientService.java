package ru.stepanov.db.mapper;

import org.apache.ibatis.session.SqlSession;
import ru.stepanov.db.domain.Client;

import java.util.List;

public class ClientService {

    public List<Client> getAll() {

        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            ClientMapper clientMapper = sqlSession.getMapper(ClientMapper.class);
            return clientMapper.getAll();
        } finally {
            sqlSession.close();
        }
    }

    public void deleteById(int id) {

        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

        try {
            ClientMapper clientMapper = sqlSession.getMapper(ClientMapper.class);
            clientMapper.deleteById(id);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    public void insertClient(Client client) {

        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

        try {
            ClientMapper clientMapper = sqlSession.getMapper(ClientMapper.class);
            clientMapper.insertClient(client);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    public void updateById(Client client) {

        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

        try {
            ClientMapper clientMapper = sqlSession.getMapper(ClientMapper.class);
            clientMapper.updateById(client);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

}
