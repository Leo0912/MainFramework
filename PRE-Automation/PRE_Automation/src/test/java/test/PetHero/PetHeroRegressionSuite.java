package test.PetHero;

import java.io.IOException;

import java.util.HashMap;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
//import org.testng.asserts.SoftAssert;
import utilities.SoftAssert;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import pages.PetHero.FindAVetAsMemberPage;

import pages.PetHero.MarketingSitePage;
import pages.PetHero.MemberPortalHomePage;
import pages.PetHero.PetCustomizePage;
import pages.PetHero.PetHeroAccountPage;
import pages.PetHero.PetHeroLoginPage;
import pages.PetHero.VetSavingsCardPage;
import pages.PetHero.ViewMemberPortalPage;
import pages.PetHero.YourAccountPortalPage;
import test.FrameworkDriverScript;
import utilities.ApplicationGenericUtils;

public class PetHeroRegressionSuite extends FrameworkDriverScript {
	WebDriver driverLoginPage = null;
	PetHeroLoginPage refPetHeroLoginPage;
	String MemberEmailID;
	HashMap<String, String> petCharges = new HashMap<String, String>();
	int noofPets;
	ApplicationGenericUtils refApplicationGenericUtils;

	public void reportAndPageinit() {
		softAssert= new SoftAssert();
		reportLogger = extent.createTest(Thread.currentThread().getStackTrace()[2].getMethodName());
		if (appName.containsKey("Local Machine")) {
			refPetHeroLoginPage = new PetHeroLoginPage(driver, objectRepository, appName);

		} else {
			refPetHeroLoginPage = new PetHeroLoginPage(driver, objectRepository, appName);
		}
	}

	public void SingleDogAcquisitionFlow() throws IOException {
		reportAndPageinit();
		try {

			driverLoginPage = refPetHeroLoginPage.logintoApplication(envDetails, "Subscription");
			PetCustomizePage refPetCustomizePage = new PetCustomizePage(driverLoginPage, objectRepository, softAssert);
			petCharges = refPetCustomizePage.readandStorePricingPlans();
			
			refPetCustomizePage.validateNoPetSelected(tcParameters.get("NoPetSelectionAlert").toString());
			refPetCustomizePage.petSelection(tcParameters.get("PetSelection").toString(),
					tcParameters.get("SelectionPlusCount").toString(), "PLUS");
			
			refPetCustomizePage.validateSingleDog(tcParameters.get("PetSelection").toString());
			noofPets = refPetCustomizePage.clickNext();
			PetHeroAccountPage refPetHeroAccountPage = new PetHeroAccountPage(driverLoginPage, objectRepository,
					softAssert, tcParameters);
			refPetHeroAccountPage.createPetHeroAccount(noofPets, petCharges);
			
			
			ApplicationGenericUtils.extentReportLogENDTEST();
		} catch (AssertionError | Exception e) {
			ApplicationGenericUtils.extentReportLogFAIL(e.getMessage());
			e.printStackTrace();
		} finally {
			extent.flush();
			closeBrowser();
		}
	}

	public void SingleCatAcquisitionFlow() throws IOException {
		reportAndPageinit();
		try {

			driverLoginPage = refPetHeroLoginPage.logintoApplication(envDetails, "Subscription");
			PetCustomizePage refPetCustomizePage = new PetCustomizePage(driverLoginPage, objectRepository, softAssert);
			petCharges = refPetCustomizePage.readandStorePricingPlans();
			refPetCustomizePage.petSelection(tcParameters.get("PetSelection").toString(),
					tcParameters.get("SelectionPlusCount").toString(), "PLUS");
			refPetCustomizePage.validateSingleCat(tcParameters.get("PetSelection").toString());
			noofPets = refPetCustomizePage.clickNext();
			PetHeroAccountPage refPetHeroAccountPage = new PetHeroAccountPage(driverLoginPage, objectRepository,
					softAssert, tcParameters);
			refPetHeroAccountPage.createPetHeroAccount(noofPets, petCharges);

			ApplicationGenericUtils.extentReportLogENDTEST();
		} catch (AssertionError | Exception e) {
			ApplicationGenericUtils.extentReportLogFAIL(e.getMessage());
			e.printStackTrace();
		} finally {
			extent.flush();
			closeBrowser();
		}
	}

