package pages.Subs3;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.Screen;
import org.testng.Assert;
//import org.testng.asserts.SoftAssert;
import utilities.SoftAssert;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import freemarker.core.Environment;

import utilities.ApplicationGenericUtils;
import utilities.MainFrameGenericUtils;

public class MainFrameValidations {

	// Declaration
	public WebDriver driver;
	public LinkedHashMap<String, By> objectRepository;
	ApplicationGenericUtils refApplicationGenericUtils;
	SoftAssert softAssert;
	public ExtentTest reportLogger;
	public  LinkedHashMap<Object, Object> tcParameters;
	MainFrameGenericUtils refMFutils = new MainFrameGenericUtils();
	HashMap<String, String> orderFulfillmentValues = new HashMap<String, String>();
	
	public MainFrameValidations(SoftAssert softAssert,LinkedHashMap<Object, Object> tcParameters,HashMap<String, String> orderFulfillment){
		this.softAssert = softAssert;
		this.tcParameters = tcParameters;
		this.orderFulfillmentValues = orderFulfillment;
	}
	
	public MainFrameValidations openMainFrameApplication() throws Exception{
		System.out.println("Open Mainframe");
		Runtime runtime = Runtime.getRuntime(); // getting Runtime object
		File mfInput = new File("src/test/java/resources/MAGNET_BZ.zmd");
		System.out.println("cmd /c "+mfInput.getAbsolutePath());
		Desktop.getDesktop().open(mfInput);
		return this;
	}

	public MainFrameValidations loginMainFrameApplication() throws Exception{
		Screen scr = new Screen();
		scr.wait("./src/test/java/resources/MFimg/OpenPageHeader.png", 20);
		scr.find("./src/test/java/resources/MFimg/OpenPageHeader.png");
		// scr.type("./src/test/java/resources/MFimg/OpenCommand.png","magnet" + Key.ENTER);

		String text = "magnet";
		refMFutils.MFrobot_sendText(text);
		refMFutils.MFrobot_Enter();

		scr.wait("./src/test/java/resources/MFimg/LoginPageuserID.png", 20);
		refMFutils.MFrobot_sendText("nc0029");
		refMFutils.MFrobot_Tab();
		refMFutils.MFrobot_sendText("jan@2018");
		refMFutils.MFrobot_Tab();
		refMFutils.MFrobot_sendText("0231");
		refMFutils.MFrobot_Enter();

		scr.wait("./src/test/java/resources/MFimg/LoginSuccess.png", 20);
		scr.find("./src/test/java/resources/MFimg/LoginSuccess.png");
		return this;
	}
	
	
	public MainFrameValidations validateMEVKM() throws Exception{
			// Thread.sleep(10000);
	
		refMFutils.MFrobot_sendText("mevmk");
		refMFutils.MFrobot_Enter();

		refMFutils.MFrobot_sendText(orderFulfillmentValues.get("MG").toString());
		refMFutils.MFrobot_Tab();
		refMFutils.MFrobot_sendText(orderFulfillmentValues.get("EK").toString());
		refMFutils.MFrobot_Tab();
		refMFutils.MFrobot_Enter();
		Thread.sleep(3000);
		String mevmkPageText = refMFutils.MFrobotOCR_getScreenText("mevmk");

		System.out.println("MEVMK Page: \t"+mevmkPageText );
		
	
		return this;
	}
	
	public MainFrameValidations validateMSORK() throws Exception{
		// Thread.sleep(10000);


	refMFutils.MFrobot_ShiftTab();
	refMFutils.MFrobot_ShiftTab();
	refMFutils.MFrobot_ShiftTab();
	
	refMFutils.MFrobot_sendText("msork");
	refMFutils.MFrobot_Enter();
	Thread.sleep(3000);
	
	refMFutils.MFrobot_sendText("inq");
	refMFutils.MFrobot_Tab();
	refMFutils.MFrobot_Tab();
	refMFutils.MFrobot_sendText(orderFulfillmentValues.get("KeyLine").toString());
	refMFutils.MFrobot_Enter();
	Thread.sleep(3000);
	String msorkPageText = refMFutils.MFrobotOCR_getScreenText("msork");
	System.out.println("MSORK Page: \t"+msorkPageText );
	return this;
}

}
