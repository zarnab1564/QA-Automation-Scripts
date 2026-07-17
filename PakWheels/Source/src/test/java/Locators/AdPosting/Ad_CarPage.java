package Locators.AdPosting;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import Locators.Base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import Locators.LeadForms.LeadForm_SIFMPage;

public class Ad_CarPage extends AdPosting
{	
	AppiumDriver<MobileElement> driver= null;
	ExtentTest test= null;
	WebDriverWait wait= null;
	JSONObject Object = null;
	LeadForm_SIFMPage SIFM= new LeadForm_SIFMPage(driver, test, wait, Object);
	
	public Ad_CarPage(AppiumDriver<MobileElement> driver, ExtentTest test, WebDriverWait wait, JSONObject Object) 
	{
		super(driver, test, wait, Object);
		this.driver= driver;
		this.test= test;
		this.wait= wait;
		this.Object= Object;
	}

	public void NavigateToCarAdPost() throws Exception
	{	
		//DataPicker("Verify that user can Select Ad Type <br>Test Data: <b>Car</b>", "My Ads", 0);  	
		test.log(Status.INFO, "<h3><b>Sell form </b></h3>");
		ClickSellButton();                       
		DataPicker("Verify that user can Select Ad Type <br>Test Data: <b>Car</b>", "Car", 0);  	
		DataPicker("Verify that user can select option for Car Ad (Test Data: Post your ad!)", "Post your ad", 0);
	}
	
	public void SelectCarYearMakeModelVersion() throws Exception
	{	   
		JSONObject CarObj = (JSONObject) Object.get("CarInformation");
		String MakeModel= (String) CarObj.get("Model");
		String Year= (String) CarObj.get("Year");
		String FuelType= (String) CarObj.get("FuelType");
		String Transmission= (String) CarObj.get("Transmission");
		String Version= (String) CarObj.get("Version");
		
		if (!MakeModel.isEmpty()) 
		{
			DataPicker("Verify that user can select car model button to open model listing", "Car Model", 0);	

			if (Year != null && !Year.isEmpty()) 
				DataPicker("Verify that user can select car year <br>Test Data: <b>"+Year+"</b>", Year, 0);
						
			SendText("Verify that user can enter text to refine Make Model search", "Type to refine search", MakeModel);
			DataPicker("Verify that user can select car Make and Model <br>Test Data: <b>"+MakeModel+"</b>", MakeModel, 0);   
		
			DataPicker("Verify that user can select Fuel type <br>Test Data: <b>"+FuelType+"</b>", FuelType, 0);                             
			DataPicker("Verify that user can select Transmission <br>Test Data: <b>"+Transmission+"</b>", Transmission, 0); 	

			if (Version != null && !Version.isEmpty()) 
				DataPicker("Verify that user can select car Version <br>Test Data: <b>"+Version+"</b>", Version, 0);    		
		}
	}
	
	public void EnterSuggestions() throws Exception
	{
		JSONObject CarObj = (JSONObject) Object.get("Suggestion");
		 String Description= (String) CarObj.get("Description");
		 String NumberOfSuggestions= (String) CarObj.get("NumberOfSuggestions");
		 
		SendText("Verify that user can enter Description <br>Test Data: <b>"+Description+"</b>", "For Example: Alloy Rims, First Owner, etc.", Description);	  
		DataPicker("Verify that user can tap on View all Suggestions button", "View All Suggestions", 0);
		
		int Number= Integer.valueOf(NumberOfSuggestions);
		try {
			for (int i=0; i<Number; i++)
				driver.findElements(By.id("com.pakwheels.staging:id/checkbx_features")).get(i).click();                                      			
			test.log(Status.PASS, "Verify that user can select Suggestion <br>Total Suggestions: <b>"+Number+"</b>");
		}catch(org.openqa.selenium.NoSuchElementException e){
			test.log(Status.FAIL, "Verify that user can select Suggestion <br>Total Suggestions: <b>"+Number+"</b>");
		}		
		DataPicker("Verify that user can select click on Done button", "Done", 2);		
	}	
			
	public void SelectCarColor() throws Exception
	{		   
		String Color= (String) Object.get("Color");
		DataPicker("Verify that user can select body <b>Color</b> button to open color listing", "Body Color", 0);
		DataPicker("Verify that user can select body color <br>Test Data: <b>"+Color+"</b>", Color, 0);
	}
	