	public void MultiPetMiniandMeowAcquisitionFlow() throws IOException {
		reportAndPageinit();
		try {

			driverLoginPage = refPetHeroLoginPage.logintoApplication(envDetails, "Subscription");
			PetCustomizePage refPetCustomizePage = new PetCustomizePage(driverLoginPage, objectRepository, softAssert);
			petCharges = refPetCustomizePage.readandStorePricingPlans();
			refPetCustomizePage.petSelection(tcParameters.get("PetSelection").toString(),
					tcParameters.get("SelectionPlusCount").toString(), "PLUS");
			refPetCustomizePage.validateMultiPetMiniandMeow(tcParameters.get("PetSelection").toString());
			noofPets = refPetCustomizePage.clickNext();
			PetHeroAccountPage refPetHeroAccountPage = new PetHeroAccountPage(driverLoginPage, objectRepository,
					softAssert, tcParameters);
			refPetHeroAccountPage.createPetHeroAccount(noofPets, petCharges);

			ApplicationGenericUtils.extentReportLogENDTEST();
		} catch (AssertionError | Exception e) {
			ApplicationGenericUtils.extentReportLogFAIL(e.getMessage());
			e.printStackTrace();
		} finally {
			extent.flush();
			closeBrowser();
		}
	}

	public void MultiPetAllAcquisitionFlow() throws IOException {
		reportAndPageinit();
		try {

			driverLoginPage = refPetHeroLoginPage.logintoApplication(envDetails, "Subscription");
			PetCustomizePage refPetCustomizePage = new PetCustomizePage(driverLoginPage, objectRepository, softAssert);
			petCharges = refPetCustomizePage.readandStorePricingPlans();
			refPetCustomizePage.petSelection(tcParameters.get("PetSelection").toString(),
					tcParameters.get("SelectionPlusCount").toString(), "PLUS");
			refPetCustomizePage.petSelection(tcParameters.get("PetSelection").toString(),
					tcParameters.get("SelectionMinusCount").toString(), "MINUS");
			refPetCustomizePage.validateMultiPetAll(tcParameters.get("PetSelection").toString());
			noofPets = refPetCustomizePage.clickNext();
			PetHeroAccountPage refPetHeroAccountPage = new PetHeroAccountPage(driverLoginPage, objectRepository,
					softAssert, tcParameters);

			refPetHeroAccountPage.createPetHeroAccount(noofPets, petCharges);

			ApplicationGenericUtils.extentReportLogENDTEST();
		} catch (AssertionError | Exception e) {
			ApplicationGenericUtils.extentReportLogFAIL(e.getMessage());
			e.printStackTrace();
		} finally {
			extent.flush();
			closeBrowser();
		}
	}
	public void ValidateAccountMemberPortal() throws IOException {
		reportAndPageinit();

		try {
			driverLoginPage = refPetHeroLoginPage.logintoApplication(envDetails, "Subscription");
			PetHeroAccountPage refPetHeroAccountPage = new PetHeroAccountPage(driverLoginPage, objectRepository,
					softAssert, tcParameters);
			String mailId=refPetHeroAccountPage.createAccountId(appName, envDetails);
			closeBrowser();
			driverLoginPage = refPetHeroLoginPage.logintoApplication(envDetails, "MemberPortal");
			ViewMemberPortalPage memberPortalPage = new ViewMemberPortalPage(driverLoginPage, objectRepository,
					softAssert, tcParameters);
			memberPortalPage.loginMemberPortal(mailId);
			reportLogger.info("Member Login Success");

			MemberPortalHomePage refMemberPortalHomePage = new MemberPortalHomePage(driverLoginPage, objectRepository,
					softAssert, tcParameters);
			
			refMemberPortalHomePage.verifyAccountLink();
			refMemberPortalHomePage.verifyPetsSubscribed();
			YourAccountPortalPage refYourAccountPage = new YourAccountPortalPage(driverLoginPage, objectRepository,
					softAssert, tcParameters);
			refYourAccountPage.verifyHomeLink();	
			refYourAccountPage.validateDetails();
			refMemberPortalHomePage.validateAddBenefits();			
			ApplicationGenericUtils.extentReportLogENDTEST();
		} catch (AssertionError | Exception e) {
			ApplicationGenericUtils.extentReportLogFAIL(e.getMessage());
			e.printStackTrace();
		} finally {
			extent.flush();
			closeBrowser();
		}
	}
	

