package TestSuite;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import Locators.Base;
import Locators.DataProviders;
import Locators.Search_NewBikes;
import Locators.Search_NewCars;
import Locators.UsedSearch.Search_Accessory;
import Locators.UsedSearch.Search_UsedBikes;
import Locators.UsedSearch.Search_UsedCars;

public class TestCases_Search extends Base
{	  
	@Test(priority = 1, dataProvider= "UsedCars", dataProviderClass= DataProviders.class, retryAnalyzer= Resources.RetryAnalyzer.class)
 	public void UsedCarsSearch(JSONObject Object) throws Exception
 	{
		if(Testing.equalsIgnoreCase("Smoke"))
			test= extent.createTest("Used Car Search");	
		else if (Testing.equalsIgnoreCase("System"))
			test= extent.createTest("Used Car Search (Test Number: "+ (String) Object.get("TestNumber")+")");
		
		Search_UsedCars C = new Search_UsedCars(driver, test, wait, Object); 		
		C.Login();
		C.NavigateToCarSearch();
		C.VerifyDifferentSectionsOnCarSearch();
		C.NavigateToLandingScreen();	
		//C.VerifyTotalNumberOfAdsOnListing();
		C.SelectAdvancedSearch();
		C.ApplyLocationFilter();		
		C.SelectMakeModel();
		C.SelectMinMaxPrice();		
		C.SelectMinMaxModelYear();
		C.SelectMinMaxMileage();	
		C.SelectRegisteredCity();	
		C.SelectTransmission();	
		C.SelectBodyColor();
		C.SelectEngineTypeInformation();		
		C.SelectModelCategory();	
		C.SelectSellerType();
		C.SelectAdProperties();		
		C.ApplyFilter();
		C.ChecklistinghasAds();
		C.ApplySort();
		C.SaveAd();
		//C.CreateAlert((String) d.get("AlertTime"));
		C.SelectAd();
		//C.VerifySmsChatCallButtons();
		C.CheckPicturesOnAdDetail();
		C.CheckSellerCommentsSection();		
		C.CheckFeatureSection();	
		C.VisitSellerDetailPage();	
		C.ReportAd();	
		C.CheckPakweelsServicesSection();
		C.VerifyWantToSellCarSection();	
		C.CheckFeaturePartnerSection();	
		C.CheckSimilarAdsCarousel();	
		System.out.println("Used Car search is completed");	
 	}

	@Test(priority = 2, dataProvider= "UsedBike", dataProviderClass= DataProviders.class, retryAnalyzer= Resources.RetryAnalyzer.class)
 	public void UsedBikesSearch(JSONObject Object) throws Exception
 	{	
		if(Testing.equalsIgnoreCase("Smoke"))
			test= extent.createTest("Used Bike Search");	
		else if (Testing.equalsIgnoreCase("System"))
			test= extent.createTest("Used Bike Search (Test Number: "+(String) Object.get("TestNumber")+")");
		
		Search_UsedBikes Bike = new Search_UsedBikes(driver, test, wait, Object);		
		Bike.Login();     
		Bike.NavigateToBikeSearch();	
		//Bike.VerifyDifferentSectionsOnBikeSearch();
		Bike.NavigateToLandingScreen();		
		Bike.SelectAdvancedSearch();
		Bike.SelectBikeInformation();
		Bike.EnterKeywords();	
		Bike.ApplyLocationFilter();	
		Bike.SelectMinMaxPrice();	
		Bike.SelectMinMaxModelYear();
		Bike.SelectMinMaxMileage();
		Bike.SelectEngineType();			
		//Bike.SelectBodyType();	
		Bike.SelectSellerType();	
		Bike.SelectAdProperties();	
		Bike.ApplyFilter();
		Bike.ApplySort();	
		Bike.SaveAd();	
		//Bike.CreateAlert((String) d.get("AlertTime"));
		Bike.SelectAd();
	    //Bike.VerifySmsChatCallButtons();		
		//Bike.CheckModelName();				
		Bike.CheckSellerCommentsSection();	
		Bike.CheckFeatureSection();
		Bike.VisitSellerDetailPage();	
		Bike.ReportAd();
		Bike.VerifyWantToSellBikeSection();
		Bike.CheckSimilarAdsCarousel();		
		System.out.println("Used Bike search is completed");
 	}
	
