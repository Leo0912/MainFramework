package utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import test.FrameworkDriverScript;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
//import org.testng.asserts.SoftAssert;
import utilities.SoftAssert;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import constants.Constants;

public class ApplicationGenericUtils extends FrameworkDriverScript {
	public static WebDriver driver;

	public ApplicationGenericUtils(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Explicit wait - Waiting for element
	 * 
	 * @param element
	 * @param timeoutinSec.
	 */
	public void waitForElement(By byXpath, int timeoutinSec) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeoutinSec);
			wait.until(ExpectedConditions.elementToBeClickable(byXpath));
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Error Occured in function waitForElement " + e.getMessage());
			extentReportLogFAIL("Error Occured in function waitForElement " + e.getMessage());
		}
	}

	/**
	 * 
	 * @param byXpath
	 * @param timeoutinSec
	 */
	public void waitForVisibilty(By byXpath, int timeoutinSec) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, timeoutinSec);
			wait.until(ExpectedConditions.presenceOfElementLocated(byXpath));
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Unable to locate the element " + e.getMessage());
			extentReportLogFAIL("Unable to locate the element " +e.getMessage());
		}
	}

	public boolean checkForElement(By byXpath, String elementName) {
		boolean flag;
		try {
			waitForVisibilty(byXpath, 30);
			flag = driver.findElement(byXpath).isDisplayed();
			reportLogger.info("Page Element is Found: " + elementName);
			System.out.println(elementName + " is displayed");
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
			Reporter.log("Error Occured in function checkForElement " + e.getMessage());
			extentReportLogFAIL(elementName + " is not displayed as Expected");
		}
		return flag;
	}

	public void selectValueFromList(By byXpath, String elementName, Object listValue) {
		try {
			waitForVisibilty(byXpath, 100);

			try {
				WebElement DropDown = driver.findElement(byXpath);
				Select sel = new Select(DropDown);
				sel.selectByValue(listValue.toString());
				reportLogger.info("Value \""+listValue.toString()+"\" selected from Dropdown: " + elementName);
			} catch (Exception e) {
				driver.findElement(byXpath).sendKeys(listValue.toString());
				reportLogger.info(elementName + "Selected value from List");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Error Occured in function selectFromList " + e.getMessage());
			extentReportLogFAIL(elementName + " is not displayed as Expected");
		}
	}

	public void clickOnElement(By byXpath, String elementName) {
		try {
			waitForElement(byXpath, 60);
			driver.findElement(byXpath).click();
			reportLogger.info(elementName + " clicked!");
			System.out.println(elementName + " clicked!");
		} catch (Exception e) {
			extentReportLogFAIL(elementName + " is not clickable!");
			e.printStackTrace();
			Reporter.log("Error Occured in function clickOnElement " + e.getMessage());
		}
	}

	public void setElementValue(By byXpath, String elementName, Object sendKeyValue) {
		try {
			waitForVisibilty(byXpath, 120);
			//driver.findElement(byXpath).clear();
			driver.findElement(byXpath).sendKeys(sendKeyValue.toString());
			reportLogger.info(elementName + " value set to--> \"" + sendKeyValue.toString()+"\"");
			System.out.println(elementName + " value set to--> \"" + sendKeyValue.toString()+"\"");
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Error Occured in function setElementValue " + e.getMessage());
			extentReportLogFAIL(elementName + " is not Clickable");
		}
	}

	public void clearElementValue(By byXpath, String elementName, Object sendKeyValue) {
		try {
			waitForVisibilty(byXpath, 120);
			driver.findElement(byXpath).clear();
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Error Occured in function setElementValue " + e.getMessage());
			extentReportLogFAIL(elementName + " is not Clickable");
		}
	}

	public void switchingFrame(By byFrameXpath) {
		try {
			waitForVisibilty(byFrameXpath, 60);
			WebElement elementFrame = driver.findElement(byFrameXpath);
			driver.switchTo().frame(elementFrame);
			//reportLogger.info("Frame switched");
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Error Occured in function switchingFrame " + e.getMessage());
			extentReportLogFAIL("Unable to Switch to Frame");
		}
	}

	public void switchingToDefaultFrame() {
		try {
			driver.switchTo().defaultContent();
			//reportLogger.info("Frame switched to default");
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Error Occured in function switchingFrame " + e.getMessage());
			extentReportLogFAIL("Unable to Switch to Frame");
		}
	}

	/**
	 * Author: Clinton checkSizeOfElements - Used to check for No. of
	 * Webelements
	 * 
	 * @param byWebElements
	 *            - Pass Elements xpath
	 * @param size
	 *            - Expected Size ex:-4
	 */
	public void checkSizeOfElements(By byWebElements, int size, String elementName) {
		int count = 0;
		try {
			count = driver.findElements(byWebElements).size();
			if (count == size) {
				Reporter.log("Number Of Elements available are: " + size);
				System.out.println(elementName + " contains " + size + " articles");
			} else {
				extentReportLogFAIL(size + " Elements identified which is not Expected");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Error Occured in function checkSizeOfElements " + e.getMessage());
			extentReportLogFAIL("Error Occured in function checkSizeOfElements " + e.getMessage());
		}
	}

	/**
	 * Author: Clinton checkForFeaturedContents - Used to check if the article
	 * has a Featured Image or Video
	 * 
	 * @param byVideoXpath:
	 *            Pass the Featured Video xpath
	 * @param VideoelementName:
	 *            Name can be "Video"
	 * @param byImageXpath:
	 *            Pass the Featured Image xpath
	 * @param ImageelementName:
	 *            Name can be "Image"
	 */

	public void checkForFeaturedContents(By byVideoXpath, String VideoelementName, By byImageXpath,
			String ImageelementName) {

		try {
			if (driver.findElement(byVideoXpath).isDisplayed()) {

				System.out.println(VideoelementName + " is available");
			}

			else if (driver.findElement(byImageXpath).isDisplayed()) {

				System.out.println(byImageXpath + " is available");
			}

			else {

				softAssert.fail("Featured Images/Videos not available for this article");
			}
		}

		catch (Exception e) {

			e.printStackTrace();
			Reporter.log("Error Occured in function checkForFeaturedContents " + e.getMessage());
			extentReportLogFAIL("Error Occured in function checkForFeaturedContents " + e.getMessage());
		}
	}

	/**
	 * Author: Clinton
	 * 
	 * @param byXpath
	 * @param elementName
	 */

	public void checkForShareTools(By byXpath, String elementName) {

		try {
			// waitForElement(byXpath, 120);
			List<WebElement> Allelements = driver.findElements(byXpath);

			for (int i = 0; i <= Allelements.size() - 1; i++) {

				if (Allelements.get(i).getAttribute("href").contains("facebook")
						| Allelements.get(i).getAttribute("href").contains("twitter")
						| Allelements.get(i).getAttribute("href").contains("mailto")
						| Allelements.get(i).getAttribute("href").contains("linkedin")
						| Allelements.get(i).getAttribute("href").contains("instagram")
						| Allelements.get(i).getAttribute("href").contains("pinterest")) {

					System.out.println(Allelements.get(i).getAttribute("href") + " is displayed");
				}

				else {
					extentReportLogFAIL("Required share tool is not displayed");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Error Occured in function checkForShareTools " + e.getMessage());
			extentReportLogFAIL(elementName + " are not displayed as Expected" + e.getMessage());
		}

	}

	/**
	 * Author: Clinton
	 * 
	 * @param byXpath
	 * @param elementName
	 */

	public void scrollToViewElement(By byXpath, String elementName) {

		try {

			JavascriptExecutor je = (JavascriptExecutor) driver;

			// Identify the WebElement which will appear after scrolling down

			WebElement elementToBeVisible = driver.findElement(byXpath);

			// now execute query which actually will scroll until that element
			// is not appeared on page.

			je.executeScript("arguments[0].scrollIntoView(true);", elementToBeVisible);
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Reporter.log("Error Occured in function checkForShareTools " + e.getMessage());
			softAssert.fail(elementName + " is not displayed as Expected" + e.getMessage());
			extentReportLogFAIL(e.getMessage());

		}
	}

	/**
	 * Author: Clinton checkForButtonFunctionality - Used to check for the
	 * functionality of buttons when clicked, if they open the Expected Page
	 * 
	 * @param byXpath
	 *            - Pass Elements xpath
	 * @param elementName
	 *            - Pass the Expected Page Title.
	 */

	public void checkForButtonFunctionality(By byXpath, String elementName) throws InterruptedException {

		try {
			clickOnElement(byXpath, elementName);
			Thread.sleep(5000);
			String HomePageWindow = driver.getWindowHandle();
			Set<String> wh = driver.getWindowHandles();
			Iterator<String> itr = wh.iterator();
			while (itr.hasNext()) {

				String ChildWindow = itr.next();
				driver.switchTo().window(ChildWindow);

			}

			String SubscribePageTitle = driver.getTitle();

			softAssert.assertEquals(SubscribePageTitle, elementName);

			// if(SubscribePageTitle.equals(elementName)){
			// System.out.println(elementName +" page is displayed");
			// }
			// System.out.println(SubscribePageTitle);
			driver.close();
			driver.switchTo().window(HomePageWindow);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Reporter.log("Error Occured in function checkForButtonFunctionality " + e.getMessage());
			extentReportLogFAIL(elementName + " is not displayed as Expected" + e.getMessage());
		}
	}

	/**
	 * Author: Clinton
	 * 
	 * @param byXpath
	 *            of the Grid containing all the images
	 * @param elementName:
	 *            FirstTenElements
	 */

	public void collectionImageGridValidation(By byXpath, String elementName) {

		try {
			List<WebElement> allImageTouts = driver.findElements(byXpath);

			for (int i = 0; i < allImageTouts.size() - 1; i = i + 2) {

				if ((allImageTouts.get(i).getLocation().getY() == allImageTouts.get(i + 1).getLocation().getY())) {

					System.out.println(allImageTouts.get(i).getAttribute("alt") + " is aligned");
				} else {
					System.out.println(elementName + " are not displayed in a same row");

				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Reporter.log("Error Occured in function collectionImageGridValidation " + e.getMessage());
			softAssert.fail(elementName + " are not displayed in a row as Expected" + e.getMessage());
			extentReportLogFAIL(e.getMessage());
		}
	}

	/**
	 * Author: Clinton
	 * 
	 * @param byXpath
	 * @param elementName
	 */

	public void checkForMultipleElements(By byXpath, String elementName) {

		System.out.println("Following Elements are available: ");

		try {
			List<WebElement> MultipleElements = driver.findElements(byXpath);

			for (int i = 0; i <= MultipleElements.size() - 1; i++) {

				String AllElements = MultipleElements.get(i).getText();

				System.out.println(AllElements);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Reporter.log("Error Occured in function checkForMultipleElements" + e.getMessage());
			softAssert.fail("All the elements are not displayed" + e.getMessage());
			extentReportLogFAIL(e.getMessage());
		}

	}

	/**
	 * Author: Manish Ojha scrolltoBottom - Scroll down to the bottom of the
	 * page.
	 */
	public void scrolltoBottom() {
		try {
			RemoteWebDriver remoteWebdriver = (RemoteWebDriver) driver;
			remoteWebdriver.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Unable to scroll down to the bottom " + e.getMessage());
			softAssert.fail("Unable to scroll down");
		}
	}

	/**
	 * Author: Manish Ojha Explicit wait - Waiting for elements to be visible in
	 * the application.
	 * 
	 * @param element
	 * @param timeoutinSec
	 */

	public List<WebElement> visibilityOfElements(By xpath, int timeoutinSec) {
		List<WebElement> elements = null;
		WebDriverWait wait = new WebDriverWait(driver, timeoutinSec);
		try {
			elements = driver.findElements(xpath);
			wait.until(ExpectedConditions.visibilityOfAllElements(elements));
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Not able to find all the elements " + e.getMessage());
			extentReportLogFAIL("Unable to find all elements "+e.getMessage());
		}
		return elements;
	}

	/**
	 * Author: Manish Ojha Switching between the tabs
	 * 
	 * @param Set
	 *            of Strings
	 * @param timeoutinSec
	 */
	public void switchingTabs(String homewindow, HashSet<String> noofWindows) {
		try {
			Iterator<String> itr = noofWindows.iterator();
			while (itr.hasNext()) {
				String windowhandle = itr.next().toString();
				if (!windowhandle.contains(homewindow)) {
					driver.switchTo().window(windowhandle);
					break;
				}
			}
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Unable to switch to window" + e.getMessage(), true);
			extentReportLogFAIL("Unable to switch to window" + e.getMessage());
		}

	}

	public void waitForTitle(String Title) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.titleContains(Title));

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("unable to find the title" + e.getMessage(), true);
			softAssert.fail("Unable to find the title" + e.getMessage());
			extentReportLogFAIL(e.getMessage());
		}
	}

	/**
	 * @author ojham
	 * @param xpath,
	 *            expDimension desc-Verifies the dimension of the Ad-unit.
	 *            expDimension should in the following format for validating
	 *            multiple dimensions e.g "200X300|300X100|..|...|.."
	 */

	public void verifyAdsDimension(By xpath, String expDimension) {

		try {

			waitForVisibilty(xpath, 40);

			WebElement ads = driver.findElement(xpath);
			int adWidth = ads.getSize().getWidth();
			int adHeight = ads.getSize().getHeight();

			String actualDimension = adWidth + "X" + adHeight;
			Reporter.log("Expected dimension =" + actualDimension, true);

			String expectedDim[] = expDimension.split("\\|");

			for (int i = 0; i <= expectedDim.length - 1; i++) {

				if (actualDimension.equals(expectedDim[i])) {

					Reporter.log("adunit Dimension  is displayed as Expected ", true);
					break;
				}

			}
		} catch (Exception Adunit) {
			Adunit.printStackTrace();
			Reporter.log("unable to find the adUnit" + Adunit.getMessage(), true);
			softAssert.fail("Unable to find the adunit" + Adunit.getMessage());
			extentReportLogFAIL(Adunit.getMessage());

		}

	}

	public boolean verifyingText(By Xpath, String expectedText) {

		boolean flag = false;
		try {
			waitForVisibilty(Xpath, 60);
			String actualText = driver.findElement(Xpath).getText();
			System.out.println("Actual: \t" + actualText);
			System.out.println("Expected: \t" + expectedText);
			if (actualText.contains(expectedText)) {
				reportLogger.info("Text matches. Expected["+expectedText+"] Actual ["+actualText+"]");
				return true;
			} else {
				System.out.println("Text doesnt matches. Expected["+expectedText+"] Actual ["+actualText+"]");
				extentReportLogFAIL("Text doesnt matches. Expected["+expectedText+"] Actual ["+actualText+"]");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Unable to find the text" + e.getMessage(), true);
			extentReportLogFAIL("Unable to find the text" + e.getMessage());
		}
		return flag;
	}

	public boolean verifyEqual(int actual, int expected, String passMessage) {

		boolean flag = false;
		try {
		
			if (actual == expected) {
				reportLogger.info(passMessage);
				return true;
			} else {
				// System.out.println("text doesnt matches");
				System.out.println("Actual: " + actual);
				System.out.println("Expected: " + expected);
				extentReportLogFAIL("Expected [" + expected +"] but actual is [" + actual + "]");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			extentReportLogFAIL(e.getMessage());
		}
		return flag;
	}

	public void verifyingUrl(String expectedUrl) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 1000);
			String actualUrl = "";

			Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
			String browserName = cap.getBrowserName().toLowerCase();
			if (browserName.equalsIgnoreCase("Firefox")) {
				actualUrl = driver.getCurrentUrl().substring(driver.getCurrentUrl().indexOf("/") + 2,
						driver.getCurrentUrl().length());
			} else if (browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("internet explorer")) {
				if (driver.getCurrentUrl().contains("@")) {
					actualUrl = driver.getCurrentUrl().substring(driver.getCurrentUrl().indexOf("@") + 1,
							driver.getCurrentUrl().length());
				} else {
					actualUrl = driver.getCurrentUrl().substring(driver.getCurrentUrl().indexOf("/") + 2,
							driver.getCurrentUrl().length());
				}
			}

			if (actualUrl.equals(expectedUrl)) {
				reportLogger.info("URL " + expectedUrl + " Verified");
			} else {
				extentReportLogFAIL("expected [" + expectedUrl + "] but found [" + actualUrl + "]");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Unable to find the  url" + e.getMessage(), true);
			extentReportLogFAIL("Unable to find the url" +e.getMessage());
		}
	}

	public boolean compareTextBysubString(By Xpath, String Expected, int beginIndex, int endIndex) {
		boolean flag = false;
		try {
			waitForVisibilty(Xpath, 40);
			String actualText = driver.findElement(Xpath).getText().substring(beginIndex, endIndex);
			String expectedText = Expected.substring(beginIndex, endIndex);
			if (actualText.equalsIgnoreCase(expectedText)) {

				flag = true;
			} else {

				flag = false;
				extentReportLogFAIL("Unable to find the text" + expectedText);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Unable to find the  text" + e.getMessage(), true);
			extentReportLogFAIL("Unable to find the text" + e.getMessage());
		}
		return flag;
	}

	public static String fetchCurrentDateAndTime() {
		DateFormat dateFormat = new SimpleDateFormat("YYMMddHHmmss");
		Date date = new Date();
		String dateAndTime = dateFormat.format(date);
		return dateAndTime;

	}

	public static String takeScreenShot(WebDriver driver, String methodName) {
		String path = "";
		try {
			// File scrFile = ((TakesScreenshot)
			// driver).getScreenshotAs(OutputType.FILE);
			Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("MMddyyyyhhmmss");
			// Save ScreenShot
			// FileUtils.copyFile(scrFile, new File("./test-output/Test_" +
			// methodName + "_SS_" + ft.format(dNow) + ".png"));
			
			
			
			/*
			Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
					.takeScreenshot(driver);
			ImageIO.write(screenshot.getImage(), "PNG",
					new File(Constants.SSPATH + "Test_" + methodName + "_SS_" + ft.format(dNow) + ".png"));

			path = System.getProperty("user.dir") + "/test-output/ErrorSS/Test_" + methodName + "_SS_" + ft.format(dNow)
					+ ".png";
			
			*/
			
			// System.out.println(path + " Saved Successfully!!");

		} catch (Exception e) {
			System.out.println("Unable to take Screenshot");

			// e.printStackTrace();
		}
		return path;
	}

	/**
	 * Used to Navigate to any screen...
	 * 
	 * @param navigationSequence
	 */
	public void navigateToScreenWithFrame(String navigationSequence, By byPageHeaderXpath, By byFrameXpath) {
		try {
			String[] arrNavigationElement = navigationSequence.split("\\|");
			for (Object navigationElement : arrNavigationElement) {
				clickOnElement(objectRepository.get(navigationElement), navigationElement.toString());
			}
			switchingFrame(byFrameXpath);
			waitForElement(byPageHeaderXpath, 60);
			switchingToDefaultFrame();
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Error Occured in function navigateToScreen " + e.getMessage());
			softAssert.fail("Error Occured in function navigateToScreen " + e.getMessage());
		}
	}

	public String getElementValue(By byXpath, String elementName) {
		String value = "";
		try {
			waitForElement(byXpath, 120);
			value = driver.findElement(byXpath).getText();
			reportLogger.info("Read " + elementName + " value: \""+value+"\"");
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Error Occured in function clickOnElement " + e.getMessage());
			extentReportLogFAIL(elementName + " is not Clickable");
		}
		return value;
	}

	public String getObjectRepoValue(Object repoString) {
		return repoString.toString().substring(repoString.toString().indexOf(":") + 2, repoString.toString().length());
	}

	public String getAlertText() {
		return driver.switchTo().alert().getText();
	}

	public void acceptAlert() {
		reportLogger.info("Alert Accepted");
		driver.switchTo().alert().accept();
	}

	public String getMethodName() {
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}

	public static void extentReportLogENDTEST() {
		softAssert.assertAll();
		reportLogger.log(Status.PASS,
				MarkupHelper.createLabel(
						"Test Case " + Thread.currentThread().getStackTrace()[2].getMethodName() + " PASSED",
						ExtentColor.GREEN));
		try {
			reportLogger.info("Snapshot below: "
					+ reportLogger.addScreenCaptureFromPath(ApplicationGenericUtils.takeScreenShot(driver, "")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to take Screenshot");
		}
	}

	public static void extentReportLogPASS() {
		reportLogger.log(Status.PASS,
				MarkupHelper.createLabel(
						"Test Case " + Thread.currentThread().getStackTrace()[2].getMethodName() + " PASSED",
						ExtentColor.GREEN));
		try {
			reportLogger.info("Snapshot below: "
					+ reportLogger.addScreenCaptureFromPath(ApplicationGenericUtils.takeScreenShot(driver, "")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to take Screenshot");
		}
	}

	/**
	 * Author: Sushant windowHandle -- Used to switch to new tab and validate
	 * it's title
	 * 
	 * @param By
	 *            ByXpath --for Any element on Parent window and String
	 *            text--New tab title
	 */

	public void windowHandle(By byParentXpath, By childxpath, String text) throws InterruptedException {

		waitForVisibilty(byParentXpath, 40);
		String Parent_Window = driver.getWindowHandle();
		String Child_Window;
		//
		// System.out.println("To get the parent window
		// title"+driver.getTitle());
		Set<String> s1 = driver.getWindowHandles();

		if (s1 != null) {

			Iterator<String> itr = s1.iterator();
			while (itr.hasNext()) {
				Child_Window = itr.next();

				if (!Parent_Window.equalsIgnoreCase(Child_Window)) {
					driver.switchTo().window(Child_Window);
					Thread.sleep(2000);
					String currentText = driver.findElement(childxpath).getText();
					if (currentText.contains(text)) {
						reportLogger.log(Status.PASS, MarkupHelper
								.createLabel("Expected " + text + " Title opened on New tab ", ExtentColor.GREEN));
					} else {
						reportLogger.log(Status.FAIL, MarkupHelper
								.createLabel("Expected Title " + text + " not opened on New tab :", ExtentColor.RED));
					}
				}
			}
		}
	}

	/**
	 * Author: Sushant getExpiryDate -- Used to get the expire date from the
	 * current date
	 * 
	 * @param String
	 *            -- Year or Month plans
	 * @Return String --Date as string in format MM/dd/YYYY
	 */

	public static String getExpiryDate(String OneYrMnth) {

		SimpleDateFormat sdf = new SimpleDateFormat("M/d/YYYY");
		Calendar cal = Calendar.getInstance();
		String reqDate;
		if (OneYrMnth == "Month") {
			cal.add(Calendar.DATE, 29);
			// System.out.println("15 days After: " + calendar.getTime());
			reqDate = sdf.format(cal.getTime());
		} else if (OneYrMnth == "Year") {
			cal.add(Calendar.YEAR, 1);
			// System.out.println("15 days After: " + calendar.getTime());
			reqDate = sdf.format(cal.getTime());

		} else {
			reqDate = "Invalid Date";
		}
		Reporter.log("The required date is :[" + reqDate + "] should be checked", true);
		return reqDate;
	}

	public static void extentReportLogPASS(String Message) {
		reportLogger.log(Status.PASS, MarkupHelper.createLabel(Message, ExtentColor.GREEN));
		try {
			reportLogger.info("Snapshot below: "
					+ reportLogger.addScreenCaptureFromPath(ApplicationGenericUtils.takeScreenShot(driver, "")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to take Screenshot");
		}
	}

	public static String currentDateinMMDDYY(){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH)+1; 
        String reqDate = month+"/"+day+"/"+year;
        return reqDate;
    }

	public static String extentReportLogFAIL(String ErrorMessage) {
		softAssert.fail(ErrorMessage);
		reportLogger.log(Status.FAIL,
				MarkupHelper.createLabel("Test case FAILED due to issues: " + ErrorMessage, ExtentColor.RED));
		testFailStatus = true;
		try {
			reportLogger.fail("Snapshot below: "
					+ reportLogger.addScreenCaptureFromPath(ApplicationGenericUtils.takeScreenShot(driver, "")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to take Screenshot");
		}
		return "";
	}

	public int calculatePets(By byParentXpath) {
		waitForVisibilty(byParentXpath, 30);
		List<WebElement> eleList = driver.findElements(byParentXpath);
		int totalPets = 0;
		for (WebElement ele : eleList) {
			totalPets = totalPets + Integer.parseInt(ele.getText().substring(0, ele.getText().indexOf(" ")));
		}

		return totalPets;
	}
	
	public String getElementValueJS(By byXpath, String elementName) {
		String value = "";
		try {
			waitForVisibilty(byXpath, 30);
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			WebElement element = driver.findElement(byXpath);
			value = (String) jse.executeScript("return arguments[0].innerText", element);
			reportLogger.info(elementName + " value: "+value+" read successfully!");
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Error Occured in function clickOnElement " + e.getMessage());
			softAssert.fail("Unable to read element: "+elementName);
			extentReportLogFAIL("Unable to read element: "+elementName);
		}
		return value;
	}

	public void clickonElementJS(By byXpath, String elementName) {
		try {
			waitForVisibilty(byXpath, 30);
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			WebElement element = driver.findElement(byXpath);
			jse.executeScript("return arguments[0].click();", element);
			reportLogger.info(elementName + " clicked successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Error Occured in function clickOnElement " + e.getMessage());
			softAssert.fail(elementName + " is not Clickable");
			extentReportLogFAIL(elementName + " is not Clickable");
		}
	}
	
	public static String getDriverPath_bak(String name,File file)
    {
		String path="";
        File[] list = file.listFiles();
        if(list!=null)
        for (File fil : list)
        {
            if (fil.isDirectory())
            {
            	getDriverPath(name,fil);
            }
            else if (name.equalsIgnoreCase(fil.getName()))
            {
            	path = fil.getParentFile().toString();
            }
        }
        return path;
    }
	
	public static String getDriverPath(String fileName,File root)
    {
		String path="";
	    try {
            boolean recursive = true;

            Collection files = FileUtils.listFiles(root, null, recursive);

            for (Iterator iterator = files.iterator(); iterator.hasNext();) {
                File file = (File) iterator.next();
                if (file.getName().contains(fileName)){
                    System.out.println("Path for "+fileName+" driver: \t"+file.getAbsolutePath());
                    path = file.getAbsolutePath();
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }
	
	public static void setDrivers(){
		System.setProperty("webdriver.gecko.driver", getDriverPath("geckodriver",new File(Constants.AUTO_DOWNLOAD_DRIVER_PATH)));
		System.setProperty("webdriver.chrome.driver", getDriverPath("chromedriver",new File(Constants.AUTO_DOWNLOAD_DRIVER_PATH)));
		System.setProperty("webdriver.ie.driver", getDriverPath("IEDriverServer",new File(Constants.AUTO_DOWNLOAD_DRIVER_PATH)));
	}
	
	public boolean verifyingText(String actualText, String expectedText) {

		boolean flag = false;
		try {
			System.out.println("Actual: \t" + actualText);
			System.out.println("Expected: \t" + expectedText);
			if (actualText.contains(expectedText)) {
				reportLogger.info("Text matches. Expected["+expectedText+"] Actual ["+actualText+"]");
				return true;
			} else {
				System.out.println("Text doesnt matches. Expected["+expectedText+"] Actual ["+actualText+"]");
				extentReportLogFAIL("Text doesnt matches. Expected["+expectedText+"] Actual ["+actualText+"]");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Unable to find the text" + e.getMessage(), true);
			extentReportLogFAIL("Unable to find the text" + e.getMessage());
		}
		return flag;
	}
	
}
