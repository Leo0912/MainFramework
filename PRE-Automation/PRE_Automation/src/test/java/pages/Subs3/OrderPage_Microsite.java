package pages.Subs3;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
//import org.testng.asserts.SoftAssert;
import utilities.SoftAssert;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import freemarker.core.Environment;

import utilities.ApplicationGenericUtils;

public class OrderPage_Microsite {

	// Declaration
	public WebDriver driver;
	public LinkedHashMap<String, By> objectRepository;
	ApplicationGenericUtils refApplicationGenericUtils;
	SoftAssert softAssert;
	public ExtentTest reportLogger;
	public  LinkedHashMap<Object, Object> tcParameters;
	Map<String, Object[]> data = new LinkedHashMap<String, Object[]>();
	
	public OrderPage_Microsite(WebDriver driver, LinkedHashMap<String, By> objectRepository, SoftAssert softAssert,LinkedHashMap<Object, Object> tcParameters){
		this.driver = driver;
		this.objectRepository = objectRepository;
		refApplicationGenericUtils = new ApplicationGenericUtils(driver);
		this.softAssert = softAssert;
		this.tcParameters = tcParameters;
	}

	public OrderPage_Microsite selectDeal(){
		refApplicationGenericUtils.clickOnElement(objectRepository.get("SelectDeal.Deal1"),"Select Deal1");
		return this;
	}
	
	public OrderPage_Microsite selectPaymentType() throws Exception{
		refApplicationGenericUtils.waitForElement(objectRepository.get("SelectPaymentType.CreditCard"), 60);
		refApplicationGenericUtils.waitForVisibilty(objectRepository.get("SelectPaymentType.CreditCard"), 60);
		Thread.sleep(2000);
		refApplicationGenericUtils.clickOnElement(objectRepository.get("SelectPaymentType.CreditCard"),"Select PaymentType CreditCard");
		return this;
	}

	public Map<String, Object[]> enterAddressInformation(String datatime) throws Exception{
		refApplicationGenericUtils.waitForElement(objectRepository.get("AddressInformation.FirstName"), 60);
		refApplicationGenericUtils.waitForVisibilty(objectRepository.get("AddressInformation.FirstName"), 60);
		Thread.sleep(2000);
		refApplicationGenericUtils.setElementValue(objectRepository.get("AddressInformation.FirstName"), "AddressInformation.FirstName", tcParameters.get("FNAME").toString()+datatime);
		refApplicationGenericUtils.setElementValue(objectRepository.get("AddressInformation.LastName"), "AddressInformation.LastName", tcParameters.get("LNAME").toString()+datatime);
		//refApplicationGenericUtils.setElementValue(objectRepository.get("AddressInformation.Street"), "AddressInformation.Street", tcParameters.get("BillingAddress").toString());
		refApplicationGenericUtils.setElementValue(objectRepository.get("AddressInformation.Street"), "AddressInformation.Street", tcParameters.get("FNAME").toString()+datatime);
		refApplicationGenericUtils.setElementValue(objectRepository.get("AddressInformation.City"), "AddressInformation.City", tcParameters.get("CITY").toString());
		refApplicationGenericUtils.setElementValue(objectRepository.get("AddressInformation.Zip"), "AddressInformation.Zip", tcParameters.get("ZIP").toString());
		refApplicationGenericUtils.setElementValue(objectRepository.get("AddressInformation.State"), "AddressInformation.State", tcParameters.get("STATE").toString());
		refApplicationGenericUtils.setElementValue(objectRepository.get("AddressInformation.EmailAddress"), "AddressInformation.EmailAddress", tcParameters.get("FNAME").toString()+datatime+"@test.com");
		
		return data;
	}
	
	public OrderPage_Microsite enterPaymentInformation() throws Exception{
		refApplicationGenericUtils.waitForElement(objectRepository.get("PaymentInformation.CardNumber"), 60);
		refApplicationGenericUtils.waitForVisibilty(objectRepository.get("PaymentInformation.CardNumber"), 60);
		Thread.sleep(2000);
		refApplicationGenericUtils.setElementValue(objectRepository.get("PaymentInformation.CardNumber"), "PaymentInformation.CardNumber", tcParameters.get("CCNUMBER").toString());
		refApplicationGenericUtils.setElementValue(objectRepository.get("PaymentInformation.Month"), "PaymentInformation.Month", tcParameters.get("MONTH").toString());
		refApplicationGenericUtils.setElementValue(objectRepository.get("PaymentInformation.Year"), "PaymentInformation.Year", tcParameters.get("YEAR").toString());
		return this;
	}
	
	public OrderPage_Microsite submitOrder() throws Exception{
		refApplicationGenericUtils.waitForElement(objectRepository.get("OrderPage.Submit"), 60);
		refApplicationGenericUtils.waitForVisibilty(objectRepository.get("OrderPage.Submit"), 60);
		Thread.sleep(2000);
		refApplicationGenericUtils.clickOnElement(objectRepository.get("OrderPage.Submit"),"Submit Order");
		return this;
	}
	
	public String getOrderID(){
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		String return_value = (String) js.executeScript("return document.getElementById('orderNumber').value");
		return return_value;
	}

}
