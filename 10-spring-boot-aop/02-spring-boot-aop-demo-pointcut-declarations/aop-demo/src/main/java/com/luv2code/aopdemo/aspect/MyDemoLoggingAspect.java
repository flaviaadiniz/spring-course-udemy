package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {


    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
    private void forDaoPackage() {
    }

    // create a pointcut for getter methods
    @Pointcut("execution(* com.luv2code.aopdemo..*.get*(..))")
    private void getter() {

    }

    // create a pointcut for setter methods
    @Pointcut("execution(* com.luv2code.aopdemo..*.set*(..))")
    private void setter() {

    }

    // create pointcut: include package ... exclude getter/setter methods
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    private void forDaoPackageNoGettersSetters() {

    }

    @Before("forDaoPackageNoGettersSetters()")
    public void beforeAddAccountAdvice() {
        System.out.println("====> Executing @Before advice on method");
    }

    @Before("forDaoPackageNoGettersSetters()")
    public void performApiAnalytics() {
        System.out.println("====> Performing API analytics");
    }


}