	public void ValidateMemberPortal() throws IOException {
		reportAndPageinit();

		try {
			String memberId;
			driverLoginPage = refPetHeroLoginPage.logintoApplication(envDetails, "Subscription");
			
			PetHeroAccountPage refPetHeroAccountPage = new PetHeroAccountPage(driverLoginPage, objectRepository,
					softAssert, tcParameters);
			String mailId=refPetHeroAccountPage.createAccountId(appName, envDetails);
			
			refPetHeroAccountPage.driver.close();
					
			driverLoginPage = refPetHeroLoginPage.logintoApplication(envDetails, "MemberPortal");
			ViewMemberPortalPage memberPortalPage = new ViewMemberPortalPage(driverLoginPage, objectRepository,
					softAssert, tcParameters);
			memberPortalPage.loginMemberPortal(mailId); 
			reportLogger.info("Member Login Success");
			
			MemberPortalHomePage refMemberPortalHomePage = new MemberPortalHomePage(driverLoginPage, objectRepository,
					softAssert, tcParameters);
			
			memberId=refMemberPortalHomePage.getMemberId();
			
			refMemberPortalHomePage.verifyAndlickOnPrintVetSavingsCardLink();
			reportLogger.log(Status.PASS, MarkupHelper.createLabel("Availability of 'Print Vet Savings Card' link and it's clickable", ExtentColor.GREEN));	
			
										
			//Validation of "Print Vet Savings Card" page navigation and it's page contents
			
			VetSavingsCardPage refVetSavingsCardPage=new VetSavingsCardPage(driverLoginPage, objectRepository,
					softAssert, tcParameters);

			refVetSavingsCardPage.validateMemberPageHeader();
			reportLogger.log(Status.PASS, MarkupHelper.createLabel(
					"Member page Header i.e 'Pet’s Hero Since' and Date field is validated", ExtentColor.GREEN));

			refVetSavingsCardPage.validateMemberNameVetSavingsCard();
			reportLogger.log(Status.PASS,
					MarkupHelper.createLabel("Mamber's full name is validated on Vet Savings Card", ExtentColor.GREEN));

			refVetSavingsCardPage.validateMemberAddressVetSavingsCard();
			reportLogger.log(Status.PASS, MarkupHelper
					.createLabel("Mamber's full address is validated on Vet Savings Card", ExtentColor.GREEN));

			String memberIdOnCard=refVetSavingsCardPage.getMemberIdOnVetCard();
			
			softAssert.assertEquals(memberId,memberIdOnCard);
			reportLogger.log(Status.PASS, MarkupHelper.createLabel("Mamber's ID number is validated on Vet Savings Card", ExtentColor.GREEN));
			
		    refVetSavingsCardPage.validateExpiryDate();
			reportLogger.log(Status.PASS, MarkupHelper.createLabel(" Expiry date is validated on Vet Savings Card", ExtentColor.GREEN));
			
			//A Button named "Print Membership Card" and a link "Find a Vet" elements validation
			
			refVetSavingsCardPage.validatePrintmembercardAndFindAVetElements();
			reportLogger.log(Status.PASS, MarkupHelper.createLabel("Print member card And Find A Vet elements is validated on Vet Savings Card", ExtentColor.GREEN));
						
			//Validate visible href links on Vet Savings card
			
			refVetSavingsCardPage.linksOnPerHeroAndAssure();
			reportLogger.log(Status.PASS, MarkupHelper.createLabel("Three url's displayed on Vet Savings Card", ExtentColor.GREEN));
			
			//Validate header,petHero and PetAssure logo
				
			refVetSavingsCardPage.VerifypetHeroAndPetAssureImages();
			reportLogger.log(Status.PASS, MarkupHelper.createLabel("PatHero and PetAssure Logos are displayed", ExtentColor.GREEN));
			
			//Clicking on FindAVetAsMemberInHeader
			
			refVetSavingsCardPage.clickonFindAVetAsMemberInHeader();
								
			FindAVetAsMemberPage refFindAVetAsMemberPage=new FindAVetAsMemberPage(driverLoginPage, objectRepository,
					softAssert, tcParameters);
			//Test for valid zip code
			refFindAVetAsMemberPage.EnterValidZipCodeAndSearchforVet();
			int noOfVets=refFindAVetAsMemberPage.verifyDataList();
			if(noOfVets>0){
				reportLogger.log(Status.PASS, MarkupHelper.createLabel("Veterinary clinics displayed for valid Zipcode", ExtentColor.GREEN));
			}else{
				reportLogger.log(Status.FAIL, MarkupHelper.createLabel("Kindly Enter a Valid ZIP code", ExtentColor.RED));	
			}
			
			//Test for load more button.
			refFindAVetAsMemberPage.clickOnLoadMoreButton();
												
			//Test for Invalid zip code 
			refFindAVetAsMemberPage.EnterInValidZipCodeAndSearchforVet();
			int noOfVetsZero=refFindAVetAsMemberPage.verifyDataList();
			if(noOfVetsZero==0){
				reportLogger.log(Status.PASS, MarkupHelper.createLabel(" Veterinary clinics not displayed for Invalid ZipCode", ExtentColor.GREEN));
			}else{
				reportLogger.log(Status.FAIL, MarkupHelper.createLabel(" Kindly Enter InValid ZIpCode", ExtentColor.RED));
			}
						
			//Connect with Members on Member account
			refFindAVetAsMemberPage.clickOnPetHeroHeader();
			refMemberPortalHomePage.verifyAndlickOnConnectWithMembersLink();
					
			ApplicationGenericUtils.extentReportLogENDTEST();
		} catch (AssertionError | Exception e) {
			ApplicationGenericUtils.extentReportLogFAIL(e.getMessage());
			e.printStackTrace();
		} finally {
			extent.flush();
			closeBrowser();
		}

	}	

