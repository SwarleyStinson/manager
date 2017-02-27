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


}
