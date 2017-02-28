package ru.stepanov.db.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.List;

public abstract class AbstractMyBatisDao {
    @Autowired
    protected SqlSession sqlSession;

    @Resource(name = "sqlSessionBatch")
    protected SqlSession sqlSessionBatch;

    @Resource(name = "txTemplate")
    protected TransactionTemplate txTemplate;

    public String namespace = "";

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public int delete(String statement, Object parameter) {
        return sqlSession.delete(statement, parameter);
    }

    public <E> List<E> selectList(String statement) {
        return sqlSession.selectList(namespace + "." + statement);
    }

    public int insert(String statement, Object parameter) {
        return sqlSession.insert(statement, parameter);
    }

    public int update(String statement, Object parameter) {
        return sqlSession.update(statement, parameter);
    }
}
