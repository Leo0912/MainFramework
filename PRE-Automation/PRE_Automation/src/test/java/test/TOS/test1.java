package test.TOS;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import constants.Constants;

public class test1 {
	
	
	@Test
	public void testClinton() throws InterruptedException {
		System.setProperty("webdriver.ie.driver", Constants.IE_DRIVER_PATH);
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
	    capabilities.setCapability("ignoreZoomSetting", true);
	    capabilities.setCapability("ignoreProtectedModeSettings", true);
	    capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "http://nycitappstgclav1.timeinc.com:8010/");
		InternetExplorerOptions options = new InternetExplorerOptions();
		options.merge(capabilities);
		InternetExplorerDriver driver = new InternetExplorerDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		JavascriptExecutor myExecutor = ((JavascriptExecutor) driver);
		myExecutor.executeScript("arguments[0].value='cpgoms';", driver.findElement(By.id("loginName")));
		myExecutor.executeScript("arguments[0].value='supercpg11';", driver.findElement(By.id("loginPass")));
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='Login']")));
		JavascriptExecutor myExecutorClick = ((JavascriptExecutor) driver);
		myExecutorClick.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("input[name='Login']")));
		Thread.sleep(5000);
		//Clicking on the proceed to TimeExchange link
		myExecutorClick.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='center fine']>a")));
		
		
		Thread.sleep(5000);
		myExecutorClick.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[title='Menu for Distribution']")));
		Thread.sleep(5000);
		myExecutorClick.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[title='Define destination for subscriber copies']")));
		Thread.sleep(5000);
		//waitForVisibilty(byFrameXpath, 30);
		WebElement elementFrame=driver.findElement(By.cssSelector("iframe#frame_main"));
		driver.switchTo().frame(elementFrame);
		
		//myExecutorClick.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("td>select#magazine")));
		Thread.sleep(5000);
		WebElement wb= driver.findElement(By.cssSelector("select#magazine"));
		Select select= new Select(wb);
		select.selectByIndex(40);
		 
		 Thread.sleep(5000);
		 WebElement wb1= driver.findElement(By.cssSelector("select#plant"));
		 Select select1= new Select(wb1);
		 select1.selectByIndex(8);
		 
		 Thread.sleep(5000);
		 WebElement wb2= driver.findElement(By.cssSelector("select#issue_date"));
		 Select select2= new Select(wb2);
		 select2.selectByIndex(192);
		
		 
		 myExecutorClick.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a#findReels")));
		 Thread.sleep(5000);
		 myExecutorClick.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a#saveAsExcel")));
		 
		 driver.switchTo().defaultContent();
		  
		  String Parent_Window=driver.getWindowHandle();
         //
         System.out.println("To get the parent window title"+driver.getTitle());
         Set<String> s1=driver.getWindowHandles();
         
         Iterator<String>itr=s1.iterator();
         while(itr.hasNext()){
               String Child_Window= itr.next();
               
               if(!Parent_Window.equalsIgnoreCase(Child_Window)){
                     driver.switchTo().window(Child_Window);
                     System.out.println("To get the child window title"+driver.getTitle());
                     driver.close();
         }
                            
               
            }
          
		 
	}
	

}
