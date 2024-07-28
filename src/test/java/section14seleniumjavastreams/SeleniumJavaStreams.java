package section14seleniumjavastreams;

import static org.testng.Assert.fail;

import java.security.cert.X509CertSelector;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class SeleniumJavaStreams {

	static void regular() {
		
		ArrayList<String> al = new ArrayList<String>();
		al.add("Abc");
		al.add("def");
		int count = 0;

		for (String a : al) {
			if (a.startsWith("A")) {
				count++;
			}
		}

		System.out.println("total count : " + count);
	}

	static void stream_prct() {

		ArrayList<String> al = new ArrayList<String>();
		al.add("Abc");
		al.add("def");

		// stream + intermediate operation(as many required) + terminal operation
		System.out.println(al.stream().filter(s -> s.startsWith("A")).count());

		long count = Stream.of("Abc", "def").filter(s -> {
			s.startsWith("A");
			return true;
		}).count();

		System.out.println("total count: " + count);

		al.stream().filter(s -> s.length() > 1).forEach(s -> System.out.println(s));
		al.stream().filter(s -> s.length() > 1).limit(1).forEach(s -> System.out.println(s));
	}

	static void stream_map() {

		ArrayList<String> al = new ArrayList<String>();
		al.add("Aef");
		al.add("Abc");
		al.add("def");

		al.stream().filter(s -> s.startsWith("A")).map(s -> s.toUpperCase()).forEach(s -> System.out.println(s));
		
		//Function interface
		ArrayList<String> alstrr=al.stream().filter(s -> s.startsWith("A"))
					.map(s -> s.toUpperCase()).collect(Collectors.toCollection(ArrayList::new));
		
		System.out.println("=========================================================");

		// sort and covert to upper which has starting letter A
		List<String> als = Arrays.asList("Aef", "Abc", "def");
		als.stream().filter(s -> s.startsWith("A")).sorted().map(s -> s.toUpperCase())
				.forEach(s -> System.out.println(s));

		System.out.println("=========================================================");

		// merging two lists
		ArrayList<String> al2 = new ArrayList<String>();
		al2.add("Aef");
		al2.add("Abc");
		al2.add("def");

		ArrayList<String> al3 = new ArrayList<String>();
		al3.add("Zef");
		al3.add("Xbc");
		al3.add("Mef");

		Stream<String> al_merge = Stream.concat(al2.stream(), al3.stream());
		al_merge.forEach(s -> System.out.println(s));

		System.out.println("=========================================================");

		// checking the string existence in a list
		boolean flag = al3.stream().anyMatch(s -> s.equalsIgnoreCase("Zef"));
		System.out.println(flag);
		Assert.assertTrue(flag);
	}

	static void stream_collect() {
		List<String> coll = Stream.of("Abhijeet", "Don", "Alekhya", "Adam").filter(s -> s.endsWith("a"))
				.map(s -> s.toUpperCase()).collect(Collectors.toList());
		System.out.println("element : " + coll.get(0));

		// print unique numbers
		List<Integer> values = Arrays.asList(9, 2, 4, 5, 7, 5);
		// values.stream().distinct().forEach(x->System.out.println(x));

		// getting 3rd index value
		List<Integer> li = values.stream().distinct().sorted().collect(Collectors.toList());
		System.out.println(li.get(2));
	}

	static void webtable_usecases_usingstreams() {

		System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");

		// click on column
		driver.findElement(By.xpath("//tr/th[1]")).click();

		// capture all webelements into list
		List<WebElement> elementsList = driver.findElements(By.xpath("//tr/td[1]"));

		// capture text of all webelements into new(original) list
		List<String> originalList = elementsList.stream().map(s -> s.getText()).collect(Collectors.toList());

		// sort on the original list of step 3 -> sorted list
		List<String> sortedList = originalList.stream().sorted().collect(Collectors.toList());

		// compare original list vs sorted list
		Assert.assertTrue(originalList.equals(sortedList));

		List<String> price;
		// scan the name column with getText ->Beans->print the price of the Rice

		do {
			List<WebElement> rows = driver.findElements(By.xpath("//tr/td[1]"));
			price = rows.stream().filter(s -> s.getText().contains("Rice")).map(s -> getPriceVeggie(s))
					.collect(Collectors.toList());
			price.forEach(a -> System.out.println(a));

			if (price.size() < 1) {
				driver.findElement(By.cssSelector("[aria-label='Next']")).click();
			}

		}while (price.size() < 1);	

	}
	
	private static String getPriceVeggie(WebElement s) {
		// TODO Auto-generated method stub
		String pricevalue = s.findElement(By.xpath("following-sibling::td[1]")).getText();
		return pricevalue;
	}
	
	static void filter_webtable() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		
		driver.findElement(By.id("search-field")).sendKeys("Rice");
        List<WebElement> veggies=driver.findElements(By.xpath("//tr/td[1]"));

        //1 results
       List<WebElement> filteredList= veggies.stream().filter(veggie->veggie.getText().contains("Rice"))
       .collect(Collectors.toList());

       //1 result
       Assert.assertEquals(veggies.size(), filteredList.size());
	}
	
	static void wabtec_practice() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.worldometers.info/world-population/");
		
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000L));
		
		//System.out.println(driver.findElement(By.className("maincounter-number")).getText());
		By span_txt=By.xpath("//div[contains(@class,'sec-box')]/div/span");
		WebDriverWait wt=new WebDriverWait(driver, Duration.ofSeconds(5000));
		wt.until(ExpectedConditions.visibilityOfElementLocated(span_txt));
		//Thread.sleep(5000L);
		List<WebElement> al=driver.findElements(span_txt);
		al.forEach(x->System.out.println(x.getText()));		
		driver.quit();;
	}
	
	public static void main(String[] args) throws InterruptedException {
		// regular();
		 // stream_prct();
		// stream_map();
		// stream_collect();
		//webtable_usecases_usingstreams();
		// filter_webtable();
		wabtec_practice();
		
		ArrayList<String> al=new ArrayList<String>();
		al.add("Abc");
		al.add("Aef");
		al.add("defghf");
		
//		long count=al.stream().filter(x->{
//			if(x.startsWith("A"))
//			{return true;}
//			else {
//				return false;
//			}
//		}).count();
//		
//		System.out.println(count);
//		
//		al.stream().filter(x->x.length()>1).forEach(x->System.out.println(x));
//		al.stream().filter(x->x.length()>1).limit(1).forEach(x->System.out.println(x));
		
		
//		Stream<String>str=al.stream().filter(x->x.startsWith("A")).map(x->x.toUpperCase());
//		str.forEach(x->System.out.println(x));
//		
//		List<String> str2=al.stream().filter(x->x.startsWith("A")).map(x->x.toUpperCase()).collect(Collectors.toList());
//		str2.forEach(x->System.out.println(str2));
		
		String name="Dinesh Dinesh Dinesh";
		char[] all=name.toCharArray();
		ArrayList<Character> alc=new ArrayList<Character>();
		for(char c:all) {
			alc.add(c);
		}
		Stream<Character> strr=alc.stream().distinct();
		String output=strr.map(x->x.toString()).collect(Collectors.joining());
		//System.out.println(output);
		
		/* File Upload
		System.setProperty("webdriver.chrome.driver", "C:\\Capgemini_Office\\RahulShetty\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/upload-download-test/");
		Thread.sleep(3000L);
		driver.findElement(By.id("downloadButton")).click();
		WebElement upload = driver.findElement(By.cssSelector("#fileinput"));
		upload.sendKeys("C:\\Users\\gurramku\\Downloads\\download.xlsx");
		*/
		
		
	}

}
