package pages.PetHero;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
//import org.testng.asserts.SoftAssert;
import utilities.SoftAssert;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import utilities.ApplicationGenericUtils;

public class PetHeroAccountPage {

	//Declaration
	public WebDriver driver;
	public LinkedHashMap<String, By> objectRepository;
	ApplicationGenericUtils refApplicationGenericUtils;
	SoftAssert softAssert; 
	public static LinkedHashMap<Object, Object> tcParameters;
	public ExtentTest reportLogger;
	
	public String monthlyChargePerMWithoutPromo;
	public String monthlyChargePerMWithPromo;
	public String monthlyChargePerQuarterWithoutPromo;
	public String monthlyChargePerQuarterWithPromo;

	public String annualChargePerMWithoutPromo;
	public String annualChargePerMWithPromo;
	public String annualChargePerYWithoutPromo;
	public String annualChargePerYWithPromo;

	public PetHeroAccountPage(WebDriver driver, LinkedHashMap<String, By> objectRepository, SoftAssert softAssert,
			LinkedHashMap<Object, Object> tcParameters) {
		this.driver = driver;
		this.objectRepository = objectRepository;
		refApplicationGenericUtils = new ApplicationGenericUtils(driver);
		this.softAssert = softAssert;
		PetHeroAccountPage.tcParameters = tcParameters;
	}

	
	
	public String createPetHeroAccount(int noOfPets, HashMap<String, String> petCharges) throws InterruptedException {
		Object emailId = tcParameters.get("EmailId") + ApplicationGenericUtils.fetchCurrentDateAndTime()
				+ "@test.co.in";
		objectRepository.put("MemberLoginEmailID", By.xpath(emailId.toString()));
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step2.PetHeroAccount.EmailID"),
				"Email ID", emailId);
		refApplicationGenericUtils.clickOnElement(objectRepository.get("Step2.PetHeroAccount.ContinueButton"),
				"Comtinue Button");
			// Validate 30 days Trial message before Promo
		validate30DaysTrailMessage();

		// promocode selection
		Object promocode = tcParameters.get("PromoCode");
		if (!promocode.equals("No_Value")) {
			refApplicationGenericUtils.clickOnElement(objectRepository.get("Step3.HavePromoCodeText"),
					"HavePromoCodeText");
			refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.EnterPromoCodeTextBox"),
					"EnterPromoCodeTextBox", promocode);
			refApplicationGenericUtils.clickOnElement(objectRepository.get("Step3.PromoCodeApplyButton"),
					"PromoCodeApplyButton");
			refApplicationGenericUtils.checkForElement(objectRepository.get("Step3.PromoMessage"),
					"PromoMessage");
			
