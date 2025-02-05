package org.iitwf.selenium.mmpequinox;

import java.time.Duration;
import java.util.HashMap;

import org.iitwf.selenium.lib.FutureDateEx;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScheduleAppointment {
	
	/*
	 * 
	 * Monday,Tuesday,Thursday - 9 pm 
	 * 
	 * 
	Formatted Date:::February/20/2025
	Same Year 
	Same Month -> Click on the date - 2
	----------------------------------------
	Formatted Date:::April/20/2025 - split("/") - [0] - April,[1]- 20,[2]-2025
	Same Year 
	Different Month - Expected Year - April -> Click on next button
	Different Month - Expected Year - December -> till i see the month as December 
	---------------------------------------------------------------------------------
	Formatted Date: April/20/2026
	Different Year - Click on next button till 2026 is displayed 
	Different Month - Click on next button till April is displayed 
	click on 20th as date 
	---------------------------------------------------------------------
	*/
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
		navigatetoAModule("Schedule Appointment");
		HashMap<String,String> expectedHMap= bookAppointment(60,"MMMM/d/YYYY","Cardiologist","Charlie");
        HashMap<String,String> actualHMap = fetchAppointmentDetails();
		if(expectedHMap.equals(actualHMap))
		{
			System.out.println("Booking is successful");
		}
		else
		{
			System.out.println("Booking is unsuccessful");
		}
	}
	public static HashMap<String, String>  fetchAppointmentDetails()
	{
		
		HashMap<String,String> actualHMap = new HashMap<String,String>();
		actualHMap.put("doctor",driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[4]")).getText().trim() );
		actualHMap.put("date",driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[1]")).getText().trim()  );
		actualHMap.put("time",driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[2]")).getText().trim() );
		actualHMap.put("sym", driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[3]")).getText().trim());
		return actualHMap;
	}
	public static void navigatetoAModule(String moduleName)
	{
		driver.findElement(By.xpath("//span[normalize-space()='"+moduleName+"']")).click();
	}
	public static  HashMap<String, String> bookAppointment(int n , String format,String specialization,String doctorName  )
	{
		
		HashMap<String,String> expectedHMap = new HashMap<String,String>();
		expectedHMap.put("doctor", doctorName);
		
		driver.findElement(By.xpath("//input[@value='Create new appointment']")).click();
		//driver.findElement(By.id("opener")).click();
		driver.findElement(By.	
			xpath("//p[text()='Description: "+specialization+"']/parent::div/preceding-sibling::h4[text()='Dr."+doctorName+"']/ancestor::ul/following-sibling::button")).
		    click();
		driver.switchTo().frame("myframe");
		driver.findElement(By.id("datepicker")).click();
		String expectedDate= FutureDateEx.generateFutureDate(n,format);
		String expectedDateArr[] = expectedDate.split("/");
		String expectedMonth = expectedDateArr[0];
		String expectedDay = expectedDateArr[1];
		String expectedYear = expectedDateArr[2];
		
		 
		expectedHMap.put("date", FutureDateEx.generateFutureDate(n,"MM/dd/YYYY"));
		
		System.out.println("######################" + expectedMonth +"--"+ expectedDay +"---" + expectedYear );
		
		
		//Logic 
		String actualYear = driver.findElement(By.className("ui-datepicker-year")).getText();
		String actualMonth = driver.findElement(By.className("ui-datepicker-month")).getText();
		
		while(!(expectedYear.equals(actualYear)))
		{
			System.out.println("in while year loop");
			driver.findElement(By.xpath("//span[text()='Next']")).click();
			actualYear = driver.findElement(By.className("ui-datepicker-year")).getText();
		}
		while(!(expectedMonth.equals(actualMonth)))
		{
			System.out.println("in while month loop");
			driver.findElement(By.xpath("//span[text()='Next']")).click();
			actualMonth = driver.findElement(By.className("ui-datepicker-month")).getText();
		}
		driver.findElement(By.linkText(expectedDay)).click();
		
		
		Select timeSelect = new Select(driver.findElement(By.id("time")));
		timeSelect.selectByVisibleText("10Am");
		expectedHMap.put("time",timeSelect.getFirstSelectedOption().getText());
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("status"), "OK"));
		driver.findElement(By.id("ChangeHeatName")).click();
		driver.switchTo().defaultContent();
		
		driver.findElement(By.id("sym")).sendKeys("Fever and cold");
		expectedHMap.put("sym",driver.findElement(By.id("sym")).getDomProperty("value"));
		
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		
		return expectedHMap;
		
		
	}
}
