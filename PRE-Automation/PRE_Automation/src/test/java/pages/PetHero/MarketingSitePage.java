package pages.PetHero;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
//import org.testng.asserts.SoftAssert;
import utilities.SoftAssert;
import utilities.ApplicationGenericUtils;
import utilities.ExcelUtility;
import constants.Constants;

public class MarketingSitePage {

	// Declaration.
	public WebDriver driver;
	public LinkedHashMap<String, By> objectRepository;
	ApplicationGenericUtils refApplicationGenericUtils;
	SoftAssert softAssert;
	public static LinkedHashMap<Object, Object> tcParameters;

	public MarketingSitePage(WebDriver driver, LinkedHashMap<String, By> objectRepository, SoftAssert softAssert,
			LinkedHashMap<Object, Object> tcParameters) {
		this.driver = driver;
		this.objectRepository = objectRepository;
		refApplicationGenericUtils = new ApplicationGenericUtils(driver);
		this.softAssert = softAssert;
		MarketingSitePage.tcParameters = tcParameters;
	}

	public void findAVetLinkValidation() {

		refApplicationGenericUtils.checkForElement(objectRepository.get("MarketingSitePage.FindAVetLink"),
				"FindAVetLink");

		refApplicationGenericUtils.clickOnElement(objectRepository.get("MarketingSitePage.FindAVetLink"),
				"FindAVetLink");

	}

	public void verifyInvalidZipCode() {
		findAVetLinkValidation();

		refApplicationGenericUtils.checkForElement(
				objectRepository.get("MarketingSitePage.FindAVetPage.ZipCodeTextBox"), "zipCodeTextBox");

		refApplicationGenericUtils.setElementValue(
				objectRepository.get("MarketingSitePage.FindAVetPage.ZipCodeTextBox"), "zipCodeTextBox",
				tcParameters.get("InvalidZipCode"));

		refApplicationGenericUtils.checkForElement(objectRepository.get("MarketingSitePage.FindAVetPage.GoBtn"),
				"GoBtn");

		refApplicationGenericUtils.clickOnElement(objectRepository.get("MarketingSitePage.FindAVetPage.GoBtn"),
				"GoBtn");

		refApplicationGenericUtils
				.scrollToViewElement(objectRepository.get("MarketingSitePage.FindAVetPage.JoinNowBtn"), "JoinNowBtn");

		refApplicationGenericUtils.waitForVisibilty(objectRepository.get("MarketingSitePage.FindAVetPage.JoinNowBtn"),
				10);

	}

	public void verifyValidZipCode() {

		findAVetLinkValidation();

		refApplicationGenericUtils.checkForElement(
				objectRepository.get("MarketingSitePage.FindAVetPage.ZipCodeTextBox"), "zipCodeTextBox");

		refApplicationGenericUtils.setElementValue(
				objectRepository.get("MarketingSitePage.FindAVetPage.ZipCodeTextBox"), "zipCodeTextBox",
				Double.valueOf(tcParameters.get("Zipcode").toString()).longValue());

		refApplicationGenericUtils.checkForElement(objectRepository.get("MarketingSitePage.FindAVetPage.GoBtn"),
				"GoBtn");

		refApplicationGenericUtils.clickOnElement(objectRepository.get("MarketingSitePage.FindAVetPage.GoBtn"),
				"GoBtn");

		refApplicationGenericUtils
				.scrollToViewElement(objectRepository.get("MarketingSitePage.FindAVetPage.JoinNowBtn"), "JoinNowBtn");

		refApplicationGenericUtils.waitForVisibilty(objectRepository.get("MarketingSitePage.FindAVetPage.JoinNowBtn"),
				10);

	}

	public int verifyDataList() {
		int size;

		refApplicationGenericUtils.waitForElement(objectRepository.get("MarketingSitePage.Frame1"), 30);

		refApplicationGenericUtils.checkForElement(objectRepository.get("MarketingSitePage.Frame1"), "Iframe1");

		refApplicationGenericUtils.switchingFrame(objectRepository.get("MarketingSitePage.Frame1"));

		refApplicationGenericUtils.waitForElement(objectRepository.get("MarketingSitePage.NoOfVetsInDataList"), 30);

		List<WebElement> numberOfVetsElements = driver
				.findElements(objectRepository.get("MarketingSitePage.NoOfVetsInDataList"));

		size = numberOfVetsElements.size();

		return size;
	}

