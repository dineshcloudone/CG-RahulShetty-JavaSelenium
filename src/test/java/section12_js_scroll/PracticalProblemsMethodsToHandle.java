package section12_js_scroll;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

public class PracticalProblemsMethodsToHandle {
public static void main(String[] args) throws InterruptedException {
	System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://rahulshettyacademy.com/AutomationPractice/");
	Thread.sleep(4000);
	JavascriptExecutor js=(JavascriptExecutor)driver;
	
	//scrolling web page
	js.executeScript("window.scrollBy(0,700)");
	Thread.sleep(3000L);
	
	//scrolling the web table
	js.executeScript("document.querySelector('.tableFixHead').scrollTop=5000");
	
	//getting 4th column values of table and suming them and doing assertion at the end 
	List<WebElement> values=driver.findElements(By.cssSelector(".tableFixHead td:nth-child(4)"));
	int sum=0;
	
	for(WebElement ele:values) {
		sum = sum + Integer.parseInt(ele.getText());
	}	
	
	System.out.println("Total sum value:"+sum);
	
	String s=driver.findElement(By.cssSelector(".totalAmount")).getText().split(":")[1].trim();
	int total=Integer.parseInt(s);
	
	Assert.assertEquals(sum, total);
	
	driver.close();
	driver.quit();
}
}
