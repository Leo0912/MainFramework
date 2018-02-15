package pages.TOS;

import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.testng.asserts.SoftAssert;
import utilities.SoftAssert;
import utilities.ApplicationGenericUtils;

public class NewConsigneePage {
	//Declaration
	public WebDriver driver;
	public LinkedHashMap<String, By> objectRepository;
	ApplicationGenericUtils refApplicationGenericUtils;
	SoftAssert softAssert; 
	public static LinkedHashMap<Object, Object> tcParameters;
	public NewConsigneePage(WebDriver driver,LinkedHashMap<String, By> objectRepository,SoftAssert softAssert,LinkedHashMap<Object, Object> tcParameters){
		this.driver=driver;
		this.objectRepository=objectRepository;
		refApplicationGenericUtils=new ApplicationGenericUtils(driver);
		this.softAssert=softAssert;
		this.tcParameters=tcParameters;
	}
	public void createConsignee(String applicationName) {
		refApplicationGenericUtils.navigateToScreenWithFrame("Navigation.DistributionMenu|Navigation.NewConsigneeMenu",objectRepository.get("CreateConsignee.Save"),objectRepository.get("AllTOSPages.Frame"));
		Object type=tcParameters.get("Type");
		 switch (type.toString()) {
         case "Airline COMAT":
         case "Internation Subs":        	 
        	 refApplicationGenericUtils.switchingFrame(objectRepository.get("AllTOSPages.Frame"));
        	 
        	 refApplicationGenericUtils.selectValueFromList(objectRepository.get("CreateConsignee.ConsigneeTypeList"),"ConsigneeTypeList",type);
        	 Object consigneeName=tcParameters.get("ConsigneeName")+ refApplicationGenericUtils.fetchCurrentDateAndTime();
        	 refApplicationGenericUtils.setElementValue(objectRepository.get("CreateConsignee.ConsigneeNametxt"),"ConsigneeNametxt",consigneeName);
        	 refApplicationGenericUtils.setElementValue(objectRepository.get("CreateConsignee.ConsigneeNumtxt"),"ConsigneeNumtxt",tcParameters.get("ConsigneeNo"));
        	 
        	 refApplicationGenericUtils.switchingToDefaultFrame();
          break;
         case "NewsStand":
        	 //Code
         break;
     }
	}

	public void searchConsignee(String applicationName) {
		// TODO Auto-generated method stub
		
	}
	
	

}
