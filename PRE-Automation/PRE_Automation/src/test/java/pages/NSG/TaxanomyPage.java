package pages.NSG;

import static org.testng.Assert.assertEquals;

import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
//import org.testng.asserts.SoftAssert;
import utilities.SoftAssert;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;
import utilities.ApplicationGenericUtils;

public class TaxanomyPage
{
	public WebDriver driver;
	public LinkedHashMap<String, By> objectRepository;
	ApplicationGenericUtils refApplicationGenericUtils;
	SoftAssert softAssert;
	
	public TaxanomyPage(WebDriver driver,LinkedHashMap<String, By> objectRepository,SoftAssert softAssert){
		this.driver=driver;
		this.objectRepository=objectRepository;
		refApplicationGenericUtils=new ApplicationGenericUtils(driver);
		this.softAssert=softAssert;
	}
	
	
	
	
	public void navigateToTaxonomyPage(String appname){
		
	//click on the Featured Article
	refApplicationGenericUtils.clickOnElement(objectRepository.get("GlobalElements.FeaturedArticle"), "Featured Article");
		
	// Verify if sectional Tag is displayed.
	refApplicationGenericUtils.checkForElement(objectRepository.get("ArticlePageElements.Tag"), "Tag");
	
	
	//Click on the  tag.
	refApplicationGenericUtils.clickOnElement(objectRepository.get("ArticlePageElements.Tag"), "Tag");
	
	//Scroll till the news letter Signup.
	subscribeNewsLetter();

	}
	
	
	public void subscribeNewsLetter()
	{
		//verify newsLettersignupModule is displayed.
		refApplicationGenericUtils.checkForElement(objectRepository.get("TaxonomyPage.SubscribeNewsLetter"), "SubscribeNewsLetter");
		
		
		//scroll till the new letter section
		refApplicationGenericUtils.scrollToViewElement(objectRepository.get("TaxonomyPage.SubscribeNewsLetter"), "SubscribeNewsLetter");
		
		
		//Click on the subscribe
		
		
	}
	
	
	
	
	
	
	
	}