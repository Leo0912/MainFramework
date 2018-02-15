package pages.PetHero;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
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

public class PetCustomizePage {

	// Declaration
	public WebDriver driver;
	public LinkedHashMap<String, By> objectRepository;
	ApplicationGenericUtils refApplicationGenericUtils;
	SoftAssert softAssert;
	public String singleCatCost;
	public String singleDogCost;
	public String multiPetCost;
	public String additionalPetCost;
	public ExtentTest reportLogger;
	HashMap<String, String> petCharges = new HashMap<String, String>(); 
	int noOfPets = 0;
	
	public PetCustomizePage(WebDriver driver, LinkedHashMap<String, By> objectRepository, SoftAssert softAssert){
		this.driver = driver;
		this.objectRepository = objectRepository;
		refApplicationGenericUtils = new ApplicationGenericUtils(driver);
		this.softAssert = softAssert;
	}

	public HashMap<String, String> readandStorePricingPlans() {
		try {
			refApplicationGenericUtils.clickOnElement(objectRepository.get("Step1.PetCustomize.helpButton"),
					"Help Button");
			singleCatCost = refApplicationGenericUtils.getElementValue(
					objectRepository.get("Step1.HelpPopUp.SingleCatCost"), "Single Cat Cost ");
			singleDogCost = refApplicationGenericUtils.getElementValue(
					objectRepository.get("Step1.HelpPopUp.SingleDogCost"), "Single Dog Cost ");
			multiPetCost = refApplicationGenericUtils.getElementValue(
					objectRepository.get("Step1.HelpPopUp.MultiPetCost"), "Multi Pet Cost ");
			additionalPetCost = refApplicationGenericUtils.getElementValue(
					objectRepository.get("Step1.HelpPopUp.AdditionalPetCost"), "Additional Pet Cost ");
			refApplicationGenericUtils.clickOnElement(objectRepository.get("Step1.HelpPopUp.HelpPopUpCloseButton"),
					"HelpPopUp Close Button");
			additionalPetCost = additionalPetCost.substring(additionalPetCost.indexOf("$"), additionalPetCost.length());
			
			singleCatCost = singleCatCost.substring(singleCatCost.indexOf("$") + 1, singleCatCost.indexOf("/"));
			singleDogCost = singleDogCost.substring(singleDogCost.indexOf("$") + 1, singleDogCost.indexOf("/"));
			multiPetCost = multiPetCost.substring(multiPetCost.indexOf("$") + 1, multiPetCost.indexOf("/"));
			additionalPetCost = additionalPetCost.substring(additionalPetCost.indexOf("$") + 1,
					additionalPetCost.indexOf(" "));
			
			
			petCharges.put("additionalPetCost", additionalPetCost);
			petCharges.put("singleCatCost", singleCatCost);
			petCharges.put("singleDogCost", singleDogCost);
			petCharges.put("multiPetCost", multiPetCost);
			
			objectRepository.put("singleCatCost", By.xpath(singleCatCost));
			objectRepository.put("singleDogCost", By.xpath(singleDogCost));
			objectRepository.put("multiPetCost", By.xpath(multiPetCost));
			objectRepository.put("additionalPetCost", By.xpath(additionalPetCost));
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return petCharges;
	}

	public String validateSingleDog(String objects) throws IOException {
		//refApplicationGenericUtils.clickOnElement(objectRepository.get("Step1.PetCustomize.MiniPuppyBox"),"MiniPuppyBox");
		String charge = refApplicationGenericUtils
				.getElementValue(objectRepository.get("Step1.PetCustomize.PlanSelectionText"), "Plan charge Text ");
		charge = charge.substring(charge.indexOf("$") + 1, charge.indexOf("/"));
		softAssert.assertEquals(
				charge, refApplicationGenericUtils.getObjectRepoValue(objectRepository.get("singleDogCost").toString())
				);
		calculatePlan(objects);
		ApplicationGenericUtils.extentReportLogPASS();
		return charge;
	}

	public String validateSingleCat(String objects) throws IOException {
		//refApplicationGenericUtils.clickOnElement(objectRepository.get("Step1.PetCustomize.MeowBox"), "MeowBox");
		String charge = refApplicationGenericUtils
				.getElementValue(objectRepository.get("Step1.PetCustomize.PlanSelectionText"), "Plan charge Text ");
		charge = charge.substring(charge.indexOf("$") + 1, charge.indexOf("/"));
		softAssert.assertEquals(
				refApplicationGenericUtils.getObjectRepoValue(objectRepository.get("singleCatCost").toString()),
				charge);
		calculatePlan(objects);
		ApplicationGenericUtils.extentReportLogPASS();
		return charge;
	}

	public String validateMultiPetMiniandMeow(String objects) throws IOException {
		//refApplicationGenericUtils.clickOnElement(objectRepository.get("Step1.PetCustomize.MeowBox"), "MeowBox");
		//refApplicationGenericUtils.clickOnElement(objectRepository.get("Step1.PetCustomize.MiniPuppyBox"),"MiniPuppyBox");

		String charge = refApplicationGenericUtils
				.getElementValue(objectRepository.get("Step1.PetCustomize.PlanSelectionText"), "Plan charge Text ");
		charge = charge.substring(charge.indexOf("$") + 1, charge.indexOf("/"));
		// System.out.println("Actual :\t" + charge);
		// System.out.println("Expected :\t" + calculatePlan("Mini|Meow"));
		//Assert.assertEquals(calculatePlan("Mini|Meow"), Integer.parseInt(charge));
		calculatePlan(objects);
		ApplicationGenericUtils.extentReportLogPASS();
		return charge;
	}

	public String validateMultiPetAll(String objects) throws IOException {
		String charge = refApplicationGenericUtils
				.getElementValue(objectRepository.get("Step1.PetCustomize.PlanSelectionText"), "Plan charge Text ");
		charge = charge.substring(charge.indexOf("$") + 1, charge.indexOf("/"));
		/*
		 * System.out.println("Actual :\t" + charge);
		 * System.out.println("Expected :\t" + calculatePlan(objects));
		 */
		softAssert.assertEquals(calculatePlan(objects), Integer.parseInt(charge));
		ApplicationGenericUtils.extentReportLogPASS();
		return charge;
	}

	public int calculatePlan(String objects) {

		String[] pet = objects.split("\\|");

		noOfPets = 0;
		for (int i = 0; i < pet.length; i++) {
			if (pet[i].equalsIgnoreCase("Mini")) {
				noOfPets = noOfPets + Integer.parseInt(refApplicationGenericUtils
						.getElementValue(objectRepository.get("Step1.PetCustomize.MiniPuppyBoxNumber"), "No Of Mini Selected"));
			} else if (pet[i].equalsIgnoreCase("Medium")) {
				noOfPets = noOfPets + Integer.parseInt(refApplicationGenericUtils
						.getElementValue(objectRepository.get("Step1.PetCustomize.MediumPuppyBoxNumber"), "No Of Medium Selected"));

			} else if (pet[i].equalsIgnoreCase("Mighty")) {
				noOfPets = noOfPets + Integer.parseInt(refApplicationGenericUtils
						.getElementValue(objectRepository.get("Step1.PetCustomize.MightyPuppyBoxNumber"), "No Of Mighty Selected"));
			} else if (pet[i].equalsIgnoreCase("Meow")) {
				noOfPets = noOfPets + Integer.parseInt(refApplicationGenericUtils
						.getElementValue(objectRepository.get("Step1.PetCustomize.MeowPuppyBoxNumber"), "No Of Meow Selected"));
			}

		}
		int planCharge = Integer.parseInt(
				refApplicationGenericUtils.getObjectRepoValue(objectRepository.get("singleCatCost").toString()));
		if (noOfPets > 1) {
			planCharge = ((noOfPets - 1) * Integer.parseInt(refApplicationGenericUtils
					.getObjectRepoValue(objectRepository.get("additionalPetCost").toString())))
					+ Integer.parseInt(refApplicationGenericUtils
							.getObjectRepoValue(objectRepository.get("singleCatCost").toString()));
			return planCharge;
		} else {
			return planCharge;
		}
	}

	public void validateNoPetSelected(String alertTextExpected) throws IOException {
		clickNext();
		String alertText = refApplicationGenericUtils.getAlertText();
		softAssert.assertEquals(alertText, alertTextExpected,"");
		refApplicationGenericUtils.acceptAlert();
		ApplicationGenericUtils.extentReportLogPASS();
	}

	public int clickNext() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		refApplicationGenericUtils.clickOnElement(objectRepository.get("Step1.PetCustomize.NextButton"),
				"Next Button");
		return noOfPets;
	}

