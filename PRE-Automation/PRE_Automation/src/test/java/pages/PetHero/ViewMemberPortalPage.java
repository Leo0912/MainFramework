package pages.PetHero;

import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
//import org.testng.asserts.SoftAssert;
import utilities.SoftAssert;
import utilities.ApplicationGenericUtils;

public class ViewMemberPortalPage {

	//Declaration
	public WebDriver driver;
	public LinkedHashMap<String, By> objectRepository;
	ApplicationGenericUtils refApplicationGenericUtils;
	SoftAssert softAssert; 
	public static LinkedHashMap<Object, Object> tcParameters;
	
	public ViewMemberPortalPage(WebDriver driver,LinkedHashMap<String, By> objectRepository,SoftAssert softAssert,LinkedHashMap<Object, Object> tcParameters){
		this.driver=driver;
		this.objectRepository=objectRepository;
		refApplicationGenericUtils=new ApplicationGenericUtils(driver);
		this.softAssert=softAssert;
		this.tcParameters=tcParameters;
	}
	
	public void loginMemberPortal(String emailID){
		refApplicationGenericUtils.setElementValue(objectRepository.get("MemberPortalHomePage.Usernamebox"), "Usernamebox", emailID);
		refApplicationGenericUtils.setElementValue(objectRepository.get("MemberPortalHomePage.Passwordbox"), "Passwordbox", tcParameters.get("Password"));
		refApplicationGenericUtils.clickOnElement(objectRepository.get("MemberPortalHomePage.LoginButton"), "LoginButton");
	}
	private void validateYourAccount(Object planType) {
			refApplicationGenericUtils.clickOnElement(objectRepository.get("MemberPortalHomePage.YourAccountLink"), "YourAccountLink");
			refApplicationGenericUtils.waitForVisibilty(objectRepository.get("MemberPortalAccountPage.ProfileInfo"), 1000);
			refApplicationGenericUtils.verifyingText(objectRepository.get("MemberPortalAccountPage.MemberInfoName"), tcParameters.get("FirstName").toString());
			refApplicationGenericUtils.verifyingText(objectRepository.get("MemberPortalAccountPage.MemberInfoEmail"), tcParameters.get("EmailId").toString());
			refApplicationGenericUtils.verifyingText(objectRepository.get("MemberPortalAccountPage.MemberInfoShippingAddress1Text"), tcParameters.get("Address1").toString());
			refApplicationGenericUtils.verifyingText(objectRepository.get("MemberPortalAccountPage.MemberInfoShippingAddress2Text"), tcParameters.get("Address2").toString());
			refApplicationGenericUtils.verifyingText(objectRepository.get("MemberPortalAccountPage.MemberInfoShippingAddress3Text"), tcParameters.get("Address3").toString());
			refApplicationGenericUtils.verifyingText(objectRepository.get("MemberPortalAccountPage.MemberInfoBillingAddress1Text"), tcParameters.get("Address1").toString());
			refApplicationGenericUtils.verifyingText(objectRepository.get("MemberPortalAccountPage.MemberInfoBillingAddress2Text"), tcParameters.get("Address2").toString());
			refApplicationGenericUtils.verifyingText(objectRepository.get("MemberPortalAccountPage.MemberInfoBillingAddress3Text"), tcParameters.get("Address3").toString());
			refApplicationGenericUtils.verifyingText(objectRepository.get("MemberPortalAccountPage.MemberInfoBillingCardText"), tcParameters.get("CardNumber").toString());
			refApplicationGenericUtils.compareTextBysubString(objectRepository.get("MemberPortalAccountPage.MemberInfoBillingCardText"), tcParameters.get("CardNumber").toString(), 13, 16);
					
	}
	
