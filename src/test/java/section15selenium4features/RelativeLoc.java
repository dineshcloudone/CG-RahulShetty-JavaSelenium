package section15selenium4features;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.openqa.selenium.support.locators.RelativeLocator.*;

// Automation Practice - 3 link from rahul shetty academy
// practice link : https://rahulshettyacademy.com/practice-project

// relative locators link : https://www.selenium.dev/documentation/webdriver/elements/locators/#relative-locators

public class RelativeLoc {
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		
		//above
		WebElement nameEditBox =driver.findElement(By.cssSelector("[name='name']"));
		System.out.println(driver.findElement(with(By.tagName("label")).above(nameEditBox)).getText());
		
		//below
		WebElement dateofBirth = driver.findElement(By.cssSelector("[for='dateofBirth']"));
		driver.findElement(with(By.tagName("input")).below(dateofBirth)).click();		
		
		//toLeftof		
		WebElement iceCreamLabel =driver.findElement(By.xpath("//label[text()='Check me out if you Love IceCreams!']"));
		driver.findElement(with(By.tagName("input")).toLeftOf(iceCreamLabel)).click();
		WebElement rdb = driver.findElement(By.id("inlineRadio1"));
		
		//toRightof
		System.out.println(driver.findElement(with(By.tagName("label")).toRightOf(rdb)).getText());
		}
}