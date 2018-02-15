package pages.NSG;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
//import org.testng.asserts.SoftAssert;
import utilities.SoftAssert;
import utilities.ApplicationGenericUtils;




@SuppressWarnings("unused")
public class GlobalPages {
	//Declaration
	public WebDriver driver;
	public LinkedHashMap<String, By> objectRepository;
	ApplicationGenericUtils refApplicationGenericUtils;
	SoftAssert softAssert;
	
	public GlobalPages(WebDriver driver,LinkedHashMap<String, By> objectRepository,SoftAssert softAssert){
		this.driver=driver;
		this.objectRepository=objectRepository;
		refApplicationGenericUtils=new ApplicationGenericUtils(driver);
		this.softAssert=softAssert;
	}
	/**
	 * globalHeaderValidation - Perform validation on Header & Footer
	 * @param appName
	 */
	public void globalHeaderValidation(String appName,String deviceType){
		refApplicationGenericUtils.checkForElement(objectRepository.get("GlobalElements.HamburgerMenu"), "GlobalElements.HamburgerMenu");
		refApplicationGenericUtils.checkForElement(objectRepository.get("GlobalElements.AppLogo"), "AppLogo");
		globalSignInValidation(appName,deviceType);
		refApplicationGenericUtils.clickOnElement(objectRepository.get("GlobalElements.Subscribe"), "Subscribe");
		refApplicationGenericUtils.checkForElement(objectRepository.get("GlobalElements.SubscribeHeader"),"SubscribeHeader");
	}
	/**
	 * Sign-In Field UI Validation
	 * @param appName - Time,Money,Fortune
	 */
	public void globalSignInValidation(String appName,String deviceType)
	{
		
		switch(appName.toUpperCase()){
		case "TIME":
		case "FORTUNE":
			if (appName.equalsIgnoreCase("FORTUNE")|| !deviceType.equals("Desktop")){
				refApplicationGenericUtils.clickOnElement(objectRepository.get("GlobalElements.HamburgerMenu"), "HamburgerMenu");
			}
			refApplicationGenericUtils.checkForElement(objectRepository.get("GlobalElements.SignIn"), "SignIn");
			refApplicationGenericUtils.clickOnElement(objectRepository.get("GlobalElements.SignIn"), "SignIn");
			if (appName.equalsIgnoreCase("TIME")){
				refApplicationGenericUtils.waitForElement(objectRepository.get("SignIn.CloseButton"), 20);
				refApplicationGenericUtils.switchingFrame(objectRepository.get("SignIn.Frame"));
			}
			
			refApplicationGenericUtils.checkForElement(objectRepository.get("SignIn.UserName"), "UserName");	
			refApplicationGenericUtils.checkForElement(objectRepository.get("SignIn.Password"), "Password");	
			refApplicationGenericUtils.checkForElement(objectRepository.get("SignIn.RememberMe"), "RememberMe");	
			refApplicationGenericUtils.checkForElement(objectRepository.get("SignIn.ForgotPassword"), "ForgotPassword");	
			driver.switchTo().parentFrame();
			refApplicationGenericUtils.clickOnElement(objectRepository.get("SignIn.CloseButton"), "SignInClose");
			
			break;
		case "MONEY":
			return;
		default: 
		}
	}
	
                 //	*************************Footer************************//
	
	
	/**
	 * globalFooterValidation - Perform validation on Footer
	 * @param appName - Time , Money , Fortune
	 * @throws InterruptedException 
	 */
	
	public void globalFooterValidation(String appName) throws InterruptedException 
	{
		refApplicationGenericUtils.scrolltoBottom();
		refApplicationGenericUtils.checkForElement(objectRepository.get("GlobalElements.Copyright"), "Copyright");
		refApplicationGenericUtils.checkForElement(objectRepository.get("GlobalElements.WordPress"), "Wordpress");
		refApplicationGenericUtils.checkForElement(objectRepository.get("GlobalElements.Disclaimer"), "Disclaimer");
		globalFooterLinksValidation(appName);
	}	
	
	
	/**
	 * globalFooterLLinksValidation - Perform validation on FooterLinks whether All the links are clickable.
	 * @param appName - Time , Money , Fortune
	 * @throws InterruptedException 
	 */
	
