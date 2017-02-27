package ru.stepanov.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

@Aspect
public class AopLogger {
    private static final Logger logger = LoggerFactory.getLogger(Logger.class);

    @Before("execution(* ru.stepanov.db.dao.ClientDAO.addClient(..))")
    public void logBeforeAddToDB(JoinPoint joinPoint) {
        logger.info("New Client add to database: {}", new Date().toString());
    }

    @Before("execution(* ru.stepanov.db.dao.ClientDAO.deleteClientByID(..))")
    public void logBeforeDeleteToDB(JoinPoint joinPoint) {
        logger.info("Client was delete from database: {}", new Date().toString());
    }

    @AfterThrowing(
            pointcut = "execution(* ru.stepanov.db.dao.ClientDAO.updateClient(..))",
            throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        logger.debug("");
    }
}
