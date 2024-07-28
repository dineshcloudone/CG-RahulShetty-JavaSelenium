package section11_keys_chord;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RealTimeExcerciseEndToEndProgramming {

	static void links_specific() throws InterruptedException {

		// get links count
		System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		System.out.println(driver.findElements(By.tagName("a")).size());

		// limiting webdriver
		WebElement footerdriver = driver.findElement(By.id("gf-BIG"));
		WebElement columndriver = driver.findElement(By.xpath("//table/tbody//td/ul"));
		System.out.println(columndriver.findElements(By.tagName("a")).size());

		// opening links in new tab
		for (int i = 0; i < columndriver.findElements(By.tagName("a")).size(); i++) {

			String clickonlinkTab = Keys.chord(Keys.CONTROL, Keys.ENTER);
			columndriver.findElements(By.tagName("a")).get(i).sendKeys(clickonlinkTab);
			Thread.sleep(3000L);
		}

		// getting titles of each tab
		Set<String> abc = driver.getWindowHandles();
		for (String s : abc) {
			System.out.println(driver.switchTo().window(s).getTitle());
		}

	}

	static void calendar_ui() {

		String monthnumber = "";
		String date = "";
		String year = "";

		System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		//driver.get("https://rahulshettyacademy.com/seleniumPractice/#/offers");
		driver.get("https://www.apsrtconline.in/oprs-web/guest/home.do?h=1");
		driver.findElement(By.id("txtJourneyDate")).click();
		driver.findElement(By.xpath("(//div[@id='ui-datepicker-div']//table)[1]//tr//a[text()='15']")).click();
		driver.findElement(By.id("txtJourneyDate")).getText();
	}

	public static void main(String[] args) throws InterruptedException {
		//links_specific();
		calendar_ui();

	}

}
