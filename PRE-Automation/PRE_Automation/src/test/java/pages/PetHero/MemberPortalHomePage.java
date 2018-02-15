package pages.PetHero;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//import org.testng.asserts.SoftAssert;
import utilities.SoftAssert;
import constants.Constants;

import utilities.ApplicationGenericUtils;

public class MemberPortalHomePage {
	
	public static WebDriver driver;
	public LinkedHashMap<String, By> objectRepository;
	ApplicationGenericUtils refApplicationGenericUtils;
	ViewMemberPortalPage memberPortalPage;
	SoftAssert softAssert; 
	public static LinkedHashMap<Object, Object> tcParameters;
	public MemberPortalHomePage(WebDriver driver,LinkedHashMap<String, By> objectRepository,SoftAssert softAssert,LinkedHashMap<Object, Object> tcParameters){
		this.driver=driver;
		this.objectRepository=objectRepository;
		refApplicationGenericUtils=new ApplicationGenericUtils(driver);
		this.softAssert=softAssert;
		this.tcParameters=tcParameters;
	}
	
	public String getMemberId(){
		String memberId;
		refApplicationGenericUtils.waitForElement(objectRepository.get("MemberPortalHomePage.MemberCardId"), 25);
		memberId=refApplicationGenericUtils.getElementValue(objectRepository.get("MemberPortalHomePage.MemberCardId"), "MemberCardId fetched");
		return memberId;
		
			}
	
	public void verifyAndlickOnPrintVetSavingsCardLink(){
		refApplicationGenericUtils.waitForElement(objectRepository.get("MemberPortalHomePage.PrintVetSavingsCardLink"), 120);
		refApplicationGenericUtils.verifyingText(objectRepository.get("MemberPortalHomePage.PrintVetSavingsCardLink"), "Print Vet Savings Card");
		refApplicationGenericUtils.clickOnElement(objectRepository.get("MemberPortalHomePage.PrintVetSavingsCardLink"), "Print Vet Savings Card");
	
		}
	
	public void verifyAndlickOnConnectWithMembersLink() throws InterruptedException{
		refApplicationGenericUtils.waitForElement(objectRepository.get("MemberPortalHomePage.ConnectWithMembersLink"), 25);
		refApplicationGenericUtils.clickOnElement(objectRepository.get("MemberPortalHomePage.ConnectWithMembersLink"), "Connect With MembersLink");
		refApplicationGenericUtils.windowHandle(objectRepository.get("MemberPortalHomePage.ConnectWithMembersLink"), objectRepository.get("MemberPortalHomePage.FacebookRef"), "Facebook");
		
			}
			
	public void loginMemberPortal(String emailID){
		refApplicationGenericUtils.setElementValue(objectRepository.get("MemberPortalHomePage.Usernamebox"), "Usernamebox", emailID);
		refApplicationGenericUtils.setElementValue(objectRepository.get("MemberPortalHomePage.Passwordbox"), "Passwordbox", tcParameters.get("Password"));
		refApplicationGenericUtils.clickOnElement(objectRepository.get("MemberPortalHomePage.LoginButton"), "LoginButton");
	}
	
	public void verifyAccountLink() throws InterruptedException{
		Thread.sleep(3000);
		refApplicationGenericUtils.waitForVisibilty(objectRepository.get("MemberPortalHomePage.YourAccountLink"), 30);
		refApplicationGenericUtils.clickOnElement(objectRepository.get("MemberPortalHomePage.YourAccountLink"), "YourAccountLink");
		Thread.sleep(3000);
		refApplicationGenericUtils.verifyingText(objectRepository.get("MemberPortalAccountPage.ProfileInfo"), "Profile Information");
		refApplicationGenericUtils.verifyingUrl("qa-my.pethero.com/storefront/member-profile.ep");
	}
	
	
	public void verifyPetsSubscribed(){
		String[] petSelection = tcParameters.get("SelectionPlusCount").toString().split("\\|");
		int expectedTotalNoPets=0;
		int actualTotalNoPets=0;
		for (int i=0; i< petSelection.length; i++){
			expectedTotalNoPets = expectedTotalNoPets+ Integer.parseInt(petSelection[i]);
		}
		actualTotalNoPets = refApplicationGenericUtils.calculatePets(objectRepository.get("MarketingSitePage.PetList"));
		refApplicationGenericUtils.verifyEqual(actualTotalNoPets, expectedTotalNoPets, "Validated Total No Of Pets successfully!");
	}
	
	public void validateAddBenefits(){
		refApplicationGenericUtils.checkForElement(objectRepository.get("MemberPortalHomePage.AdditionalBenefitsLink"), "AdditionalBenefitsLink");
		refApplicationGenericUtils.clickOnElement(objectRepository.get("MemberPortalHomePage.AdditionalBenefitsLink"), "AdditionalBenefitsLink");
		refApplicationGenericUtils.checkForElement(objectRepository.get("MemberPortalAdditionalBenefits.PetCareXText"), "PetCareXText");
		//System.out.println(driver.findElement(objectRepository.get("MemberPortalAdditionalBenefits.PetCareXText")).getText());
		refApplicationGenericUtils.verifyingText(objectRepository.get("MemberPortalAdditionalBenefits.PetCareXText"), tcParameters.get("PetCareXText").toString());
		
		refApplicationGenericUtils.checkForElement(objectRepository.get("MemberPortalAdditionalBenefits.PetCofeecoText"), "PetCofeecoText");
		//System.out.println(driver.findElement(objectRepository.get("MemberPortalAdditionalBenefits.PetCofeecoText")).getText());
		refApplicationGenericUtils.verifyingText(objectRepository.get("MemberPortalAdditionalBenefits.PetCofeecoText"), tcParameters.get("PetCofeecoText").toString());
		
		refApplicationGenericUtils.checkForElement(objectRepository.get("MemberPortalAdditionalBenefits.PetCommunityText"), "PetCommunityText");
		//System.out.println(driver.findElement(objectRepository.get("MemberPortalAdditionalBenefits.PetCommunityText")).getText());
		refApplicationGenericUtils.verifyingText(objectRepository.get("MemberPortalAdditionalBenefits.PetCommunityText"), tcParameters.get("PetCommunityText").toString());
		
		refApplicationGenericUtils.checkForElement(objectRepository.get("MemberPortalAdditionalBenefits.MoreExclusiveText"), "MoreExclusiveText");
		//System.out.println(driver.findElement(objectRepository.get("MemberPortalAdditionalBenefits.MoreExclusiveText")).getText());
		refApplicationGenericUtils.verifyingText(objectRepository.get("MemberPortalAdditionalBenefits.MoreExclusiveText"), tcParameters.get("MoreExclusiveText").toString());
		
		refApplicationGenericUtils.checkForElement(objectRepository.get("MemberPortalAdditionalBenefits.LostPetText"), "LostPetText");
		//System.out.println(driver.findElement(objectRepository.get("MemberPortalAdditionalBenefits.LostPetText")).getText());			
		refApplicationGenericUtils.verifyingText(objectRepository.get("MemberPortalAdditionalBenefits.LostPetText"), tcParameters.get("LostPetText").toString());
	}

}

	



	
	

