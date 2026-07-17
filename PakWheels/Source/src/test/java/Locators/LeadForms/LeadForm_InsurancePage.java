package Locators.LeadForms;
import java.util.List;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Locators.Base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class LeadForm_InsurancePage extends Leadforms
{
	AppiumDriver<MobileElement> driver= null;
	ExtentTest test= null;	
	WebDriverWait wait= null;
	JSONObject Object = null;
	public LeadForm_InsurancePage(AppiumDriver<MobileElement> driver, ExtentTest test, WebDriverWait wait, JSONObject Object) 
	{
		super(driver, test, wait, Object);
		this.driver= driver;
		this.test= test;
		this.wait= wait;
		this.Object= Object;
	}

	public void NavigateToInsurancePage() throws Exception{
		
		String  Navigate = (String) Object.get("Navigation");
		if(Navigate.equalsIgnoreCase("Product"))
		{
		DataPicker("Verify that user can tap on Auto Services button to open Drop-Down", "Auto Services", 0);  
		DataPicker("Verify that user can navigate to Car Insurance page by cliking on Insurance button", "Car Insurance", 0);
		test.log(Status.INFO, "User is navigated to Insurance page");	
		}
	}
	
	public void SelectCarInformation() throws Exception
	{	
	
		DataPicker("Verify that user can select car model button to open model listing", "Make/model/version", 0);			
		
		JSONObject CarObj = (JSONObject) Object.get("CarInformation");
		String MakeModel= (String) CarObj.get("Model");
		String Year= (String) CarObj.get("Year");
		String Version= (String) CarObj.get("Version");
		
		if (Year != null && !Year.isEmpty()) 
			DataPicker("Verify that user can select car year <br>Test Data: <b>"+Year+"</b>", Year, 0);
					
	    //driver.findElement(By.xpath("//android.widget.TextView[@text='Search']")).sendKeys(MakeModel);
	 // Locate the search EditText field
	    MobileElement searchField = (MobileElement) driver.findElement(By.className("android.widget.EditText"));

	    // Clear and type model name
	    searchField.clear();
	    searchField.sendKeys(MakeModel);
		DataPicker("Verify that user can select car Make and Model <br>Test Data: <b>"+MakeModel+"</b>", MakeModel, 0);   

		if (Version != null && !Version.isEmpty()) 
			
			// Locate the search EditText field
		    searchField = (MobileElement) driver.findElement(By.className("android.widget.EditText"));

			// Clear and type model name
			searchField.clear();
			searchField.sendKeys(Version);
			//driver.findElement(By.xpath("//android.widget.TextView[@text='Search']")).sendKeys(Version);
			DataPicker("Verify that user can select car Version <br>Test Data: <b>"+Version+"</b>", Version, 0);    
		
	}
	
	
	public void EnterPrice() throws Exception{

		String PriceValue= (String) Object.get("Price");
		//SendText("Verify that user can enter Price <br>Test Data: <b>"+PriceValue+"</b>", "Set a price", PriceValue);
		driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.EditText[2]")).sendKeys(PriceValue);
		test.log(Status.PASS,"Verify that user can enter Price <br>Test Data: <b>"+PriceValue+"</b>");
	}
	
	public void ClickOnShowPlans() throws Exception{

		DataPicker("Verify that user can tap on <b>Apply Easily Now</b> button", "Apply Easily Now", 0);		
	}
	
	public void SelectBank() throws Exception
	{
		JSONObject BankObj = (JSONObject) Object.get("BankDetails");
		String BankName= (String) BankObj.get("BankName");
		String AddTracker= (String) BankObj.get("AddTracker");
		
		test.log(Status.INFO, "User is navigated to insurance packages listing page");
	
		MobileElement Parent = null;
		try {
			Scroll(BankName);
			Parent= driver.findElement(By.xpath("//android.widget.TextView[@text='"+BankName+"']/../.."));	// Go to Bank class
			test.log(Status.PASS, "Verify that user can find Bank in the list");
		}catch(org.openqa.selenium.NoSuchElementException e){
			test.log(Status.FAIL, "No such bank exists in the list");
			throw new Exception("Element not found");
		}
		
		if(AddTracker.equals("Yes"))
		{
			try {
				Parent.findElement(By.id("com.pakwheels.staging:id/cbAddTrakcer")).click();    // From bank, click on its tracker
				test.log(Status.PASS, "Verify that user can add Tracker");
			}catch(org.openqa.selenium.NoSuchElementException e){ 		
				test.log(Status.FAIL, "Verify that user cant add Tracker");
				throw new Exception("Element not found");
			}		
		}
		
		try {
			driver.findElement(By.xpath("//android.widget.TextView[@text='"+BankName+"']")).click();	
			test.log(Status.PASS, "Verify that user can select Bank");	
			test.log(Status.INFO, "User is navigated to Bank Detail form");
		}catch(org.openqa.selenium.NoSuchElementException e){ 		
			test.log(Status.FAIL, "Verify that user can select Bank");	
			throw new Exception("Element not found");
		}
		
		Thread.sleep(1000);
		DataPicker("Verify that user can tap on Apply Now button", "Apply Now", 2);
	}
	
	public void FillForm() throws Exception{
		
		if(Base.Testing.equalsIgnoreCase("System"))
		{
			test.log(Status.PASS, "<h2>Insurance Apply Now Form</h2>");
			
			test.log(Status.PASS, "<b>Verify that following Fields are showing</b>");
			List<MobileElement> field_elements = driver.findElementsById("com.pakwheels.staging:id/tv_input_module_title");
               
	        String[] field_texts = new String[field_elements.size()];
	        for (int i = 0; i < field_elements.size(); i++) {
	        	field_texts[i] = field_elements.get(i).getText();
	        	test.log(Status.PASS,"<b>Field</b>  "+field_texts[i]);
	        }
			
			SelectContinueButton();
			
			List<MobileElement> Validations = driver.findElements(By.id("com.pakwheels.staging:id/tv_validation_error"));
			for(int i=0;i<Validations.size();i++)
			{
				if((Validations.get(i).getText()).equalsIgnoreCase("Please enter your full name"))
				{
					test.log(Status.PASS, "Verified That Validation Message is showing for Your Full Name field "+Validations.get(i).getText());
				}
				if((Validations.get(i).getText()).equalsIgnoreCase("Enter a valid phone number"))
				{
					test.log(Status.PASS, "Verified That Validation Message is showing for Phone number  field "+Validations.get(i).getText());
				}
				if((Validations.get(i).getText()).equalsIgnoreCase("Please tell us where you live"))
				{
					test.log(Status.PASS, "Verified That Validation Message is showing for Location field "+Validations.get(i).getText());
				}
				if((Validations.get(i).getText()).equalsIgnoreCase("Please enter CNIC number with valid format\n"+ " xxxxx-xxxxxxx-x."))
				{
					test.log(Status.PASS, "Verified That Validation Message is showing for CNIC field "+Validations.get(i).getText());
				}
			}
		}
		EnterContactInformation();
		
		String City= (String) Object.get("City");
		DataPicker("Verify that user can select city button", "Please select your city", 4);
		SendText("Verify that user can enter text to refine City search", "Type to refine search", City);		
		DataPicker("Verify that user can select City <br>Test Data: <b>"+City+"</b>", City, 0);
		
		String CNIC= (String) Object.get("CNIC");
		SendText("Verify that user can enter CNIC <br>Test Data: <b>"+CNIC+"</b>", "xxxxx-xxxxxxx-x", CNIC);	
		
		JSONObject RegistrationObj = (JSONObject) Object.get("Registration");
		String RegistrationStatus= (String) RegistrationObj.get("RegistrationStatus");
		String RegisterationNumber= (String) RegistrationObj.get("RegisterationNumber");
		DataPicker("Verify that user can select registration status", RegistrationStatus, 3);
		if(RegistrationStatus.equalsIgnoreCase("Registered"))
			SendText("Verify that user can enter Registration number <br>Test Data: <b>"+RegisterationNumber+"</b>", "Enter Registration Number", RegisterationNumber);
	}

	public void VerifyInsuranceLandingPage() throws Exception {
		String  VerifyLanding = (String) Object.get("Flow");	
	try {
		if(Base.Testing.equalsIgnoreCase("System") && VerifyLanding!=null && VerifyLanding.equals("Yes"))
		{
			test.log(Status.PASS, "<h2>Insurance Landing Page</h2>");
			
			String h1=driver.findElement(By.xpath("(//android.widget.TextView[@text='PakWheels Car Insurance'])[2]")).getText();
			if(h1.equalsIgnoreCase("PakWheels Car Insurance"))
			{
				test.log(Status.PASS, "Verified That H1 is showing."+h1);
			}
			
			
			Boolean Car_field=driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.EditText[1]")).isDisplayed();
			Boolean Pricefield=driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.EditText[2]")).isDisplayed();
			if(Car_field && Pricefield)
			{
				test.log(Status.PASS, "Verified That Car Model And Price Field are visible");
				driver.findElement(By.xpath("//android.widget.TextView[@text='Apply Easily Now']")).click();
				test.log(Status.PASS, "Verified that Apply Easily Now button showing and user clicks on it.");
				
				List<MobileElement> Validations = driver.findElements(By.xpath("//android.widget.TextView"));
				for(int i=0;i<Validations.size();i++)
				{
					if((Validations.get(i).getText()).equalsIgnoreCase("Please select car information"))
					{
						test.log(Status.PASS, "Verified That Validation Message is showing for Car Model field "+Validations.get(i).getText());
					}
					if((Validations.get(i).getText()).equalsIgnoreCase("Please enter price"))
					{
						test.log(Status.PASS, "Verified That Validation Message is showing for Price field "+Validations.get(i).getText());
					}

				}
			}
			
			DataPicker("Verify that Why Choose PakWheels Car Insurance? section is showing", "Why Choose PakWheels Car Insurance?",0);
			DataPicker("Verify that Car Insurance Steps section is showing", "Car Insurance Steps",0);
			
			DataPicker("Verify that Current car insurance rates in Pakistan is showing", "Current car insurance rates in Pakistan",0);
			List<MobileElement> Insurance_Companies = driver.findElements(By.xpath("//android.widget.ScrollView/android.view.View[2]/android.view.View"));
			String Company=Insurance_Companies.get(1).getText();
			DataPicker("Verify that USer Clicks on Show More button for Insurance Comapnies Section", "Show More",0);
			DataPicker("Verify that  on Show Less is showing on clicking Show More", "Show Less",0);
			Insurance_Companies.get(1).click();
			test.log(Status.PASS, "Verified that user clicks on Insurance Company."+Company);
			Thread.sleep(3000);
			DataPicker("Verify that user redirected to Insurance Company Page ", Company,0);
			DataPicker("Verify that user Clicks Apply For Car Insuranc button on Insurance Company Page", "Apply for car insurance",0);
			DataPicker("Verify that user redirected Back to Insurance Landing Page ", "Pakwheels Car Insurance",0);
			
			
		    
			
			DataPicker("Verify that Car insurance success stories section is showing", "Car Insurance Success Stories",0);
			/*DataPicker("Verify that Car Insurance News section is showing", "Car Insurance News",0);
			Thread.sleep(2000);
			DataPicker("Verify that View All Button is showing and User Clicks on View All Button", "View All",0);
			Thread.sleep(2000);
			DataPicker("Verify that User Redirected to Blogs Page", "Detail",0);
			Thread.sleep(2000);
			driver.navigate().back();*/
			
			
			DataPicker("Verify that FAQs section is showing", "Car Insurance FAQs",0);
			
			/*DataPicker("Verify that PakWheels Offerings is showing", "PakWheels Offerings",0);
			//ScrollToEnd();
			List<MobileElement> Offerings = driver.findElements(By.id("com.pakwheels.staging:id/tvOurProductTitle"));
			String navigation_Offering=Offerings.get(0).getText();
			System.out.println(navigation_Offering+" " +Offerings.size());
			Offerings.get(0).click();
			Thread.sleep(2500);
			String heading=driver.findElement(By.id("com.pakwheels.staging:id/header_heading")).getText();
			if(heading.equalsIgnoreCase(navigation_Offering))
			{
				test.log(Status.PASS, "Verified That User Navigated to ."+heading);
				driver.navigate().back();
			}*/		
		}	
		
	  }
	catch(org.openqa.selenium.NoSuchElementException e){ 		
		test.log(Status.FAIL, "Error Message : "+e);	
		throw new Exception("Element not found");
	}
	}

	public void NavigatetoNewCarModel() throws Exception {
		String  Navigate = (String) Object.get("Navigation");
		if(Navigate.equalsIgnoreCase("NewCarModel"))
		{
		  NavigateToNewCarSearch();
		  SelectCarMake();
		  SelectCarModel();
		}
		
	}

	public void InsurancethroughProductPage() throws Exception {
		String  Navigate = (String) Object.get("Navigation");
		if(Navigate.equalsIgnoreCase("Product"))
		{
		  VerifyInsuranceLandingPage();
		  SelectCarInformation();
		  EnterPrice();
		  ClickOnShowPlans();   
		  FillBasicInfoInsurance();
		  EnterVerificationCode();
		  SelectBank();
		}
		
	}
	private void VerifyInsuranceBottomSheet() throws Exception
	{
		if(driver.findElement(By.xpath("//android.widget.TextView[@text='Explore Insurance Options']")).isDisplayed())
		{
			test.log(Status.PASS, "Verified that Explore Insrance Options Bottom sheet Appears");

			List<MobileElement> Banks = driver.findElements(By.id("com.pakwheels.staging:id/item_image"));
			for(int i=0;i<Banks.size();i++)
			{
				test.log(Status.PASS, "Verified that Explore Banks listing is showing on Explore Inurance Options bottom Sheet");
				Banks.get(i).click();
				test.log(Status.PASS, "Verified that User can select banks from bank listing");
			}
		
		    String Tracker_Cost=driver.findElement(By.id("com.pakwheels.staging:id/tv_tracker_cost")).getText();
		    if(!Tracker_Cost.equalsIgnoreCase("Add Tracker"))
		    {	
	          String Track = Tracker_Cost.replaceAll("[^\\d,]", "");
	          String cleanNumber = Track.replaceAll(",", "");
	          int Tracker_Int = Integer.parseInt(cleanNumber);
	          
	          test.log(Status.PASS, "Verified that Tracker Cost is showing "+Tracker_Cost);
	          
		    }
	        
		    DataPicker("Verify that Yearly Payment is showing", "Yearly Payment",0);
		    DataPicker("Verify that Insurance Rate is showing", "Insurance Rate",0);
		    
		    String InstallmentValue=driver.findElement(By.id("com.pakwheels.staging:id/monthly_installment_value")).getText();
		    String DownPaymentValue=driver.findElement(By.id("com.pakwheels.staging:id/down_payment_value")).getText();
		    
		    String numericAmountString = InstallmentValue.replaceAll("[^\\d]", "");
		    String numericPercentageString = DownPaymentValue.replaceAll("[^\\d.]", "");
		    
		    
		    int Installment_Int = Integer.parseInt(numericAmountString);
		    float DownPayment_Int = Float.parseFloat(numericPercentageString);
		    
			driver.findElement(By.id("com.pakwheels.staging:id/cbAddTrakcer")).click();
			
		    InstallmentValue=driver.findElement(By.id("com.pakwheels.staging:id/monthly_installment_value")).getText();
		    DownPaymentValue=driver.findElement(By.id("com.pakwheels.staging:id/down_payment_value")).getText();
		    
		    numericAmountString = InstallmentValue.replaceAll("[^\\d]", "");
		    numericPercentageString = DownPaymentValue.replaceAll("[^\\d.]", "");
		    
		    int Installment_Tracker = Integer.parseInt(numericAmountString);
		    float DownPayment_Tracker = Float.parseFloat(numericPercentageString);
		    
		    if(Installment_Int<Installment_Tracker &&  DownPayment_Int<DownPayment_Tracker)
		    {
		    	test.log(Status.PASS, "Verified that After Tracker is on then Values gets changed. Yearly Payment : " +Installment_Int+" Changes to "+Installment_Tracker+" DownPayment : "+ DownPayment_Int+" Changes to "+DownPayment_Tracker );
		    }
		}

	}
	public void InsurancethroughModelPage() throws Exception {
		
		String  Navigate = (String) Object.get("Navigation");
		JSONObject CarObj = (JSONObject) Object.get("CarInformation");
		String Model= (String) CarObj.get("Model");
		String flow=(String) Object.get("Flow");
		if(Navigate.equalsIgnoreCase("NewCarModel"))
		{
			ScrollSecondView("Explore car insurance");
			DataPicker("Verify that finance Widget is showing", "Explore car insurance",0);
			VerifyInsuranceBottomSheet();
			if(flow!=null && !flow.isEmpty() && flow.equalsIgnoreCase("View Plan"))
			{
				DataPicker("Verify that View Plan Button is showing and user clicks on View Plan Button", "View Plan",2);
				DataPicker("Verify that user can tap on Apply Now button", "Apply Now", 2);
				
			}
			if(flow!=null && !flow.isEmpty() && flow.equalsIgnoreCase("Show More Plans"))
			{
				DataPicker("Verify that View Plan Button is showing and user clicks on View Plan Button", "Show More Plans",2);
				SelectBank();
			}
		}
		
	}

	public void InsurancethroughUsedCarAd() throws Exception {
		String  Navigate = (String) Object.get("Navigation");
		String flow=(String) Object.get("Flow");
		if(Navigate.equalsIgnoreCase("UsedCarAd"))
		{
			
			Thread.sleep(2000);
			ClickHomeButton();
			NavigateToLandingScreen();
			DataPicker("Verify that user can select <b>Advanced Search</b>", "ADVANCED SEARCH", 0);
		    test.log(Status.INFO, "User is navigated to Advance Search Filters Screen");
		    Thread.sleep(2000);

		    JSONObject CarObj = (JSONObject) Object.get("CarInformation");
			String MakeModel= (String) CarObj.get("Model");
			String Version= (String) CarObj.get("Version");
			
			System.out.println(MakeModel+" Model "+Version);
			
			if (!MakeModel.isEmpty()) 
			{
				DataPicker("Verify that user can select car model button to open model listing", "Car Model", 0);
				SendText("Verify that user can enter text to refine Make Model search", "Type to refine search", MakeModel);
				DataPicker("Verify that user can select car Make and Model <br>Test Data: <b>"+MakeModel+"</b>", MakeModel, 0); 
				
				if (Version != null && !Version.isEmpty()) 
				{	
				  SendText("Verify that user can enter text to refine Version search", "Type to refine search", Version);
				  DataPicker("Verify that user can select car Version <br>Test Data: <b>"+Version+"</b>", Version, 0); 
				}
			}
		    
			DataPicker("Verify that user can select <b>Apply Filters</b> button", "Apply Filters", 2);
			Thread.sleep(2000);
			SelectAd();
			Thread.sleep(5000);
			try
			{
			driver.findElement(By.id("com.pakwheels.staging:id/mb_finance_button")).click();
			test.log(Status.INFO, "User is Clicks on Finance animated button showing on image Gallery");
			}
			catch(org.openqa.selenium.NoSuchElementException e){ 		
				test.log(Status.FAIL, "Error Message : "+e);
				ScrollForward();
				Scroll("Explore car insurance");
				throw new Exception("Element not found");
			}
			
			
			DataPicker("Verify that Insurance Widget is showing", "Explore car insurance",0);
			VerifyInsuranceBottomSheet();
			if(flow!=null && !flow.isEmpty() && flow.equalsIgnoreCase("View Plan"))
			{
				DataPicker("Verify that View Plan Button is showing and user clicks on View Plan Button", "View Plan",2);
				DataPicker("Verify that user can tap on Apply Now button", "Apply Now", 2);
				
			}
			if(flow!=null && !flow.isEmpty() && flow.equalsIgnoreCase("Show More Plans"))
			{
				DataPicker("Verify that View Plan Button is showing and user clicks on View Plan Button", "Show More Plans",2);
				SelectBank();
			}
		}
	}

	public void InsurancethroughVersionPage() throws Exception {
		String  Navigate = (String) Object.get("Navigation");
		String flow=(String) Object.get("Flow");
		JSONObject CarObj = (JSONObject) Object.get("CarInformation");
		String Model=(String) CarObj.get("Model");
		String Version= (String) CarObj.get("Version");
		//

		if(Navigate.equalsIgnoreCase("NewCarVersion") && Base.Testing.equalsIgnoreCase("System"))
		{
		  NavigateToNewCarSearch();
		  SelectCarMake();
		  SelectCarModel();
		  Thread.sleep(2000);
		  System.out.println("Variants");
		  ScrollSecondView("Variants");
		  Thread.sleep(2000);
		  if (Version != null && !Version.isEmpty()) 
		  {  DataPicker("Verify that user can select car Version <br>Test Data: <b>"+Version+"</b>", Version, 0);    				
		     test.log(Status.PASS, "Verified That User Clicks on Version." +Version);
		     
		     Thread.sleep(15000);
		     String Car=driver.findElement(By.id("com.pakwheels.staging:id/tv_model_name")).getText();
		     System.out.print("Car Name of Version"+Car);
		     if(Car.contains(Version))
		     {
		    	 test.log(Status.PASS, "Verified That User redirected to Version Page" +Car);
		     }
		  }
		   Thread.sleep(2000);
		   System.out.println("Version"+Version);
		   ScrollSecondView("Explore car insurance");
		   DataPicker("Verify that finance Widget is showing", "Explore car insurance",0);
		   VerifyInsuranceBottomSheet();
			if(flow!=null && !flow.isEmpty() && flow.equalsIgnoreCase("View Plan"))
			{
				DataPicker("Verify that View Plan Button is showing and user clicks on View Plan Button", "View Plan",2);
				DataPicker("Verify that user can tap on Apply Now button", "Apply Now", 2);
				
			}
			if(flow!=null && !flow.isEmpty() && flow.equalsIgnoreCase("Show More Plans"))
			{
				DataPicker("Verify that View Plan Button is showing and user clicks on View Plan Button", "Show More Plans",2);
				SelectBank();
			}
		}
		
	}

	public void SummaryScreen() throws Exception {
		test.log(Status.PASS, "<h2>Summary Screen</h2>");
		String  Navigate = (String) Object.get("Navigation");
		if(Navigate.equalsIgnoreCase("Product") && (Base.Testing.equalsIgnoreCase("System")))
		{
			if((driver.findElement(By.id("com.pakwheels.staging:id/lbl_screen_title")).getText()).equalsIgnoreCase("Applied successfully"))
			{
				test.log(Status.PASS, "Verified That Applied Succesfully Text is showing on Summary Screen");
				DataPicker("Verify that Next Steps Widget is showing on Summary Screen", "Next steps",0);
				DataPicker("Verify that Partner Workshop Banner  Widget is showing on Summary Screen", "Pakwheels partner workshop",0);
				driver.findElement(By.id("com.pakwheels.staging:id/action_close")).click();
				test.log(Status.PASS, "Verified that user clicks on Cross icon on Summary Screen");
				Thread.sleep(2000);
				//DataPicker("Verify that User Redirected to Home Screen ","Used Cars",3);	
			}
		}
		else
		{
			if((driver.findElement(By.id("com.pakwheels.staging:id/lbl_screen_title")).getText()).equalsIgnoreCase("Applied successfully"))
			{
				test.log(Status.PASS, "Verified That USer Redirected to Summary Screen");
			}
		}
	}


}
