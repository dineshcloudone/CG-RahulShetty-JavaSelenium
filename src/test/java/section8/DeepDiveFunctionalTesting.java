package section8;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Arrays;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class DeepDiveFunctionalTesting {

	public static void addItems(WebDriver driver, String[] itemsNeeded)
	{
		/*
		 * 1. Adding multiple items to cart
		 * 2. Controlling for loope iteration by comparing arraylist size to 
		 *    required number of items added
		 * 3. Never use text as it changes in coming days (ex: ADD TO CART) 
		 *  
		 */
		
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
			List<String> itemsNeededList = Arrays.asList(itemsNeeded);
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

	public static void main(String[] args) throws InterruptedException 
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		String[] itemsNeeded = { "Cucumber", "Brocolli", "Beetroot" };
		driver.get("https://rahulshettyacademy.com/seleniumPractise/");
		Thread.sleep(3000);

		addItems(driver, itemsNeeded);
	}

}
