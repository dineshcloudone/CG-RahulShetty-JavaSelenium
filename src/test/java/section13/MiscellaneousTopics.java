package section13;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.asserts.SoftAssert;

// link : https://chromedriver.chromium.org/capabilities

public class MiscellaneousTopics {

	static void options() {

		ChromeOptions options = new ChromeOptions();
		// FirefoxOptions foptions=new FirefoxOptions();
		// EdgeOptions eoptions=new EdgeOptions();
		options.setAcceptInsecureCerts(true);

		Proxy proxy = new Proxy();

		proxy.setHttpProxy("ipaddress:4444");

		options.setCapability("proxy", proxy);

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("download.default_directory", "/directory/path");
		options.setExperimentalOption("prefs", prefs);

//		options.addArguments("--disable-notifications");
//		options.addArguments("--deny-permission-prompts");

		System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://expired.badssl.com/");

	}

	static void maximize_window_cookies() {

		System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		// driver.manage().deleteAllCookies();
		driver.manage().deleteCookieNamed("sessionkey");

		// click on any link
		// login page - verify login url

		driver.get("https://www.google.com");
	}

	static void take_screenshot() throws IOException {
		System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C:\\Users\\gurramku\\Desktop\\Screenshot_auto.jpg"));
	}

	static void valdiate_broken_links() throws MalformedURLException, IOException, URISyntaxException {
		System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");

		List<WebElement> links = driver.findElements(By.cssSelector("li[class='gf-li'] a"));

		SoftAssert a = new SoftAssert();

		for (WebElement link : links) {

			String url_str = link.getAttribute("href");
			URI uri = new URI(url_str);
			URL url = uri.toURL();
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("HEAD");
			conn.connect();
			int respCode = conn.getResponseCode();
			System.out.println(respCode);
			a.assertTrue(respCode < 400, "The link with Text" + link.getText() + " is broken with code" + respCode);

		}

		a.assertAll();
	}

	public static void main(String[] args) throws IOException, URISyntaxException {
		//take_screenshot();
		valdiate_broken_links();
	}
}
