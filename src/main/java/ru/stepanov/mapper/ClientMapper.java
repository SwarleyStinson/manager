package ru.stepanov.mapper;

import org.apache.ibatis.session.SqlSession;
import ru.stepanov.entity.Client;

import java.util.List;

public class ClientMapper {
    public List<Client> getAll() {

        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            ClientMapper clientMapper = sqlSession.getMapper(ClientMapper.class);
            return clientMapper.getAll();
        } finally {
            sqlSession.close();
        }
    }
}