	public void AcquisitionPage2Validation() throws IOException {
		reportAndPageinit();
		try {

			driverLoginPage = refPetHeroLoginPage.logintoApplication(envDetails, "Subscription");
			PetCustomizePage refPetCustomizePage = new PetCustomizePage(driverLoginPage, objectRepository, softAssert);
			refPetCustomizePage.readandStorePricingPlans();
			refPetCustomizePage.petSelection(tcParameters.get("PetSelection").toString(),
					tcParameters.get("SelectionPlusCount").toString(), "PLUS");
			refPetCustomizePage.validateMultiPetMiniandMeow(tcParameters.get("PetSelection").toString());
			refPetCustomizePage.petSelection(tcParameters.get("PetSelection").toString(),
					tcParameters.get("SelectionPlusCount").toString(), "PLUS");
			refPetCustomizePage.petSelection(tcParameters.get("PetSelection").toString(),
					tcParameters.get("SelectionMinusCount").toString(), "MINUS");
			refPetCustomizePage.validateMultiPetAll(tcParameters.get("PetSelection").toString());
			refPetCustomizePage.clickNext();
			PetHeroAccountPage refPetHeroAccountPage = new PetHeroAccountPage(driverLoginPage, objectRepository,
					softAssert, tcParameters);

			refPetHeroAccountPage.validateAcqNoEmailPage2();
			refPetHeroAccountPage.validateFacebook();

			ApplicationGenericUtils.extentReportLogENDTEST();
		} catch (AssertionError | Exception e) {
			ApplicationGenericUtils.extentReportLogFAIL(e.getMessage());
			e.printStackTrace();
		} finally {
			extent.flush();
			closeBrowser();
		}
	}

