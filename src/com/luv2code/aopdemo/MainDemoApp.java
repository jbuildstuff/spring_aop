package com.luv2code.aopdemo;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.Account;
import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {
		// read spring config java class
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the bean from spring container
		
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		// get membership bean from spring container
		
		MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);	
	
		
		Account myAccount = new Account();
		myAccount.setName("Brandon");
		myAccount.setLevel("Platinum");
		theAccountDAO.addAccount(myAccount, true);
		theAccountDAO.doWork();
		
		//call the accountdao getter/setter methods
		
		theAccountDAO.setName("foobar");
		theAccountDAO.setServiceCode("silver");
		
		String name = theAccountDAO.getName();
		String code = theAccountDAO.getServiceCode();
		
//		 call the business method

		System.out.println("Let's do it again!\n");
		
		
		theMembershipDAO.addSillyMember();
		theMembershipDAO.goToSleep();
		// close the context
		
		context.close();
	}

}