			int counter = 0;
			do {
				Thread.sleep(500);
				if (refApplicationGenericUtils.getElementValue(objectRepository.get("Step3.PromoMessage"),"Promo Message").equalsIgnoreCase("Applying coupon code...please wait.")){
					counter = 0;
				}else{
					counter = 1;
				}
				
			} while (counter == 0);
			softAssert.assertEquals(refApplicationGenericUtils.getElementValue(objectRepository.get("Step3.PromoMessage"),"PromoMessage"), promocode + " has been applied.");
		}

		// Calculate All charges according to No of Pets and pet charges
		calculateCharges(noOfPets, petCharges);

		// Validate all Pet charges
		validateAllCharges();

		// Validate 30 days Trial message after Promo
		validate30DaysTrailMessagePostPromo();

		planSelection(tcParameters.get("PlanType"));

		// Card Details
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.BillingInfoCardNumber"),
				"BillingInfoCardNumber", tcParameters.get("CardNumber").toString());
		refApplicationGenericUtils.selectValueFromList(objectRepository.get("Step3.BillingInfoExpiryMonth"),
				"BillingInfoExpiryMonth", Double.valueOf(tcParameters.get("MM").toString()).longValue());
		refApplicationGenericUtils.selectValueFromList(objectRepository.get("Step3.BillingInfoExpiryYear"),
				"BillingInfoExpiryYear", Double.valueOf(tcParameters.get("YYYY").toString()).longValue());
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.BillingInfoCVVCode"),
				"BillingInfoCVVCode", Double.valueOf(tcParameters.get("CVV").toString()).longValue());
		// Address
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.BillingInfoFirstName"),
				"BillingInfoFirstName", tcParameters.get("FirstName"));
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.BillingInfoLastName"),
				"BillingInfoLastName", tcParameters.get("LastName"));
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.BillingInfoAddress"),
				"BillingInfoAddress", tcParameters.get("Address"));
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.BillingInfoCity"),
				"BillingInfoCity", tcParameters.get("City"));
		refApplicationGenericUtils.selectValueFromList(objectRepository.get("Step3.BillingInfoState"),
				"BillingInfoState", tcParameters.get("State"));
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.BillingInfoZipCode"),
				"BillingInfoZipCode", tcParameters.get("Zipcode"));
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.BillingInfoPassword"),
				"BillingInfoPassword", tcParameters.get("Password"));
		refApplicationGenericUtils.clickOnElement(objectRepository.get("Step3.BillingInfoSubmitButton"),
				"BillingInfoSubmitButton");
		MessageValidation();
		refApplicationGenericUtils.clickOnElement(objectRepository.get("ThankyouPage.VisitMemberPortal"),
				"VisitMemberPortal");
		checkMemberLoginPage();
		return emailId.toString();
	}

	public void validate30DaysTrailMessage() {
		String trialMessageCharge = "";
		String expectedPrice;

		// click annual
		refApplicationGenericUtils.clickOnElement(objectRepository.get("Step3.AnnualPlanSelectionBox"),
				"Annual Plan Selection Box");
		trialMessageCharge = refApplicationGenericUtils.getElementValue(objectRepository.get("Step3.finalTrialPrice"),"Final Trial Period Price");
		expectedPrice = refApplicationGenericUtils.getElementValue(objectRepository.get("Step3.AnnualPriceText"),"AnnualPriceText");
		Assert.assertEquals(trialMessageCharge, expectedPrice);
		// click Quarterly
		refApplicationGenericUtils.clickOnElement(objectRepository.get("Step3.QuarterlyPlanSelectionBox"),
				"Quarterly Plan Selection Box");
		trialMessageCharge = refApplicationGenericUtils.getElementValue(objectRepository.get("Step3.finalTrialPrice"),"Final Trial Period Price");
		expectedPrice = refApplicationGenericUtils.getElementValue(objectRepository.get("Step3.QuarterlyPriceText"),"QuarterlyPriceText");
		softAssert.assertEquals(trialMessageCharge, expectedPrice);
		ApplicationGenericUtils.extentReportLogPASS();
	}

	public void validate30DaysTrailMessagePostPromo() {
		String trialMessageCharge = "";
		String expectedPrice;

		// click Annual
		refApplicationGenericUtils.clickOnElement(objectRepository.get("Step3.AnnualPlanSelectionBox"),
				"Annual Plan Selection Box");
		trialMessageCharge = refApplicationGenericUtils.getElementValue(objectRepository.get("Step3.finalTrialPrice"),"Final Trial Period Price");
		expectedPrice = refApplicationGenericUtils.getElementValue(objectRepository.get("Step3.AnnualPriceDiscText"),"Annual Price Disc Text");
		softAssert.assertEquals(trialMessageCharge, expectedPrice);
		// click Quarterly
		refApplicationGenericUtils.clickOnElement(objectRepository.get("Step3.QuarterlyPlanSelectionBox"),
				"Quarterly Plan Selection Box");
		trialMessageCharge = refApplicationGenericUtils.getElementValue(objectRepository.get("Step3.finalTrialPrice"),"Final Trial Period Price");
		expectedPrice = refApplicationGenericUtils.getElementValue(objectRepository.get("Step3.QuarterlyPriceDiscText"),"Quarterly Price Disc Text");
		Assert.assertEquals(trialMessageCharge, expectedPrice);
		ApplicationGenericUtils.extentReportLogPASS();
	}

	public void validateAllCharges() {

		String actualQuarterlyCostPerMonthText = refApplicationGenericUtils.getElementValue(objectRepository.get("Step3.QuarterlyCostPerMonthText"),"QuarterlyCostPerMonthText");
		String actualAnnualCostPerMonthText = refApplicationGenericUtils.getElementValue(objectRepository.get("Step3.AnnualCostPerMonthText"),"AnnualCostPerMonthText");
		String actualQuarterlyCostDiscPerMonthText = refApplicationGenericUtils.getElementValue(objectRepository.get("Step3.QuarterlyCostDiscPerMonthText"),"QuarterlyCostDiscPerMonthText");
		String actualAnnualCostDiscPerMonthText = refApplicationGenericUtils.getElementValue(objectRepository.get("Step3.AnnualCostDiscPerMonthText"),"AnnualCostDiscPerMonthText");
		String actualAnnualPriceText = refApplicationGenericUtils.getElementValue(objectRepository.get("Step3.AnnualPriceText"),"AnnualPriceText");
		String actualAnnualPriceDiscText = refApplicationGenericUtils.getElementValue(objectRepository.get("Step3.AnnualPriceDiscText"),"AnnualPriceDiscText");
		String actualQuarterlyPriceText = refApplicationGenericUtils.getElementValue(objectRepository.get("Step3.QuarterlyPriceText"),"QuarterlyPriceText");
		String actualQuarterlyPriceDiscText = refApplicationGenericUtils.getElementValue(objectRepository.get("Step3.QuarterlyPriceDiscText"),"QuarterlyPriceDiscText");

		actualQuarterlyCostPerMonthText = String
				.valueOf(String.format("%.2f", Float.parseFloat(actualQuarterlyCostPerMonthText)));
		actualAnnualCostPerMonthText = String
				.valueOf(String.format("%.2f", Float.parseFloat(actualAnnualCostPerMonthText)));
		actualQuarterlyCostDiscPerMonthText = String
				.valueOf(String.format("%.2f", Float.parseFloat(actualQuarterlyCostDiscPerMonthText)));
		actualAnnualCostDiscPerMonthText = String
				.valueOf(String.format("%.2f", Float.parseFloat(actualAnnualCostDiscPerMonthText)));
		actualAnnualPriceText = String.valueOf(String.format("%.2f", Float.parseFloat(actualAnnualPriceText)));
		actualAnnualPriceDiscText = String.valueOf(String.format("%.2f", Float.parseFloat(actualAnnualPriceDiscText)));
		actualQuarterlyPriceText = String.valueOf(String.format("%.2f", Float.parseFloat(actualQuarterlyPriceText)));
		actualQuarterlyPriceDiscText = String
				.valueOf(String.format("%.2f", Float.parseFloat(actualQuarterlyPriceDiscText)));

		
		refApplicationGenericUtils.verifyingText(actualQuarterlyPriceText, monthlyChargePerQuarterWithoutPromo);
		//softAssert.assertEquals(actualQuarterlyPriceText, monthlyChargePerQuarterWithoutPromo);
		refApplicationGenericUtils.verifyingText(actualAnnualCostPerMonthText,annualChargePerMWithoutPromo );
		refApplicationGenericUtils.verifyingText(actualQuarterlyCostDiscPerMonthText,monthlyChargePerMWithPromo );
		refApplicationGenericUtils.verifyingText(actualAnnualCostDiscPerMonthText,annualChargePerMWithPromo );
		refApplicationGenericUtils.verifyingText(actualAnnualPriceText, annualChargePerYWithoutPromo);
		refApplicationGenericUtils.verifyingText(actualAnnualPriceDiscText, annualChargePerYWithPromo);
		refApplicationGenericUtils.verifyingText(actualQuarterlyCostPerMonthText, monthlyChargePerMWithoutPromo);
		refApplicationGenericUtils.verifyingText(actualQuarterlyPriceDiscText, monthlyChargePerQuarterWithPromo);
		ApplicationGenericUtils.extentReportLogPASS();
	}

	public void calculateCharges(int noOfPets, HashMap<String, String> petCharges) {
		float i_monthlyChargePerMWithoutPromo;
		float i_monthlyChargePerMWithPromo;
		float i_monthlyChargePerQuarterWithoutPromo;
		float i_monthlyChargePerQuarterWithPromo;

		float i_annualChargePerMWithoutPromo;
		float i_annualChargePerMWithPromo;
		float i_annualChargePerYWithoutPromo;
		float i_annualChargePerYWithPromo;

		float i_quarterlyBasePrice;
		float i_annualBasePrice;

		float costPrice;

		costPrice = Float.parseFloat(petCharges.get("singleDogCost"))
				+ ((noOfPets - 1) * Float.parseFloat(petCharges.get("additionalPetCost")));

		i_monthlyChargePerMWithoutPromo = costPrice;
		i_monthlyChargePerQuarterWithoutPromo = i_monthlyChargePerMWithoutPromo * 3;
		i_annualChargePerYWithoutPromo = costPrice * 11;
		i_annualChargePerMWithoutPromo = i_annualChargePerYWithoutPromo / 12;

		i_quarterlyBasePrice = costPrice * 3;
		i_monthlyChargePerQuarterWithPromo = i_quarterlyBasePrice
				- (i_quarterlyBasePrice * (Float.parseFloat(tcParameters.get("PromoDiscPercentage").toString()) / 100));
		i_monthlyChargePerMWithPromo = i_monthlyChargePerQuarterWithPromo / 3;

		i_annualBasePrice = i_monthlyChargePerMWithoutPromo * 12;

		i_annualChargePerYWithPromo = i_annualBasePrice
				- (i_annualBasePrice * (Float.parseFloat(tcParameters.get("PromoDiscPercentage").toString()) / 100));
		i_annualChargePerMWithPromo = i_annualChargePerYWithPromo / 12;

		monthlyChargePerMWithoutPromo = String.valueOf(String.format("%.2f", i_monthlyChargePerMWithoutPromo));
		monthlyChargePerMWithPromo = String.valueOf(String.format("%.2f", i_monthlyChargePerMWithPromo));
		monthlyChargePerQuarterWithoutPromo = String
				.valueOf(String.format("%.2f", i_monthlyChargePerQuarterWithoutPromo));
		monthlyChargePerQuarterWithPromo = String.valueOf(String.format("%.2f", i_monthlyChargePerQuarterWithPromo));

		annualChargePerMWithoutPromo = String.valueOf(String.format("%.2f", i_annualChargePerMWithoutPromo));
		annualChargePerMWithPromo = String.valueOf(String.format("%.2f", i_annualChargePerMWithPromo));
		annualChargePerYWithoutPromo = String.valueOf(String.format("%.2f", i_annualChargePerYWithoutPromo));
		annualChargePerYWithPromo = String.valueOf(String.format("%.2f", i_annualChargePerYWithPromo));

	}

	private void checkMemberLoginPage() {
		try {
			Thread.sleep(3000);
			refApplicationGenericUtils.switchingFrame(objectRepository.get("VisitMemberPortal.iFrame"));
			softAssert.assertEquals(driver.findElement(objectRepository.get("VisitMemberPortal.LoginButton")).isDisplayed(),true);
		} catch (Exception e) {
			softAssert.assertEquals(driver.findElement(objectRepository.get("VisitMemberPortal.LoginButton")).isDisplayed(),
					true);
		}
		ApplicationGenericUtils.extentReportLogPASS();
	}

	private void planSelection(Object planType) {
		if (planType.toString().equalsIgnoreCase("ANNUAL")) {
			refApplicationGenericUtils.clickOnElement(objectRepository.get("Step3.AnnualPlanSelectionBox"),
					"AnnualPlanSelectionBox");
		} else if (planType.toString().equalsIgnoreCase("QUARTERLY")) {
			refApplicationGenericUtils.clickOnElement(objectRepository.get("Step3.QuarterlyPlanSelectionBox"),
					"QuarterlyPlanSelectionBox");
		}
	}

	private void MessageValidation() {
		refApplicationGenericUtils.waitForElement(objectRepository.get("ThankyouPage.VisitMemberPortal"), 120);
		String successMessage = driver.findElement(objectRepository.get("Thankyou.Message")).getText();
		if (!successMessage.contains(tcParameters.get("ThankYouMessage").toString())) {
			Assert.fail(tcParameters.get("ThankYouMessage").toString()
					+ " :Thank you message is not displayed as Expected");
		}
		ApplicationGenericUtils.extentReportLogPASS();
	}
	public void validateAcqNoEmailPage2() {
		Object emailId=tcParameters.get("EmailId")+ApplicationGenericUtils.fetchCurrentDateAndTime();
		objectRepository.put("MemberLoginEmailID",By.xpath(emailId.toString()));
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step2.PetHeroAccount.EmailID"),"Email ID", emailId);
		refApplicationGenericUtils.clickOnElement(objectRepository.get("Step2.PetHeroAccount.ContinueButton"),"Next Button");
		refApplicationGenericUtils.checkForElement(objectRepository.get("Step2.PetHeroAccount.InvalidEmailID"), "Invalid EmailID");
		ApplicationGenericUtils.extentReportLogPASS();
		
	}
	public void validateFacebook() throws InterruptedException {
		refApplicationGenericUtils.switchingFrame(objectRepository.get("Step2.PetHeroAccount.FbFrame"));
		refApplicationGenericUtils.checkForButtonFunctionality(objectRepository.get("Step2.PetHeroAccount.FacebookButton"), "Facebook");
		ApplicationGenericUtils.extentReportLogPASS();
	}
	public String validateAcqPage3Validation(String validationType){
		Object emailId=tcParameters.get("EmailId")+ApplicationGenericUtils.fetchCurrentDateAndTime()+"@test.co.in";
		objectRepository.put("MemberLoginEmailID",By.xpath(emailId.toString()));
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step2.PetHeroAccount.EmailID"),"Email ID", emailId);
		refApplicationGenericUtils.clickOnElement(objectRepository.get("Step2.PetHeroAccount.ContinueButton"),"Next Button");
		planSelection(tcParameters.get("PlanType"));
		//promocode selection
		Object promocode=tcParameters.get("PromoCode");
		if(!promocode.equals("No_Value")){
			refApplicationGenericUtils.clickOnElement(objectRepository.get("Step3.HavePromoCodeText"),"HavePromoCodeText");
			refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.EnterPromoCodeTextBox"),"EnterPromoCodeTextBox", promocode);
			refApplicationGenericUtils.clickOnElement(objectRepository.get("Step3.PromoCodeApplyButton"),"PromoCodeApplyButton");
			refApplicationGenericUtils.checkForElement(objectRepository.get("Step3.PromoMessage"),"PromoMessage");
		}
		//Card Details
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.BillingInfoCardNumber"),"BillingInfoCardNumber", tcParameters.get("CardNumber").toString());
		refApplicationGenericUtils.selectValueFromList(objectRepository.get("Step3.BillingInfoExpiryMonth"), "BillingInfoExpiryMonth", Double.valueOf(tcParameters.get("MM").toString()).longValue());
		refApplicationGenericUtils.selectValueFromList(objectRepository.get("Step3.BillingInfoExpiryYear"), "BillingInfoExpiryYear", Double.valueOf(tcParameters.get("YYYY").toString()).longValue());
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.BillingInfoCVVCode"),"BillingInfoCVVCode", Double.valueOf(tcParameters.get("CVV").toString()).longValue());
		//Address
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.BillingInfoFirstName"),"BillingInfoFirstName", tcParameters.get("FirstName"));
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.BillingInfoLastName"),"BillingInfoLastName", tcParameters.get("LastName"));
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.BillingInfoAddress"),"BillingInfoAddress", tcParameters.get("Address"));
		//refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.BillingInfoAptSuite"),"Step3.BillingInfoAptSuite", tcParameters.get("Apt_Suite"));
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.BillingInfoCity"),"BillingInfoCity", tcParameters.get("City"));
		refApplicationGenericUtils.selectValueFromList(objectRepository.get("Step3.BillingInfoState"),"BillingInfoState", tcParameters.get("State"));
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.BillingInfoZipCode"),"BillingInfoZipCode", tcParameters.get("Zipcode"));
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.BillingInfoPassword"),"BillingInfoPassword", tcParameters.get("Password"));
		refApplicationGenericUtils.clickOnElement(objectRepository.get("Step3.BillingInfoSubmitButton"),"BillingInfoSubmitButton");
		MessageValidation();
		return emailId.toString();
	}
	public void breadCrumbsLink() throws InterruptedException{
		
		// Links should come from constants
		refApplicationGenericUtils.verifyingUrl("qa-my.pethero.com/storefront/site/mm-pethero-4step-acquisition.html#p3");
		refApplicationGenericUtils.clickOnElement(objectRepository.get("Step3.Registerlink"), "Registerlink");
		refApplicationGenericUtils.verifyingUrl("qa-my.pethero.com/storefront/site/mm-pethero-4step-acquisition.html#p2");
		refApplicationGenericUtils.clickOnElement(objectRepository.get("Step2.PetHeroAccount.ContinueButton"),"PetHeroAccount.ContinueButton");
		refApplicationGenericUtils.clickOnElement(objectRepository.get("Step3.BuildPlanLink"), "BuildPlanLink");
		refApplicationGenericUtils.verifyingUrl("qa-my.pethero.com/storefront/site/mm-pethero-4step-acquisition.html#p1");
		ApplicationGenericUtils.extentReportLogPASS();
	}
	public void checkboxLink() throws InterruptedException{
		Object emailId=tcParameters.get("EmailId")+ApplicationGenericUtils.fetchCurrentDateAndTime()+"@test.co.in";
		objectRepository.put("MemberLoginEmailID",By.xpath(emailId.toString()));
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step2.PetHeroAccount.EmailID"),"EmailID", emailId);
		refApplicationGenericUtils.clickOnElement(objectRepository.get("Step2.PetHeroAccount.ContinueButton"),"ContinueButton");
	
		if(! driver.findElement(By.xpath(".//*[@id='G_0'][@type='checkbox']")).isSelected()){		
		refApplicationGenericUtils.verifyingText(objectRepository.get("Step3.ShippingTextVerify"), "ENTER YOUR SHIPPING INFORMATION");
		
		}else{
			refApplicationGenericUtils.clickOnElement(objectRepository.get("Step3.CheckBoxButton"), "CheckBoxButton");
			refApplicationGenericUtils.verifyingText(objectRepository.get("Step3.ShippingTextVerify"), "ENTER YOUR SHIPPING INFORMATION");
			refApplicationGenericUtils.checkForElement(objectRepository.get("Step3.ShippingInfoFirstName"), "ShippingInfo FirstName");
			refApplicationGenericUtils.checkForElement(objectRepository.get("Step3.ShippingInfoLastName"), "ShippingInfo LastName");
		}
		ApplicationGenericUtils.extentReportLogPASS();
	}
	public void ValidationEditPlan(int noOfPets, HashMap<String, String> petCharges) throws InterruptedException {
		Object emailId = tcParameters.get("EmailId") + ApplicationGenericUtils.fetchCurrentDateAndTime()
				+ "@test.co.in";
		objectRepository.put("MemberLoginEmailID", By.xpath(emailId.toString()));
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step2.PetHeroAccount.EmailID"),
				"EmailID", emailId);
		refApplicationGenericUtils.clickOnElement(objectRepository.get("Step2.PetHeroAccount.ContinueButton"),
				"NextButton");

		
		// promocode selection
		Object promocode = tcParameters.get("PromoCode");
		if (!promocode.equals("No_Value")) {
			refApplicationGenericUtils.clickOnElement(objectRepository.get("Step3.HavePromoCodeText"),
					"HavePromoCodeText");
			refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.EnterPromoCodeTextBox"),
					"EnterPromoCodeTextBox", promocode);
			refApplicationGenericUtils.clickOnElement(objectRepository.get("Step3.PromoCodeApplyButton"),
					"PromoCodeApplyButton");
			refApplicationGenericUtils.checkForElement(objectRepository.get("Step3.PromoMessage"),
					"PromoMessage");

			int counter = 0;
			do {
				Thread.sleep(500);
				if (refApplicationGenericUtils.getElementValue(objectRepository.get("Step3.PromoMessage"),"PromoMessage").equalsIgnoreCase("Applying coupon code...please wait.")){
					counter = 0;
				}else{
					counter = 1;
				}
				
			} while (counter == 0);
			softAssert.assertEquals(refApplicationGenericUtils.getElementValue(objectRepository.get("Step3.PromoMessage"),"PromoMessage"), promocode + " has been applied.");
		}


		// Calculate All charges according to No of Pets and pet charges
		calculateCharges(noOfPets, petCharges);

		// Validate all Pet charges
		validateAllCharges();
	}
	
	public void clickEdit(){
		refApplicationGenericUtils.clickOnElement(objectRepository.get("Step3.EditLink"), "EditLink");
	}

	public void validateInvalidPromo() throws InterruptedException{

		refApplicationGenericUtils.clickOnElement(objectRepository.get("Step3.HavePromoCodeText"),
				"HavePromoCodeText");
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.EnterPromoCodeTextBox"),
				"EnterPromoCodeTextBox", ApplicationGenericUtils.fetchCurrentDateAndTime());
		refApplicationGenericUtils.clickOnElement(objectRepository.get("Step3.PromoCodeApplyButton"),
				"PromoCodeApplyButton");
		refApplicationGenericUtils.checkForElement(objectRepository.get("Step3.PromoMessage"),
				"PromoMessage");

		int counter = 0;
		do {
			Thread.sleep(500);
			if (refApplicationGenericUtils.getElementValue(objectRepository.get("Step3.PromoMessage"),"PromoMessage").equalsIgnoreCase("Applying coupon code...please wait.")){
				counter = 0;
			}else{
				counter = 1;
			}
			
		} while (counter == 0);
		softAssert.assertEquals(refApplicationGenericUtils.getElementValue(objectRepository.get("Step3.PromoMessage"),"PromoMessage"), tcParameters.get("Invalid Promo Error Message").toString());

		ApplicationGenericUtils.extentReportLogPASS();
	}
	public String createAccountId(LinkedHashMap<Object, LinkedHashMap<Object, Object>> appName,LinkedHashMap<Object, Object> envDetails) throws InterruptedException {
		
		PetCustomizePage refPetCustomizePage = new PetCustomizePage(driver, objectRepository, softAssert);
		refPetCustomizePage.petSelection(tcParameters.get("PetSelection").toString(),
							tcParameters.get("SelectionPlusCount").toString(), "PLUS");
		Object emailId = tcParameters.get("EmailId") + ApplicationGenericUtils.fetchCurrentDateAndTime()
				+ "@test.co.in";
		objectRepository.put("MemberLoginEmailID", By.xpath(emailId.toString()));
		refApplicationGenericUtils.clickOnElement(objectRepository.get("Step1.PetCustomize.NextButton"),
				"NextButton");
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step2.PetHeroAccount.EmailID"),
				"Email ID", emailId);
		refApplicationGenericUtils.clickOnElement(objectRepository.get("Step2.PetHeroAccount.ContinueButton"),
				"Comtinue Button");
			// Validate 30 days Trial message before Promo.
		//validate30DaysTrailMessage();

		// promocode selection
		Object promocode = tcParameters.get("PromoCode");
		if (!promocode.equals("No_Value")) {
			refApplicationGenericUtils.clickOnElement(objectRepository.get("Step3.HavePromoCodeText"),
					"HavePromoCodeText");
			refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.EnterPromoCodeTextBox"),
					"EnterPromoCodeTextBox", promocode);
			refApplicationGenericUtils.clickOnElement(objectRepository.get("Step3.PromoCodeApplyButton"),
					"PromoCodeApplyButton");
			refApplicationGenericUtils.checkForElement(objectRepository.get("Step3.PromoMessage"),
					"PromoMessage");
			
			int counter = 0;
			do {
				Thread.sleep(500);
				if (refApplicationGenericUtils.getElementValue(objectRepository.get("Step3.PromoMessage"),"PromoMessage").equalsIgnoreCase("Applying coupon code...please wait.")){
					counter = 0;
				}else{
					counter = 1;
				}
				
			} while (counter == 0);
			softAssert.assertEquals(refApplicationGenericUtils.getElementValue(objectRepository.get("Step3.PromoMessage"),"PromoMessage"), promocode + " has been applied.");
		}

		planSelection(tcParameters.get("PlanType"));

		// Card Details
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.BillingInfoCardNumber"),
				"BillingInfoCardNumber", tcParameters.get("CardNumber").toString());
		refApplicationGenericUtils.selectValueFromList(objectRepository.get("Step3.BillingInfoExpiryMonth"),
				"BillingInfoExpiryMonth", Double.valueOf(tcParameters.get("MM").toString()).longValue());
		refApplicationGenericUtils.selectValueFromList(objectRepository.get("Step3.BillingInfoExpiryYear"),
				"BillingInfoExpiryYear", Double.valueOf(tcParameters.get("YYYY").toString()).longValue());
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.BillingInfoCVVCode"),
				"BillingInfoCVVCode", Double.valueOf(tcParameters.get("CVV").toString()).longValue());
		// Address
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.BillingInfoFirstName"),
				"BillingInfoFirstName", tcParameters.get("FirstName"));
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.BillingInfoLastName"),
				"BillingInfoLastName", tcParameters.get("LastName"));
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.BillingInfoAddress"),
				"BillingInfoAddress", tcParameters.get("Address"));
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.BillingInfoCity"),
				"BillingInfoCity", tcParameters.get("City"));
		refApplicationGenericUtils.selectValueFromList(objectRepository.get("Step3.BillingInfoState"),
				"BillingInfoState", tcParameters.get("State"));
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.BillingInfoZipCode"),
				"BillingInfoZipCode", tcParameters.get("Zipcode"));
		refApplicationGenericUtils.setElementValue(objectRepository.get("Step3.BillingInfoPassword"),
				"BillingInfoPassword", tcParameters.get("Password"));
		refApplicationGenericUtils.clickOnElement(objectRepository.get("Step3.BillingInfoSubmitButton"),
				"BillingInfoSubmitButton");
		MessageValidation();
		refApplicationGenericUtils.clickOnElement(objectRepository.get("ThankyouPage.VisitMemberPortal"),
				"VisitMemberPortal");
		return emailId.toString();
	}
	
	
}