	public void AcquisitionPage3Validation() throws IOException {
		reportAndPageinit();
		try {

			driverLoginPage = refPetHeroLoginPage.logintoApplication(envDetails, "Subscription");
			PetCustomizePage refPetCustomizePage = new PetCustomizePage(driverLoginPage, objectRepository, softAssert);
			refPetCustomizePage.readandStorePricingPlans();
			refPetCustomizePage.petSelection(tcParameters.get("PetSelection").toString(),
					tcParameters.get("SelectionPlusCount").toString(), "PLUS");
			refPetCustomizePage.validateMultiPetMiniandMeow(tcParameters.get("PetSelection").toString());
			refPetCustomizePage.petSelection(tcParameters.get("PetSelection").toString(),
					tcParameters.get("SelectionPlusCount").toString(), "PLUS");
			refPetCustomizePage.petSelection(tcParameters.get("PetSelection").toString(),
					tcParameters.get("SelectionMinusCount").toString(), "MINUS");
			refPetCustomizePage.validateMultiPetAll(tcParameters.get("PetSelection").toString());
			refPetCustomizePage.clickNext();
			PetHeroAccountPage refPetHeroAccountPage = new PetHeroAccountPage(driverLoginPage, objectRepository,
					softAssert, tcParameters);
			refPetHeroAccountPage.checkboxLink();
			refPetHeroAccountPage.breadCrumbsLink();
			
			ApplicationGenericUtils.extentReportLogENDTEST();
		} catch (AssertionError | Exception e) {
			ApplicationGenericUtils.extentReportLogFAIL(e.getMessage());
			e.printStackTrace();
		}finally {
			extent.flush();
			closeBrowser();
		}
	}

