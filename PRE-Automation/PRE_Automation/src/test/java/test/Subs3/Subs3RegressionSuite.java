package test.Subs3;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
//import org.testng.asserts.SoftAssert;
import utilities.SoftAssert;
import pages.Subs3.FulfillmentPage;
import pages.Subs3.MainFrameValidations;
import pages.Subs3.OrderPageLogin_Microsite;
import pages.Subs3.OrderPage_Microsite;
import test.FrameworkDriverScript;
import utilities.ApplicationGenericUtils;
import utilities.ExcelUtility;

public class Subs3RegressionSuite extends FrameworkDriverScript {
	WebDriver driverLoginPage = null;
	OrderPageLogin_Microsite refMicrositeLoginPage;
	String MemberEmailID;
	HashMap<String, String> orderFulfillmentValues = new HashMap<String, String>();
	int noofPets;
	ApplicationGenericUtils refApplicationGenericUtils;
	String OrderID;
	Map<String, Object[]> data = new LinkedHashMap<String, Object[]>();
	String datatime;
	ExcelUtility refExcelUtil = new ExcelUtility();
	
	public void reportAndPageinit() {
		softAssert= new SoftAssert();
		reportLogger = extent.createTest(Thread.currentThread().getStackTrace()[2].getMethodName());
		if (appName.containsKey("Local Machine")) {
			refMicrositeLoginPage = new OrderPageLogin_Microsite(driver, objectRepository, appName);

		} else {
			refMicrositeLoginPage = new OrderPageLogin_Microsite(driver, objectRepository, appName);
		}
		
		datatime = ApplicationGenericUtils.fetchCurrentDateAndTime();
	}

	public void PlaceOrder_Microsite() throws IOException {
		reportAndPageinit();
		try {
			
			order_Microsite();
			validate_FulfillmentSystem();
			
			if (orderFulfillmentValues.get("KeyLine").toString() != null){
				validate_MF();
			}else {
				ApplicationGenericUtils.extentReportLogFAIL("KeyLine is Null");
			}
			
			ApplicationGenericUtils.extentReportLogENDTEST();
		} catch (AssertionError | Exception e) {
			ApplicationGenericUtils.extentReportLogFAIL(e.getMessage());
			e.printStackTrace();
		} finally {
			extent.flush();
			closeBrowser();
		}
	}

	
	public void order_Microsite() throws Exception {
		driverLoginPage = refMicrositeLoginPage.logintoApplication(envDetails, "Subscription");
		OrderPage_Microsite refOrderPage = new OrderPage_Microsite(driverLoginPage, objectRepository, softAssert,tcParameters);
		
		refOrderPage.selectDeal();
		refOrderPage.selectPaymentType();
		refOrderPage.enterAddressInformation(datatime);
		refOrderPage.enterPaymentInformation();
		refOrderPage.submitOrder();
		
		OrderID = refOrderPage.getOrderID();
		data.put("1", new Object[]{tcParameters.get("FNAME")+datatime,	tcParameters.get("LNAME")+datatime,	tcParameters.get("FNAME")+datatime+"@test.com",tcParameters.get("BillingAddress"),tcParameters.get("CITY"),	tcParameters.get("STATE"),tcParameters.get("ZIP"),tcParameters.get("CCNUMBER"),tcParameters.get("MONTH"),tcParameters.get("YEAR"),tcParameters.get("CARDTYPE"),	OrderID});
		refExcelUtil.writeSubs3Excel(constants.Constants.SUBS3_MFDATA_FILE_PATH, data);

		closeBrowser();
	}
	
	public void validate_FulfillmentSystem() throws Exception {
		//OrderID ="4845040";
		System.out.println("Order ID is : \t"+OrderID );
		
		driverLoginPage = refMicrositeLoginPage.logintoApplication(envDetails, "Fulfillment");
		
		FulfillmentPage refFulfillmentPage = new FulfillmentPage(driverLoginPage, objectRepository, softAssert,tcParameters);
		
		refFulfillmentPage.login();
		orderFulfillmentValues = refFulfillmentPage.searchOrderID(OrderID);
		
	}
	
	
	public void validate_MF() throws Exception {
		MainFrameValidations refMainFrameValidations = new MainFrameValidations(softAssert,tcParameters,orderFulfillmentValues);
		refMainFrameValidations.openMainFrameApplication();
		refMainFrameValidations.loginMainFrameApplication();
		refMainFrameValidations.validateMEVKM();
		refMainFrameValidations.validateMSORK();
	}
	
	
	public void closeBrowser() {
		Capabilities cap = ((RemoteWebDriver) driverLoginPage).getCapabilities();
		String browserName = cap.getBrowserName().toLowerCase();
		if (browserName.equalsIgnoreCase("Firefox")) {
			driverLoginPage.close();
		} else {
			driverLoginPage.quit();
		}
	}

}
