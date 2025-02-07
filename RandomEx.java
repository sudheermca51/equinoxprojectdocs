package org.iitwf.selenium.lib;

import java.util.Random;

public class RandomEx {
	
	public static void main(String[] args) {
		
		 
		int i =65;
		
		char ch = (char) i;
		System.out.println("Ch value::" + ch);
		
		//lBound+rand.nextInt(uBound-lbound+1)
		
		Random rand = new Random();
		 i = rand.nextInt(100); // 0 inclusive 100 exclusive
		System.out.println(i);
		
		int lBound = 10000000, uBound =99999999;
		
		int digit_3= rand.nextInt(lBound+(uBound-lBound+1));
		System.out.println("3 digit Random Number" + digit_3);
		
		digit_3 = generateRandomDigits(lBound,uBound);
		System.out.println("3 digit Random Number from function::: " + digit_3);
		
		String randomString = generateRandomString("AUTTeamFname");
		System.out.println("randomString" +randomString );
		generateRandomEmailID("AUTO");
	}
	public static int generateRandomDigits(int lBound,int uBound)
	{
		Random rand = new Random();
		int digits = lBound+rand.nextInt((uBound-lBound+1));
		return digits;
	}
	public static String generateRandomString(String str)
	{
		Random rand = new Random();
		int digit1 =65+ rand.nextInt((90-65+1));
		char upperCaseCh = (char) digit1;
		
		
		int digit2 = 97+rand.nextInt((122-97+1));
		System.out.println(digit2);
		char lowerCaseCh = (char) digit2;
		
		System.out.println("Lower Case Char::: " + lowerCaseCh);
		
		String randomString = str+upperCaseCh+lowerCaseCh;
		
		return randomString;
		
	}
	
	public static String generateRandomEmailID(String str)
	{
		Random rand = new Random();
		int digit1 =65+ rand.nextInt((90-65+1));
		char upperCaseCh = (char) digit1;
		
		
		int digit2 = 97+rand.nextInt((122-97+1));
		System.out.println(digit2);
		char lowerCaseCh = (char) digit2;
		
		System.out.println("Lower Case Char::: " + lowerCaseCh);
		
		String randomString = str+upperCaseCh+lowerCaseCh+"@gmail.com";
		
		System.out.println("Random Email ID:::::" + randomString);
		return randomString;
		
	}
}