	public void globalFooterLinksValidation(String appName)
	{
		String homepageurl=null;
		List<WebElement> footerLinks;
		List<WebElement>staticFooterLinks;
		List<WebElement>shareFooterLinks;
		
		switch(appName.toUpperCase())
		{
		case "TIME":
			 homepageurl=driver.getCurrentUrl();
			 
	         footerLinks = refApplicationGenericUtils.visibilityOfElements(objectRepository.get("GLobalElements.FooterLinks"), 20);
	         verifyLinksInFooter(footerLinks, homepageurl);
			 break;
		
		case "MONEY":
		     homepageurl=driver.getCurrentUrl();
		  
	         footerLinks = refApplicationGenericUtils.visibilityOfElements(objectRepository.get("GLobalElements.FooterLinks"), 20);
	         verifyLinksInFooter(footerLinks, homepageurl);
	         break;
	   	   
		case "FORTUNE":
			  homepageurl=driver.getCurrentUrl();
//			  
//		      footerLinks = refApplicationGenericUtils.visibilityOfElements(objectRepository.get("GLobalElements.FooterLinks"), 20);
//		      verifyLinksInFooter(footerLinks, homepageurl);
//		           
//		      staticFooterLinks=  refApplicationGenericUtils.visibilityOfElements(objectRepository.get("GLobalElements.staticFooterLinks"), 20);
//		      verifyLinksInFooter(staticFooterLinks, homepageurl);
//		      
		      driver.get(homepageurl);
		      verifySocialLinks(objectRepository.get("GlobalElements.FooterShareLinks"));
		      break; 
		}  	   
	 }
	

	/**
	 *verifyLinksInFooter- It Click on individual link in the footer and Navigate back to home page.
	 * @param footerlinks
	 * @param homepageurl
	 */
	
	public void verifyLinksInFooter(List<WebElement> footerlinks,String homepageurl)
	{
		LinkedHashMap<String,String> footerUrls = new LinkedHashMap<String,String>();

		for(WebElement footerOptions:footerlinks)// Change Naming Convention
	     {
	      footerUrls.put(footerOptions.getText(), footerOptions.getAttribute("href"));  
	      Reporter.log(footerOptions.getText(), true);
	     }
		
	     Set<String> optionInFooter = footerUrls.keySet();// Change Naming Convention
	     
	     for(String urlForOption: optionInFooter)// Change Naming Convention
	      {
	         System.out.println(footerUrls.get(urlForOption)); 
		     driver.navigate().to(footerUrls.get(urlForOption));
		     
		      try{
		    	  
			  driver.findElement(objectRepository.get("GLobalElements.FooterSubscribe"));
			  Reporter.log(urlForOption+"is displayed", true);
			  driver.navigate().to(homepageurl);
			//refApplicationGenericUtils.clickOnElement(objectRepository.get("GLobalElements.Logo"), "GLobalElements.Logo");
			 refApplicationGenericUtils.scrolltoBottom();
		         }
		     catch(Exception subscribeFooter)// Change Naming Convention
		       {
		  		 try{
		  			driver.findElement(objectRepository.get("GlobalElements.AppLogo"));
		  			Reporter.log(urlForOption+"is displayed", true);
		  			
		  			}
		  		catch(Exception Applogo)// Change Naming Convention
		  			{
		  					if(driver.findElement(By.tagName("body"))!=null)
		  					{
		  						Reporter.log(urlForOption+"is displayed", true);
		  						driver.navigate().to(homepageurl);
		  					}
	 	             } 
	         }  
	   
	    }
	 
	}
	
	
	/**
	 * verifySocialLinks- It clicks on the share Links in the Footer and navigate to the Respective Page , 
	 * get the and title and then navigate back.
	 * @param shareLinks
	 */	
	
public void verifySocialLinks(By shareLinks)
{
     String homewindow = driver.getWindowHandle();
     refApplicationGenericUtils.scrolltoBottom();
     
     
     // Add Comments
     
     List<WebElement> sharelinks = driver.findElements(shareLinks);
	    for (int i=1;i<=sharelinks.size();i++ )
	     {
	    	try 
			  {
		       By shareLink = By.xpath("(//ul[@class='footer-social']//li//a//span)["+i+"]");
		       refApplicationGenericUtils.clickOnElement(shareLink, "ShareLinks");
		       HashSet<String> noofwindows = (HashSet<String>) driver.getWindowHandles();
		       
		       		//switching to different tab.
		            refApplicationGenericUtils.switchingTabs(homewindow, noofwindows);
		            
		            
			        refApplicationGenericUtils.waitForVisibilty(By.xpath("//body"), 30);
			        
			        if (driver.findElement(By.tagName("body"))!=null)
				     {
			         refApplicationGenericUtils.waitForTitle(driver.getTitle());
					 Reporter.log(driver.getTitle()+" page is displayed", true);
				     } 
			        driver.close();
			        driver.switchTo().window(homewindow);
		    	 
			    }    
				 catch(Exception E)
				    {
						 E.printStackTrace();
						 Reporter.log("Unable to switch to window"+E.getMessage(),true);
						 softAssert.fail("Unable to switch to window"+E.getMessage());
					}  
				
	     }	
}
//			      for (String window: noofwindows)
//			      {
//				       
//				        refApplicationGenericUtils.waitForVisibilty(By.xpath("//body"), 30);
//				         if (driver.findElement(By.tagName("body"))!=null)
//					     {
//						 Reporter.log(driver.getTitle()+" page is displayed", true);
//					     } 
//			      }
//			      
			     
//			          driver.close();
//				      driver.switchTo().window(homewindow);
		   

		
         /**
          * validatingHomePage- It verifies Right trail ad,Market Data module," Sign up for the Fortune Daily” 
          * Retire with money 'Sign up module, Ad below the Retire With Money for time , Money and fortune
          * @param-appname 
          * 
          */	

