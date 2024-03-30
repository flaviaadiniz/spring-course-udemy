package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;
import com.luv2code.aopdemo.service.TrafficFortuneService;
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
	public CommandLineRunner commandLineRunner(AccountDAO accountDAO,
											   MembershipDAO membershipDAO,
											   TrafficFortuneService trafficFortuneService) {

		return runner -> demoTheAroundAdviceRethrowException(trafficFortuneService);
		//demoTheBeforeAdvice(accountDAO, membershipDAO);
		//demoTheAfterReturningAdvice(accountDAO);
		//demoTheAfterThrowingAdvice(accountDAO);
		//demoTheAfterAdvice(accountDAO);
		//demoTheAroundAdvice(trafficFortuneService);
		//demoTheAroundAdviceHandleException(trafficFortuneService);
	}

	private void demoTheAroundAdviceRethrowException(TrafficFortuneService trafficFortuneService) {

		System.out.println("\nMain Program: demoTheAroundAdviceRethrowException");

		System.out.println("Calling getFortune()");

		boolean tripWire = true;
		String data = trafficFortuneService.getFortune(tripWire);

		System.out.println("\nMy fortune is: " + data);

		System.out.println("Finished");

	}

	private void demoTheAroundAdviceHandleException(TrafficFortuneService trafficFortuneService) {

		System.out.println("\nMain Program: demoTheAroundAdviceHandleException");

		System.out.println("Calling getFortune()");

		boolean tripWire = true;
		String data = trafficFortuneService.getFortune(tripWire);

		System.out.println("\nMy fortune is: " + data);

		System.out.println("Finished");

	}

	private void demoTheAroundAdvice(TrafficFortuneService trafficFortuneService) {

		System.out.println("\nMain Program: demoTheAroundAdvice");

		System.out.println("Calling getFortune()");

		String data = trafficFortuneService.getFortune();

		System.out.println("\nMy fortune is: " + data);

		System.out.println("Finished");

	}

	private void demoTheAfterAdvice(AccountDAO accountDAO) {

		// call method to find the accounts
		List<Account> accounts = null;

		try {
			// add a boolean flag to simulate exceptions
			boolean tripWire = false;
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

	private void demoTheAfterThrowingAdcie(AccountDAO accountDAO) {

		// call method to find the accounts
		List<Account> accounts = null;

		try {
			// add a boolean flag to simulate exceptions
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
