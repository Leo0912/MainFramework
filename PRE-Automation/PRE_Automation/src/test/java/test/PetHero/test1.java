package test.PetHero;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class test1 {
	
	public void test6() throws Exception {
		//throw new SkipException("");
		// TODO Auto-generated method stub
		//launchProcess("test.NewsGroupSmokeTesting", "globalHeaderValidation");
	}
	@Test
	public void testClinton() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\balur\\git\\PRE-Automation_10_30\\PRE_Automation\\src\\test\\java\\resources\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("https://nycitappstgclav1.timeinc.com:8680/index.jsp");
		System.out.println();
	}
	public void launchProcess(String className, String methodName) throws Exception {

	    Class<?> processClass = Class.forName(className); // convert string classname to class
	    Object process = processClass.newInstance(); // invoke empty constructor

	    Method aMethod = process.getClass().getMethod(methodName);
	    Object res = aMethod.invoke(process); 
	}

}
