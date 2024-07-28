package section10;

import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

// windows: https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
// Frames : https://demo.guru99.com/test/guru99home/
// alert : https://demo.guru99.com/test/delete_customer.php
// frames : https://jqueryui.com/droppable/

public class Ajax_ChildWind_Fram {

	static void handle_windows() throws InterruptedException{
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--deny-permission-prompts");
		System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(options);
		Thread.sleep(3000);
		driver.manage().window().maximize();
		//driver.get("https://www.amazon.com/");
		driver.get("https://www.spicejet.com/");
		
	}
	
	static void action_mouse_keyboard() throws InterruptedException {
		
		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--disable-notifications");
//		options.addArguments("--deny-permission-prompts");
//		
//		Map<String, Object> prefs = new HashMap<String, Object>();
//		prefs.put("profile.default_content_setting_values.notifications", 2);		
//		ChromeOptions options = new ChromeOptions();
//		options.setExperimentalOption("prefs", prefs);
		
		
		System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(options);
		Thread.sleep(3000);
		driver.manage().window().maximize();
		//driver.get("https://www.amazon.com/");
		driver.get("https://www.spicejet.com/");
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//div[@data-testid='to-testID-origin']/div")).click();
		
		Actions action = new Actions(driver);
		
		//move to element
		action.moveToElement(driver.findElement(By.cssSelector("#nav-link-accountList"))).perform();
		// driver.quit();

		//entering capital letters in search box
		action.moveToElement(driver.findElement(By.cssSelector("#twotabsearchtextbox"))).click().keyDown(Keys.SHIFT)
				.sendKeys("hello").build().perform();
		
		//doing right click on 
		action.moveToElement(driver.findElement(By.cssSelector("#nav-link-accountList"))).contextClick().perform();

	}
	
	public static void main(String[] args) throws InterruptedException {
		
		
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--deny-permission-prompts");

		
		System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(options);
		Thread.sleep(3000);
		driver.manage().window().maximize();
		//driver.get("https://www.amazon.com/");
		driver.get("https://www.spicejet.com/");
		

	}

}
