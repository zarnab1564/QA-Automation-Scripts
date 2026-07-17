package Locators.UsedSearch;
import static org.testng.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Locators.Base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Search_UsedBikes extends UsedSearch
{
	AppiumDriver<MobileElement> driver= null;
	ExtentTest test= null;	
	WebDriverWait wait= null;
	JSONObject Object = null;
	public Search_UsedBikes(AppiumDriver<MobileElement> driver, ExtentTest test, WebDriverWait wait, JSONObject Object) 
	{
		super(driver, test, wait, Object);
		this.driver= driver;
		this.test= test;
		this.wait= wait;
		this.Object= Object;
	}

	public void NavigateToBikeSearch() throws Exception
	{
		ClickHomeButton();
		DataPicker("Verify that user can select <b>Bike</b> tab from headers", "Bikes", 0);
	}
	
	public void VerifyDifferentSectionsOnBikeSearch()
	{
		if (Base.Testing.equalsIgnoreCase("System"))
		{
			test.log(Status.INFO, "User is navigated to Bike Page");		
			ScrollSecondView("Browse Used Bikes");
			try{
				String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Browse Used Bikes']")).getText();          
				assertEquals(text, "Browse Used Bikes");
				test.log(Status.PASS, "Verify that Browse Used Bikes section is displayed");
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify that Browse Used Bikes section is displayed");
			}
			
			try{
				String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Browse More']")).getText();          
				assertEquals(text, "Browse More");
				test.log(Status.PASS, "Verify that Browse More section is displayed");
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify that Browse More section is displayed");
			}
			
			ScrollSecondViewWithID("com.pakwheels.staging:id/iv_feature_icon_feature_ads");
			try{
				String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Featured Ads']")).getText();          
				assertEquals(text, "Featured Ads");
				test.log(Status.PASS, "Verify that Featured Ads section is displayed");
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify that Featured Ads section is displayed");
			}
			
			try{
				driver.findElement(By.id("com.pakwheels.staging:id/iv_feature_icon_feature_ads")).click();   
				test.log(Status.PASS, "Verify that user can click first ad from Featured Ads Carousel and is navigated to Ad detailed page");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can click first ad from Featured Ads Carousel and is navigated to Ad detailed page");
			}
			
			try{
				driver.findElement(By.id(Back1)).click();   
				test.log(Status.PASS, "Verify that user can click back button from Ad detailed page");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can click back button from Ad detailed page");
			}
			
			ScrollForward();
			try{
				String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Latest Videos']")).getText();          
				assertEquals(text, "Latest Videos");
				test.log(Status.PASS, "Verify that Latest Videos section is displayed");
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify that Latest Videos section is displayed");
			}
		
			ScrollForward();
			try{
				String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Latest News']")).getText();          
				assertEquals(text, "Latest News");
				test.log(Status.PASS, "Verify that Latest News section is displayed");
			}catch(AssertionError e) {
				test.log(Status.FAIL, "Verify that Latest News section is displayed");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that Latest News section is displayed");
			}
		}
	}
	
	public void NavigateToLandingScreen() throws Exception
	{
		//ScrollToBeginning(); 	
		DataPicker("Verify that User can navigate to <b>Used Bikes Search landing</b> screen", "Search used bikes", 0);
		/*if (!BikeName.isEmpty()){		
			test.log(Status.PASS, "Verify that user can tap on Search Bar to enter text");		
			//driver.findElement(By.xpath("//android.widget.AutoCompleteTextView[@text= 'Search used bikes']")).sendKeys(BikeName);
			SendText("Search used bikes", BikeName);
			test.log(Status.PASS, "Verify that user can enter Bike Make Model in Search Bar (Test Data: "+BikeName+")");
			DataPicker(BikeName, 0);
			test.log(Status.PASS, "Verify that user can select Bike Model from search result (Test Data: "+BikeName+")");
			Thread.sleep(2000);
			driver.navigate().back();
		}*/
	}
	
	public void SelectBikeInformation() throws Exception
	{
		String MakeModel= (String) Object.get("MakeModel");
		if (!MakeModel.isEmpty()){
			DataPicker("Verify that user can select Bike information button", "Bike Information", 0);	
			SendText("Verify that user can enter text to refine Make Model search", "Type to refine search", MakeModel);	
			DataPicker("Verify that user can select Bike Model from Model listing <br>Test Data: <b>"+MakeModel+"</b>", MakeModel, 0);	
		}
	}
	
	public void EnterKeywords() throws Exception
	{			
		String Keyword= (String) Object.get("Keyword");
		SendText("Verify that user can enter Keyword <br>Test Data: <b>"+Keyword+"</b>", "Search (e.g Honda Cd 70 in Lahore)", Keyword);	
	}
	
	public void VerifyWantToSellBikeSection() throws Exception
	{
		if (Base.Testing.equalsIgnoreCase("System"))
		{
			try{
			//	String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Want to Sell Your Bike?']")).getText();       
				DataPicker("Verify Want to Sell Your Bike section is displayed", "Want to Sell Your Bike?", 0);
				assertEquals(text, "Want to Sell Your Bike?");
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify Want to Sell Your Bike section is displayed");
			}
		}
	}
	
	public void SelectEngineType() throws Exception
	{
		String Stroke= (String) Object.get("Stroke");
		if (!Stroke.isEmpty()) {
			ScrollSecondView("Engine Type");		
			DataPicker("Verify that user can select Engine Type <br>Test Data: <b>"+Stroke+"</b>", Stroke, 2);
			
		}
	}
	
	public void SelectBodyType()
	{
		Scroll("Body Type");
		try {
			
			driver.findElement(By.xpath("//android.widget.TextView[@text= 'Standard']")).click(); 
			test.log(Status.PASS, "Verify that user can select Standard Body Type (Test Data: Standard)");
		}catch(org.openqa.selenium.NoSuchElementException e){
			test.log(Status.FAIL, "Verify that user can select Standard Body Type (Test Data: Standard)");
		}
	}
	
	public void CheckModelName()
	{
		try{
			String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Honda CD 70']")).getText(); 
			assertEquals(text, "Honda CD 70");	
			test.log(Status.PASS, "Verify Model Name on Ad detailed Page");
		}catch(AssertionError e){
			test.log(Status.FAIL, "Verify Model Name on Ad detailed Page");
	    }

	}
	
	public void SelectSellerType() throws Exception
	{
		String SellerType= (String) Object.get("SellerType");
		if (!SellerType.isEmpty()) {
			Scroll(SellerType);		
			DataPicker("Verify that user can select Seller Type <br>Test Data: <b>"+SellerType+"</b>", SellerType, 2);
			
		}
	}
	
	public void SelectAdProperties() throws Exception
	{
		String AdProperties= (String) Object.get("AdProperties");
		if (!AdProperties.isEmpty()) {
			Scroll(AdProperties);		
			DataPicker("Verify that user can select Ad Properties <br>Test Data: <b>"+AdProperties+"</b>", AdProperties, 2);	
		}
	}
}
