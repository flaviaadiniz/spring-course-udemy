package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    // this is where we add all of our related advices for logging
    // let's start with an @Before advice

    @Bean("execution(public void addAcount())")
    public void beforeAddAccountAdvice() {

        System.out.println("\n ====> Executing @Before advice on method addAccount()");

    }

}
