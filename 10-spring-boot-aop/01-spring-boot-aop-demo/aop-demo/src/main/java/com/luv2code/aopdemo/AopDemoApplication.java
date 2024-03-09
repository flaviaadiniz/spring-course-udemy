package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO accountDAO) {

		return runner -> demoTheBeforeAdvice(accountDAO);

	}

	private void demoTheBeforeAdvice(AccountDAO accountDAO) {
		// call the business method
		accountDAO.addAccount();

		// do it again
		System.out.println("\nLet's call it again!");

		// call the business method again
		accountDAO.addAccount();

	}

}
