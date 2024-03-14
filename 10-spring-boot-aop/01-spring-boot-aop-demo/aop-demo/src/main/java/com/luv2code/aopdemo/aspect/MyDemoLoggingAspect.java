package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    // this is where we add all of our related advices for logging
    // let's start with an @Before advice

    @Before("execution(* add*())") // using * as a wildcard to match any return type
    public void beforeAddAccountAdvice() {
        System.out.println("\n ====> Executing @Before advice on any method that starts with add");
    }

    @Before("execution(* add*(com.luv2code.aopdemo.Account))")
    public void beforeAddAccountAdviceMatchingParameter() {
        System.out.println("\n ====> Executing @Before advice before method with parameter of type Account");
    }

    @Before("execution(* add*(com.luv2code.aopdemo.Account, ..))") // matches on any number of arguments
    public void beforeAddAccountAdviceMatchingMultipleParameters() {
        System.out.println("\n ====> Executing @Before advice before method with any number of parameters of type Account");
    }

    @Before("execution(* com.luv2code..add*(..))") // matches on any number of arguments
    public void beforeAddAccountAdviceMatchingAnyParameters() {
        System.out.println("\n ====> Executing @Before advice before method with any parameters");
    }

}
