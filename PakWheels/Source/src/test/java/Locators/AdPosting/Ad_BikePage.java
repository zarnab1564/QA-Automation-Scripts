package Locators.AdPosting;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Ad_BikePage extends AdPosting
{
	AppiumDriver<MobileElement> driver= null;
	ExtentTest test= null;	
	WebDriverWait wait= null;
	JSONObject Object = null;
	public Ad_BikePage(AppiumDriver<MobileElement> driver, ExtentTest test, WebDriverWait wait, JSONObject Object) 
	{
		super(driver, test, wait, Object);
		this.driver= driver;
		this.test= test;
		this.wait= wait;
		this.Object= Object;
	}
	
	public void NavigateToBikeAdPost() throws Exception
	{		
		ClickSellButton();
		DataPicker("Verify that user can Select Ad Type <br>Test Data: <b>Bike</b>", "Bike", 0);                             
	}
	
	public void SelectBikeMakeModelYear() throws Exception
	{
		JSONObject BikeInformation = (JSONObject) Object.get("BikeInformation");
		String Year= (String) BikeInformation.get("Year");
		String MakeModel= (String) BikeInformation.get("MakeModel");
		
		DataPicker("Verify that user can select Bike Information button to open Model listing", "Bike Information", 0);        	
		DataPicker("Verify that user can select Bike Year <br>Test Data: <b>"+Year+"</b>", Year, 0);                	
		SendText("Verify that user can enter text to refine Make Model search","Type to refine search", MakeModel);
		DataPicker("Verify that user can select Bike Make and Model <br>Test Data: <b>"+MakeModel+"</b>", MakeModel, 0);               
	}
	
	public void SelectEngineType() throws Exception
	{	
		String EngineType= (String) Object.get("EngineType");
		DataPicker("Verify that user can select Engine Type button to open engine listing", "Engine Type", 0);           
		DataPicker("Verify that user can select Engine Type <br>Test Data: <b>"+EngineType+"</b>", EngineType, 0);                            
	}
	
	public void EnterDescription() throws InterruptedException
	{
		String Description= (String) Object.get("Description");
		Scroll("Features");		
		SendText("Verify that user can enter Description <br>Test Data: <b>"+Description+"</b>", "For Example: Alloy Rims, First Owner, etc.", Description);			
	}
	
	public void SelectFeatures() throws Exception
	{
		String NumberOfFeatures= (String) Object.get("NumberOfFeatures");
	
		DataPicker("Verify user can select feature button to open <b>Features</b> listing", "Features", 0);              	
		int Number= Integer.valueOf(NumberOfFeatures);		
		try {
			for (int i=0; i<Number; i++)	
				driver.findElements(By.id("com.pakwheels.staging:id/checkbx_features")).get(i).click();                                    
			test.log(Status.PASS, "Verify user can select Features <br>Total Features: <b>"+Number+"</b>");
		}catch(org.openqa.selenium.NoSuchElementException e){
			test.log(Status.FAIL, "Verify user can select Features <br>Total Features: <b>"+Number+"</b>");
			throw new Exception("Element not found");
		}				  
		DataPicker("Verify user can select Done button to close feature listing", "Done", 2);	
	}
}
