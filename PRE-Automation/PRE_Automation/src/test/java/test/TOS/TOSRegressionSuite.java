package test.TOS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import constants.Constants;
import pages.PetHero.PetHeroLoginPage;
import pages.TOS.NewConsigneePage;
import pages.TOS.TOSLoginPage;
import test.FrameworkDriverScript;
public class TOSRegressionSuite extends FrameworkDriverScript {
	
	  public void createConsigneeValidation() throws Exception{
		  TOSLoginPage refTOSLoginPage;
		  if(appName.containsKey("Local Machine")){
			System.setProperty("webdriver.gecko.driver", Constants.FF_DRIVER_PATH);
			WebDriver driver = new FirefoxDriver();
			refTOSLoginPage=new TOSLoginPage(driver,objectRepository);
			  
		  }else{
			  refTOSLoginPage=new TOSLoginPage(driverBrowserStack,objectRepository);
		 }
			WebDriver driverLoginPage=refTOSLoginPage.logintoApplication(envDetails);
			NewConsigneePage refNewConsigneePage=new NewConsigneePage(driverLoginPage,objectRepository,softAssert,tcParameters);
			refNewConsigneePage.createConsignee(applicationName);
		
	  }
	  
	  public void searchConsigneeValidation() throws Exception{
		  
		  TOSLoginPage refTOSLoginPage;
		  if(appName.containsKey("Local Machine")){
			System.setProperty("webdriver.gecko.driver", Constants.FF_DRIVER_PATH);
			WebDriver driver = new FirefoxDriver();
			refTOSLoginPage=new TOSLoginPage(driver,objectRepository);
			  
		  }else{
			  refTOSLoginPage=new TOSLoginPage(driverBrowserStack,objectRepository);
		 }
			WebDriver driverLoginPage=refTOSLoginPage.logintoApplication(envDetails);
			NewConsigneePage refNewConsigneePage=new NewConsigneePage(driverLoginPage,objectRepository,softAssert,tcParameters);
			refNewConsigneePage.searchConsignee(applicationName);
	  }
	 
}
