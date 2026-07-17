package Locators.UsedSearch;
import static org.testng.Assert.assertEquals;

import java.util.List;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Locators.Base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Search_Accessory extends UsedSearch
{
	AppiumDriver<MobileElement> driver= null;
	ExtentTest test= null;	
	WebDriverWait wait= null;
	JSONObject Object = null;
	public Search_Accessory(AppiumDriver<MobileElement> driver, ExtentTest test, WebDriverWait wait, JSONObject Object) 
	{
		super(driver, test, wait, Object);
		this.driver= driver;
		this.test= test;
		this.wait= wait;
		this.Object= Object;
	}
	
	public void NavigateToAccessorySearch() throws Exception{
		ClickHomeButton();
		DataPicker("Verify that user can select <b>Auto Parts</b> tab from headers", "Autostore", 0);
	}
	
	public void VerifyDifferentSectionsDisplayed(){
		
		if (Base.Testing.equalsIgnoreCase("System"))
		{
			test.log(Status.INFO, "User is navigated to Auto Parts Page");			
			ScrollSecondView("Browse Auto Parts & Accessoriess");
			try{
				String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Browse Auto Parts & Accessoriess']")).getText();          
				assertEquals(text, "Browse Auto Parts & Accessoriess");
				test.log(Status.PASS, "Verify that Browse Auto Parts & Accessoriess section is displayed");
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify that Browse Auto Parts & Accessoriess section is displayed");
			}
			
			ScrollSecondView("PakWheels Autoshop");
			try{
				String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'PakWheels Autoshop']")).getText();          
				assertEquals(text, "PakWheels Autoshop");
				test.log(Status.PASS, "Verify that PakWheels Autoshop section is displayed");
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify that PakWheels Autoshop section is displayed");
			}
			
			ScrollSecondView("Top Selling Products");
			try{
				String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Top Selling Products']")).getText();          
				assertEquals(text, "Top Selling Products");
				test.log(Status.PASS, "Verify that Top Selling Products section is displayed");
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify that Top Selling Products section is displayed");
			}
			
			ScrollSecondView("Hot Deals");
			try{
				String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Hot Deals']")).getText();          
				assertEquals(text, "Hot Deals");
				test.log(Status.PASS, "Verify that Hot Deals section is displayed");
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify that Hot Deals section is displayed");
			}
			
			try{
				String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Car Parts by Brands']")).getText();          
				assertEquals(text, "Car Parts by Brands");
				test.log(Status.PASS, "Verify that Car Parts by Brands section is displayed");
			}catch(AssertionError e){				
				test.log(Status.FAIL, "Verify that Car Parts by Brands section is displayed");			
			}catch(org.openqa.selenium.NoSuchElementException e){
				ScrollForward();
				String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Car Parts by Brands']")).getText();          
				assertEquals(text, "Car Parts by Brands");
				test.log(Status.PASS, "Verify that Car Parts by Brands section is displayed");
			}
			
			ScrollForward();
			try{
				String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Latest Videos']")).getText();          
				assertEquals(text, "Latest Videos");
				test.log(Status.PASS, "Verify that Latest Videos section is displayed");
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify that Latest Videos section is displayed");
			}
		}
	}
	
	public void NavigateToLandingScreen() throws Exception{
		//DataPicker("Verify that user can navigate to Auto Part search Screen", "Search auto parts", 0);
		DataPicker("Verify that user can navigate to Auto Part search Screen", "Search products for your Vehicle", 0);
	}
	
	public void EnterKeywords(){
		String Keyword= (String) Object.get("Keyword");
		if (Keyword != null && !Keyword.trim().isEmpty()){
			try{
				driver.findElement(By.xpath("//android.widget.EditText[@text= 'Search (e.g. Lights, Tyres, etc)']")).sendKeys(Keyword); 
				test.log(Status.PASS, "Verify that user can enter <b>Keyword</b> (Test Data: "+Keyword+")");	
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can enter <b>Keyword</b> (Test Data: "+Keyword+")");	
			}
		}
	}
	
	public void SelectShopNowOption() throws Exception{
		String ShopNow= (String) Object.get("ShopNow");
		Scroll("Sold By PakWheels"); 
		DataPicker("Verify that user can select show now options <br>Test Data: <b>"+ShopNow+"</b>", ShopNow, 2);
	}
	
	public void VerifyWantToSellAutoPartSection()
	{
		if (Base.Testing.equalsIgnoreCase("System"))
		{
			try{
				String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Want to Sell Your Auto Parts?']")).getText();          
				assertEquals(text, "Want to Sell Your Auto Parts?");
				test.log(Status.PASS, "Verify Want to Sell Your Auto Parts section is displayed");
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify Want to Sell Your Auto Parts section is displayed");
			}
		}
	}

	public void Category() throws Exception {	
		try{
			   
			String Category= (String) Object.get("Category");
			DataPicker("Verify that user can Category", "Category", 0); 
			SendText("Verify that user can enter text to refine Category search <br>Test Data: <b>"+Category+"</b>", "Type to refine search", Category);
			DataPicker("Verify that user can select category <br>Test Data: <b>"+Category+"</b>", Category, 0);
			test.log(Status.PASS, "Verify that user selects category filter");
		}catch(AssertionError e){
			test.log(Status.FAIL, "Verify that user unable to select category filter");
		}
	}

	public void Brand() throws Exception {
		try{
			   
			String Brand= (String) Object.get("Brand");
			DataPicker("Verify that user can click on Brand", "Brand", 0); 
			DataPicker("Verify that user can select Brand <br>Test Data: <b>"+Brand+"</b>", Brand, 0);
			test.log(Status.PASS, "Verify that user selects Brand filter");
		}catch(AssertionError e){
			test.log(Status.FAIL, "Verify that user unable to select Brand filter");
		}
	}

	public void SelectAutoMinMaxPrice() throws InterruptedException {
		 JSONObject PriceObj = (JSONObject) Object.get("Price");
		 String MinPrice= (String) PriceObj.get("MinPrice");
		 String MaxPrice= (String) PriceObj.get("MaxPrice");
		 
		if (!MinPrice.isEmpty()) 	
			SendText("Verify that user can enter Min Price <br>Test Data: <b>"+MinPrice+"</b>", "0", MinPrice);	
		
		if (!MaxPrice.isEmpty()) 	
			SendText("Verify that user can enter Max Price <br>Test Data: <b>"+MaxPrice+"</b>", "2 Lac +", MaxPrice);
		
	}
	
	public void Applyfilters() throws Exception
	{
		DataPicker("Verify that user can select <b>Apply Filters</b> button", "Apply filters", 2);
		Thread.sleep(2000);
	}

	public void SavedVehicles() throws Exception {
		
		 JSONObject Obj = (JSONObject) Object.get("CarInfo");
		 String Year= (String) Obj.get("ModelYear");
		 String Make= (String) Obj.get("Make"); 
		 String MakeModel= (String) Obj.get("Model"); 
		 String Version=(String) Obj.get("Version"); 
		 String SavedVehicle=Year+" "+Make+" "+MakeModel+" "+Version;
		 DataPicker("Verify that user can click Saved Vehicles", "Saved vehicles", 0);
		Boolean FoundSavedVehicle=false;
		 try{
			 if(driver.findElement(By.xpath("//android.widget.TextView[@text='Select your saved vehicle']")).isDisplayed())
			 {      
					List<MobileElement> data= driver.findElements(By.className("android.widget.TextView"));
					int size = data.size();
					for (int i = 0; i < size; i++) 
					{
						text = driver.findElements(By.className("android.widget.TextView")).get(i).getText();
						if(text.equalsIgnoreCase(SavedVehicle)) 
						{   FoundSavedVehicle=true;
							DataPicker("Verify that user can click on Saved Vehicles<b>"+text+"</b>", text, 0);
							test.log(Status.PASS, "Verified that user selects Saved Vehicle from Saved Vehicles bottom sheet.");
						}
						
					}
					
					System.out.println(FoundSavedVehicle);
					if(FoundSavedVehicle!=true)
					{
						DataPicker("Verify that user can Add Another Vehicle", "Add another vehicle", 2);
						if (Year != null && !Year.isEmpty()) 
						{	DataPicker("Verify that user can select car year <br>Test Data: <b>"+Year+"</b>", Year, 0);}
						if (!MakeModel.isEmpty()) 
						{		
							SendText("Verify that user can enter text to refine Make Model search", "Type to refine search", MakeModel);
							DataPicker("Verify that user can select car Make and Model <br>Test Data: <b>"+MakeModel+"</b>", MakeModel, 0);   	

							if (Version != null && !Version.isEmpty()) 
							{
								SendText("Verify that user can enter text to refine Version search", "Type to refine search", Version);
								DataPicker("Verify that user can select car Version <br>Test Data: <b>"+Version+"</b>", Version, 0);    	
							}
						}
					}
			 }

		}catch(AssertionError e)
		{
		  if(driver.findElement(By.xpath("//android.widget.TextView[@text='Select Year']")).isDisplayed())
		  { 
			if (Year != null && !Year.isEmpty()) 
			{	DataPicker("Verify that user can select car year <br>Test Data: <b>"+Year+"</b>", Year, 0);}
			if (!MakeModel.isEmpty()) 
			{		
				SendText("Verify that user can enter text to refine Make Model search", "Type to refine search", MakeModel);
				DataPicker("Verify that user can select car Make and Model <br>Test Data: <b>"+MakeModel+"</b>", MakeModel, 0);   	

				if (Version != null && !Version.isEmpty()) 
				{
					SendText("Verify that user can enter text to refine Version search", "Type to refine search", Version);
					DataPicker("Verify that user can select car Version <br>Test Data: <b>"+Version+"</b>", Version, 0);    	
				}
			}
		  }	
		}
	}

	public void ApplyAutoSort() throws Exception {
	    String SortValue = (String) Object.get("Sort");

	    test.log(Status.INFO, "User is navigated to Ads Listing Screen");

	    if (SortValue != null && !SortValue.isEmpty()) {
	        try {
	            // Use XPath instead of By.id
	            driver.findElement(By.xpath("//android.view.View[@resource-id='sort_icon']")).click();

	            // Select the option dynamically
	            driver.findElement(By.xpath("//android.widget.TextView[@text='" + SortValue + "']")).click();

	            test.log(Status.PASS, "✅ Verify that user can choose from sort options <br>Test Data: <b>" + SortValue + "</b>");
	        } catch (org.openqa.selenium.NoSuchElementException e) {
	            test.log(Status.FAIL, "❌ Sort option not found <br>Test Data: <b>" + SortValue + "</b>");
	            throw new Exception("Sort option not found: " + SortValue, e);
	        }
	    } else {
	        test.log(Status.SKIP, "⚠️ Sort value is empty, skipping step.");
	    }
	}

	public void SelectAutoAd() throws Exception {
		    try {
		        // Locate all ad cards dynamically
		        List<MobileElement> ads = driver.findElements(By.xpath("//android.view.View[@resource-id]"));

		        if (ads.isEmpty()) {
		            test.log(Status.FAIL, "❌ No ads found on the Listing Page");
		            throw new Exception("No ads found");
		        }

		        // Select the first ad (resource-id = 0)
		        MobileElement firstAd = ads.get(0);

		        firstAd.click();
		        test.log(Status.PASS, "✅ Verify that user can select Ad from Listing Page");

		    } catch (org.openqa.selenium.NoSuchElementException e) {
		        test.log(Status.FAIL, "❌ Verify that user can select Ad from Listing Page");
		        throw new Exception("Element not found", e);
		    } catch (Exception e) {
		        test.log(Status.FAIL, "❌ Unexpected error while selecting Ad");
		        throw e;
		    }
		}


	public void SaveAutoAd() {
	    String SaveAds = (String) Object.get("SaveAds");
	    if (SaveAds != null && !SaveAds.isEmpty()) {
	        int Number = Integer.parseInt(SaveAds);

	        for (int i = 0; i < Number; i++) {
	            try {
	                // Build dynamic XPath: saveAd_0, saveAd_1, ...
	                String xpath = "//android.view.View[@resource-id='saveAd_" + i + "']";
	                driver.findElement(By.xpath(xpath)).click();

	                test.log(Status.PASS, "✅ Verify that user can <b>Save Ad</b> number = " + i);
	            } catch (org.openqa.selenium.NoSuchElementException e) {
	                test.log(Status.FAIL, "❌ Verify that user can <b>Save Ad</b> number = " + i + " (element not found)");
	            } catch (Exception e) {
	                test.log(Status.FAIL, "⚠️ Unexpected error while saving Ad number = " + i + ": " + e.getMessage());
	            }
	        }
	    } else {
	        test.log(Status.SKIP, "⚠️ SaveAds value is empty, skipping SaveAd function.");
	    }
	}
	
}
