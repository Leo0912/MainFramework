package constants;

public class Constants {
	public static String RESOURCES_PATH=System.getProperty("user.dir") +"/src/test/java/resources/";
	public static String APP_CONFIG_SHEET_NAME="Application_Config";
	public static String DRIVER_PATH=Constants.RESOURCES_PATH+"/Drivers/";
	//NSG Files Path
	public static String NSG_TESTCASE_SHEET_NAME="NSG_Validation";
	public static String NSG_BROWSERSTACK_SHEETNAME="BrowserStack_Config";
	public static String TEST_DATA_FILE_PATH=RESOURCES_PATH +"TestData.xlsx";
	public static String NSG_BROWSER_STACK_CONFIG_FILE_PATH=RESOURCES_PATH +"BrowserStackConfig.json";
	public static String NSG_ENVIRONMENT_FILE_PATH=RESOURCES_PATH +"NSG_EnvironmentConfig.json";
	public static String NSG_OR_FILE_PATH=RESOURCES_PATH +"NSG_ObjectRepository.json";
	public static String NSG_TEST_PATH="test.NSG.NewsGroupSmokeTesting";
	//TOS Validations
	public static String TOS_TESTCASE_SHEET_NAME="TOS_Validation";
	public static String TOS_ENVIRONMENT_FILE_PATH=RESOURCES_PATH +"TOS_EnvironmentConfig.json";
	public static String TOS_OR_FILE_PATH=RESOURCES_PATH +"TOS_ObjectRepository.json";
	public static String TOS_TEST_PATH="test.TOS.TOSRegressionSuite";
	//PetHero Files Path
	public static String PETHERO_TESTCASE_SHEET_NAME="PetHero_Validation";
	public static String PETHERO_BROWSERSTACK_SHEETNAME="BrowserStack_Config";
	public static String PETHERO_DATA_FILE_PATH=RESOURCES_PATH +"TestData.xlsx";
	public static String PETHERO_BROWSER_STACK_CONFIG_FILE_PATH=RESOURCES_PATH +"BrowserStackConfig.json";
	public static String PETHERO_ENVIRONMENT_FILE_PATH=RESOURCES_PATH +"PetHero_EnvironmentConfig.json";
	public static String PETHERO_OR_FILE_PATH=RESOURCES_PATH +"PetHero_ObjectRepository.json";
	public static String PETHERO_TEST_PATH="test.PetHero.PetHeroRegressionSuite";

	//Subs3 Files Path
	public static String SUBS3_TESTCASE_SHEET_NAME="Subs3_Validation";
	public static String SUBS3_BROWSERSTACK_SHEETNAME="BrowserStack_Config";
	public static String SUBS3_DATA_FILE_PATH=RESOURCES_PATH +"TestData.xlsx";
	public static String SUBS3_MFDATA_FILE_PATH=RESOURCES_PATH +"Subs3_Validation.xlsx";
	public static String SUBS3_BROWSER_STACK_CONFIG_FILE_PATH=RESOURCES_PATH +"BrowserStackConfig.json";
	public static String SUBS3_ENVIRONMENT_FILE_PATH=RESOURCES_PATH +"Subs3Microsite_EnvironmentConfig.json";
	public static String SUBS3_OR_FILE_PATH=RESOURCES_PATH +"Subs3Microsite_ObjectRepository.json";
	public static String SUBS3_TEST_PATH="test.Subs3.Subs3RegressionSuite";

	//Driver 
	public static String FF_DRIVER_PATH=Constants.RESOURCES_PATH+"Drivers/geckodriver.exe";
	public static String IE_DRIVER_PATH=Constants.RESOURCES_PATH+"Drivers/IEDriverServer.exe";
	public static String CHROME_DRIVER_PATH=Constants.RESOURCES_PATH+"Drivers/chromedriver.exe";
	public static String AUTO_DOWNLOAD_DRIVER_PATH=Constants.RESOURCES_PATH+"Drivers/resource/";
	//PetHeroMarketingSite
	public static String[] TabsNm = {"All About PetHero", "Veterinary Discounts", "Seasonal Box","Subscription Details","Billing & Shipping"};
	public static String PETHEROLINKS=RESOURCES_PATH+"PetHero_Links.xlsx";
    public static String PETHEROLINKSHEETNAME="MarketingPageLinks";
    public static String PETHEROLINKANSWERLINKS="ANSWERLINKS";
    public static String PETHEROLINKQUESTIONTABS="TABS_FAQS";
    
    public static String SSPATH="./test-output/ErrorSS/";
}