	public void validLoginMemberPortal(String emailID) throws InterruptedException{
		Thread.sleep(4100);
		driver.switchTo().parentFrame();
		Thread.sleep(4000);
		refApplicationGenericUtils.waitForElement(objectRepository.get("MemberPortalHomePage.LoginLink"), 25);
		refApplicationGenericUtils.clickonElementJS(objectRepository.get("MemberPortalHomePage.LoginLink"), "Login");
		Thread.sleep(4000);
		driver.switchTo().frame("login-frame");
		Thread.sleep(4000);
		refApplicationGenericUtils.waitForElement(objectRepository.get("MemberPortalHomePage.Usernamebox"), 25);
		refApplicationGenericUtils.setElementValue(objectRepository.get("MemberPortalHomePage.Usernamebox"), "Usernamebox", emailID);
		refApplicationGenericUtils.setElementValue(objectRepository.get("MemberPortalHomePage.Passwordbox"), "Passwordbox", tcParameters.get("Password"));
		refApplicationGenericUtils.clickonElementJS(objectRepository.get("MemberPortalHomePage.LoginButton"), "LoginButton");
		driver.switchTo().parentFrame();
	}
	
	public void invalidLoginMemberPortal(String emailID){
		refApplicationGenericUtils.waitForElement(objectRepository.get("MemberPortalHomePage.Usernamebox"), 25);
		refApplicationGenericUtils.setElementValue(objectRepository.get("MemberPortalHomePage.Usernamebox"), "Usernamebox", emailID);
		refApplicationGenericUtils.setElementValue(objectRepository.get("MemberPortalHomePage.Passwordbox"), "Passwordbox", tcParameters.get("Password"));
		refApplicationGenericUtils.clickOnElement(objectRepository.get("MemberPortalHomePage.LoginButton"), "LoginButton");
		refApplicationGenericUtils.waitForElement(objectRepository.get("MemberPortalHomePage.InvalidUserErrorText"), 25);
		softAssert.assertTrue(refApplicationGenericUtils.verifyingText(objectRepository.get("MemberPortalHomePage.InvalidUserErrorText"), tcParameters.get("InvalidUserErrorText").toString()));
		
	}
	
	public void forgotPasswordMemberPortal(String emailID)throws InterruptedException{
		driver.switchTo().parentFrame();
		Thread.sleep(4000);
		refApplicationGenericUtils.waitForElement(objectRepository.get("MemberPortalHomePage.LoginLink"), 25);
		refApplicationGenericUtils.clickonElementJS(objectRepository.get("MemberPortalHomePage.LoginLink"), "Login");
		Thread.sleep(4000);
		driver.switchTo().frame("login-frame");
		Thread.sleep(4000);
		refApplicationGenericUtils.waitForElement(objectRepository.get("MemberPortalHomePage.ForgotPasswordLink"), 25);
		refApplicationGenericUtils.clickonElementJS(objectRepository.get("MemberPortalHomePage.ForgotPasswordLink"), "ForgotPassword");
		//Thread.sleep(7100);
		refApplicationGenericUtils.waitForElement(objectRepository.get("MemberPortalHomePage.ResetUsernamebox"), 25);
		refApplicationGenericUtils.setElementValue(objectRepository.get("MemberPortalHomePage.ResetUsernamebox"), "ResetUsernamebox", emailID);
		refApplicationGenericUtils.setElementValue(objectRepository.get("MemberPortalHomePage.ConfirmUsernamebox"), "ConfirmUsernamebox", emailID);
		refApplicationGenericUtils.clickonElementJS(objectRepository.get("MemberPortalHomePage.ResetButton"), "ResetButton");
		refApplicationGenericUtils.waitForElement(objectRepository.get("MemberPortalHomePage.EmailSentText"), 25);
		//Thread.sleep(7000);
		softAssert.assertTrue(refApplicationGenericUtils.verifyingText(objectRepository.get("MemberPortalHomePage.EmailSentText"), tcParameters.get("EmailSentText").toString()));
		}
	
	private void printVetSavingsCardValidation() {
			
	}
	
	private void planType(Object planType){
		
	}
	
}
