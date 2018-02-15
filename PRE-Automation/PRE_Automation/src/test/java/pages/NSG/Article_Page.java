package pages.NSG;

import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
//import org.testng.asserts.SoftAssert;
import utilities.SoftAssert;
import utilities.ApplicationGenericUtils;

public class Article_Page {
	
	//Declaration
		public WebDriver driver;
		public LinkedHashMap<String, By> objectRepository;
		ApplicationGenericUtils refApplicationGenericUtils;
		SoftAssert softAssert; 
		public Article_Page(WebDriver driver,LinkedHashMap<String, By> objectRepository,SoftAssert softAssert){
			this.driver=driver;
			this.objectRepository=objectRepository;
			refApplicationGenericUtils=new ApplicationGenericUtils(driver);
			this.softAssert=softAssert;
		}
		
		public void topRecircValidation(String appName){
			
			refApplicationGenericUtils.clickOnElement(objectRepository.get("GlobalElements.FeaturedArticle"),"FeaturedArticle");
			refApplicationGenericUtils.checkForElement(objectRepository.get("ArticlePageElements.TopRecirc"), "TopRecirc");
			refApplicationGenericUtils.checkSizeOfElements(objectRepository.get("ArticlePageElements.TopRecircArticleCount"), 4, "Top Recirc Content");
			
		}
		
		public void headerContentsValidation(String appName){
			
			refApplicationGenericUtils.checkForElement(objectRepository.get("ArticlePageElements.Breadcrumbs"), "Breadcrumbs");
			refApplicationGenericUtils.checkForElement(objectRepository.get("ArticlePageElements.Headline"), "Headline");
			refApplicationGenericUtils.checkForShareTools(objectRepository.get("ArticlePageElements.ShareTools"), "ShareTools");
			refApplicationGenericUtils.checkForFeaturedContents(objectRepository.get("ArticlePageElements.FeaturedVideo"), "Featured Video", objectRepository.get("ArticlePageElements.FeaturedImage"), "Featured Image");
			
		}
		
		public void rightRailContentsValidation(String appName){
			
			//refApplicationGenericUtils.checkForElement(objectRepository.get("ArticlePageElements.RightRailYouMayLike"), "YouMayLike");
			//refApplicationGenericUtils.checkSizeOfElements(objectRepository.get("ArticlePageElements.YouMayLikeArticleCount"), 4);
			refApplicationGenericUtils.checkForElement(objectRepository.get("ArticlePageElements.RightRailStoriesFrom"), "Stories From");
			refApplicationGenericUtils.checkSizeOfElements(objectRepository.get("ArticlePageElements.StoriesFromArticleCount"), 5, "Stories From Content");
			refApplicationGenericUtils.checkForElement(objectRepository.get("ArticlePageElements.RightRailReadMore"), "Read More");
			refApplicationGenericUtils.checkSizeOfElements(objectRepository.get("ArticlePageElements.ReadMoreArticleCount"), 5, "Read More");
			refApplicationGenericUtils.checkForElement(objectRepository.get("ArticlePageElements.RightRailNewsletter"), "News letter");
			
			//Test Case For Right Rail Ad Unit needs to be incorporated here
		}
		
		public void postArticleContentsValidation(String appName){
			
			refApplicationGenericUtils.checkForElement(objectRepository.get("ArticlePageElements.SponsoredStories"), "Sponsored Stories");
			refApplicationGenericUtils.checkSizeOfElements(objectRepository.get("ArticlePageElements.SponsoredStoriesArticleCount"), 6, "Sponsored Stories");
			refApplicationGenericUtils.checkForElement(objectRepository.get("ArticlePageElements.SponsoredFinancialContent"), "Sponsored Financial Content");
			refApplicationGenericUtils.checkSizeOfElements(objectRepository.get("ArticlePageElements.SponsoredFinancialContentArticleCount"), 6, "Sponsored Financial Content");
			refApplicationGenericUtils.checkForElement(objectRepository.get("ArticlePageElements.YouMayLike"), "You May Like");
			refApplicationGenericUtils.checkSizeOfElements(objectRepository.get("ArticlePageElements.YouMayLikeArticleCount"), 5, "You May Like");	
		}
		
		public void aboveFooter(String appName) throws InterruptedException{
			
			refApplicationGenericUtils.checkForElement(objectRepository.get("ArticlePageElements.Subscribe&Save"), "Subscribe & Save");
			refApplicationGenericUtils.checkForElement(objectRepository.get("ArticlePageElements.SubscribeMediaImage"), "Subscribe Media Image");
			refApplicationGenericUtils.checkForElement(objectRepository.get("ArticlePageElements.SubscribeMediaBody"), "Subscribe Media Body");
			refApplicationGenericUtils.checkForElement(objectRepository.get("ArticlePageElements.SubscribeNowButton"), "Subscribe Now Button");
			refApplicationGenericUtils.checkForButtonFunctionality(objectRepository.get("ArticlePageElements.SubscribeNowButton"), "Fortune Magazine Subscription");
			refApplicationGenericUtils.checkForElement(objectRepository.get("ArticlePageElements.SignUpForNewslettersHeader"), "SignUp For Newsletters");
			refApplicationGenericUtils.checkForElement(objectRepository.get("ArticlePageElements.SignUpForNewslettersContent"), "SignUp For Newsletters Content");
			refApplicationGenericUtils.checkForElement(objectRepository.get("ArticlePageElements.SignUpForNewslettersSubscribeButton"), "SignUp For Newsletters Subscribe Button");
			refApplicationGenericUtils.checkForButtonFunctionality(objectRepository.get("ArticlePageElements.SignUpForNewslettersSubscribeButton"), "Fortune");
			refApplicationGenericUtils.checkForElement(objectRepository.get("ArticlePageElements.FortuneLinkAboveFooter"), "Fortune Link Above Footer");
			refApplicationGenericUtils.checkForButtonFunctionality(objectRepository.get("ArticlePageElements.FortuneLinkAboveFooter"), "Fortune - Fortune 500 Daily & Breaking Business News");
			refApplicationGenericUtils.clickOnElement(objectRepository.get("GlobalElements.FeaturedArticle"),"FeaturedArticle");
			refApplicationGenericUtils.checkForShareTools(objectRepository.get("ArticlePageElements.ShareToolsAboveFooter"), "Share Tools Above Footer");
			
		}
}

