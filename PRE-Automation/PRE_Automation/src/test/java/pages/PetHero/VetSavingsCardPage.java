package pages.PetHero;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//import org.testng.asserts.SoftAssert;
import utilities.SoftAssert;
import utilities.ApplicationGenericUtils;

public class VetSavingsCardPage {
	
	public WebDriver driver;
	public LinkedHashMap<String, By> objectRepository;
	ApplicationGenericUtils refApplicationGenericUtils;
	SoftAssert softAssert; 
	public static LinkedHashMap<Object, Object> tcParameters;
	public VetSavingsCardPage(WebDriver driver,LinkedHashMap<String, By> objectRepository,SoftAssert softAssert,LinkedHashMap<Object, Object> tcParameters){
		this.driver=driver;
		this.objectRepository=objectRepository;
		refApplicationGenericUtils=new ApplicationGenericUtils(driver);
		this.softAssert=softAssert;
		this.tcParameters=tcParameters;
	}
	
	public void validateMemberPageHeader() throws InterruptedException{
	       
		String expectedMemberDate=ApplicationGenericUtils.currentDateinMMDDYY();
		Thread.sleep(5000);
		refApplicationGenericUtils.waitForElement(objectRepository.get("VetSavingsCardPage.YoursPetHeroSince"),60);
	    refApplicationGenericUtils.verifyingText(objectRepository.get("VetSavingsCardPage.YoursPetHeroSince"),tcParameters.get("Header Name").toString());
	    String actualMemberDate = refApplicationGenericUtils.getElementValueJS(objectRepository.get("VetSavingsCardPage.MemberDate"),"MemberDate");
	    if(!actualMemberDate.contains(expectedMemberDate)){
	    	System.out.println("Member Date does not match. Expected ["+expectedMemberDate+"] but actual ["+actualMemberDate+"]");
	    	ApplicationGenericUtils.extentReportLogFAIL("Member Date does not match. Expected ["+expectedMemberDate+"] but actual ["+actualMemberDate+"]");
	    } else {
	    	ApplicationGenericUtils.extentReportLogPASS("Member Date value matched");
	    }
		}
	
	public void validateMemberNameVetSavingsCard(){
	    String accountName =tcParameters.get("FirstName")+" "+tcParameters.get("LastName");
	    refApplicationGenericUtils.verifyingText(objectRepository.get("VetSavingsCardPage.MemberCardName"),accountName);
    
	}
	
	public void validateMemberAddressVetSavingsCard() throws InterruptedException{
		Thread.sleep(3000);
	    String addressLineOne =tcParameters.get("Address")+",";
	    refApplicationGenericUtils.verifyingText(objectRepository.get("VetSavingsCardPage.MemberAddressLine1"),addressLineOne);
	    refApplicationGenericUtils.verifyingText(objectRepository.get("VetSavingsCardPage.MemberAddressLine2"),tcParameters.get("Address Line2").toString());
	    
		}
	
	public String getMemberIdOnVetCard() throws InterruptedException{
	    String memberIdOnVetCard;
	    Thread.sleep(2000);
	    refApplicationGenericUtils.checkForElement(objectRepository.get("VetSavingsCardPage.MemberIDValue"),"MemberId on Vet Saving Card");
	    memberIdOnVetCard=refApplicationGenericUtils.getElementValue(objectRepository.get("VetSavingsCardPage.MemberIDValue"),"MemberId on Vet Card");
	    return memberIdOnVetCard;
		}
	
	public void validateExpiryDate(){
		String expiryDate=ApplicationGenericUtils.getExpiryDate("Month");
		refApplicationGenericUtils.verifyingText(objectRepository.get("VetSavingsCardPage.MemberExpiresValue"),expiryDate);
		
	}
	
	
	public void validatePrintmembercardAndFindAVetElements(){
		
		refApplicationGenericUtils.checkForElement(objectRepository.get("VetSavingsCardPage.PrintMemberCardEnabled"), "Print Membership Card");
		refApplicationGenericUtils.checkForElement(objectRepository.get("VetSavingsCardPage.FindAVetLink"), "Find a Vet");
	}
	
	public void linksOnPerHeroAndAssure(){
		refApplicationGenericUtils.checkForElement(objectRepository.get("VetSavingsCardPage.OnPetAssureLink1"), "www.pethero.com--Link");
		refApplicationGenericUtils.checkForElement(objectRepository.get("VetSavingsCardPage.OnPetAssureLink2"), "www.pethero.com/validate--Link");
		refApplicationGenericUtils.checkForElement(objectRepository.get("VetSavingsCardPage.OnPetHeroLink"), "www.pethero.com/findavet--Link");
		
	}
	
	public void VerifypetHeroAndPetAssureImages(){
					
		//Header PetHero Image should be clickable
		refApplicationGenericUtils.checkForElement(objectRepository.get("VetSavingsCardPage.PetHeroHeaderLogo"), "PetHeroHeaderLogo");
		//Footer PetHero Image should be non clickable
	    refApplicationGenericUtils.checkForElement(objectRepository.get("VetSavingsCardPage.PetHeroFooterLogo"), "PetHeroFooterLogo");
		//PetHero Image should be non-clickable
	    refApplicationGenericUtils.checkForElement(objectRepository.get("VetSavingsCardPage.PetHeroCardLogo"), "PetHeroCardLogo");
		//PetAssure Image should be non-clickable
	    refApplicationGenericUtils.checkForElement(objectRepository.get("VetSavingsCardPage.PetAssureLogo"),"PetAssureLogo");
	    refApplicationGenericUtils.checkForElement(objectRepository.get("VetSavingsCardPage.PetAssureLogoOnCard"),"PetAssureLogoOnCard");
	}
	
	public void clickonFindAVetAsMemberInHeader(){
		refApplicationGenericUtils.checkForElement(objectRepository.get("VetSavingsCardPage.FindAVetAsMemberInHeader"),"FindAVetAsMemberInHeader");
		//refApplicationGenericUtils.clickOnElement(objectRepository.get("VetSavingsCardPage.FindAVetAsMemberInHeader"),"FindAVetAsMemberInHeader");
		refApplicationGenericUtils.clickonElementJS(objectRepository.get("VetSavingsCardPage.FindAVetAsMemberInHeader"),"FindAVetAsMemberInHeader");
	}
}
