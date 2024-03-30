package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGettersSetters()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        System.out.println("====> Executing @Before advice on method");

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        // get args
        Object[] objects = joinPoint.getArgs();

        // loop through the args
        for (Object object : objects) {
            System.out.println(object);

            if (object instanceof Account) {
                //downcast and print Account specific stuff
                Account account = (Account) object;

                System.out.println("Account name: " + account.getName());
                System.out.println("Account level: " + account.getLevel());
            }
        }
    }

    // add a new advice for @AfterReturning on the findAccounts method
    @AfterReturning(
            pointcut = "execution (* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n====> Executing @AfterReturning on method: " + method);

        // print out the results of the method call
        System.out.println("\n====> result is: " + result);

        // let's post-process the data ... convert the account names to uppercase
        convertAccountNamesToUpperCase(result);
        System.out.println("====> result is: " + result);

    }

    @AfterThrowing(
            pointcut = "execution (* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theException")
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable theException) {

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n====> Executing @AfterThrowing on method: " + method);

        // log the exception
        System.out.println("\n====> The exception is: " + theException);

    }

    @After("execution (* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountAdvice(JoinPoint joinPoint) {

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n====> Executing @After (finally) on method: " + method);

    }

    @Around("execution (* com.luv2code.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        // print out method we are advising on
        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n====> Executing @Around on method: " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // execute the method
        Object result = null;

        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception exception) {
            // log the exception
            System.out.println(exception.getMessage());

            // rethrow the exception
            throw exception;
        }

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute duration and display it
        long duration = end - begin;
        System.out.println("\n====> Duration: " + duration / 1000.0 + " seconds");

        return result;
    }



    private void convertAccountNamesToUpperCase(List<Account> result) {

        // loop through accounts
        for (Account account : result) {

            // get uppercase version of name
            String upperName = account.getName().toUpperCase();

            // update the name on the account
            account.setName(upperName);
        }

    }

}
