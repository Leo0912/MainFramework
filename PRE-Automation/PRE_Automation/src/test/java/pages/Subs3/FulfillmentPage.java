package pages.Subs3;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.SoftAssert;

import utilities.ApplicationGenericUtils;

public class FulfillmentPage {
	public WebDriver driver;
	public LinkedHashMap<String, By> objectRepository;
	ApplicationGenericUtils refApplicationGenericUtils;
	SoftAssert softAssert; 
	public static LinkedHashMap<Object, Object> tcParameters;
	HashMap<String, String> orderFulfillmentValues = new HashMap<String, String>();
	
	public FulfillmentPage(WebDriver driver,LinkedHashMap<String, By> objectRepository,SoftAssert softAssert,LinkedHashMap<Object, Object> tcParameters){
		this.driver=driver;
		this.objectRepository=objectRepository;
		refApplicationGenericUtils=new ApplicationGenericUtils(driver);
		this.softAssert=softAssert;
		this.tcParameters=tcParameters;
}
	
	public void login() throws InterruptedException{
		refApplicationGenericUtils.waitForElement(objectRepository.get("FulfillmentPage.username"), 60);
		refApplicationGenericUtils.waitForVisibilty(objectRepository.get("FulfillmentPage.username"), 60);
		Thread.sleep(2000);
		refApplicationGenericUtils.setElementValue(objectRepository.get("FulfillmentPage.username"), "FulfillmentPage.username", "devarajl");
		refApplicationGenericUtils.setElementValue(objectRepository.get("FulfillmentPage.password"), "FulfillmentPage.password", "jan#Updnu8");
		Thread.sleep(2000);
		refApplicationGenericUtils.clickOnElement(objectRepository.get("FulfillmentPage.loginSubmit"), "loginSubmit");
	}
	
	public HashMap<String, String>  searchOrderID(String OrderID) throws InterruptedException{
		refApplicationGenericUtils.waitForElement(objectRepository.get("FulfillmentPage.orderNumber"), 60);
		refApplicationGenericUtils.waitForVisibilty(objectRepository.get("FulfillmentPage.orderNumber"), 60);
		Thread.sleep(2000);
		refApplicationGenericUtils.setElementValue(objectRepository.get("FulfillmentPage.orderNumber"), "FulfillmentPage.orderNumber", OrderID);
		refApplicationGenericUtils.clickOnElement(objectRepository.get("FulfillmentPage.searchButton"), "Search button");
		
		String SKUstatus = refApplicationGenericUtils.getElementValue(objectRepository.get("FulfillmentPage.SkuFulfillmentStatus"), "SkuFulfillmentStatus");
		System.out.println("SKU Status: \t"+SKUstatus);
		
		do {
			refApplicationGenericUtils.clearElementValue(objectRepository.get("FulfillmentPage.orderNumber"), "FulfillmentPage.orderNumber", OrderID);
			refApplicationGenericUtils.setElementValue(objectRepository.get("FulfillmentPage.orderNumber"), "FulfillmentPage.orderNumber", OrderID);
			refApplicationGenericUtils.clickOnElement(objectRepository.get("FulfillmentPage.searchButton"), "Search button");
			Thread.sleep(1000);
			SKUstatus = refApplicationGenericUtils.getElementValue(objectRepository.get("FulfillmentPage.SkuFulfillmentStatus"), "SkuFulfillmentStatus");
			System.out.println("SKU Status: \t"+SKUstatus);
		}while (SKUstatus.equals("N"));

		String MagicID = refApplicationGenericUtils.getElementValue(objectRepository.get("FulfillmentPage.MAGICID"), "MAGICID");
		System.out.println("Magic ID: \t"+MagicID);

		String PageID = refApplicationGenericUtils.getElementValue(objectRepository.get("FulfillmentPage.PageID"), "PageID");
		System.out.println("PageID: \t"+PageID);

		String ResponseText= refApplicationGenericUtils.getElementValue(objectRepository.get("FulfillmentPage.ResponseText"), "ResponseText");
		System.out.println("ResponseText: \t"+ResponseText);
		
		orderFulfillmentValues.put("SKUstatus", SKUstatus);
		orderFulfillmentValues.put("MagicID", MagicID);
		orderFulfillmentValues.put("MG", MagicID.substring(0, 2));
		orderFulfillmentValues.put("EK", MagicID.substring(2, 6));
		orderFulfillmentValues.put("PageID", PageID);
		orderFulfillmentValues.put("ResponseText", ResponseText);
		String keylineStart = "<keyline>";
		String keylineEnd = "</keyline>";
		
		if (ResponseText.contains("N/A")){
			System.out.println("KeyLine: \t"+ResponseText.substring(ResponseText.indexOf(keylineStart)+keylineStart.length(), ResponseText.indexOf(keylineEnd)));
			String KeyLine = ResponseText.substring(ResponseText.indexOf(keylineStart)+keylineStart.length(), ResponseText.indexOf(keylineEnd));
			orderFulfillmentValues.put("KeyLine", KeyLine);
		}
		return orderFulfillmentValues;
	}
}

