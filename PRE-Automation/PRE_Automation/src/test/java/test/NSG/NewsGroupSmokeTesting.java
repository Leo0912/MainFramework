package test.NSG;
import pages.NSG.Article_Page;
import pages.NSG.Collection_Page;
import pages.NSG.GlobalPages;
import pages.NSG.Motto_Page;
import pages.NSG.NSGLoginPage;
import pages.NSG.TaxanomyPage;
import pages.PetHero.PetHeroLoginPage;

import java.util.LinkedHashMap;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.asserts.SoftAssert;
import utilities.SoftAssert;
import test.FrameworkDriverScript;
import constants.Constants;

public class NewsGroupSmokeTesting extends FrameworkDriverScript {
	

	  public void globalHeaderValidation() throws Exception{
		    //System.setProperty("webdriver.gecko.driver", Constants.FF_DRIVER_PATH);
			//WebDriver driver = new FirefoxDriver();
			//Login_Page refLogin_Page=new Login_Page(driver,objectRepository);
			NSGLoginPage refLogin_Page=new NSGLoginPage(driverBrowserStack,objectRepository);
			WebDriver driverLoginPage=refLogin_Page.logintoApplication(envDetails);
			GlobalPages refGlobalPages=new GlobalPages(driverLoginPage,objectRepository,softAssert);
			refGlobalPages.globalHeaderValidation(applicationName,deviceType);
		  
	  }
	
	//=========================Clinton Space
	
	
	  public void articlePageValidation() throws Exception{
		  	//System.setProperty("webdriver.gecko.driver", Constants.FF_DRIVER_PATH);
			//WebDriver driver = new FirefoxDriver();
			//Login_Page refLogin_Page=new Login_Page(driver,objectRepository);
			NSGLoginPage refLogin_Page=new NSGLoginPage(driverBrowserStack,objectRepository);
			WebDriver driverArticlePage=refLogin_Page.logintoApplication(envDetails);
			//GlobalPages refGlobalPages=new GlobalPages(driverLoginPage,objectRepository);
			//refGlobalPages.globalHeaderValidation(applicationName);
			Article_Page refArticlePage = new Article_Page(driverArticlePage,objectRepository,softAssert);
			refArticlePage.topRecircValidation(applicationName);
			refArticlePage.headerContentsValidation(applicationName);
			refArticlePage.rightRailContentsValidation(applicationName);
			refArticlePage.postArticleContentsValidation(applicationName);
			refArticlePage.aboveFooter(applicationName);
			driverArticlePage.quit();
		
	  }
	  
	  public void collectionPageValidation() throws Exception{
		  
		  System.setProperty("webdriver.gecko.driver", Constants.FF_DRIVER_PATH);
		  WebDriver driver = new FirefoxDriver();
		  NSGLoginPage refLogin_Page=new NSGLoginPage(driver,objectRepository);
		  
		//  Login_Page refLogin_Page=new Login_Page(driverBrowserStack,objectRepository);
		  WebDriver driverCollectionPage=refLogin_Page.logintoApplication(envDetails);
		  Collection_Page refCollectionPage = new Collection_Page(driverCollectionPage, objectRepository,softAssert);
		  refCollectionPage.collectionPageGridAndAdValidation(applicationName);
		  driver.quit();
		  
	  }
	  
	  public void mottoPageValidation(){
		  NSGLoginPage refLogin_Page;
		  if(appName.containsKey("Local Machine")){
			System.setProperty("webdriver.gecko.driver", Constants.FF_DRIVER_PATH);
			WebDriver driver = new FirefoxDriver();
			refLogin_Page=new NSGLoginPage(driver,objectRepository);
			  
		  }else{
			refLogin_Page=new NSGLoginPage(driverBrowserStack,objectRepository);
		 }
		  
		  
		  
		  //Login_Page refLogin_Page=new Login_Page(driverBrowserStack,objectRepository);
		  WebDriver driverCollectionPage=refLogin_Page.logintoApplication(envDetails);
		  Motto_Page refmottoPage = new Motto_Page(driverCollectionPage, objectRepository,softAssert);
		  refmottoPage.mottoPageContentValidation(applicationName);
		  
		  driver.quit();
		  
	  }
	  
	  
	        //=========================Manish Space
		
		  public void globalFooterValidation() throws Exception{
			//System.setProperty("webdriver.gecko.driver", Constants.RESOURCES_PATH+"geckodriver.exe");
			//WebDriver driver = new FirefoxDriver();
			//Login_Page refLogin_Page=new Login_Page(driver,objectRepository);
			NSGLoginPage refLogin_Page=new NSGLoginPage(driverBrowserStack,objectRepository);
			WebDriver driverLoginPage=refLogin_Page.logintoApplication(envDetails);
			GlobalPages refGlobalPages=new GlobalPages(driverLoginPage,objectRepository,softAssert);
			refGlobalPages.globalFooterValidation(applicationName);
		  }
	
		  /**@author ojham
		   * Desc- Validates Market data module, Right trail Ad, TopFeaturedStory 
		   * “Sign up for the Fortune Daily”, 'Retire with money ' Sign up module ,
		   * subscribe Ad Unit below the list of Articles, Ad below the  Retire with money sign up section
		   * 
		   * 
		   */
		  
		  
		   public void homePageValidation(){
			  
			    //System.setProperty("webdriver.gecko.driver", Constants.RESOURCES_PATH+"geckodriver.exe");
				//WebDriver driver = new FirefoxDriver();
				//Login_Page refLogin_Page=new Login_Page(driver,objectRepository);
				NSGLoginPage refLogin_Page=new NSGLoginPage(driverBrowserStack,objectRepository);
				WebDriver driverLoginPage=refLogin_Page.logintoApplication(envDetails);
				GlobalPages refGlobalPages=new GlobalPages(driverLoginPage,objectRepository,softAssert);
				refGlobalPages.homePageValidation(applicationName);
		  }
		    public void taxonomyPageValidation()
		   {
			   //System.setProperty("webdriver.gecko.driver", Constants.RESOURCES_PATH+"geckodriver.exe");
				//WebDriver driver = new FirefoxDriver();
				//Login_Page refLogin_Page=new Login_Page(driver,objectRepository);
				NSGLoginPage refLogin_Page=new NSGLoginPage(driverBrowserStack,objectRepository);
				WebDriver driverLoginPage=refLogin_Page.logintoApplication(envDetails);
				TaxanomyPage taxanomypage = new TaxanomyPage(driverLoginPage, objectRepository,softAssert);
				taxanomypage.navigateToTaxonomyPage(applicationName);
				
				
			   
			   
		   }
}