	public void petSelection(String objects, String counts, String action) {
		By elementXpath = null;
		String[] pet = objects.split("\\|");
		String[] count = counts.split("\\|");

		String appendMinus = "";

		if (count.length==1){
			count[0] = count[0].substring(0, count[0].indexOf("."));
		}
		
		
		if (!action.equalsIgnoreCase("plus")) {
			appendMinus = "Minus";
		}

		for (int i = 0; i < pet.length; i++) {
			if (pet[i].equalsIgnoreCase("Mini")) {
				elementXpath = objectRepository.get("Step1.PetCustomize.MiniPuppyBox" + appendMinus);
			} else if (pet[i].equalsIgnoreCase("Medium")) {
				elementXpath = objectRepository.get("Step1.PetCustomize.MediumBox" + appendMinus);
			} else if (pet[i].equalsIgnoreCase("Mighty")) {
				elementXpath = objectRepository.get("Step1.PetCustomize.MightyBox" + appendMinus);
			} else if (pet[i].equalsIgnoreCase("Meow")) {
				elementXpath = objectRepository.get("Step1.PetCustomize.MeowBox" + appendMinus);
			}
			for (int clickcount = 0; clickcount < Integer.parseInt(count[i]); clickcount++) {
				refApplicationGenericUtils.clickOnElement(elementXpath, pet[i] +" pet button ");
			}
		}

	}

}