	public void aboutPetHeroValidation() throws InterruptedException {
		refApplicationGenericUtils.waitForVisibilty(objectRepository.get("MarketingSitePage.AboutPetHeroTitle"), 30);
		refApplicationGenericUtils.clickOnElement(objectRepository.get("MarketingSitePage.AboutPetHeroTitle"),
				"AboutPetHeroTitle");
		refApplicationGenericUtils.clickOnElement(objectRepository.get("MarketingSitePage.AboutPetHeroTitle"),
				"AboutPetHeroTitle");
		refApplicationGenericUtils.waitForVisibilty(objectRepository.get("AboutPetHero.Header"), 30);
		List<WebElement> TabNames = driver.findElements(objectRepository.get("MarketingSitePage.FAQTabs"));
		// LinkedHashMap<Object, LinkedHashMap<Object, Object>> FAQLinksData =
		// refExcelUtility.readExcel(excelFilePath,.
		// Constants.PETHERO_TESTCASE_SHEET_NAME);
		String failFlag;
		int Tabflag = 1;

		for (WebElement Verifytabs : TabNames) {
			try {
				if (Constants.TabsNm.equals(Verifytabs)) {
					System.out.println(Verifytabs.getText() + " Tab is Available");
				}
			} catch (Exception e) {
				e.printStackTrace();
			ApplicationGenericUtils.extentReportLogFAIL("Tabs names are missing: " + Verifytabs.getText());
			}
		}
		String[] questionTabChar = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k" };
		int questionTabInd = 0;
		for (WebElement tabs : TabNames) {
			int questionInd = 0;
			try {
				tabs.click();
			} catch (Exception e) {
				e.printStackTrace();
				ApplicationGenericUtils.extentReportLogFAIL("Problem in looping tabs: " + tabs.getText());
			}
			Thread.sleep(2000);
			List<WebElement> Tabsquestions = driver
					.findElements(objectRepository.get("AboutPetHero.PetHeroQuestionTab" + Tabflag));
			for (WebElement eachQuestion : Tabsquestions) {
				questionInd++;
				eachQuestion.click();
				Thread.sleep(1000);
				try {
					eachQuestion.findElement(objectRepository.get("AboutPetHero.PetHeroAnswerTab" + Tabflag));
					Thread.sleep(1000);
					// System.out.println(eachQuestion.findElements(objectRepository.get("AboutPetHero.PetHeroAnswerTab"+Tabflag)).size());
					//System.out.println("check answer status: \t" + eachQuestion.findElement(By.xpath("//div[contains(@id,'question"+ questionTabChar[questionTabInd] + questionInd + "')]//li")).isDisplayed());
					if (!eachQuestion.findElement(By.xpath(
							"//div[contains(@id,'question" + questionTabChar[questionTabInd] + questionInd + "')]//li"))
							.isDisplayed()) {
						failFlag = "Y";
						ApplicationGenericUtils.extentReportLogFAIL("Answer for Question: " + eachQuestion.getText()+" not displayed");
						System.out.println("Answer for Question: " + eachQuestion.getText()+" not displayed!");
					} else {
						System.out.println("Answer for Question: " + eachQuestion.getText()+" is displayed successfully!");
					}
				} catch (Exception e) {
					e.printStackTrace();
					failFlag = "Y";
					ApplicationGenericUtils.extentReportLogFAIL("Question doesn't have answer: " + eachQuestion.getText());
				}
			}
			Tabflag = Tabflag + 1;
			questionTabInd++;
		}
	}

