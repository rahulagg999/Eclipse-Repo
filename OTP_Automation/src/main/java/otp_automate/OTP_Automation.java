//OTP AUtomation Code

//Header Files
package otp_automate;

import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileInputStream;
import org.jboss.aerogear.security.otp.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.Properties;

// Main Class TryOTP
public class OTP_Automation {

	public static WebDriver driver;
	public static Properties prop;

//	Invoke Chrome Driver
	public static void createDriver() {

//	Set Chrome driver path here
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://dev.azure.com/899730/");
		System.out.println("Website is Launched");

	}

//	Two Factor Authentication Method
	public static String getTwoFactorCode() {

		Totp totp = new Totp(" --Enter your Secret Key here--  "); // 2FA secret key
		String twoFactorCode = totp.now(); // Generated 2FA code here
		return twoFactorCode;
	}

//	Login Method	
	public static void login() {

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Email id Xpath
		driver.findElement(By.xpath("//*[@id= 'i0116']")).sendKeys(" --Enter your cognizant id--  ");

		// Submit Button Xpath
		driver.findElement(By.id("idSIButton9")).click();

		// Password Xpath
		driver.findElement(By.id("i0118")).sendKeys("   --Enter your Password--   ");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Submit button xpath
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		// Fetching 6 digit otp
		String otp = getTwoFactorCode();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Sending otp in textbox
		driver.findElement(By.xpath("//input[@name='otc']")).sendKeys(otp);

		// Clicking submit Button
		driver.findElement(By.id("idSubmit_SAOTCC_Continue")).click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Clicking yes
		driver.findElement(By.id("idSIButton9")).click();

	}

	// Main Method
	public static void main(String[] args) {

		OTP_Automation t1 = new OTP_Automation();
		t1.createDriver();
		t1.login();

	}

}