	@Test(priority = 3, dataProvider= "Accessory", dataProviderClass= DataProviders.class, retryAnalyzer= Resources.RetryAnalyzer.class)
 	public void AccessoriesSearch(JSONObject Object) throws Exception
 	{
		if(Testing.equalsIgnoreCase("Smoke"))
			test= extent.createTest("Accessories Search");
		else if (Testing.equalsIgnoreCase("System"))
			test= extent.createTest("Accessories Search (Test Number: "+(String) Object.get("TestNumber")+")");
		
		Search_Accessory Accessory = new Search_Accessory(driver, test, wait, Object); 		
		Accessory.Login();
		Accessory.NavigateToAccessorySearch();    
		//Accessory.VerifyDifferentSectionsDisplayed();
		Accessory.NavigateToLandingScreen();	
		Accessory.SelectAdvancedSearch();
		Accessory.Category();
		Accessory.Brand();
		//Accessory.SavedVehicles();
		Accessory.SelectShopNowOption();	
		Accessory.SelectAutoMinMaxPrice();
		Accessory.Applyfilters();
		Accessory.ApplyAutoSort();
		Accessory.SaveAutoAd();	
		Accessory.SelectAutoAd();
		Accessory.CheckSellerCommentsSection();	
		Accessory.VisitSellerDetailPage();
		Accessory.ReportAd();
		Accessory.VerifyWantToSellAutoPartSection();
		Accessory.CheckSimilarAdsCarousel();	
		System.out.println("Accessories search is completed");
 	}
	
	@Test(priority = 4, dataProvider= "NewCar", dataProviderClass= DataProviders.class, retryAnalyzer= Resources.RetryAnalyzer.class)
 	public void NewCarsSearch(JSONObject Object) throws Exception
 	{  
		if(Testing.equalsIgnoreCase("Smoke"))
			test= extent.createTest("New Car Search");
		else if (Testing.equalsIgnoreCase("System"))
			test= extent.createTest("New Car Search (Test Number: "+(String) Object.get("TestNumber")+")");

		Search_NewCars Car = new Search_NewCars(driver, test, wait, Object); 		
		Car.Login();
		Car.NavigateToNewCarSearch();
		//Car.VerifyDifferentSectionsOnNewCarPage();
		Car.SelectCarMake();
		Car.VerifyDifferentSectionsOnModelListingPage();
		Car.SelectCarModel();
		//Car.VerifyDifferentSectionsOnModelDetailedPage();
		//Car.ClickOnRoadPrice();
		//Car.FillForm();
		/*Car.SelectBookThisCarNow();
		Car.EnterContactInformation();
		Car.SelectPayementMethod();
		Car.SelectBookThisCarNow();		*/
		System.out.println("New Car search is completed");
 	}
	
	@Test(priority = 5, dataProvider= "NewBike", dataProviderClass= DataProviders.class, retryAnalyzer= Resources.RetryAnalyzer.class)
 	public void NewBikesSearch(JSONObject Object) throws Exception
 	{	
		if(Testing.equalsIgnoreCase("Smoke"))
			test= extent.createTest("New Bike Search");
		else if (Testing.equalsIgnoreCase("System"))
			test= extent.createTest("New Bike Search (Test Number: "+(String) Object.get("TestNumber")+")");	
	
		Search_NewBikes Bike = new Search_NewBikes(driver, test, wait, Object);  		
		Bike.Login();
		Bike.NavigateToNewBikeSearch();
		Bike.SelectBikeMake();
		Bike.VerifyBikeModelListingPage();
		Bike.SelectBikeModel();
		Bike.VerifyBikeModelDetailedPage();
		System.out.println("New Bike search is completed");	
 	}

}
