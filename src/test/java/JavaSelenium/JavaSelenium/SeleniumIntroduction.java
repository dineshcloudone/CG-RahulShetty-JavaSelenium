package JavaSelenium.JavaSelenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.util.*;

import junit.framework.Assert;

import java.time.Duration;

import org.openqa.selenium.By;

public class SeleniumIntroduction {

	static void launch_browser_login() throws InterruptedException {

		// chromedriver download :
		// https://googlechromelabs.github.io/chrome-for-testing/
		// geckodriver download : https://github.com/mozilla/geckodriver/releases
		// edge driver download :
		// https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/?form=MA13LH

		// Invoking Browser

		// Chrome - ChromeDriver exten->Methods close get

		// Firefox- FirefoxDriver ->methods close get

		// WebDriver close get

		// WebDriver methods + class methods

		// Chrome
//				ChromeOptions options = new ChromeOptions();
//				options.addArguments("--remote-allow-origins=*");
//				System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");
//				WebDriver driver = new ChromeDriver();

		// Firefox
		// installed location of firefox : C:\Users\gurramku\AppData\Local\Mozilla
		// Firefox
//				FirefoxOptions options=new FirefoxOptions();
//				options.setBinary("C:\\Users\\gurramku\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
//				System.setProperty("webdriver.gecko.driver", "C:\\Capgemini_Office\\RahulShetty\\geckodriver.exe");
//				WebDriver driver = new FirefoxDriver(options);

		// Microsoft Edge
		//System.setProperty("webdriver.edge.driver", "C:\\Capgemini_Office\\RahulShetty\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();

		driver.get("https://rahulshettyacademy.com");
		Thread.sleep(3000);
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		
		// driver.close();
		driver.quit();
	}

	static void locators_ex_login() throws InterruptedException {
		// TODO Auto-generated method stub

		// implicit wait - 2 seconds time out

		System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.get("https://rahulshettyacademy.com/locatorspractice/");

		driver.findElement(By.id("inputUsername")).sendKeys("rahul");

		driver.findElement(By.name("inputPassword")).sendKeys("hello123");

		// driver.findElement(By.className("signInBtn")).click();
		driver.findElement(By.className("submit")).click();

		System.out.println(driver.findElement(By.cssSelector("p.error")).getText());

		driver.findElement(By.linkText("Forgot your password?")).click();

		Thread.sleep(1000);//

		driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("John");

		driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("john@rsa.com");

		driver.findElement(By.xpath("//input[@type='text'][2]")).clear();

		driver.findElement(By.cssSelector("input[type='text']:nth-child(3)")).sendKeys("john@gmail.com");

		driver.findElement(By.xpath("//form/input[3]")).sendKeys("9864353253");

		driver.findElement(By.cssSelector(".reset-pwd-btn")).click();

		System.out.println(driver.findElement(By.cssSelector("form p")).getText());

		driver.findElement(By.xpath("//div[@class='forgot-pwd-btn-conainer']/button[1]")).click();

		Thread.sleep(1000);

		driver.findElement(By.cssSelector("#inputUsername")).sendKeys("rahul");

		driver.findElement(By.cssSelector("input[type*='pass']")).sendKeys("rahulshettyacademy");

		driver.findElement(By.id("chkboxOne")).click();

		driver.findElement(By.xpath("//button[contains(@class,'submit')]")).click(); //In CSS button[class*="submit"]

		driver.quit();
	}

	static void verify_rahul_site_login_success() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/locatorspractice/");
		driver.findElement(By.cssSelector("#inputUsername")).sendKeys("rahul");

		driver.findElement(By.cssSelector("input[type*='pass']")).sendKeys("rahulshettyacademy");

		driver.findElement(By.id("chkboxOne")).click();

		driver.findElement(By.xpath("//button[contains(@class,'submit')]")).click();

		Thread.sleep(2000);

		Assert.assertEquals(driver.findElement(By.xpath("//p")).getText(), "You are successfully logged in.");

		driver.findElement(By.className("logout-btn")).click();
		// driver.findElement(By.xpath("//*[text()='Log Out']")).click();

		Thread.sleep(2000);

		driver.quit();

	}

	// Section_6

	static void sec6_41_42_43() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
			
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String password = getPassword(driver);
		
		driver.get("https://rahulshettyacademy.com/locatorspractice/");
		
		String name = "rahul";
		
		driver.findElement(By.id("inputUsername")).sendKeys(name);

		driver.findElement(By.name("inputPassword")).sendKeys(password);

		driver.findElement(By.className("signInBtn")).click();

		Thread.sleep(2000);

		System.out.println(driver.findElement(By.tagName("p")).getText());

		Assert.assertEquals(driver.findElement(By.tagName("p")).getText(), "You are successfully logged in.");

		Assert.assertEquals(driver.findElement(By.cssSelector("div[class='login-container'] h2")).getText(),"Hello "+name+",");

		driver.findElement(By.xpath("//*[text()='Log Out']")).click();

		driver.quit();

	}

	public static String getPassword(WebDriver driver) throws InterruptedException

	{

		driver.get("https://rahulshettyacademy.com/locatorspractice/");

		driver.findElement(By.linkText("Forgot your password?")).click();

		Thread.sleep(1000);

		driver.findElement(By.cssSelector(".reset-pwd-btn")).click();

		String passwordText = driver.findElement(By.cssSelector("form p")).getText();

		// Please use temporary password 'rahulshettyacademy' to Login.

		String[] passwordArray = passwordText.split("'");

		// String[] passwordArray2 = passwordArray[1].split("'");

		// passwordArray2[0]

		String password = passwordArray[1].split("'")[0];

		return password;

		// 0th index - Please use temporary password

		// 1st index - rahulshettyacademy' to Login.

		// 0th index - rahulshettyacademy

		// 1st index - to Login.

	}

	static void sec6_46_47_48() {

		System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();

		// Sibling - Child to parent traverse

		//header/div/button[1]/following-sibling::button[1]

		driver.get("https://rahulshettyacademy.com/AutomationPractice/");

		System.out.println(driver.findElement(By.xpath("//header/div/button[1]/following-sibling::button[1]")).getText());
		System.out.println(driver.findElement(By.xpath("//header/div/button[1]/parent::div/button[2]")).getText());
	}
	
	public static void main(String[] args) throws InterruptedException {
		//launch_browser_login();
		// locators_ex_login();
		// verify_rahul_site_login_success();		
		//sec6_41_42_43();
		//sec6_46_47_48();
		
		
	}

}
