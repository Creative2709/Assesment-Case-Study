package WorldlineAssesment.testing;


import java.time.Duration;
import java.time.LocalDateTime;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;


public class Go_indigo {
	static WebDriver driver;
	private static String url = "https://www.goindigo.in/?linkNav=home_header";
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		logInfo("Starting Browser...");
        System.setProperty("webdriver.chrome.driver","D:\\WORLDLINE\\Testing\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		
	
		logInfo("Navigate to url : " + url);
		driver.navigate().to(url);
		
		logInfo("Maximize window");
		driver.manage().window().maximize();
	}

	@Test
	public void test() throws InterruptedException {
		
//		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		driver.findElement(By.id("salePopupCloseBtn")).click();
		
		logInfo("Click One way");
		Thread.sleep(1200);
		
		logInfo("Click From");
		String xpath1 = "(//input[@placeholder='From'])";
		FindElement(driver, By.xpath(xpath1), 4).click();
		
		driver.findElement(By.xpath("//*[@id=\"container-fabdaf897e\"]/div/div[1]/div/div/div/section/div/div[3]/div[1]/div[1]/input")).clear();

		driver.findElement(By.xpath("//*[@id=\"container-fabdaf897e\"]/div/div[1]/div/div/div/section/div/div[3]/div[1]/div[1]/input")).sendKeys("Bengaluru");
		
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.TAB).build().perform();

		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.switchTo().activeElement());
		
			
		logInfo("Click To");   //Destination selection
		Thread.sleep(1200);
		String xpath3 = "(//input[@placeholder='To'])";
		FindElement(driver, By.xpath(xpath3), 4).click();
		driver.findElement(By.xpath("//*[@id=\"container-fabdaf897e\"]/div/div[1]/div/div/div/section/div/div[3]/div[2]/div[1]/input")).sendKeys("Lucknow");
		actions.sendKeys(Keys.TAB).build().perform();

		Thread.sleep(1000); 
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.switchTo().activeElement());
				
		
		logInfo("Click passenger selection");       //No of passengers selection
		driver.findElement(By.xpath("//*[@id=\"bar\"]/div[2]/div[1]/button/i")).click();
		
		logInfo("Select 3 Adult");
		for(int i=0 ; i<2; i++) {
		driver.findElement(By.xpath("//*[@id=\"bar\"]/div[2]/div[2]/div[2]/label/div/button[2]/i")).click();
		 Thread.sleep(500);
		}
				
		logInfo("Select 2 children");
		for(int i=0; i<2; i++) {
		driver.findElement(By.xpath("//*[@id=\"bar\"]/div[2]/div[2]/div[4]/label/div/button[2]")).click();
		Thread.sleep(500);
		}

		logInfo("Click Done");
		driver.findElement(By.className("custom-button__label")).click();
		

		logInfo("Click Search Flight");
		driver.findElement(By.xpath("//span[contains(text(), 'Search Flight')]")).click();
		
		
		logInfo("Flights list loading complete");
		logInfo("Page Title : " + driver.getTitle() );
	
	}
	
	

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		logInfo("Closing Browser...");
		driver.quit();
		logInfo("DONE...");
	}
	
	
	private static WebElement FindElement(WebDriver driver, By by, int i) {
	    try {
	        Duration timeoutDuration = Duration.ofSeconds(i);
	        WebDriverWait wait = new WebDriverWait(driver, timeoutDuration);
	        wait.until(ExpectedConditions.presenceOfElementLocated(by));
	        return driver.findElement(by);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	
	public static void logInfo(String msg) {
		
		System.out.println( LocalDateTime.now() + " : " + msg );
		
	}

}
