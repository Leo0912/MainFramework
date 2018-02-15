package pages.PetHero;

import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.testng.asserts.SoftAssert;
import utilities.SoftAssert;
import utilities.ApplicationGenericUtils;

public class FindAVetAsMemberPage {
	
	public WebDriver driver;
	public LinkedHashMap<String, By> objectRepository;
	ApplicationGenericUtils refApplicationGenericUtils;
	SoftAssert softAssert; 
	public static LinkedHashMap<Object, Object> tcParameters;
	public FindAVetAsMemberPage(WebDriver driver,LinkedHashMap<String, By> objectRepository,SoftAssert softAssert,LinkedHashMap<Object, Object> tcParameters){
		this.driver=driver;
		this.objectRepository=objectRepository;
		refApplicationGenericUtils=new ApplicationGenericUtils(driver);
		this.softAssert=softAssert;
		this.tcParameters=tcParameters;
	}		
	
	public void EnterValidZipCodeAndSearchforVet(){
		
		// spit the "98290.0" based on dot, to make use of only "98290" zipcode
		String strZip= tcParameters.get("Zipcode").toString();
		String reqzip=strZip.split("\\.")[0];
		refApplicationGenericUtils.checkForElement(objectRepository.get("FindAVetAsMemberPage.ZipcodeTextBox"), "ZipCodeTextBox");		
		refApplicationGenericUtils.setElementValue(objectRepository.get("FindAVetAsMemberPage.ZipcodeTextBox"), "ZipCodeTextBox",reqzip);
		refApplicationGenericUtils.clickonElementJS(objectRepository.get("FindAVetAsMemberPage.ZipcodeGoButton"), "GoButton");
	
	}
	
	public void EnterInValidZipCodeAndSearchforVet() throws InterruptedException{
		refApplicationGenericUtils.switchingToDefaultFrame();
		refApplicationGenericUtils.clickonElementJS(objectRepository.get("FindAVetAsMemberPage.FindAVetLink"), "Find A Vet");
		refApplicationGenericUtils.checkForElement(objectRepository.get("FindAVetAsMemberPage.ZipcodeTextBox"), "ZipCodeTextBox");
		Thread.sleep(2000);
		refApplicationGenericUtils.setElementValue(objectRepository.get("FindAVetAsMemberPage.ZipcodeTextBox"), "ZipCodeTextBox", "AZTPQRT");
		refApplicationGenericUtils.clickonElementJS(objectRepository.get("FindAVetAsMemberPage.ZipcodeGoButton"), "GoButton");
	
	}
	
	public int verifyDataList() throws InterruptedException{
		int size;
		Thread.sleep(5000);
		refApplicationGenericUtils.checkForElement(objectRepository.get("FindAVetAsMemberPage.Frame1"), "Iframe1");
		refApplicationGenericUtils.switchingFrame(objectRepository.get("FindAVetAsMemberPage.Frame1"));
		List<WebElement> numberOfVetsElements=driver.findElements(objectRepository.get("FindAVetAsMemberPage.NoOfVetsInDataList"));
		size=numberOfVetsElements.size();
		return size;
	}
	
	public void clickOnPetHeroHeader(){
		refApplicationGenericUtils.switchingToDefaultFrame();
		refApplicationGenericUtils.clickonElementJS(objectRepository.get("FindAVetAsMemberPage.PetHeroHeaderLogo"), "Clicked on PetHero header logo");
	}
		
	public void clickOnLoadMoreButton(){
		refApplicationGenericUtils.checkForElement(objectRepository.get("FindAVetAsMemberPage.LoadMoreButton"), "LoadMoreButton");
		refApplicationGenericUtils.clickonElementJS(objectRepository.get("FindAVetAsMemberPage.LoadMoreButton"), "LoadMoreButton");
	
	}
	

}
