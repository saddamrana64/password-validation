package com.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
	static String ex = "";
	static int conditions = 0;
	public static void main(String[] args) throws Exception {
		String password = "aaaaaaaaaaaaaaaaaaaa";
		//conditions = PasswordValidator.getConditions(password);
		
		
		
		/*- password should be larger than 8 chars
		- password should not be null
		- password should have one uppercase letter at least
		- password should have one lowercase letter at least
		- password should have one number at least*/
		validateAllCondition(password);
		
		
		
		
		//Password is OK if at least three of the previous conditions is true
		validateAtLeast3Condition(password);
		
		
		
		//password is never OK if (password should have one lowercase letter at least) is not true.
		validate2Plus1Condition(password);
		
	}

	static void validateAllCondition(String password) throws Exception {
		int validNumbers = 0;
		try {
			validNumbers = PasswordValidator.getConditions(password) + PasswordValidator.hasOneLowerCase(password);
		}catch (Exception e) {
			ex = ex + e.getMessage() + "\n";
		}
		if(validNumbers >= 5) {System.out.println("Password is OK");}
		else {System.err.println(ex);}
	}
	
	static void validateAtLeast3Condition(String password) throws Exception {
		int validNumbers = PasswordValidator.getConditions(password);
		try {
			validNumbers =  validNumbers + PasswordValidator.hasOneLowerCase(password);
		}catch (Exception e) {
			ex = ex + e.getMessage() + "\n";
		}
		if(validNumbers >= 3) {System.out.println("Password is OK");}
		else {System.err.println(ex);}
	}
	
	static void validate2Plus1Condition(String password) throws Exception {
		try {
			if(PasswordValidator.hasOneLowerCase(password) > 0 && PasswordValidator.getConditions(password) >= 2) System.out.println("Password is OK " );
		}catch (Exception e) {
			System.err.println(e.getMessage() + ex );
		}
	}
	
	
	
	
	
	static int getConditions(String password) throws Exception {
		int conditions = 0;
		try {
			conditions = conditions + isLargeEnough(password) ;
		}catch (Exception e) {
			ex = ex + e.getMessage() + "\n";
		}
		
		try {
			conditions = conditions + isNotNull(password) ;
		}catch (Exception e) {
			ex = ex + e.getMessage() + "\n";
		}
		
		try {
			conditions = conditions + hasOneUpperCase(password) ;
		}catch (Exception e) {
			ex = ex + e.getMessage() + "\n";
		}
		
		try {
			conditions = conditions + hasOneDigit(password) ;
		}catch (Exception e) {
			ex = ex + e.getMessage() + "\n";
		}
		
		
		
		
		return conditions;
	}
	
	private static int isLargeEnough(String password) throws Exception {
		String largerEnough = ".{9,}";
		if(matcher(password, largerEnough) == 0 ) throw new Exception("length must be larger than 8 character");
		return matcher(password, largerEnough);
	}
	
	private static int isNotNull(String password) throws Exception {
		int value = 0 ;
		boolean flag = null == password || password.isBlank() || password.isEmpty() || !(password instanceof String);
		if(!flag) value = 1;
		else throw new Exception("password should not be null");
		return value;
	}
	
	private static int hasOneUpperCase(String password) throws Exception {
		String atLeastOneUpperCase = "(.*[A-Z].*)";
		if(matcher(password, atLeastOneUpperCase) == 0 ) throw new Exception("password must have atleast one upper case character");
		return matcher(password, atLeastOneUpperCase);
	}
	
	static int hasOneLowerCase(String password) throws Exception {
		String atLeastOneLowerCase = "(.*[a-z].*)";
		if(matcher(password, atLeastOneLowerCase) == 0 ) throw new Exception("password must have atleast one lower case character");
		return matcher(password, atLeastOneLowerCase);
	}
	
	private static int hasOneDigit(String password) throws Exception {
		String atLeastOneLowerCase = "(.*[0-9].*)";
		if(matcher(password, atLeastOneLowerCase) == 0 ) throw new Exception("password must have atleast one digit");
		return matcher(password, atLeastOneLowerCase);
	}
	
	private static int matcher(String pwd, String regex) {
		int value = 0 ;
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(pwd);
		if(m.matches()) value = 1;
		return value;
	}
	
	
	
	

}