	public void validateAnswerTabLinks() throws InterruptedException, IOException {

		LinkedHashMap<Object, LinkedHashMap<Object, Object>> FaqTabs;
		LinkedHashMap<Object, LinkedHashMap<Object, Object>> AnswerLinks;

		refApplicationGenericUtils.clickOnElement(objectRepository.get("MarketingSitePage.AboutPetHeroTitle"),
				"AboutPetHeroTitle");
		refApplicationGenericUtils.waitForVisibilty(objectRepository.get("AboutPetHero.Header"), 30);
		ExcelUtility refExcelUtility = new ExcelUtility();
		String excelFilePath = Constants.PETHEROLINKS;
		int Tabflag = 1;
		FaqTabs = refExcelUtility.readExcel(excelFilePath, Constants.PETHEROLINKQUESTIONTABS);
		AnswerLinks = refExcelUtility.readExcel(excelFilePath, Constants.PETHEROLINKANSWERLINKS);

		Set<Object> FaqTab = FaqTabs.keySet();
		Set<Object> AnswerLinksText = AnswerLinks.keySet();
		String homeUrl = driver.getCurrentUrl();
		String homeWindow = driver.getWindowHandle();

		Set<Object> Tab = FaqTabs.keySet();
		int i = 0;

		for (Object Tablink : Tab) {

			try {

				refApplicationGenericUtils.clickOnElement(By.xpath(Tablink.toString()), Tablink.toString());
				Thread.sleep(2000);
				List<WebElement> Tabsquestions = driver
						.findElements(objectRepository.get("AboutPetHero.PetHeroQuestionTab" + Tabflag));
				for (int j = 1; j <= Tabsquestions.size(); j++) {

					try {
						By questLink = By.xpath("(//div[@class='tab-content']/div[" + Tabflag
								+ "]//div[@class='panel']/div/a)[" + j + "]");
						String questLinkText = driver.findElement(questLink).getText();

						refApplicationGenericUtils.clickOnElement(questLink, "Question: "+questLinkText);
						Thread.sleep(1000);

						String xpath = "//div[@id='question" + (char) ('a' + i) + j + "']//li";
						By answerText = By.xpath(xpath);

						refApplicationGenericUtils.checkForElement(answerText, "answerText");

						List<WebElement> noLinksinAnwerTab = driver.findElements(By.xpath(xpath + "//a"));

						if (noLinksinAnwerTab.size() >= 1) {
							Reporter.log("Links are there in the answer", true);

							for (WebElement link : noLinksinAnwerTab) {

								// System.out.println(link.getText());

								String linkText = link.getText();
								if (link.getText().equalsIgnoreCase("CustomerCare@PetHero.com")) {

									Reporter.log("Dont Click its mail Link", true);
								}

								else {
									link.click();
									Thread.sleep(1000);
									HashSet<String> noofWindows = (HashSet) driver.getWindowHandles();

									if (noofWindows.size() > 1) {
										refApplicationGenericUtils.switchingTabs(homeWindow, noofWindows);
										Thread.sleep(1000);

										validateAnswerLinks(AnswerLinksText, AnswerLinks, linkText, questLinkText);

										driver.close();
										driver.switchTo().window(homeWindow);
									}

									else {
										Thread.sleep(1000);
										// System.out.println(driver.getCurrentUrl());
										validateAnswerLinks(AnswerLinksText, AnswerLinks, linkText, questLinkText);
										driver.get(homeUrl);
										refApplicationGenericUtils.clickOnElement(By.xpath(Tablink.toString()),
												Tablink.toString());

									}

								}

							}
						}

						else {
							Reporter.log("No Links in the Answer tab", true);
						}

					} catch (Exception e) {
						Reporter.log("Unable to find the QuestionLink or answer text", true);
						ApplicationGenericUtils.extentReportLogFAIL("Unable to find the QuestionLink or answer text");
					}
				}
			}

			catch (Exception e) {
				Reporter.log("Unable to find the tab", true);
				ApplicationGenericUtils.extentReportLogFAIL("Unable to find the tab");
			}
			i++;
			Tabflag = Tabflag + 1;
		}

	}

	public void validateAnswerLinks(Set<Object> Linktext,
			LinkedHashMap<Object, LinkedHashMap<Object, Object>> AnswerLinks, String text, String questtext) {
		try {

			if (Linktext.contains(questtext + ":" + text)) {
				String linkValue = AnswerLinks.get(questtext + ":" + text).get("Link").toString();
				if (linkValue.equalsIgnoreCase(driver.getCurrentUrl())) {
					Reporter.log("Link Matches. Expected ["+linkValue+"] Matches Actual ["+driver.getCurrentUrl()+"]", true);
					ApplicationGenericUtils.extentReportLogPASS("Link Matches. Expected ["+linkValue+"] Matches Actual ["+driver.getCurrentUrl()+"]");
				} else {
					Reporter.log("Link does not Matches. Expected ["+linkValue+"] Actual ["+driver.getCurrentUrl()+"]", true);
					ApplicationGenericUtils.extentReportLogFAIL("Link does not Matches. Expected ["+linkValue+"] Actual ["+driver.getCurrentUrl()+"]");
				}
			}

			else {
				Reporter.log("Link is not clicked ", true);
			}
		} catch (Exception e) {
			Reporter.log("Unable to find the Link in the sheet", true);
			ApplicationGenericUtils.extentReportLogFAIL("Unable to find the Link in the sheet");
			e.printStackTrace();
		}

	}

