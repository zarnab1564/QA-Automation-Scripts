package Locators.AdPosting;
import java.util.List;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import Locators.Base;
import Locators.BaseHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AdPosting extends BaseHelper{

	AppiumDriver<MobileElement> driver= null;
	ExtentTest test= null;
	WebDriverWait wait= null;
	JSONObject Object = null;
	Boolean isActive=true, inReview=false, isLimitExceed=false;
	
	public AdPosting(AppiumDriver<MobileElement> driver, ExtentTest test, WebDriverWait wait, JSONObject Object) 
	{
		super(driver, test, wait, Object);
		this.driver= driver;
		this.test= test;
		this.wait= wait;
		this.Object= Object;
	}

	public void ClickSellButton() throws Exception
	{
		try {
			driver.findElement(By.id("com.pakwheels.staging:id/fab_home_layout")).click();                       
			test.log(Status.PASS, "Verify that user can tap on <b>Sell</b> Button to select Ad type");
		}catch(org.openqa.selenium.NoSuchElementException e){
			test.log(Status.FAIL, "Verify that user can tap on <b>Sell</b> Button to select Ad type");
			throw new Exception("Element not found");
		}
	}
	public void AddPhotos() throws Exception
	{  
		DataPicker("Verify that user can click on <b>Add photos</b> button", "Add Photo", 0);   
		
		try {
			driver.findElement(By.xpath("//android.widget.TextView[@text='Upload photos from gallery']")).click(); 
			test.log(Status.PASS, "Verify that user can select option to upload photos <br>Test Data: <b>Upload Photos from gallery</b>");
		}catch(org.openqa.selenium.NoSuchElementException e){
			test.log(Status.FAIL, "Verify that user can select option to upload photos <br>Test Data: <b>Upload Photos from gallery</b>");
			throw new Exception("Element not found");
		}	
		
		try{
			driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_button")).click();
			test.log(Status.PASS, "Verify that user can select Allow button to give access to Gallery");  		
		}catch(org.openqa.selenium.NoSuchElementException e){
			test.log(Status.INFO, "Gallery permission is already granted to the application"); 
		}
		
		Thread.sleep(3000);  // Waiting for the images to appear
		int Number= Integer.valueOf((String) Object.get("NumberOfImages"));  	
		try{
			for (int i=0; i<Number; i++) {                    
				driver.findElements(By.id("com.pakwheels.staging:id/image_view")).get(i).click();       // Upload Pictures
				Thread.sleep(1000);
			}
			test.log(Status.PASS, "Verify that user can select images for uploading <br>Total Images: <b>"+Number+"</b>");	
		}catch(org.openqa.selenium.NoSuchElementException e){
			test.log(Status.FAIL, "Verify that user can select images for uploading <br>Total Images: <b>"+Number+"</b>");
			throw new Exception("Element not found");
		}
		
		
		try{
			driver.findElement(By.id("com.pakwheels.staging:id/menu_done")).click();                  	
			test.log(Status.PASS, "Verify that user can end pictures selection by tapping on Tick Button");
		}catch(org.openqa.selenium.NoSuchElementException e){
			test.log(Status.FAIL, "Verify that user can end pictures selection by tapping on Tick Button");
			throw new Exception("Element not found");
		}
	}
	public void SelectLocation() throws Exception {
	    JSONObject locationObj = (JSONObject) Object.get("Location");

	    String city = (String) locationObj.get("City");
	    String cityArea = (String) locationObj.get("CityArea");

	    if (city != null && !city.trim().isEmpty()) {
	        DataPicker("Verify that user can select <b>Location</b> button", "Location", 0);
	        SendText("Verify that user can enter text to refine City search", "Type to refine search", city);
	        DataPicker("Verify that user can select City <br>Test Data: <b>" + city + "</b>", city, 0);
	    }

	    if (cityArea == null || cityArea.trim().isEmpty()) {
	        waitForPushNotificationToDisappear();
	        DataPicker("Verify that user can select cancel button to not select any city area", "Cancel", 0);
	    } else {
	        SendText("Verify that user can enter text to refine City Area search", "Type to refine search", cityArea);
	        DataPicker("Verify that user can select City Area <br>Test Data: <b>" + cityArea + "</b>", cityArea, 0);
	    }
	}

	// Better than Thread.sleep() – allows dynamic waiting
	private void waitForPushNotificationToDisappear() {
	    try {
	        Thread.sleep(5000); // Replace with explicit wait if possible
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }
	}
	public void EnterMileageAndPrice() throws InterruptedException
	{
		String MileageValue= (String) Object.get("Mileage");
		String PriceValue= (String) Object.get("Price");
		Scroll("Specify KMs driven");
		SendText("Verify that user can enter Mileage <br>Test Data: <b>"+MileageValue+"</b>", "Specify KMs driven", MileageValue);
		Scroll("Set a price");
		SendText("Verify that user can enter Price <br>Test Data: <b>"+PriceValue+"</b>", "Set a price", PriceValue);		
	}
	
	public void EnterAdContactInformation() throws InterruptedException{
		test.log(Status.INFO, "<b>Contact Information</b>");
		Scroll("Post Your Ad");
		// Find the phone number field using the 'hint' attribute
		MobileElement phoneNumberField = driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.pakwheels.staging:id/et_input_text' and @hint='Enter Mobile Number']"));
		phoneNumberField.clear();
		SendText("Verify that user can enter Mobile Number <br>Test Data: <b>"+Base.MobileNumber+"</b>", "Enter Mobile Number", Base.MobileNumber);
	}
	
	public void AllowWhatsAppContact() throws Exception
	{
		if(((String) Object.get("AllowWhatsApp")).equalsIgnoreCase("No"))
		{
			try {
				driver.findElement(By.id("com.pakwheels.staging:id/switch_whatsapp_allow")).click();     
				test.log(Status.PASS, "Verify that user can disable WhatsApp Contact");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can disable WhatsApp Contact");
				throw new Exception("Element not found");
			}			
		}else
		{
			test.log(Status.PASS, "Verify that user can enable WhatsApp Contact");
		}
	}
	
	public void SelectPostYourAdButton() throws Exception
	{
		Scroll("Post Your Ad");
		DataPicker("Verify user can select <b>Post Your Ad</b> button to post Ad", "Post Your Ad", 2);
		Thread.sleep(5000);
	}
	
	public void checkAdStatus() throws Exception
	{
		Thread.sleep(3000);	
		try
		{
			if (driver.findElement(By.xpath("//android.widget.TextView[@text='Skip']")).isDisplayed())
			{
				try
				{
					if (driver.findElement(By.xpath("//android.widget.TextView[@text='Your Free Ad Limit has Exceeded']")).isDisplayed())
					{
						test.log(Status.INFO, "Ad has been posted but is in <b style='color: red;'>Limit Exceed</b>");
						isLimitExceed= true;
					}
				}
				catch(org.openqa.selenium.NoSuchElementException e) 
				{
					test.log(Status.INFO, "Ad has been posted successfully and is <b>Active</b>");
					DataPicker("Verify that user can select skip button", "Skip" , 0);
					isActive=true;
				}
			}
		}
		catch(org.openqa.selenium.NoSuchElementException e)
		{
			try
			{
				if (driver.findElement(By.xpath("//android.widget.Button[@text='Edit']")).isDisplayed())
				{
					test.log(Status.INFO, "Ad has been posted and is in <b style='color: red;'>Review</b>");
					inReview=true;
				}
			}
			catch(org.openqa.selenium.NoSuchElementException ex) 
			{
					test.log(Status.FAIL, "<b style='color: red;'>Ad Failed to post</b>");
					throw new Exception("Element not found");
			}
		}
	}
	
	public void ActivateYourAd() throws Exception
	{
		if (Base.Testing.equalsIgnoreCase("System") && isLimitExceed== true && inReview== false) 
		{
			test.log(Status.INFO, "<h3><b>Activate Ad</b></h3>");
			
			try  // If user has feature credits, use feature credits
			{	
				if (driver.findElement(By.xpath("//android.widget.Button[@text='Available Credits']")).isDisplayed())
				{
					DataPicker("Verify that user can select Feature Package", "7 Days" , 0);  // Selected 1 credit option
					DataPicker("Verify that user can select <b>submit</b> Button", "Submit", 2);  // Click submit button
					DataPicker("Verify that user can select ok button", "OK", 2);
					Thread.sleep(1000);  // Wait for screen to refresh
				}
				
			}catch(org.openqa.selenium.NoSuchElementException ex) // If user has no credits, buy normal car ad 
			{
				JSONObject ActivateObject= (JSONObject) Object.get("ActivateAd");
				String ProductName= (String) ActivateObject.get("ProductName");
				DataPicker("Verify that user can select "+ProductName+" option to activate his ad", ProductName , 0); 
				DataPicker("Verify that user can select <b>Proceed to Checkout</b> Button", "Proceed to Checkout", 2);
				
				// Apply any discount code
				String ActivateDiscountCode= (String) ActivateObject.get("ActivateDiscountCode");
				ApplyDiscountCode(ActivateDiscountCode);
				
				// Select checkout button
				DataPicker("Verify that user can select <b>Checkout</b> Button", "Checkout", 2);
				
				// Make payment using payment method selected
				String PaymentMethod= (String) ActivateObject.get("PaymentMethod");
				Boolean status= false; 
				status = PaymentFlow(PaymentMethod);		
				
				if(status== true)  // If payment is true
				{
					isActive=true;
					test.log(Status.INFO, "Ad has been activated");
				}
				else if (status== false)  // If payment fails
					test.log(Status.INFO, "Ad failed to activated");
			}
		}
	}
	
	public void OpenAdDetail() throws Exception
	{
		try
		{	
			if (driver.findElement(By.xpath("//android.widget.TextView[@text='My Ads']")).isDisplayed())
			{
				String AdType= (String) Object.get("AdType");
				
				if(AdType.equalsIgnoreCase("Car"))
				{
					JSONObject CarObj = (JSONObject) Object.get("CarInformation");
					String Make= (String) CarObj.get("Make");
					String MakeModel= (String) CarObj.get("MakeModel");
				 	String Version= (String) CarObj.get("Version");
				 	
				 	if (Base.Testing.equalsIgnoreCase("System") && isActive== true) 
						DataPicker("Verify that user can click on ad", Make+" "+MakeModel+" "+Version, 0);					
				}
				else if(AdType.equalsIgnoreCase("Bike"))
				{
					JSONObject BikeObj = (JSONObject) Object.get("BikeInformation");
					String Make= (String) BikeObj.get("Make");
					String Model= (String) BikeObj.get("MakeModel");
				 	
				 	if (Base.Testing.equalsIgnoreCase("System") && isActive== true) 
						DataPicker("Verify that user can click on ad", Make+" "+Model, 0);				
				}
				else if(AdType.equalsIgnoreCase("Accessory"))
				{
					String Category= (String) Object.get("Category");				
				 	if (Base.Testing.equalsIgnoreCase("System") && isActive== true) 
						DataPicker("Verify that user can click on ad", Category, 0);			
				}
				
			}
		}
		catch(org.openqa.selenium.NoSuchElementException e){}
	}
	
	public void FeatureYourAd() throws Exception
	{
		Boolean status= false; 
		JSONObject FeatureObject= (JSONObject) Object.get("FeatureAd");
			
		if (Base.Testing.equalsIgnoreCase("System") && ((isActive== true || isLimitExceed== true) && inReview== false) && FeatureObject != null) 
		{	
			test.log(Status.INFO, "<h3><b>Feature Ad</b></h3>");
			OpenAdDetail();
			
			DataPicker("Verify that User can select Feature This Ad button", "Feature This Ad" , 2);
			try
			{			
				if (driver.findElement(By.xpath("//android.widget.Button[@text='Available Credits']")).isDisplayed())
				{
					DataPicker("Verify that user can select Feature Package", "7 Days" , 0);  // Selected 1 credit option
					DataPicker("Verify that user can select <b>submit</b> Button", "Submit", 2);  // Click submit button
					DataPicker("Verify that user can select ok button", "OK", 2);
					Thread.sleep(1000);  // Wait for screen to refresh
				}
				
			}catch(org.openqa.selenium.NoSuchElementException ex) 
			{
				String FeatureBundle= (String) FeatureObject.get("FeatureBundle");
				if(FeatureBundle.contains("Pack"))   // If it is a Feature Bundle, switch to bundles
					DataPicker("Verify that User can switch to Feature Bundles", "Buy Feature Bundles" , 0);
				
				DataPicker("Verify that User can select Feature Package", FeatureBundle , 0);
				DataPicker("Verify that User can select <b>Proceed to Checkout</b> Button", "Proceed to Checkout", 2);
				
				String FeatureDiscountCode= (String) FeatureObject.get("FeatureDiscountCode");
				ApplyDiscountCode(FeatureDiscountCode);
				DataPicker("Verify that User can select <b>Checkout</b> Button", "Checkout", 2);
				String PaymenMethod= (String) FeatureObject.get("PaymentMethod");
				status= PaymentFlow(PaymenMethod);
				
				if(status== true)  // If payment is true
				{
					isActive=true;
					test.log(Status.INFO, "Ad has been featured");
				}
				else if (status== false)  // If payment fails
					test.log(Status.INFO, "Ad failed to featured");
			}		
		}
	}
	
	public void BoostYourAd() throws Exception
	{
		Boolean status= false; 
		if (Base.Testing.equalsIgnoreCase("System") && isActive== true && inReview== false) 
		{	
			JSONObject FeatureObject= (JSONObject) Object.get("BoostAd");
			OpenAdDetail();
			test.log(Status.INFO, "<h3><b>Boost Ad</b></h3>");
			DataPicker("Verify that user can tap on <b>Boost now</b> Button", "Boost now", 2);
			
			try
			{			
				if (driver.findElement(By.xpath("//android.widget.Button[@text='Proceed to Checkout']")).isDisplayed())
				{
					String BoostBundle= (String) FeatureObject.get("BoostBundle");
					DataPicker("Verify that user can select Boost Package <br>Test Data: <b>"+ BoostBundle +"</b>", BoostBundle, 0);
					DataPicker("Verify that User can select <b>Proceed to Checkout</b> Button", "Proceed to Checkout", 2);
					
					String BoostDiscountCode= (String) FeatureObject.get("BoostDiscountCode");
					ApplyDiscountCode(BoostDiscountCode);
					
					DataPicker("Verify that User can select <b>Checkout</b> Button", "Checkout", 2);
					String PaymenMethod= (String) FeatureObject.get("PaymentMethod");
					status= PaymentFlow(PaymenMethod);
					
					if(status== true)
						test.log(Status.INFO, "Ad has been boosted using payment");
					else if(status== false)
						test.log(Status.FAIL, "Ad failed to boost using payment");
				}
			}catch(org.openqa.selenium.NoSuchElementException ex) 
			{
				test.log(Status.INFO, "User has boost credits");
				DataPicker("Verify that user can tap on <b>Boost</b> Button", "BOOST", 2);		
				driver.findElement(By.xpath("//android.widget.Button[@text='OK']")).click();	
				Thread.sleep(2000);
				test.log(Status.INFO, "Ad has been boosted using credits");		
			}
		}
	}
	
	public void RemoveAd() throws Exception
	{
		if (Base.Testing.equalsIgnoreCase("System") && isActive== true && inReview== false) 
		{
			test.log(Status.INFO, "<h3><b>Remove Ad</b></h3>");
			OpenAdDetail();
			DataPicker("Verify that user can select remove button", "Remove", 2);
			
			JSONObject RemoveObj = (JSONObject) Object.get("RemoveAd");
			String RemoveReason= (String) RemoveObj.get("RemoveReason");
			String SoldVia= (String) RemoveObj.get("SoldVia");
			String Price= (String) RemoveObj.get("Price");
			
			String AdType= (String) Object.get("AdType");
			if(RemoveReason.contains("I have sold my"))
			{
				if(AdType.equalsIgnoreCase("Car"))
				{
					DataPicker("Verify that user can tap on reason for removing", "I have sold my Car", 3);
				}
				else if(AdType.equalsIgnoreCase("Bike"))
				{
					DataPicker("Verify that user can tap on reason for removing", "I have sold my Bike", 3);
				}
				else if(AdType.equalsIgnoreCase("Accessory"))
				{
					DataPicker("Verify that user can tap on reason for removing", "I have sold my Auto Part/Accessory", 3);
				}
				DataPicker("Verify that user can tap on Sold via", SoldVia , 3);
				DataPicker("Verify that User can select <b>Remove Ad</b> Button", "Remove Ad", 2);
				SendText("Verify that user can enter price in popup", "Enter sold price here", Price);
				DataPicker("Verify that user can select save button", "Save" , 2);
				Thread.sleep(2000);
			}
			else if(RemoveReason.contains("I am not selling my"))
			{
				if(AdType.equalsIgnoreCase("Car"))
				{
					DataPicker("Verify that user can tap on reason for removing", "I am not selling my Car", 3);
				}
				else if(AdType.equalsIgnoreCase("Bike"))
				{
					DataPicker("Verify that user can tap on reason for removing", "I am not selling my Bike", 3);
				}
				else if(AdType.equalsIgnoreCase("Accessory"))
				{
					DataPicker("Verify that user can tap on reason for removing", "I am not selling my Auto Part/Accessory", 3);
				}
				DataPicker("Verify that User can select <b>Remove Ad</b> Button", "Remove Ad", 2);
			}
			else if(RemoveReason.equalsIgnoreCase("Other (Just remove my Ad)"))
			{
				DataPicker("Verify that user can tap on reason for removing", "Other (Just remove my Ad)", 3);
				driver.hideKeyboard();
				DataPicker("Verify that User can select <b>Remove Ad</b> Button", "Remove Ad", 2);
			}
		}
	}
	
	public void ReactivateAd() throws Exception
	{
		if (Base.Testing.equalsIgnoreCase("System") && isActive== true && inReview== false) 
		{
			test.log(Status.INFO, "<h3><b>Re-activate Ad</b></h3>");
			DataPicker("Verify that user can select Reactive button", "Re-Activate", 2);
			
			try  // If user has free credits
			{
				if (driver.findElement(By.xpath("//android.widget.TextView[@text='Your Ad has been re-activated successfully']")).isDisplayed())
				{
					test.log(Status.INFO, "Ad has been re-activated using free credits");
					DataPicker("Verify that user can select skip button", "Skip" , 0);
				}
			}
			catch(org.openqa.selenium.NoSuchElementException e) // If user has no credits
			{
				try 
				{
					if (driver.findElement(By.xpath("//android.widget.TextView[@text='Your Free Ad Limit has Exceeded']")).isDisplayed())
					{
						JSONObject ActivateObject= (JSONObject) Object.get("ActivateAd");
						String ProductName= (String) ActivateObject.get("ProductName");
						DataPicker("Verify that user can select "+ProductName+" option to activate his ad", ProductName , 0); 
						DataPicker("Verify that user can select <b>Proceed to Checkout</b> Button", "Proceed to Checkout", 2);
						
						// Apply any discount code
						String ActivateDiscountCode= (String) ActivateObject.get("ActivateDiscountCode");
						ApplyDiscountCode(ActivateDiscountCode);
						
						// Select checkout button
						DataPicker("Verify that user can select <b>Checkout</b> Button", "Checkout", 2);
						
						// Make payment using payment method selected
						String PaymentMethod= (String) ActivateObject.get("PaymentMethod");
						Boolean status= false; 
						status = PaymentFlow(PaymentMethod);		
						
						if(status== true)  // If payment is true
						{
							isActive=true;
							test.log(Status.INFO, "Ad has been re-activated using payment");
						}
						else if (status== false)  // If payment fails
							test.log(Status.INFO, "Ad failed to re-activated using payment");
					}
				}catch(org.openqa.selenium.NoSuchElementException ex) // If user has normal credits
				{	
					if (driver.findElement(By.xpath("//android.widget.Button[@text='Available Credits']")).isDisplayed())
					{
						DataPicker("Verify that user can select Feature Package", "7 Days" , 0);  // Selected 1 credit option
						DataPicker("Verify that user can select <b>submit</b> Button", "Submit", 2);  // Click submit button
						DataPicker("Verify that user can select ok button", "OK", 2);
						Thread.sleep(1000);  // Wait for screen to refresh
					}			
				}	
			}
		}
	}
}