package com.MentorProject;

import java.util.concurrent.TimeUnit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.chrome.ChromeDriver;

public class AlertsPractice {
	
	static WebDriver driver;
	
	public static void createDriver() {
System.setProperty("webdriver.chrome.driver", "C:\\Users\\rahul\\eclipse-workspace\\MentorProject\\Drivers\\chromedriver.exe");
		
		//Accepting Certificates
		ChromeOptions co = new ChromeOptions();
		co.setAcceptInsecureCerts(true);

		//launching chrome driver
		driver = new ChromeDriver(co);
		driver.manage().window().maximize();
		driver.get("http://webapps.tekstac.com/MultipleWindow/");
		System.out.println("Website is Launched");
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		
	}
	
	public static void getAlert(WebDriver driver) throws InterruptedException {
		
		driver.findElement(By.xpath("//input[@type = 'submit']")).click();
		System.out.println("Button is Clicked");
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		System.out.println("Alert is Accepted");
		Thread.sleep(1000);
		driver.close();
		System.out.println("Browser is Closed");
	}

	public static void main(String[] args) throws InterruptedException {
		
		createDriver();
		getAlert(driver);
	}
	
	
}
