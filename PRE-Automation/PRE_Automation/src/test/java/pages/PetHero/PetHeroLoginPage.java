package pages.PetHero;

import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import utilities.ApplicationGenericUtils;
import constants.Constants;

public class PetHeroLoginPage {
	public WebDriver driver;
	public LinkedHashMap<String, By> objectRepository;
	public static LinkedHashMap<Object, LinkedHashMap<Object, Object>> appName;
	ApplicationGenericUtils refApplicationGenericUtils;

	public PetHeroLoginPage(WebDriver driver, LinkedHashMap<String, By> objectRepository,
			LinkedHashMap<Object, LinkedHashMap<Object, Object>> appName) {
		this.driver = driver;
		this.objectRepository = objectRepository;
		this.appName = appName;
	}

	public WebDriver logintoApplication(LinkedHashMap<Object, Object> LoginDetails, String Module) {
		try {
			String url = "";

			if (Module.equalsIgnoreCase("Subscription") || Module.equalsIgnoreCase("MarketingPage")) {
				url = LoginDetails.get("url").toString();
			} else if (Module.equalsIgnoreCase("MemberPortal")) {
				url = LoginDetails.get("MemberPortalURL").toString();
			}
			String username = LoginDetails.get("username").toString();
			String pwd = LoginDetails.get("pwd").toString();
			// String browser = "chrome";
			if (appName.containsKey("InternetExplorer")) {
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				// capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,
				// true);
				capabilities.setCapability("ignoreZoomSetting", true);
				capabilities.setCapability("ignoreProtectedModeSettings", true);
				capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL,
						"https://" + username + ":" + pwd + "@" + url);
				InternetExplorerOptions options = new InternetExplorerOptions();
				options.merge(capabilities);
				driver = new InternetExplorerDriver(options);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			} else if (appName.containsKey("Firefox")) {
				driver = new FirefoxDriver();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.get("https://" + username + ":" + pwd + "@" + url);
			} else if (appName.containsKey("Chrome")) {
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.get("https://" + username + ":" + pwd + "@" + url);
			}
			if (Module.equalsIgnoreCase("MarketingPage")) {
				refApplicationGenericUtils = new ApplicationGenericUtils(driver);
				refApplicationGenericUtils.clickOnElement(objectRepository.get("MarketingSitePage.Navigation.Logo"),
						"Logo");
				refApplicationGenericUtils.visibilityOfElements(objectRepository.get("MarketingSitePage.HeroSection"),
						5);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;
	}

}
