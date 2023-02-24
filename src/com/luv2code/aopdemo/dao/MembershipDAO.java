package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {
	
	public void goToSleep() {
		System.out.println(getClass() + ": I'm going to slee now...");
	}	

	public void addSillyMember() {
		System.out.println("Adding Silly");
		
	}
	
	

}
