package com.luv2code.springboot.thymeleafdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.mapping.Join;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {

    // set up logger
    private Logger logger = Logger.getLogger(getClass().getName());


    // set up pointcut declarations
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    private void forControllerPackage() {
    }

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    private void forServicePackage() {
    }

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    private void forDaoPackage() {
    }

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {
    }


    // add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {

        // display method we are calling
        String theMethod = joinPoint.getSignature().toShortString();
        logger.info("===> in @Before: calling method: " + theMethod);

        // get the arguments
        Object[] args = joinPoint.getArgs();

        // loop through and display the arguments
        for (Object tempArg : args) {
            logger.info("===> argument: " + tempArg);
        }

    }


    // add @AfterReturning advice
    @AfterReturning(pointcut = "forAppFlow()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {

        // display method we are returning from
        String theMethod = joinPoint.getSignature().toShortString();
        logger.info("===> in @AfterReturning: from method: " + theMethod);

        // display data returned
        logger.info("===> result: " + result);

    }


}
