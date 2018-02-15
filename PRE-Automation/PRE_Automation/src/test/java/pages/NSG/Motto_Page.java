package pages.NSG;

import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
//import org.testng.asserts.SoftAssert;
import utilities.SoftAssert;
import utilities.ApplicationGenericUtils;

public class Motto_Page {
	
	//Declaration
		public WebDriver driver;
		public LinkedHashMap<String, By> objectRepository;
		ApplicationGenericUtils refApplicationGenericUtils;
		SoftAssert softAssert;
	
		public Motto_Page(WebDriver driver,LinkedHashMap<String, By> objectRepository,SoftAssert softAssert){
		
			this.driver=driver;
			this.objectRepository=objectRepository;
			refApplicationGenericUtils=new ApplicationGenericUtils(driver);
			this.softAssert=softAssert;
		}
		
		public void mottoPageContentValidation(String appName){
			
			refApplicationGenericUtils.clickOnElement(objectRepository.get("GlobalElements.HamburgerMenu"), "Hamburger Menu");
			refApplicationGenericUtils.clickOnElement(objectRepository.get("GlobalElements.HamburgerMenuMotto"), "Hamburger Menu Motto");
			refApplicationGenericUtils.checkForElement(objectRepository.get("MottoPageElements.MottoLogo"), "Motto Logo");
			refApplicationGenericUtils.checkForShareTools(objectRepository.get("MottoPageElements.HeaderShareTools"), "Share Tools");
			refApplicationGenericUtils.checkForElement(objectRepository.get("MottoPageElements.HeaderSearchIcon"), "Search Icon");
			refApplicationGenericUtils.checkForElement(objectRepository.get("MottoPageElements.NewsLetterSignUp"), "NewsLetter SignUp");
			refApplicationGenericUtils.verifyAdsDimension(objectRepository.get("MottoPageElements.LandingLeaderBoardAdUnit"), "970X250");
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,1000)", "");
			refApplicationGenericUtils.verifyAdsDimension(objectRepository.get("MottoPageElements.RightRailAdUnit"), "300X250|300X600");
			
		}

}
