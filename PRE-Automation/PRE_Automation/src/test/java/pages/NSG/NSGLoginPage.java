package pages.NSG;

import java.awt.Toolkit;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NSGLoginPage {
	public WebDriver driver;
	public LinkedHashMap<String, By> objectRepository;
	public NSGLoginPage(WebDriver driver,LinkedHashMap<String, By> objectRepository){
		this.driver=driver;
		this.objectRepository=objectRepository;
	}
	public  WebDriver logintoApplication(LinkedHashMap<Object, Object> LoginDetails){
		try {
			driver.get(LoginDetails.get("url").toString());
			driver.manage().window().maximize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;
	}

}
