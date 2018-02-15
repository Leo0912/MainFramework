package pages.TOS;

import java.awt.Toolkit;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilities.ApplicationGenericUtils;
import constants.Constants;

public class TOSLoginPage {
	public WebDriver driver;
	public LinkedHashMap<String, By> objectRepository;
	ApplicationGenericUtils refApplicationGenericUtils;
	public TOSLoginPage(WebDriver driver,LinkedHashMap<String, By> objectRepository){
		this.driver=driver;
		this.objectRepository=objectRepository;
		refApplicationGenericUtils=new ApplicationGenericUtils(driver);
	}
	public  WebDriver logintoApplication(LinkedHashMap<Object, Object> LoginDetails){
		try {
			driver.get(LoginDetails.get("url").toString());
			refApplicationGenericUtils.setElementValue(objectRepository.get("LoginPage.UserID"),"UserID", LoginDetails.get("username"));
			refApplicationGenericUtils.setElementValue(objectRepository.get("LoginPage.Password"),"Pwd", LoginDetails.get("pwd"));
			refApplicationGenericUtils.clickOnElement(objectRepository.get("LoginPage.LoginButton"),"LoginButton");
			refApplicationGenericUtils.clickOnElement(objectRepository.get("LoginPage.ProceedToTimeXchange"),"ProceedToTimeXchange");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}

}
