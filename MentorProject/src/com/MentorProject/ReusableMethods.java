package com.MentorProject;

import java.io.FileInputStream;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ReusableMethods {

	static WebDriver driver;
	public static Properties prop;

	public static void setDriver() throws Exception {
			
		//setting chrome driver path	
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\rahul\\git\\repository\\MentorProject\\Drivers\\chromedriver.exe");
		
		//launching chrome driver
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php");
		System.out.println("Website is Launched");
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
	}

	public static void tearDownAfterClass() throws Exception {
       //driver.close();
		driver.quit();
		System.out.println("Browser is Closed");
	}

	public static void test() throws InterruptedException {
		
		//Sign-Up
		//getting date and time 
		LocalDateTime dateTime = LocalDateTime.now();  
		DateTimeFormatter mydateTimeObj = DateTimeFormatter.ofPattern("ddMyyhhmm");
		String formattedDate1 = dateTime.format(mydateTimeObj);
		//wait for 20 seconds
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		
		//sign in by creating new account and filing the mandatory fields like gender, firstname, lastname,
		//address, city, pincode, state,country and phonenumber
		
		//selecting sign in option

		//MULTIPLE WINDOW :
		
		String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN); 
		driver.findElement(By.linkText("Sign in")).sendKeys(selectLinkOpeninNewTab);
		
		Set<String> pageId	= driver.getWindowHandles(); 	
		
		ArrayList arr = new ArrayList(pageId);
		String newCurrentPageId = (String) arr.get(1);
		driver.switchTo().window(newCurrentPageId);
		System.out.println("Sign in Page launched in New Tab");
		
		//entering email 
		driver.findElement(By.name("email_create")).sendKeys("rahul"+formattedDate1+"@test.com");
		System.out.println("Email is Entered");
		//clicking on submit button
		driver.findElement(By.id("SubmitCreate")).click();
		
		//RADIO BUTTON
		
		
	    driver.findElement(By.id("id_gender1")).click();
	    System.out.println("Radio Button Clicked");
	    Thread.sleep(500);
	    
	    //giving input for customer first name
		driver.findElement(By.name("customer_firstname")).sendKeys("Rahul");
		Thread.sleep(500);
		
		//input for customer last name
		driver.findElement(By.name("customer_lastname")).sendKeys("Aggarwal");
		Thread.sleep(500);
		
		//setting of password
		driver.findElement(By.id("passwd")).sendKeys("rahul@123");
		Thread.sleep(500);
		
		//DATE PICKER
		
		
		WebElement wb = driver.findElement(By.id("days"));
		wb.sendKeys(Keys.ARROW_DOWN);
		wb.sendKeys(Keys.ENTER);
		
		WebElement wb2 = driver.findElement(By.id("months"));
		wb2.sendKeys(Keys.ARROW_DOWN);
		wb2.sendKeys(Keys.ENTER);
		
		WebElement wb3 = driver.findElement(By.id("years"));
		wb3.sendKeys(Keys.ARROW_DOWN);
		wb3.sendKeys(Keys.ENTER);
		System.out.println("Date is Picked");
		
		//entering address
		driver.findElement(By.name("address1")).sendKeys("car street");
		
		//entering city
		driver.findElement(By.id("city")).sendKeys("city 2");
		
		//selecting state from  dropdown
		WebElement state = driver.findElement(By.id("id_state"));
		Select s1 = new Select(state);
		s1.selectByVisibleText("Alaska");
		Thread.sleep(500);
		
		//setting postcode
		driver.findElement(By.name("postcode")).sendKeys("21475");
		
		//entering phone number
		driver.findElement(By.id("phone_mobile")).sendKeys("9988775544");
		
		//entering address
		driver.findElement(By.name("alias")).sendKeys("no.2 street");
		
		Thread.sleep(1000);
		
		//clicking submit button
		driver.findElement(By.name("submitAccount")).click();
	    System.out.println("Submit Button is Clicked");
	    //Sign-Up validation

		//Order product
		
		//selecting women menu and choosing T-shirt in that
		WebElement women = driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/a"));
		WebElement tshirt = driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/ul/li[1]/ul/li[1]/a"));
		Actions a1 = new Actions(driver);
        a1.moveToElement(women).build().perform();
        
        //wait untill tshirt option visible
        wait.until(ExpectedConditions.visibilityOf(tshirt));
        
        //select tshirt option
        tshirt.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
		
        //selecting the product in tshirt
		WebElement prod1 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[1]/div/a[1]/img"));
		
		//CHECK BOX - IN STOCK
		driver.findElement(By.id("layered_quantity_1")).click();
		System.out.println("Check-Box is selected");
		
		//SLIDER
//		WebElement sld = driver.findElement(By.xpath("//a[@class = 'ui-slider-handle ui-state-default ui-corner-all']"));
		WebElement sld = driver.findElements(By.xpath("//a[@class = 'ui-slider-handle ui-state-default ui-corner-all']")).get(1);
		a1.dragAndDropBy(sld,-100,0).build().perform();
		System.out.println("Slider is decreased");
		
		// select more option and click ok
		WebElement more = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[2]/div[2]/a[2]/span"));
		Actions a2 = new Actions(driver);
		js.executeScript("arguments[0].scrollIntoView(true)",prod1);
		a2.moveToElement(prod1).moveToElement(more).click().build().perform();
		Thread.sleep(1000);
        
		//increase the quantity by 2
		driver.findElement(By.xpath("//*[@id=\"quantity_wanted_p\"]/a[2]/span/i")).click();
		Thread.sleep(1000);
		
		//select the size of tshirt by dropdown that is L
		WebElement size = driver.findElement(By.xpath("//*[@id=\"group_1\"]"));
		Select s2 = new Select(size);
		s2.selectByVisibleText("L");
		Thread.sleep(1000);
		
		//choosing the color of tshirt
		driver.findElement(By.id("color_14")).click();
		Thread.sleep(1000);
		
		// add to cart path
		driver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button/span")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span"))));
		System.out.println("Add to Cart is clicked");
		Thread.sleep(1000);
		
        //confirming booking of tshirt 
		driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span")).click();
		System.out.println("Proceed to Checkout");
		Thread.sleep(1000);
		
		//clicking on cart voucher
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]/span")).click();
		Thread.sleep(1000);
		
		//confirming booking
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/form/p/button/span")).click();
		driver.findElement(By.id("cgv")).click();
		System.out.println("Terms and Conditions selected");
		Thread.sleep(1000);
		
		//selecting shipment address and confirming
		driver.findElement(By.xpath("//*[@id=\"form\"]/p/button/span")).click();
		Thread.sleep(1000);
		
		//clicking on payment mode
		driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a")).click();
		Thread.sleep(1000);
		
		//navigate to order completion path
		driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button/span")).click();
		//completed product order
		String msg = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/p/strong")).getText();
		Thread.sleep(1000);
		
		// printing the message for product ordered or not
		if (msg.contains("complete")) {
			System.out.println("Product ordered successfully");
		} else {
			System.out.println("Product order failed");
		}
		
	}
	
	
	
}