	public void SelectAdditionalInformation() throws Exception
	{
		JSONObject InfoObj = (JSONObject) Object.get("AdditionalInfo");
		 String FuelType= (String) InfoObj.get("FuelType");
		 String Capacity= (String) InfoObj.get("Capacity");
		 String Transmission= (String) InfoObj.get("Transmission");
		 String Assembly= (String) InfoObj.get("Assembly");
		 String NumberOfFeatures= (String) InfoObj.get("NumberOfFeatures");
		
		DataPicker("Verify that user can select Fuel type button to open engine listing", "Fuel Type", 0);
		DataPicker("Verify that user can select Fuel type <br>Test Data: <b>"+FuelType+"</b>", FuelType, 0);                             
		SendText("Verify that user can enter engine Capacity <br>Test Data: <b>"+Capacity+"</b>", "Specify Engine Capacity", Capacity);
		DataPicker("Verify that user can select Transmission <br>Test Data: <b>"+Transmission+"</b>", Transmission, 3); 		
		DataPicker("Verify that user can select Assembly <br>Test Data: <b>"+Assembly+"</b>", Assembly, 3);      
		
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
	
	public void AttachAuctionSheet() throws Exception
	{
		if (Base.Testing.equalsIgnoreCase("System") && isActive== true && inReview== false) 
		{	
			JSONObject InfoObj = (JSONObject) Object.get("AdditionalInfo");
			String Assembly= (String) InfoObj.get("Assembly");
			
			if(Assembly.equalsIgnoreCase("Imported"))
			{
				test.log(Status.INFO, "<h3><b>Auction Sheet</b></h3>");
				JSONObject AuctionObject= (JSONObject) Object.get("AuctionSheet");
				OpenAdDetail();
				ScrollSecondView("Attach Original Auction Sheet");
				DataPicker("Verify that user can click on <b> Attach auction sheet button </b>", "Attach Original Auction Sheet", 0);
				
				String ChassisNumber = (String) AuctionObject.get("ChassisNumber");
				SendText("Verify that user can enter text chassis number", "Enter Chassis number", ChassisNumber);	
				driver.hideKeyboard();
				DataPicker("Verify that user can click on <b>Get Auction Sheet</b> Button", "Get Verified Auction Sheet", 2);	
				
				DataPicker("Verify that user can click on <b>Buy Now</b> button", "Buy Now", 2);
				Thread.sleep(2000);				
				EnterVerificationCode();	
				
				String AuctionDiscountCode= (String) AuctionObject.get("AuctionDiscountCode");
				ApplyDiscountCode(AuctionDiscountCode);
				DataPicker("Verify that User can select <b>Checkout</b> Button", "Checkout", 2);
				
				Boolean status= false; 
				String PaymenMethod= (String) AuctionObject.get("PaymentMethod");
				status= PaymentFlow(PaymenMethod);
				
				if(status== true)
					test.log(Status.INFO, "Auction sheet has been attached");
				else if(status== false)
					test.log(Status.FAIL, "Auction sheet failed to attach");
			}
		}
	}
	
	public void EditAd() throws Exception
	{
		if (Base.Testing.equalsIgnoreCase("System") && isActive== true && inReview== false) 
		{
			JSONObject CarObj = (JSONObject) Object.get("CarInformation");
			String Make= (String) CarObj.get("Make");
			String MakeModel= (String) CarObj.get("MakeModel");
		 	String Version= (String) CarObj.get("Version");
			DataPicker("Verify that user can click on ad", Make+" "+MakeModel+" "+Version, 0);	
			DataPicker("Verify that user can tap on <b>Edit</b> Button", "Edit", 2);
			
			JSONObject editObj = (JSONObject) Object.get("Edit");
		 	JSONObject locationObj = (JSONObject) editObj.get("Location");
			String City= (String) locationObj.get("City");
			String CityArea= (String) locationObj.get("CityArea");
			if (!City.isEmpty())
			 {
				DataPicker("Verify that user can select <b>Location</b> button", "Location", 0);
				SendText("Verify that user can enter text to refine City search", "Type to refine search", City);		
				DataPicker("Verify that user can select City <br>Test Data: <b>"+City+"</b>", City, 0);

				if (CityArea != null && !CityArea.isEmpty())
				{
					SendText("Verify that user can enter text to refine City Area search", "Type to refine search", CityArea);		
					DataPicker("Verify that user can select City Area <br>Test Data: <b>"+CityArea+"</b>", CityArea, 0);
				}	
			}
			
			String Version1= (String) editObj.get("Version");
			if (Version1 != null && !Version1.isEmpty()) 
			{
				DataPicker("Verify that user can select car Version <br>Test Data: <b>"+Version1+"</b>", Version1, 0); 
			}
			
			String RegisteredIn= (String) editObj.get("RegisteredIn");
			if (RegisteredIn != null && !RegisteredIn.isEmpty()) 
			{
				DataPicker("Verify that user can select <b>Registered-In</b> button to open city listing", "Registered In", 0);       
				SendText("Verify that user can enter text to refine Registered City search", "Type to refine search", RegisteredIn);		
				DataPicker("Verify that user can select Registered City <br>Test Data: <b>"+RegisteredIn+"</b>", RegisteredIn, 0);
			}
			
			String Color= (String) editObj.get("Color");
			if (Color != null && !Color.isEmpty()) 
			{
				DataPicker("Verify that user can select body <b>Color</b> button to open color listing", "Body Color", 0);
				DataPicker("Verify that user can select body color <br>Test Data: <b>"+Color+"</b>", Color, 0);
			}
			
			DataPicker("Verify that user can tap on <b>Update</b> Button", "Update", 2);
		}
	}
	
	public void SIFMrequest() throws Exception
	{ 	
		if (Base.Testing.equalsIgnoreCase("System") && isActive== true && inReview== false) 
		{
			test.log(Status.INFO, "<h3><b>Sell It For Me</b></h3>");
			OpenAdDetail();
			JSONObject SIFMObj = (JSONObject) Object.get("SIFM");
			
			ScrollSecondView("Register Now");
			DataPicker("Verify that user can tap on <b>Register Now</b> button for SIFM request", "Register Now", 2);
			DataPicker("Verify that user can tap on Register Now button on SIFM popup", "Register Now", 2);
			DataPicker("Verify that user can tap on <b>Continue</b> button", "Continue", 2);
			
			JSONObject locationObj = (JSONObject) SIFMObj.get("Location");
			String CityArea= (String) locationObj.get("CityArea");
			DataPicker("Verify that user can select City Area button", "City Area", 0);
			SendText("Verify that user can enter text to refine City Area search", "Type to refine search", CityArea);		
			DataPicker("Verify that user can select City Area <br>Test Data: <b>"+CityArea+"</b>", CityArea, 0);
			
			String Address= (String) SIFMObj.get("Address");
			SendText("Verify that user can enter Inspection Address <br>Test Data: <b>"+Address+"</b>", "House No/Building No, Street, Area", Address);
		
			SelectInspectionSlot();		
			
			try{
				driver.findElement(By.id("com.pakwheels.staging:id/cb_agree_to_terms")).click();
				test.log(Status.PASS, "Verify that user can select Check box for Terms & Services");
			}catch(org.openqa.selenium.NoSuchElementException e){ 				
				test.log(Status.FAIL, "Verify that user can select Check box for Terms & Services");
				throw new Exception("Element not found");			
			}
			DataPicker("Verify that user can select continue button", "Continue", 2);
			Thread.sleep(2000);
			
			DataPicker("Verify that User can select <b>Checkout</b> Button", "Checkout", 2);
			Boolean status= false; 
			String PaymenMethod= (String) SIFMObj.get("PaymentMethod");
			status= PaymentFlow(PaymenMethod);
			
			if(status== true)
				test.log(Status.INFO, "Ad registerted for SIFM");
			else if(status== false)
				test.log(Status.FAIL, "Ad failed to register for SIFM");
		}
	}

	public void InspectionRequest() throws Exception
	{
		if (Base.Testing.equalsIgnoreCase("System") && isActive== true && inReview== false) 
		{
			test.log(Status.INFO, "<h3><b>Inspection request</b></h3>");
			OpenAdDetail();
			JSONObject InspectionObj = (JSONObject) Object.get("Inspection");
			
			ScrollSecondView("PakWheels Car Inspection");
			DataPicker("Verify that user can tap on <b>Pakwheels car inspection</b> button", "PakWheels Car Inspection", 0);
			DataPicker("Verify that user can tap on Apply Now button on inspection popup", "Apply Now", 2);
			
			JSONObject locationObj = (JSONObject) InspectionObj.get("Location");
			String City= (String) locationObj.get("City");
			String CityArea= (String) locationObj.get("CityArea");
			
			if (!City.isEmpty())
			{
				 DataPicker("Verify that user can select City button to open cities listing\"", "City", 0);
				SendText("Verify that user can enter text to refine City search", "Type to refine search", City);		
				DataPicker("Verify that user can select City <br>Test Data: <b>"+City+"</b>", City, 0);
			}
			DataPicker("Verify that user can tap on <b>Continue</b> button", "Continue", 2);
			 
			DataPicker("Verify that user can select City Area button", "City Area", 0);
			SendText("Verify that user can enter text to refine City Area search", "Type to refine search", CityArea);		
			DataPicker("Verify that user can select City Area <br>Test Data: <b>"+CityArea+"</b>", CityArea, 0);
			
			String Address= (String) InspectionObj.get("Address");
			SendText("Verify that user can enter Inspection Address <br>Test Data: <b>"+Address+"</b>", "House No/Building No, Street, Area", Address);
		
			SelectInspectionSlot();		
			
			DataPicker("Verify that user can select confirm booking button", "Confirm Booking", 2);
			Thread.sleep(2000);
			
			DataPicker("Verify that User can select <b>Checkout</b> Button", "Checkout", 2);
			Boolean status= false; 
			String PaymenMethod= (String) InspectionObj.get("PaymentMethod");
			status= PaymentFlow(PaymenMethod);
			
			if(status== true)
				test.log(Status.INFO, "Ad registerted for Inspection");
			else if(status== false)
				test.log(Status.FAIL, "Ad failed to register for Inspection");
		}
		
	}
	
	
}