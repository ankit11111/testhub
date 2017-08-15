/**
 * 
 */
package nongrid;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;

/**
 * @author gaurav.khandelwal
 *
 */
public class SeleniumTest {

	WebDriver driver;
	String baseUrl, hubUrl;

	@BeforeTest
	public void setUp()
			throws MalformedURLException {
		baseUrl = "https://www.google.com";
		driver = new FirefoxDriver();
	}

	@Test
	public void firstNGTest() {
		sleep(2000);
		System.out.println("This is #1 tests...");
	}

	@Test
	public void secondNGTest() {
		sleep(500);
		System.out.println("This is #2 tests...");
		Assert.fail("Failing this Test for testing!");
	}

	@Test
	public void thirdNGTestCase() {
		sleep(2000);
		System.out.println("This is #3 tests...");
	}

	@Test
	public void simpleNGGoogleTest() {
		System.out.println("This is #4 tests...");
		driver.get(baseUrl);
		Assert.assertEquals("Google", driver.getTitle());
		sleep(5000);
	}

	@Test
	public void simpleNGGoogleSearch() {
		System.out.println("This is #5 tests...");
		driver.get(baseUrl);
		sleep(5000);
		WebElement searchBox = driver.findElement(By
				.xpath("//input[@id='lst-ib']"));
		searchBox.sendKeys("selenium");
		searchBox.submit();
		driver.findElement(By.linkText("Images")).click();
		Assert.assertEquals("selenium - Google Search", driver.getTitle());
	}
	
	@Test
	public void gmailNGTest(){
		 String appUrl = "https://accounts.google.com";
		 driver.get(appUrl);
		 driver.manage().window().maximize();
		 String expectedTitle = " Sign in - Google Accounts ";
		 String actualTitle = driver.getTitle();
		 if (expectedTitle.equals(actualTitle)){
		 	System.out.println("Verification Successful - The correct title is displayed on the web page.");
		 }else{
		 	System.out.println("Verification Failed - An incorrect title is displayed on the web page.");
		 }

		 WebElement username = driver.findElement(By.id("identifierId"));
		 username.clear();
		 username.sendKeys("raj.kumar.svim@gmail.com");
		 driver.findElement(By.xpath("//div[@id='identifierNext']//span")).click();
		 sleep(4000);
		 WebElement password = driver.findElement(By.name("password"));
		 password.clear();
		 password.sendKeys("OGK@12345");
		 driver.findElement(By.xpath("//div[@id='passwordNext']//span")).click();
		 sleep(7000);
		 if (driver.getTitle().contains("Inbox")){
			 System.out.println("Gmail logged in...");
		 }else{
			 System.out.println("Not able to login to gmail");
		 }
		 System.out.println("Test script executed successfully.");
	}

	private void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	private DesiredCapabilities getBrowserCaps(String browser) {
		if (browser.equalsIgnoreCase("firefox")) {
			return DesiredCapabilities.firefox();
		} else if (browser.equalsIgnoreCase("chrome")) {
			return DesiredCapabilities.chrome();
		} else if (browser.equalsIgnoreCase("ie")) {
			return DesiredCapabilities.internetExplorer();
		}
		// In case no browser provided, select firefox
		return DesiredCapabilities.firefox();
	}

}
