package JavaSelenium.JavaSelenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeCitrix {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.edge.driver", "C:\\Capgemini_Office\\RahulShetty\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.get("https://daassteppingstone.cloud.com/Citrix/StoreWeb/#/home");
		
		while(true) {
			driver.navigate().refresh();
			Thread.sleep(300000);
		}
	}

}