	public void EditPlanAndInvaliPromoAcquisitionFlow() throws IOException {
		reportAndPageinit();
		try {

			driverLoginPage = refPetHeroLoginPage.logintoApplication(envDetails, "Subscription");
			PetCustomizePage refPetCustomizePage = new PetCustomizePage(driverLoginPage, objectRepository, softAssert);
			petCharges = refPetCustomizePage.readandStorePricingPlans();
			refPetCustomizePage.petSelection(tcParameters.get("PetSelection").toString(),
					tcParameters.get("SelectionPlusCount").toString(), "PLUS");
			refPetCustomizePage.validateSingleDog(tcParameters.get("PetSelection").toString());
			noofPets = refPetCustomizePage.clickNext();
			PetHeroAccountPage refPetHeroAccountPage = new PetHeroAccountPage(driverLoginPage, objectRepository,
					softAssert, tcParameters);
			refPetHeroAccountPage.ValidationEditPlan(noofPets, petCharges);

			// click edit.
			refPetHeroAccountPage.clickEdit();
			refPetCustomizePage.petSelection(tcParameters.get("PetSelection").toString(),
					tcParameters.get("SelectionPlusCount").toString(), "PLUS");
			refPetCustomizePage.calculatePlan(tcParameters.get("PetSelection").toString());
			noofPets = refPetCustomizePage.clickNext();
			refPetHeroAccountPage.ValidationEditPlan(noofPets, petCharges);
			// Invalid Promo code Validation
			refPetHeroAccountPage.validateInvalidPromo();

			ApplicationGenericUtils.extentReportLogENDTEST();
		} catch (AssertionError | Exception e) {
			ApplicationGenericUtils.extentReportLogFAIL(e.getMessage());
			e.printStackTrace();
		} finally {
			extent.flush();
			closeBrowser();
		}
	}
	public void marketingSiteFindAVetValidation() throws IOException {
		reportAndPageinit();
		try {
			driverLoginPage = refPetHeroLoginPage.logintoApplication(envDetails, "MarketingPage");
			MarketingSitePage refMarketingSitePage = new MarketingSitePage(driverLoginPage, objectRepository,softAssert, tcParameters);
			refMarketingSitePage.findAVetLinkValidation();
			refMarketingSitePage.verifyInvalidZipCode();
			refMarketingSitePage.verifyValidZipCode();
			System.out.println(refMarketingSitePage.verifyDataList());
			//.
			ApplicationGenericUtils.extentReportLogENDTEST();

		} catch (AssertionError | Exception e) {
			ApplicationGenericUtils.extentReportLogFAIL(e.getMessage());
			e.printStackTrace();
		}finally {
			extent.flush();
			closeBrowser();
		}

	}
	public void marketingSiteFAQValidation() throws IOException {
		reportAndPageinit();
		try {
			driverLoginPage = refPetHeroLoginPage.logintoApplication(envDetails, "MarketingPage");
			MarketingSitePage refMarketingSitePage = new MarketingSitePage(driverLoginPage, objectRepository,softAssert, tcParameters);
			refMarketingSitePage.aboutPetHeroValidation();
			
			refMarketingSitePage.validateAnswerTabLinks();
			
			ApplicationGenericUtils.extentReportLogENDTEST();

		} catch (AssertionError | Exception e) {
			ApplicationGenericUtils.extentReportLogFAIL(e.getMessage());
			e.printStackTrace();
		}finally {
			extent.flush();
			closeBrowser();
		}

	}

	
	public void ValidateLoginMemberPortal() throws IOException {
		reportAndPageinit();

		try {
			driverLoginPage = refPetHeroLoginPage.logintoApplication(envDetails, "Subscription");
			PetHeroAccountPage refPetHeroAccountPage = new PetHeroAccountPage(driverLoginPage, objectRepository,
					softAssert, tcParameters);
			String mailId=refPetHeroAccountPage.createAccountId(appName, envDetails);
			closeBrowser();
			driverLoginPage = refPetHeroLoginPage.logintoApplication(envDetails, "MemberPortal");
			ViewMemberPortalPage memberPortalPage = new ViewMemberPortalPage(driverLoginPage, objectRepository,
					softAssert, tcParameters);
			//Invalid Login Validation
			memberPortalPage.invalidLoginMemberPortal("test1234@co.in");
						
			//Forgot Password Validation
			memberPortalPage.forgotPasswordMemberPortal("test1234@co.in");
						
			memberPortalPage.validLoginMemberPortal(mailId);
			
			ApplicationGenericUtils.extentReportLogENDTEST();			
		} catch (AssertionError | Exception e) {
			ApplicationGenericUtils.extentReportLogFAIL(e.getMessage());
			e.printStackTrace();
		} finally {
			extent.flush();
			closeBrowser();
		}

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

	
	


	public void marketingBrokenLinkValidation() throws IOException {

        reportAndPageinit();
        try {
            driverLoginPage = refPetHeroLoginPage.logintoApplication(envDetails, "MarketingPage");
            MarketingSitePage refMarketingSitePage = new MarketingSitePage(driverLoginPage, objectRepository,softAssert, tcParameters);
            refMarketingSitePage.brokenLinks();
            
            
            ApplicationGenericUtils.extentReportLogENDTEST();

        } catch (AssertionError | Exception e) {
			ApplicationGenericUtils.extentReportLogFAIL(e.getMessage());
			e.printStackTrace();
		} finally {
			extent.flush();
			closeBrowser();
		}

    }

}
