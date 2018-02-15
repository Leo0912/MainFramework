package pages.NSG;

import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.testng.asserts.SoftAssert;
import utilities.SoftAssert;
import utilities.ApplicationGenericUtils;

public class Collection_Page {
	
	//Declaration
	public WebDriver driver;
	public LinkedHashMap<String, By> objectRepository;
	ApplicationGenericUtils refApplicationGenericUtils;
	SoftAssert softAssert;
			
	public Collection_Page(WebDriver driver,LinkedHashMap<String, By> objectRepository,SoftAssert softAssert){
		this.driver=driver;
		this.objectRepository=objectRepository;
		refApplicationGenericUtils=new ApplicationGenericUtils(driver);
		this.softAssert=softAssert;
		
	}
	
	public void collectionPageGridAndAdValidation(String appName) throws InterruptedException{
		
		switch(appName.toUpperCase()){
		
		case "TIME":
			
			refApplicationGenericUtils.clickOnElement(objectRepository.get("GlobalElements.HamburgerMenu"), "Hamburger Menu");
		//	refApplicationGenericUtils.clickOnElement(objectRepository.get("GlobalElements.HamburgerMenuTheInfluencers"), "The Influencers");
		    refApplicationGenericUtils.checkForElement(objectRepository.get("GlobalElements.HamburgerMenuTheInfluencers"), "The Influencers");
			WebElement infylink = driver.findElement(objectRepository.get("GlobalElements.HamburgerMenuTheInfluencers"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", infylink);
			
			
			
			refApplicationGenericUtils.checkForElement(objectRepository.get("CollectionPageElements.TimeRightRailAdUnit"), "Right Rail Ad Unit");
			refApplicationGenericUtils.scrollToViewElement(objectRepository.get("CollectionPageElements.TimeRightRailAdUnit"), "Scroll To View RightRailAdUnit");
			refApplicationGenericUtils.verifyAdsDimension(objectRepository.get("CollectionPageElements.TimeRightRailAdUnit"), "300X250|300X600");
	
		break;
		
		case "MONEY":
			
			refApplicationGenericUtils.clickOnElement(objectRepository.get("CollectionPageElements.BestPlacestoLive2017"), "Best Places to Live 2017");
			refApplicationGenericUtils.verifyAdsDimension(objectRepository.get("CollectionPageElements.TopAdUnit"), "970X250");
			refApplicationGenericUtils.collectionImageGridValidation(objectRepository.get("CollectionPageElements.FirstTenGridImageTouts"), "First Ten Grid Images");
			refApplicationGenericUtils.checkForMultipleElements(objectRepository.get("CollectionPageElements.SubNavigationBar"), "Sub Navigation Bar Elements");
			
		break;
		
		}
		
	}

}
