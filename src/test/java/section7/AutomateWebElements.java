package section7;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import junit.framework.Assert;

public class AutomateWebElements {

	// Section - 7

	static void sec7_51_selenium_manager() {

		// SeleniumManager Off (Beta phase)
		System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
	}

	static void sec7_52_static_dropdown() {
		System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

		WebElement static_dropdown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));

		Select s = new Select(static_dropdown);
		s.selectByIndex(3);
		System.out.println(s.getFirstSelectedOption().getText());
		s.selectByVisibleText("AED");
		System.out.println(s.getFirstSelectedOption().getText());
		s.selectByValue("INR");
		System.out.println(s.getFirstSelectedOption().getText());
	}

	static void sec7_53_latest_dropdown_loop_ui() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

		driver.findElement(By.id("divpaxinfo")).click();

		Thread.sleep(2000L);

		/*
		 * int i=1;
		 * 
		 * while(i<5)
		 * 
		 * {
		 * 
		 * driver.findElement(By.id("hrefIncAdt")).click();
		 * 
		 * i++;
		 * 
		 * }
		 */

		System.out.println(driver.findElement(By.id("divpaxinfo")).getText());

		for (int i = 1; i < 5; i++)

		{

			driver.findElement(By.id("hrefIncAdt")).click();

		}

		driver.findElement(By.id("btnclosepaxoption")).click();

		Assert.assertEquals(driver.findElement(By.id("divpaxinfo")).getText(), "5 Adult");

		System.out.println(driver.findElement(By.id("divpaxinfo")).getText());
		driver.quit();
	}

	static void sec7_dynamic_dropdown() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

		driver.manage().window().maximize();
		// org.openqa.selenium.ElementNotVisibleException
		// org.openqa.selenium.NoSuchElementException

		// //a[@value='MAA'] - Xpath for chennai

		// //a[@value='BLR']
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1")).click();
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_originStation1_CTNR']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@text='Bengaluru (BLR)']")).click();
		Thread.sleep(2000);
		// driver.findElement(By.xpath("(//a[@value='MAA'])[2]")).click();

		driver.findElement(By.id("ctl00_mainContent_HolidayPackages_DropDownListFrom_CTXT")).click();
		driver.findElement(By.xpath("//a[@text='Chennai']")).click();
		Thread.sleep(2000);
		driver.quit();
		// driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']
		// //a[@value='MAA']")).click();
		// driver.findElement(By.cssSelector(".ui-state-default.ui-state-highlight.ui-state-active")).click();
	}

	static void sec7_58_auto_suggestive_dropdown() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		driver.findElement(By.id("autosuggest")).sendKeys("ind");

		Thread.sleep(3000);

		List<WebElement> options = driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));

		for (WebElement option : options)
		{
			if (option.getText().equalsIgnoreCase("India"))
			{
				option.click();
				break;
			}
		}
	}

	static void sec7_61_assertion() {
		System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://spicejet.com"); // URL in the browser

		Assert.assertFalse(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());

		// Assert.assertFalse(true);System.out.println(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());

		driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).click();

		System.out.println(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());

		Assert.assertTrue(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());
	}

	static void sec7_cal_ui() {

		/*
		 * there will be some difference in the webelement of highlighted date and other
		 * date in DOM
		 * 
		 * identify it and use it in xpath or CSS
		 */

		System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://spicejet.com");

		// div[class*='r-16ru68a r-y47klf']
	}

	static void ui_element_enable_disable() {

		System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://spicejet.com");

		// System.out.println(driver.findElement(By.name("ctl00$mainContent$view_date2")).isEnabled());

		System.out.println(driver.findElement(By.id("Div1")).getAttribute("style"));

		driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();

		System.out.println(driver.findElement(By.id("Div1")).getAttribute("style"));

		if (driver.findElement(By.id("Div1")).getAttribute("style").contains("1"))

		{

			System.out.println("its enabled");

			Assert.assertTrue(true);

		}

		else

		{

			Assert.assertTrue(false);

		}
		
		driver.quit();
	}

	static void handle_java_alerts() {
		//System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://spicejet.com");
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");

		driver.findElement(By.id("name")).sendKeys("rahul");

		driver.findElement(By.cssSelector("[id='alertbtn']")).click();

		System.out.println(driver.switchTo().alert().getText());

		driver.switchTo().alert().accept();

		driver.findElement(By.id("confirmbtn")).click();

		System.out.println(driver.switchTo().alert().getText());



		driver.switchTo().alert().dismiss();
		driver.quit();
	}

	public static void main(String[] args) {
		// sec7_51_selenium_manager();
		 sec7_52_static_dropdown();
		// sec7_53_latest_dropdown_loop_ui();
		// handle_java_alerts();
	}

}