	public void brokenLinks() {
		LinkedHashMap<Object, LinkedHashMap<Object, Object>> petHeroMSLinks;
		ExcelUtility refExcelUtility = new ExcelUtility();
		String excelFilePath = Constants.PETHEROLINKS;

		try {
			petHeroMSLinks = refExcelUtility.readExcel(excelFilePath, Constants.PETHEROLINKSHEETNAME);

			Set<Object> marketsitelinks = petHeroMSLinks.keySet();
			String homeUrl = driver.getCurrentUrl();
			String homeWindow = driver.getWindowHandle();

			for (Object link : marketsitelinks) {
				if (petHeroMSLinks.get(link).get("Xpath").toString() != "No_Value") {
					// driver.findElement(By.xpath(petHeroMSLinks.get(link).get("Xpath").toString())).click();
					refApplicationGenericUtils.clickOnElement(
							By.xpath(petHeroMSLinks.get(link).get("Xpath").toString()),
							petHeroMSLinks.get(link).get("Xpath").toString());
					Thread.sleep(3000);
					String actualUrl = driver.getCurrentUrl();

					if (driver.getWindowHandles().size() == 1)

					{

						if (homeUrl.equalsIgnoreCase(actualUrl)) {

							handleMarketSitePopup(link.toString(),
									petHeroMSLinks.get(link).get("Webelement").toString());
							Reporter.log("currenturl is " + actualUrl, true);

						} else {

							if (actualUrl.equalsIgnoreCase(petHeroMSLinks.get(link).get("Links").toString())) {
								Reporter.log("Link Matches ", true);

								System.out.println(petHeroMSLinks.get(link).get("Webelement").toString());

								if (refApplicationGenericUtils.checkForElement(
										By.xpath(petHeroMSLinks.get(link).get("Webelement").toString()),
										petHeroMSLinks.get(link).get("Webelement").toString())) {
									Reporter.log("Respective Element is displayed", true);
								} else {
									Reporter.log("Respective Element is not  displayed", true);
								}

							}

							else {
								Reporter.log("Link does not  Matches  ", true);
							}

						}

					}

					else if (driver.getWindowHandles().size() > 1)

					{

						HashSet<String> noofWindows = (HashSet) driver.getWindowHandles();
						refApplicationGenericUtils.switchingTabs(homeWindow, noofWindows);

						String swtichedTabUrl = driver.getCurrentUrl();

						if (swtichedTabUrl.equalsIgnoreCase(petHeroMSLinks.get(link).get("Links").toString())) {
							Reporter.log("Link Matches ", true);

							if (refApplicationGenericUtils.checkForElement(
									By.xpath(petHeroMSLinks.get(link).get("Webelement").toString()),
									petHeroMSLinks.get(link).get("Webelement").toString()))

							{

								Reporter.log("Respective Element is displayed", true);
							}

							else {
								Reporter.log("Respective Element is not  displayed", true);
							}

						}

						else {
							Reporter.log("Link does not  Matches  ", true);
						}

						driver.close();
						driver.switchTo().window(homeWindow);

					}

					driver.get(homeUrl);
				}
			}

		}

		catch (Exception | AssertionError e) {
			// TODO Auto-generated catch block
			ApplicationGenericUtils.extentReportLogFAIL(e.getMessage());
			e.printStackTrace();
		}
	}

	public void handleMarketSitePopup(Object link, String element) {

		if (link.toString().equalsIgnoreCase("Additional Details")) {

			refApplicationGenericUtils.checkForElement(objectRepository.get("MarketingSitePage.AdditionalDetailspopup"),
					"AdditionalDetailspopup");

			if (driver.findElement(By.xpath(element)).isDisplayed()) {
				Reporter.log("Respective Element is displayed in the popup", true);
			} else {
				Reporter.log("Respective Element is not  displayed in the popup", true);
			}

			refApplicationGenericUtils.clickOnElement(objectRepository.get("MarketingSitePage.AdditionalDetailspopup"),
					"AdditionalDetailspopup");
			Reporter.log("popup is closed", true);
		}

		else if (link.toString().equalsIgnoreCase("pricing")) {

			refApplicationGenericUtils.checkForElement(objectRepository.get("MarketingSitePage.pricingpopup"),
					"pricingpopup");

			if (driver.findElement(By.xpath(element)).isDisplayed()) {
				Reporter.log("Respective Element is displayed in the popup", true);
			} else {
				Reporter.log("Respective Element is not  displayed in the popup", true);
			}
			refApplicationGenericUtils.clickOnElement(objectRepository.get("MarketingSitePage.pricingpopup"),
					"pricingpopup");
			Reporter.log("popup is closed", true);

		}

		else {
			Reporter.log("No pop up is displayed , current page is refreshed ", true);
		}
	}

}
