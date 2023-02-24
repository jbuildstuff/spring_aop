package com.luv2code.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;
import com.luv2code.aopdemo.dao.AccountDAO;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	// add a new advice for @AfterReturning on the findAccounts method
	
	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		
		// print out method we are advising on
		
		String method = theProceedingJoinPoint.getSignature().toShortString();
		
		myLogger.info("\n======>>> Executing @Around (Finally) on method: " + method);
		
		// get begin timestamp
		
		long begin = System.currentTimeMillis();
		
		// now, let's execut the method
		
		Object result = null;
		
		try {
			result = theProceedingJoinPoint.proceed();
		} catch (Throwable e) {
			// log the exception
			myLogger.warning(e.getMessage());
			// give user a custom message
			
			// rethrow the exception
			
			throw e;
		}
		
		// get end timestamp
		
		long end = System.currentTimeMillis();
		
		long duration = end - begin;
		
		myLogger.info("\n=====> Duration " + duration / 1000.0 + " seconds");
		
		// compute duration and display it
		
		return result;
	}
	
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
		String method = theJoinPoint.getSignature().toShortString();
		
		myLogger.info("\n======>>> Executing @After (Finally) on method: " + method);
		// log the exception
		
		
	}
	
	@AfterThrowing(
			pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing="theExc"
			)
	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {
		// print out which method we are advising on
		
		String method = theJoinPoint.getSignature().toShortString();
		
		myLogger.info("\n======>>> Executing @AfterReturning on method: " + method);
		// log the exception
		
		
		myLogger.info("\n======>>> Executing @AfterReturning on method: " + theExc);
		
	}
	
	@AfterReturning(
			pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning="result"
			)
	public void afterReturningFindAccountAdvice(JoinPoint theJoinPoint, List<Account> result) {
		
		// print out which method we are advising on
		
		String method = theJoinPoint.getSignature().toShortString();
		
		myLogger.info("\n======>>> Executing @AfterReturning on method: " + method);
		
		
		myLogger.info("\n======>>> result is: " + result);
		
		// let's post-process the data ... let's modify it 
		
		// convert the account names to uppercase
		
		convertAccountNamesToUpperCase(result);
		
		
		myLogger.info("\n======>>> result is: " + result);
		

		// print out the result of the method call
	}
	
	
	
	private void convertAccountNamesToUpperCase(List<Account> result) {
		// loop through account
		
		for (Account tempAccount: result) {
			String theUpperName = tempAccount.getName().toUpperCase();
			
			
			tempAccount.setName(theUpperName);
			
		}
		
		// get upper case version of name
		
		// update the name on the account
		
	}



	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		myLogger.info("\n======> Executing @Before advice on addAccount() for any add method with void ret type");
		
		// display the method signature
		
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		
		// display the method arguments
		
		myLogger.info("Method: " + methodSig);
		
	
		// display method arguments
		Object student = new AccountDAO();
		if (student instanceof Object) {
			System.out.print("hello therrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
		}
		
		
		// get args
		Object[] args = theJoinPoint.getArgs();
		
		
		// loop thru args
		
		for (Object tempArg: args) {
			myLogger.info(tempArg.toString());
			
			if(tempArg instanceof Account) {
				Account theAccount = (Account) tempArg;
				
				myLogger.info("account name: " + theAccount.getName());
				myLogger.info("account level: " + theAccount.getLevel());
			}
		}
		
		
	}
	

}
