package TestSuite;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import Locators.Base;
import Locators.DataProviders;
import Locators.AdPosting.Ad_AccessoryPage;
import Locators.AdPosting.Ad_BikePage;
import Locators.AdPosting.Ad_CarPage;

public class TestCases_AdPost extends Base
{		
	@Test(priority= 1,  dataProvider= "CarAd", dataProviderClass= DataProviders.class, retryAnalyzer= Resources.RetryAnalyzer.class)
	public void CarsAdPost(JSONObject Object) throws Exception
	{
		if(Testing.equalsIgnoreCase("Smoke"))
			test= extent.createTest("Car Ad Post");		
		else if (Testing.equalsIgnoreCase("System"))
			test= extent.createTest("Car Ad Post (Test Number: "+(String) Object.get("TestNumber")+")");
		
		Ad_CarPage Car = new Ad_CarPage(driver, test, wait, Object); 		
		Car.Login();
		Car.NavigateToCarAdPost();
		Car.AddPhotos();	
		Car.SelectLocation();               
		Car.SelectCarYearMakeModelVersion();
		Car.SelectRegisteredCity();
		Car.SelectCarColor();
		Car.EnterMileageAndPrice();      		
		Car.EnterSuggestions();	
		Car.SelectAdditionalInformation();		
		Car.EnterAdContactInformation();
		Car.AllowWhatsAppContact();
		Car.SelectPostYourAdButton();
		Car.EnterVerificationCode();
		Car.checkAdStatus();
		Car.ActivateYourAd();
		Car.RemoveAd();
		Car.ReactivateAd();
		Car.FeatureYourAd();
		Car.BoostYourAd();
		//Car.EditAd();
		Car.AttachAuctionSheet();
		Car.InspectionRequest();
		Car.SIFMrequest();
	
		System.out.println("Car Ad posting is completed");
	}
	
	@Test(priority= 2, dataProvider= "BikeAd", dataProviderClass= DataProviders.class, retryAnalyzer= Resources.RetryAnalyzer.class)
	public void BikesAdPost(JSONObject Object) throws Exception
	{	
		if(Testing.equalsIgnoreCase("Smoke"))
			test= extent.createTest("Bike Ad Post");		
		else if (Testing.equalsIgnoreCase("System"))
			test= extent.createTest("Bike Ad Post (Test Number: "+(String) Object.get("TestNumber")+")");
		
		Ad_BikePage Bike = new Ad_BikePage(driver, test, wait, Object); 
		Bike.Login();
		Bike.NavigateToBikeAdPost();
		Bike.AddPhotos();
		Bike.SelectLocation();
		Bike.SelectBikeMakeModelYear();
		Bike.SelectRegisteredCity();
		Bike.EnterMileageAndPrice();   
		Bike.SelectEngineType();
		Bike.EnterDescription();
		Bike.SelectFeatures();
		Bike.EnterAdContactInformation();
		Bike.AllowWhatsAppContact();
		Bike.SelectPostYourAdButton();	
		Bike.EnterVerificationCode();
		Bike.checkAdStatus();
		Bike.ActivateYourAd();
		Bike.RemoveAd();
		Bike.ReactivateAd();
		Bike.FeatureYourAd();
		Bike.BoostYourAd();
		System.out.println("Bike Ad posting is completed");
	}
	
	@Test(priority= 3, dataProvider= "AccessoryAd", dataProviderClass= DataProviders.class, retryAnalyzer= Resources.RetryAnalyzer.class)
	public void AccessoriessAdPost(JSONObject Object) throws Exception
	{	
		if(Testing.equalsIgnoreCase("Smoke"))
			test= extent.createTest("Accessories Ad Post");		
		else if (Testing.equalsIgnoreCase("System"))
			test= extent.createTest("Accessories Ad Post (Test Number: "+(String) Object.get("TestNumber")+")");		
		
		Ad_AccessoryPage Accessory = new Ad_AccessoryPage(driver, test, wait, Object); 	
		Accessory.Login();
		Accessory.NavigateToAccessoryAdPost();
		Accessory.AddPhotos();
		Accessory.SelectLocation();
		Accessory.SelectCategory();		
		Accessory.EnterTitle();
		Accessory.EnterPrice();
		Accessory.EnterDescription();
		Accessory.EnterAdContactInformation();
		Accessory.AllowWhatsAppContact();
		Accessory.SelectPostYourAdButton();		
		Accessory.EnterVerificationCode();
		Accessory.checkAdStatus();
		Accessory.ActivateYourAd();
		Accessory.RemoveAd();
		Accessory.ReactivateAd();
		System.out.println("Accessories Ad posting is completed");
	}
}