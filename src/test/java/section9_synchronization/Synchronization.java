package section9_synchronization;

import java.time.Duration;
//import java.util.Arrays;
//
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.concurrent.TimeUnit;
//import java.util.function.Function;

import java.util.*;
import java.util.function.*;

import javax.lang.model.element.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Synchronization {

	/*
	 * Synchronization :
	 * 
	 * Implicit Wait : wait for n number of seconds before throwing exception if the
	 * webelement found before n number of seconds the driver will continue with
	 * action
	 * 
	 * Explicit Wait : 15 sec, specific to special scenario where it takes 15 sec
	 * time to load
	 * 
	 * Thread.sleep : static wait from java Fluent Wait
	 * 
	 */

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));

		String[] itemsNeeded = { "Cucumber", "Brocolli", "Beetroot" };

		driver.get("https://rahulshettyacademy.com/seleniumPractise/");

		Thread.sleep(3000);

		addItems(driver, itemsNeeded);

		driver.findElement(By.cssSelector("img[alt='Cart']")).click();

		driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")).click();

		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.promoCode")));

		driver.findElement(By.cssSelector("input.promoCode")).sendKeys("rahulshettyacademy");

		driver.findElement(By.cssSelector("button.promoBtn")).click();

		// explicit wait

		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.promoInfo")));

		System.out.println(driver.findElement(By.cssSelector("span.promoInfo")).getText());

	}

	public static void addItems(WebDriver driver, String[] itemsNeeded)

	{

		int j = 0;

		List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));

		for (int i = 0; i < products.size(); i++)

		{

			// Brocolli - 1 Kg

			// Brocolli, 1 kg

			String[] name = products.get(i).getText().split("-");

			String formattedName = name[0].trim();

			// format it to get actual vegetable name

			// convert array into array list for easy search

			// check whether name you extracted is present in arrayList or not-

			List itemsNeededList = Arrays.asList(itemsNeeded);

			if (itemsNeededList.contains(formattedName))

			{

				j++;

				// click on Add to cart

				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();

				if (j == itemsNeeded.length)

				{

					break;

				}

			}

		}

	}

	public static void fluent_wait() {
		
		System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");

		WebDriver driver=new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
		driver.findElement(By.cssSelector("[id='start'] button")).click();
		
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30L))
				.pollingEvery(Duration.ofSeconds(3L)).ignoring(NoSuchElementException.class);
		
		//WebElement foo=wait.until(new Function<WebDriver,WebElement>(){});
		
		WebElement foo=wait.until(new Function<WebDriver, WebElement>() {			
			public WebElement apply(WebDriver driver) {
				if(driver.findElement(By.cssSelector("[id='finish'] h4")).isDisplayed()) {
				return driver.findElement(By.cssSelector("[id='finish'] h4"));
			}else {
				return null;
			}
		}
		});
		
		System.out.println(foo.getText());
	}
}