	    public void homePageValidation(String appname)
	    {
	    	
	    	switch(appname.toUpperCase())
			{
	        case "TIME":
	        refApplicationGenericUtils.checkForElement(objectRepository.get("GLobalElements.Headline"), "BriefHeadline");
		    softAssert.assertTrue(refApplicationGenericUtils.verifyingText(objectRepository.get("GLobalElements.Headline"), "THE BRIEF"));
		    
		    refApplicationGenericUtils.checkForElement(objectRepository.get("GlobalElements.TopFeaturedImage"), "TopFeaturedImage");
		    
	    	refApplicationGenericUtils.checkForElement(objectRepository.get("GlobalElements.LeftTrailAd"), "LeftTrailAd");
	    	refApplicationGenericUtils.verifyAdsDimension(objectRepository.get("GlobalElements.LeftTrailAd"), "300X250|300X600|728X90");
	    	
	    	refApplicationGenericUtils.checkForElement(objectRepository.get("GlobalElements.BriefSignupModule"), "BriefSignupModule");
	    	refApplicationGenericUtils.checkForElement(objectRepository.get("GlobalElements.BriefSignupButton"), "BriefSignupButton");
	    	
	    	refApplicationGenericUtils.checkForElement(objectRepository.get("GlobalElements.AdBelowListOfArticles"), "AdBelowListOfArticles");
	    	refApplicationGenericUtils.verifyAdsDimension(objectRepository.get("GlobalElements.AdBelowListOfArticles"), "300X250|300X600|728X90");
	    	
	    	break;
	    	
	        case "MONEY":
	        	 
	        refApplicationGenericUtils.checkForElement(objectRepository.get("GlobalElements.MarketDataModule"), "MarketDataModule");
	        
	 	    refApplicationGenericUtils.checkForElement(objectRepository.get("GlobalElements.RightTrailAd"), "RightTrailAd");
	 	    refApplicationGenericUtils.verifyAdsDimension(objectRepository.get("GlobalElements.RightTrailAd"), "300X250|300X600|728X90");
	 	   
	 	    refApplicationGenericUtils.checkForElement(objectRepository.get("GlobalElements.Retire with money"), "Retire with money");
	 	    refApplicationGenericUtils.checkForElement(objectRepository.get("GlobalElements.Retire with moneySignupButton"), "Retire with moneySignupButton");
	 	    
	 	    refApplicationGenericUtils.checkForElement(objectRepository.get("GlobalElements.AdBelowRetireWithMoney"), "AdBelowRetireWithMoney");
	 	    refApplicationGenericUtils.verifyAdsDimension(objectRepository.get("GlobalElements.AdBelowRetireWithMoney"), "300X250|300X600|728X90");
	 	    
	 	    break;
	 	    
	        case "FORTUNE":
	        refApplicationGenericUtils.checkForElement(objectRepository.get("GlobalElements.TopFeaturedImage"),"TopFeaturedImage");
	        
	        refApplicationGenericUtils.checkForElement(objectRepository.get("GlobalElements.MarketDataModule"), "MarketDataModule");
	        
	 	 	refApplicationGenericUtils.checkForElement(objectRepository.get("GlobalElements.SignupFortuneDaily"), "SignupFortuneDaily");
	 	 	
	 	 	refApplicationGenericUtils.checkForElement(objectRepository.get("GlobalElements.RightTrailAd"), "RightTrailAd"); 
	 	 	refApplicationGenericUtils.verifyAdsDimension(objectRepository.get("GlobalElements.RightTrailAd"), "300X250|300X600|728x90");
	        break;
	    }
	    }
	
	
	  
	    
	 
	    
}
