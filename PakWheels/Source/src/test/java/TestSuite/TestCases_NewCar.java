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

public class TestCases_NewCar extends Base
{	  

	
	@Test(priority = 1, dataProvider= "NewCar", dataProviderClass= DataProviders.class, retryAnalyzer= Resources.RetryAnalyzer.class)
 	public void NewCarLandingPage(JSONObject Object) throws Exception
 	{  
		if(Testing.equalsIgnoreCase("Smoke"))
			test= extent.createTest("New Car Search");
		else if (Testing.equalsIgnoreCase("System"))
			test= extent.createTest("New Car Search (Test Number: "+(String) Object.get("TestNumber")+")");

		Search_NewCars Car = new Search_NewCars(driver, test, wait, Object); 		
		Car.Login();
		Car.NavigateToNewCarSearch();
		Car.BrowseNewCarByBrand();
		Car.PopularNewCars();
		Car.NewlyLaunchedCars();
		Car.UpcomingCars();
		Car.CarComparison();
		Car.Reviews();
		Car.LatestVideos();
		Car.ToolsSection();
		//Car.VerifyDifferentSectionsOnNewCarPage();
		
		System.out.println("New Car Landing Page is completed");
 	}
	/*@Test(priority = 2, dataProvider= "NewCar", dataProviderClass= DataProviders.class, retryAnalyzer= Resources.RetryAnalyzer.class)
 	public void NewCarModelPage(JSONObject Object) throws Exception
 	{  
		if(Testing.equalsIgnoreCase("Smoke"))
			test= extent.createTest("New Car Search");
		else if (Testing.equalsIgnoreCase("System"))
			test= extent.createTest("New Car Search (Test Number: "+(String) Object.get("TestNumber")+")");

		Search_NewCars Model = new Search_NewCars(driver, test, wait, Object); 		
		Model.Login();
	
		//Car.VerifyDifferentSectionsOnNewCarPage();
		
		System.out.println("New Car Landing Page is completed");
 	}*/
	


}
