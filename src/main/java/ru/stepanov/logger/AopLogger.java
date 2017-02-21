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

    @Before("execution(* ru.stepanov.dao.ClientDAO.addClient(..))")
    public void logBeforeAddToDB(JoinPoint joinPoint) {
        System.out.println("------: " + joinPoint.getSignature().getName());
        logger.debug("New Client add to database: {}", new Date().toString());
    }

    @Before("execution(* ru.stepanov.dao.ClientDAO.deleteClientByID(..))")
    public void logBeforeDeleteToDB(JoinPoint joinPoint) {
        System.out.println("------: " + joinPoint.getSignature().getName());
        logger.debug("Client was delete from database: {}", new Date().toString());
    }

    @AfterThrowing(
            pointcut = "execution(* ru.stepanov.dao.ClientDAO.setClientByID(..))",
            throwing= "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {

        System.out.println("logAfterThrowing() is running!");
        System.out.println("Problem place : " + joinPoint.getSignature().getName());
        System.out.println("Exception : " + error);
        System.out.println("******");
    }
}
