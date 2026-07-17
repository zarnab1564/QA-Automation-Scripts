package Locators.UsedSearch;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.util.Assert;

import Locators.Base;
import Locators.BaseHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class UsedSearch extends BaseHelper{

	AppiumDriver<MobileElement> driver= null;
	ExtentTest test= null;
	WebDriverWait wait= null;
	JSONObject Object = null;
	
	public UsedSearch(AppiumDriver<MobileElement> driver, ExtentTest test, WebDriverWait wait, JSONObject Object) 
	{
		super(driver, test, wait, Object);
		this.driver= driver;
		this.test= test;
		this.wait= wait;
		this.Object= Object;
	}
	
	public void ApplySort() throws Exception
	{
		String SortValue= (String) Object.get("Sort");
		
		test.log(Status.INFO, "User is navigated to Ads Listing Screen");		
		if (!SortValue.isEmpty()) 
		{
			DataPicker("Verify that user can select <b>Sort</b> Button", "Sort", 2);
			try{
				driver.findElement(By.xpath("//android.widget.TextView[@text='"+SortValue+"']")).click();
				test.log(Status.PASS, "Verify that user can choose from sort options <br>Test Data: <b>"+SortValue+"</b>");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can choose from sort options <br>Test Data: <b>"+SortValue+"</b>");
			}
		}
	}
	public void ChecklistinghasAds()
	{
		try {
			
			if (driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.pakwheels.staging:id/no_result_title']")).isDisplayed()) {
			    test.log(Status.INFO, "Sorry, your search did not return any results. Appears");
			    System.out.println("No Results Widget Appears" + "\nSorry, your search did not return any results. Appears");
			    
			    // Stop the execution of the current test case
			    test.log(Status.INFO, "Stopping the test execution for further actions on search listing");
			    System.out.println("Stopping the test execution for further actions on search listing");
			    return;  // This will stop the execution of the current test case method
			    
			}
		}catch(org.openqa.selenium.NoSuchElementException e){
		}
		
	}
	
	public void SaveAd()
	{
		String SaveAds= (String) Object.get("SaveAds");
		if (!SaveAds.isEmpty()) 
		{
			int Number= Integer.valueOf(SaveAds);  
				
			for (int i=0; i<Number; i++)
			{	
				try {
					if(Base.Testing_Enviroment.equalsIgnoreCase("Staging"))
					{driver.findElements(By.id("com.pakwheels.staging:id/circle")).get(i).click(); 	}
					else
					{
						driver.findElements(By.id("com.pakwheels:id/circle")).get(i).click(); 
					}
					
						
					test.log(Status.PASS, "Verify that user can <b>Save Ad</b> number = "+i+"");
				}catch(org.openqa.selenium.NoSuchElementException e){
					test.log(Status.FAIL, "Verify that user can <b>Save Ad</b> number = "+i+"");
				}
			}	
		}
	}
	
	public void CreateAlert(String AlertValue) throws Exception
	{
		if (!AlertValue.isEmpty()) {
			try{
				driver.findElement(By.id("com.pakwheels.staging:id/btn_create_alert")).click(); 
				test.log(Status.PASS, "Verify that user can tap on <b>Alert</b> Button from headers to create alert");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can tap on <b>Alert</b> Button from headers to create alert");
			}
			
			if(AlertValue.equalsIgnoreCase("Weekly"))
			{
				try{ 					
					driver.findElement(By.xpath("//android.widget.RadioButton[@text= '"+AlertValue+"']")).click(); 
					test.log(Status.PASS, "Verify that user can select Alert time <br>Test Data: <b>"+AlertValue);
				}catch(org.openqa.selenium.NoSuchElementException e){
					test.log(Status.FAIL, "Verify that user can select Alert time <br>Test Data: <b>"+AlertValue);
				}
			}
			DataPicker("Verify that user can select <b>Create Alert</b> button", "Create Alert", 2);
		}
	}
	
	public void ReportAd() throws Exception
	{
		String ReportAd= (String) Object.get("ReportAd");
		if (!ReportAd.isEmpty() && Base.Testing.equalsIgnoreCase("System")) {
			try{ 
				ScrollForward();
				DataPicker("Verify that user can click report this ad button", "Report this Ad", 0);	
				driver.findElement(By.xpath("//android.widget.RadioButton[@text= '"+ReportAd+"']")).click(); 
				test.log(Status.PASS, "Verify that user can select report ad reason (Test Data: "+ReportAd+")");
				DataPicker("Verify that user can select click on done button", "Done", 0);
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can click report this ad button");
			}		   		
		}	
	}
	
	public void SelectAdvancedSearch() throws Exception
	{
		DataPicker("Verify that user can select <b>Advanced Search</b>", "Advanced Search", 0);
		test.log(Status.INFO, "User is navigated to Advance Search Filters Screen");
	}
	
	public void VisitSellerDetailPage() throws InterruptedException
	{
		if (Base.Testing.equalsIgnoreCase("System")){
			try{
				Scroll("Seller Detail");
				test.log(Status.PASS, "Verify Seller Detail section is displayed");
				driver.findElement(By.id("com.pakwheels.staging:id/avatar_block_detail_page")).click(); 
				test.log(Status.PASS, "Verify that user can visit Seller Detail Page");
				driver.findElement(By.className(Back2)).click(); 
				test.log(Status.PASS, "Verify that user can get back from visiting Seller Details page");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify Seller Detail section is displayed");
			}
		}
	}
	
	public void CheckSellerCommentsSection() throws Exception
	{
		if (Base.Testing.equalsIgnoreCase("System")){
			try{
				Scroll("Seller Comments");	
				test.log(Status.PASS, "Verify Seller Comments section is displayed");
		    }catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify Seller Comments section is displayed");
	        }
		}
	}
	
	public void ApplyFilter() throws Exception
	{
		DataPicker("Verify that user can select <b>Apply Filters</b> button", "Apply Filters", 2);
		Thread.sleep(2000);
	}
	
	public void VerifyTotalNumberOfAdsOnListing()
	{
		MobileElement parent= driver.findElement(By.id("com.pakwheels.staging:id/carSearchListViewCustom"));
		int size= parent.findElements(By.className("android.widget.LinearLayout")).size();
				
		text= driver.findElement(By.id("com.pakwheels.staging:id/tv_results_count")).getText();
		String data[]= text.split(" ");
		int Number= Integer.valueOf(data[0]);  
		System.out.println(size+"VS"+Number);
		if (size == Number)
		{
			test.log(Status.PASS, "Verify that count of total results is accurate and same number of ads are appearing on listing");	
		}
	}
	
	public void SelectMinMaxPrice() throws Exception
	{
		 JSONObject PriceObj = (JSONObject) Object.get("Price");
		 String MinPrice= (String) PriceObj.get("MinPrice");
		 String MaxPrice= (String) PriceObj.get("MaxPrice");
		 
		if (!MinPrice.isEmpty()) 	
			SendText("Verify that user can enter Min Price <br>Test Data: <b>"+MinPrice+"</b>", "0", MinPrice);	
		
		if (!MaxPrice.isEmpty()) 	
			SendText("Verify that user can enter Max Price <br>Test Data: <b>"+MaxPrice+"</b>", "Any", MaxPrice);
		ScrollForward();
	}
	
	public void SelectMinMaxModelYear() throws Exception
	{
		JSONObject YearObj = (JSONObject) Object.get("Year");
		String MinYear= (String) YearObj.get("MinYear");
		String MaxYear= (String) YearObj.get("MaxYear");
		 
		if (!MinYear.isEmpty()) {
			Scroll("Model Year Range");		
			SendText("Verify that user can enter Min Year <br>Test Data: <b>"+MinYear+"</b>", "1970", MinYear);			
		}
			
		if (!MaxYear.isEmpty()) 
			SendText("Verify that user can enter Max Year <br>Test Data: <b>"+MaxYear+"</b>", "2022", MaxYear);	
		ScrollForward();
	}
	
	public void SelectMinMaxMileage() throws Exception
	{
		 JSONObject MileageObj = (JSONObject) Object.get("Mileage");
		 String MinMileage= (String) MileageObj.get("MinMileage");
		 String MaxMileage= (String) MileageObj.get("MaxMileage");
		 
		if (!MinMileage.isEmpty()) {
			Scroll("KMs Driven");
			SendText("Verify that user can enter Min Mileage <br>Test Data: <b>"+MinMileage+"</b>", "0", MinMileage);	
		}
		
		if (!MaxMileage.isEmpty()) 
			SendText("Verify that user can enter Max Mileage <br>Test Data: <b>"+MaxMileage+"</b>", "Any", MaxMileage);
		ScrollForward();
	}
	
	
	public void ApplyLocationFilter() throws Exception
	{
		JSONObject locationObj = (JSONObject) Object.get("Location");
		String CityArea= (String) locationObj.get("CityArea");
		 
		SelectLocation();
	
	}
	
	public void VerifySmsChatCallButtons() throws Exception
	{
		test.log(Status.INFO, "User is navigated to Ad Detailed Page");
				
		boolean value= driver.findElement(By.xpath("//android.widget.Button[@text= 'SMS']")).isDisplayed();			
		if (Boolean.TRUE.equals(value)) 
			test.log(Status.PASS, "Verify SMS button is displayed");		
		else 
			test.log(Status.FAIL, "Verify SMS button is displayed");
			
				
		value= driver.findElement(By.xpath("//android.widget.Button[@text= 'Chat']")).isDisplayed();
		if (Boolean.TRUE.equals(value)) 
			test.log(Status.PASS, "Verify Chat button is displayed");
		else
			test.log(Status.FAIL, "Verify Chat button is displayed");
	    
		
		value= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Call']")).isDisplayed(); 
		if (Boolean.TRUE.equals(value)) 
			test.log(Status.PASS, "Verify Call button is displayed");
		else
			test.log(Status.FAIL, "Verify Call button is displayed");
	}	
	
	public void CheckPicturesOnAdDetail()
	{ boolean value;
		if(Base.Testing_Enviroment.equalsIgnoreCase("Staging"))
		{
			 value= driver.findElement(By.id("com.pakwheels.staging:id/layout_ad_detail_pictures")).isDisplayed();
		}
		else
		{	
			 value= driver.findElement(By.id("com.pakwheels.staging:id/layout_ad_detail_pictures")).isDisplayed();
		
		}
		if (Boolean.TRUE.equals(value)) 
			test.log(Status.PASS, "Verify pictures on ad detail are displayed");		
		else 
			test.log(Status.FAIL, "Verify pictures on ad detail are not displayed");
	}
	
	public void CheckFeatureSection() throws Exception
	{
		if (Base.Testing.equalsIgnoreCase("System")){
			try{			
				Scroll("Features");
				test.log(Status.PASS, "Verify Features section is displayed");
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify Features section is displayed");
			}
		}
	}
	
	
	public void CheckSimilarAdsCarousel() throws Exception{
		if (Base.Testing.equalsIgnoreCase("System")){
			try{
				Scroll("Similar Ads");
				test.log(Status.PASS, "Verify Similar Ads section is displayed");
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify Similar Ads section is displayed");
			}		
		}
	}
}