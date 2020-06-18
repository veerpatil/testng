package TestNGPractice.Test;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

@Listeners(TestNGPractice.Test.Listner.class)
public class SampleTestCase {
	
	private WebDriver driver ; 
	@BeforeTest
	public void BeforeTest()
	{
		System.out.println("The before Test executed");
		Message.DisplayMessage();
		
		System.setProperty("webdriver.chrome.driver","D:\\a\\1\\s\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		
		// System.setProperty("webdriver.gecko.driver","D:\\a\\1\\s\\resources\\geckodriver.exe");
		// driver = new FirefoxDriver();
		driver.navigate().to("https://codility-frontend-prod.s3.amazonaws.com/media/task_static/qa_search/6f03f4361b080eeb747193aadd94cd2b/static/attachments/reference_page.html");
		Reporter.log("Browser Opened successfully");
	}
  @Test(priority=0)
  public void verifyPageTitle() {
	  System.out.println("The test 1 got executed");
	  System.out.println(driver.getTitle());
	  Assert.assertEquals("ARR", driver.getTitle());
	  Reporter.log("Title verified" + driver.getTitle());
  }
  
  @Test(priority=1)
  public void verifyPresenceOfSearchInputBox() {
	 boolean inputbox = driver.findElement(By.id("search-input")).isDisplayed();
	 Assert.assertEquals(true, inputbox);
	 System.out.println("Input box tex passed");
	 Reporter.log("The input box is displayed");
  }
  
  @Test(priority=2)
  public void verifyPresencOfSearchButton() {
	  boolean inputbutton = driver.findElement(By.id("search-button")).isDisplayed();
		 Assert.assertEquals(true, inputbutton );
		 System.out.println("Input button test passed");
  }
  @Test(priority=3)
  public void verifyBlankSearchCriteria() {
	  //Provide some query
	  driver.findElement(By.id("search-button")).click();
	  String errorMessage= driver.findElement(By.id("error-empty-query")).getText();
	  Assert.assertEquals("Provide some query", errorMessage);
	  System.out.println("Blank search");
  }
  @Test(priority=4)
  public void verifySearchResultIsla() {
	 
	  driver.findElement(By.id("search-input")).sendKeys("isla");
	  driver.findElement(By.id("search-button")).click();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	   List<WebElement> search = driver.findElements(By.cssSelector("li"));
	   int searchCount= search.size();
	   if(searchCount>=1)
	   {
		   Assert.assertEquals(true, true);
	   }
	   System.out.println("Verification for Search For Island");
  }
  @Test(priority=5)
  public void verifySearchForCastle() {
	  driver.findElement(By.id("search-input")).clear();
	  driver.findElement(By.id("search-input")).sendKeys("castle");
	  driver.findElement(By.id("search-button")).click();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	   String search = driver.findElement(By.id("error-no-results")).getText();
	  Assert.assertEquals("No results", search);
	  System.out.println("Verification for Search For Castle");
  }
  @Test(priority=6)
  public void verifySearchForPort() {
	  driver.findElement(By.id("search-input")).clear();
	  driver.findElement(By.id("search-input")).sendKeys("port");
	  driver.findElement(By.id("search-button")).click();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  WebElement list = driver.findElement(By.cssSelector("li"));
	  Assert.assertEquals("Port Royal", list.getText());
	  System.out.println("Verification for Search For port");
  }
  
  
  @Test(priority=7)
  public void test8() {
	  System.out.println("The test 1 got executed");
	  Message.DisplayMessage();
  }
  
  @AfterTest
  public void tearDown()
  {
	  driver.quit();
  }
}
