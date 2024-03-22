package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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
        System.out.println("====> Executing @AfterReturning on method: " + method);

        // print out the reuslts of the method call
        System.out.println("====> result is: " + result);

    }

}
