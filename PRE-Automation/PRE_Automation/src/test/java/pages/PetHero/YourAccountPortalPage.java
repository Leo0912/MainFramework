package pages.PetHero;

import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.SoftAssert;

import utilities.ApplicationGenericUtils;

public class YourAccountPortalPage {
	public WebDriver driver;
	public LinkedHashMap<String, By> objectRepository;
	ApplicationGenericUtils refApplicationGenericUtils;
	SoftAssert softAssert; 
	public static LinkedHashMap<Object, Object> tcParameters;
	
	
	public YourAccountPortalPage(WebDriver driver,LinkedHashMap<String, By> objectRepository,SoftAssert softAssert,LinkedHashMap<Object, Object> tcParameters){
		this.driver=driver;
		this.objectRepository=objectRepository;
		refApplicationGenericUtils=new ApplicationGenericUtils(driver);
		this.softAssert=softAssert;
		this.tcParameters=tcParameters;
}
	public void verifyHomeLink(){
		refApplicationGenericUtils.verifyingText(objectRepository.get("MemberPortalAccountPage.ProfileInfo"), "Profile Information");
		refApplicationGenericUtils.waitForVisibilty(objectRepository.get("MemberPortalAccountPage.HomeLink"), 60);
		refApplicationGenericUtils.clickOnElement(objectRepository.get("MemberPortalAccountPage.HomeLink"), "HomeLink");
		refApplicationGenericUtils.waitForVisibilty(objectRepository.get("MemberPortalHomePage.YourAccountLink"), 60);
		refApplicationGenericUtils.verifyingUrl("qa-my.pethero.com/storefront/member-home.ep");
	}
	
	public  void validateDetails() {
		refApplicationGenericUtils.clickOnElement(objectRepository.get("MemberPortalHomePage.YourAccountLink"), "YourAccountLink");
		refApplicationGenericUtils.waitForVisibilty(objectRepository.get("MemberPortalAccountPage.EditLink"), 60);
		refApplicationGenericUtils.verifyingText(objectRepository.get("MemberPortalAccountPage.ProfileInfo"), "Profile Information");
	
		
		refApplicationGenericUtils.verifyingText(objectRepository.get("MemberPortalAccountPage.MemberInfoName"), tcParameters.get("FirstName").toString()+" "+tcParameters.get("LastName").toString());
		//System.out.println(tcParameters.get("FirstName").toString()+" "+tcParameters.get("LastName").toString());
		refApplicationGenericUtils.verifyingText(objectRepository.get("MemberPortalAccountPage.MemberInfoEmail"), tcParameters.get("EmailId").toString());
		//System.out.println("Email: " + driver.findElement(objectRepository.get("MemberPortalAccountPage.MemberInfoEmail")).getText());
		
		refApplicationGenericUtils.clickOnElement(objectRepository.get("MemberPortalAccountPage.EditLink"), "EditLink");
		
		refApplicationGenericUtils.verifyingText(objectRepository.get("MemberPortalEditPage.Text"), "UPDATE ACCOUNT");
		//System.out.println("Update:  " + driver.findElement(objectRepository.get("MemberPortalEditPage.Text")).getText());
		//verifyHomeLink();
		refApplicationGenericUtils.waitForVisibilty(objectRepository.get("MemberPortalAccountPage.HomeLink"), 60);
		refApplicationGenericUtils.clickOnElement(objectRepository.get("MemberPortalAccountPage.HomeLink"), "HomeLink");
		
		
}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

