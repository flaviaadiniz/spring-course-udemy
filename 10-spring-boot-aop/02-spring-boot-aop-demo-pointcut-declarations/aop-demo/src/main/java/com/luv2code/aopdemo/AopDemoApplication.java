package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO accountDAO, MembershipDAO membershipDAO) {

		return runner -> demoTheAfterThrowingAdcie(accountDAO);
		//demoTheBeforeAdvice(accountDAO, membershipDAO);
		//demoTheAfterReturningAdvice(accountDAO);

	}

	private void demoTheAfterThrowingAdcie(AccountDAO accountDAO) {

		// call method to find the accounts
		List<Account> accounts = null;

		try {
			// add a boolean flag to simulate excepetions
			boolean tripWire = true;
			accounts = accountDAO.findAccounts(tripWire);
		} catch (Exception exception) {
			System.out.println("\nMain Program: ... caught exception " + exception);
		}

		// display the accounts
		System.out.println("\nMain Program: demoTheAfterThrowingAdvice");
		System.out.println("----");
		System.out.println(accounts);

		System.out.println("\n");

	}

	private void demoTheAfterReturningAdvice(AccountDAO accountDAO) {
		// call method to find the accounts
		List<Account> accounts = accountDAO.findAccounts();

		// display the accounts
		System.out.println("\nMain Program: demoTheAfterReturningAdvice");
		System.out.println("----");
		System.out.println(accounts);

		System.out.println("\n");
	}

	private void demoTheBeforeAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {
		// call the business method
		Account account = new Account();
		account.setName("Madhu");
		account.setLevel("Platinum");
		accountDAO.addAccount(account, true);

		accountDAO.doWork();

		// call the accountdao getters/setters methods
		accountDAO.setName("foobar");
		accountDAO.getName();
		accountDAO.setServiceCode("silver");
		accountDAO.getServiceCode();

		// call the membership business method
		membershipDAO.addMember();
		membershipDAO.goToSleep();
	}

}
