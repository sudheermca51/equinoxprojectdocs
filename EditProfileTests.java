package org.iitwf.selenium.mmpequinox;

import org.iitwf.selenium.lib.RandomEx;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class EditProfileTests {
	static WebDriver driver = new ChromeDriver();
	public static void login()
	{

		driver.manage().window().maximize();
		driver.get("http://85.209.95.122/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
	//	Duration d =Duration.ofSeconds(30);
	//	driver.manage().timeouts().implicitlyWait(d);
		driver.findElement(By.id("username")).sendKeys("ria1");
		driver.findElement(By.id("password")).sendKeys("Ria12345");
		driver.findElement(By.name("submit")).click();
	}

	public static void main(String[] args) {
		login();
		navigatetoAModule("Profile");
		editProfileTests();

	}
	public static void navigatetoAModule(String moduleName)
	{
		driver.findElement(By.xpath("//span[normalize-space()='"+moduleName+"']")).click();
	}
	
	public static void editProfileTests()
	{
		driver.findElement(By.id("Ebtn")).click();
		
		//Update Fname
		
		String expectedfnameValue= RandomEx.generateRandomString("AUTFNAME");
		WebElement fnameWE = driver.findElement(By.id("fname"));
		fnameWE.clear();
		fnameWE.sendKeys(expectedfnameValue);
		
		//Update LName
		
		//Update SSN
		
		//Update Age
		
		
		driver.findElement(By.id("Sbtn")).click();
		handleAlerts();
		
		fnameWE = driver.findElement(By.id("fname"));
		String actualfnameValue= fnameWE.getDomProperty("value");
		
		if(expectedfnameValue.equals(actualfnameValue))
		{
			System.out.println("FName value is updated");
		}
	}
	
	public static String handleAlerts()
	{
		Alert alrt = driver.switchTo().alert();
		String alertTxt = alrt.getText();
		alrt.accept();
		return alertTxt;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}



/**
 * 1. update the firstname by passing randomtext value.
 * 	  expected and actual 
 *    compare expected vs actual
 *  
 * 2. LAstName
 * 3. SSN
 * 4. Address
 * 
 * SoftAssert 
 * 
 * FluentWait
 * Breakpoint by modifying the logic
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */