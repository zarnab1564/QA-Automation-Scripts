package Locators.LeadForms;
import java.util.List;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Locators.Base;
import Locators.BaseHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Leadforms extends BaseHelper{
	
	AppiumDriver<MobileElement> driver= null;
	ExtentTest test= null;
	WebDriverWait wait= null;
	JSONObject Object = null;
	
	public Leadforms(AppiumDriver<MobileElement> driver, ExtentTest test, WebDriverWait wait, JSONObject Object) 
	{
		super(driver, test, wait, Object);
		this.driver= driver;
		this.test= test;
		this.wait= wait;
		this.Object= Object;
	}
	public void EnterContactInformation() throws InterruptedException
	{
		SendText("Verify that user can enter Name <br>Test Data: <b>"+Base.Name+"</b>", "Enter full name", Base.Name);
		SendText("Verify that user can enter Mobile Number <br>Test Data: <b>"+Base.MobileNumber+"</b>", "03xxxxxxxxx", Base.MobileNumber);
		driver.hideKeyboard();
	}
	
	public void SelectCity() throws Exception
	{
		 JSONObject locationObj = (JSONObject) Object.get("Location");
		 String City= (String) locationObj.get("City");
		
		 if (!City.isEmpty())
		 {
			DataPicker("Verify that user can select city button to open cities listing", "Select city", 4);
			SendText("Verify that user can enter text to refine City search", "Type to refine search", City);		
			DataPicker("Verify that user can select City <br>Test Data: <b>"+City+"</b>", City, 0);
		}
	}
	public void SelectLocation() throws Exception
	{
		 JSONObject locationObj = (JSONObject) Object.get("Location");
		 String City= (String) locationObj.get("City");
		
		if (!City.isEmpty())
		 {
			DataPicker("Verify that user can select <b>Location</b> button", "Location", 0);
			SendText("Verify that user can enter text to refine City search", "Type to refine search", City);		
			DataPicker("Verify that user can select City <br>Test Data: <b>"+City+"</b>", City, 0);
	
		}

	}
	
	public void SelectCarYearMakeModelVersion() throws Exception
	{			
		JSONObject CarObj = (JSONObject) Object.get("CarInformation");
		String MakeModel= (String) CarObj.get("MakeModel");
		String Year= (String) CarObj.get("Year");
		String Version= (String) CarObj.get("Version");
		
		if (Year != null && !Year.isEmpty()) 
			DataPicker("Verify that user can select car year <br>Test Data: <b>"+Year+"</b>", Year, 0);
					
		SendText("Verify that user can enter text to refine Make Model search", "Type to refine search", MakeModel);
		DataPicker("Verify that user can select car Make and Model <br>Test Data: <b>"+MakeModel+"</b>", MakeModel, 0);   

		if (Version != null && !Version.isEmpty()) 
			DataPicker("Verify that user can select car Version <br>Test Data: <b>"+Version+"</b>", Version, 0);    				
	}
	
	public void VerifyBasicInfoForm() throws Exception {
		Thread.sleep(5000);
		if(Base.Testing.equalsIgnoreCase("System"))
		{
			String h1=driver.findElement(By.id("com.pakwheels.staging:id/lbl_screen_title")).getText();
			if(h1.equalsIgnoreCase("Your basic info"))
			{
				test.log(Status.PASS, "Verified That H1 is showing."+h1);
			}
			
			//Verify that following Fields are showing:
			test.log(Status.PASS, "<b>Verify that following Fields are showing</b>");
			//p[@class='fs16 mb8 generic-primary pl10 pr10 fwb']
			List<MobileElement> field_elements = driver.findElementsById("com.pakwheels.staging:id/tv_input_module_title");

	        String[] field_texts = new String[field_elements.size()];
	        for (int i = 0; i < field_elements.size(); i++) {
	        	field_texts[i] = field_elements.get(i).getText();
	        	test.log(Status.PASS,"<b>Field</b>  "+field_texts[i]);
	        }
			
			//Check Validations on forms fields:
	        SelectContinueButton();
			test.log(Status.PASS, "<b>Verify that following Validation Messages appears on Fields are showing</b>");
			try
			{
			 if(driver.findElement(By.xpath("//android.widget.TextView[@text='Please enter your full name']")).isDisplayed())
			 {
				 test.log(Status.PASS, "Verified That Validation Message appaears for <b>User Name</b> Field.");
			 }
			 if(driver.findElement(By.xpath("//android.widget.TextView[@text='Enter a valid phone number']")).isDisplayed())
			 {
				 test.log(Status.PASS, "Verified That Validation Message appaears for <b>User Number</b> Field.");
			 }
			 if(driver.findElement(By.xpath("//android.widget.TextView[@text='Select at least 1 option']")).isDisplayed())
			 {
				 test.log(Status.PASS, "Verified That Validation Message appaears for <b>Please Select at least 1 option</b> Field.");
			 }
			 
			}
			catch(org.openqa.selenium.NoSuchElementException e){}

		}
		
	}
	
	public void VerifyCarInfoform() throws Exception {
		if(Base.Testing.equalsIgnoreCase("System"))
		{
			String h1=driver.findElement(By.id("com.pakwheels.staging:id/lbl_screen_title")).getText();
			if(h1.contains("Car Info"))
			{
				test.log(Status.PASS, "Verified That H1 is showing on Form."+h1);
			}
			
			
			test.log(Status.PASS, "<b>Verify that following Fields are showing</b>");
			//p[@class='fs16 mb8 generic-primary pl10 pr10 fwb']
			List<MobileElement> field_elements = driver.findElementsById("com.pakwheels.staging:id/tv_input_module_title");

	        String[] field_texts = new String[field_elements.size()];
	        for (int i = 0; i < field_elements.size(); i++) {
	        	field_texts[i] = field_elements.get(i).getText();
	        	test.log(Status.PASS,"<b>Field</b>  "+field_texts[i]);
	        }
			
			//Check Validations on forms fields:
	        SelectContinueButton();
			test.log(Status.PASS, "<b>Verify that following Validation Messages appears on Fields are showing</b>");
			 if(driver.findElement(By.xpath("//android.widget.TextView[@text='Please tell us about your car']")).isDisplayed())
			 {
				 test.log(Status.PASS, "Verified That Validation Message appaears for <b>Car Info</b> Field.");
			 }
			 if(driver.findElement(By.xpath("//android.widget.TextView[@text='Select place of registration']")).isDisplayed())
			 {
				 test.log(Status.PASS, "Verified That Validation Message appaears for <b>Select place of registration</b> Field.");
			 }
		}
		
	}
	
	public void SelectCarInformation() throws Exception
	{	   	 
		JSONObject CarObj = (JSONObject) Object.get("CarInformation");
		String MakeModel= (String) CarObj.get("MakeModel");
		String Year= (String) CarObj.get("Year");
		String Version= (String) CarObj.get("Version");
		
		DataPicker("Verify that user can select car model button to open model listing", "Select your car model", 4);			
		
		if (Year != null && !Year.isEmpty()) 
			DataPicker("Verify that user can select car year <br>Test Data: <b>"+Year+"</b>", Year, 0);
					
		SendText("Verify that user can enter text to refine Make Model search", "Type to refine search", MakeModel);
		DataPicker("Verify that user can select car Make and Model <br>Test Data: <b>"+MakeModel+"</b>", MakeModel, 0);   

		if (Version != null && !Version.isEmpty()) 
			DataPicker("Verify that user can select car Version <br>Test Data: <b>"+Version+"</b>", Version, 0);    				
	}
	
	public void SelectCityArea() throws Exception
	{
		JSONObject locationObj = (JSONObject) Object.get("Location");
		String CityArea= (String) locationObj.get("CityArea");
	   
		DataPicker("Verify that user can select City Area button", "Select city area", 4);
		SendText("Verify that user can enter text to refine City Area search", "Type to refine search", CityArea);		
		DataPicker("Verify that user can select City Area <br>Test Data: <b>"+CityArea+"</b>", CityArea, 0);
		
	}
	public void SelectCityAreaPartner() throws Exception
	{
		JSONObject locationObj = (JSONObject) Object.get("Location");
		String CityArea= (String) locationObj.get("CityArea");
	   
		//DataPicker("Verify that user can select City Area button", "Select city area", 4);
		Thread.sleep(2000);
		SendText("Verify that user can enter text to refine City Area search", "Type to refine search", CityArea);
				
       // System.out.println(CityArea);
        Thread.sleep(15000);
		   if (CityArea != null && !CityArea.trim().isEmpty())
		   {
			   SendText("Verify that user can enter text to refine City Area search", "Type to refine search", CityArea);
				test.log(Status.PASS, "Verify that user can enter text to refine City Area search (Test Data: "+CityArea+")");
				try
				{
				Boolean flag1=driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.pakwheels.staging:id/no_result_description']")).isDisplayed();
				System.out.println(flag1);
				if(flag1)
				{
					DataPicker("Verify that user can't select City Area and click on <br> Test Data: <b>Cancel</b>", "Cancel", 0);
					test.log(Status.PASS, "Verify that user can Click on skip button");
				}
				}catch(org.openqa.selenium.NoSuchElementException e)
				{  
				   DataPicker("Verify that user can select City Area <br> Test Data: <b>"+CityArea+"</b>", CityArea, 0);
			       test.log(Status.PASS, "Verify that user can select city area (Test Data: "+CityArea+")");
				}
		   }
		   else
		   {
			   DataPicker("Verify that user can't select City Area and click on  <br> Test Data: <b>Cancel</b>", "Cancel", 0);
				test.log(Status.PASS, "Verify that user can Click on skip button");
		   }
		
	}
	
	public void SelectContinueButton() throws Exception
	{
		DataPicker("Verify that user can select continue button", "Continue", 2);  
		
	}
		


	
	public void FillBasicInfoInsurance() throws Exception {
	    String nameFieldXpath = "(//android.widget.EditText)[1]";
	    String mobileFieldXpath = "(//android.widget.EditText)[2]";
	   

	    try {
	        MobileElement nameField = driver.findElement(By.xpath(nameFieldXpath));
	        nameField.clear();
	        nameField.sendKeys(Base.Name);
	     

	        MobileElement mobileField = driver.findElement(By.xpath(mobileFieldXpath));
	        mobileField.clear();
	        mobileField.sendKeys(Base.MobileNumber);
	    
	        driver.findElement(By.xpath("//android.widget.Button")).click();
	        test.log(Status.PASS, "Verify that user can tap on Let's go button");

	    } catch (org.openqa.selenium.NoSuchElementException e) {
	        test.log(Status.FAIL, "Verify that user unable to tap on Let's go button");
	        throw new Exception("Element not found", e);
	    }
	}
	
	public void FillBasicInfo() throws Exception
	{
      
        List<MobileElement> fields = driver.findElements(By.xpath("//android.widget.EditText[@resource-id='com.pakwheels.staging:id/et_content']"));

        for (MobileElement field : fields) {
            field.clear();
        }
		
		SendText("Verify that user can enter Name <br>Test Data: <b>"+Base.Name+"</b>", "Enter full name", Base.Name);
		SendText("Verify that user can enter Mobile Number <br>Test Data: <b>"+Base.MobileNumber+"</b>", "03xxxxxxxxx", Base.MobileNumber);	
		try {
			driver.findElement(By.id("com.pakwheels.staging:id/btn_lets_go")).click();
			test.log(Status.PASS, "Verify that user can tap on Let's go button");
		}
		catch(org.openqa.selenium.NoSuchElementException e){
			test.log(Status.FAIL, "Verify that user unable to tap on Let's go button");
			throw new Exception("Element not found");
		}
	}

	public void EnterAddress() throws InterruptedException{
		String Address= (String) Object.get("Address");
		SendText("Verify that user can enter Inspection Address <br>Test Data: <b>"+Address+"</b>", "House No, street/building no.", Address);
		driver.hideKeyboard();
	}
	
	public boolean PaymentFlow() throws Exception
	{
		String PaymentMethod= (String) Object.get("PaymentMethod");
		DataPicker("Verify that user can select Payment Method <br> Test Data: <b>"+PaymentMethod+"</b>", PaymentMethod, 0);
		//driver.findElement(By.xpath("//android.widget.TextView[@text='"+PaymentMethod+"']")).click();
		//DataPicker("Verify that user can select <b>Place Order</b> Button", "Place Order", 2);
		DataPicker("Verify that user can select continue button", "Continue", 2);   

		Boolean status= false;
		if (PaymentMethod.equalsIgnoreCase("JazzCash Mobile Account"))
		{
			JazzMobileAccount();
		}
		else if (PaymentMethod.equalsIgnoreCase("EasyPay Mobile Account"))
		{
			EasyPayMobileAccount();
		}
		else if (PaymentMethod.equalsIgnoreCase("Debit/Credit Card"))
		{
			test.log(Status.INFO, "<h3><b>Payment Screen (Debit/Credit Card)</b></h3>");
		}
		else if (PaymentMethod.equalsIgnoreCase("JazzCash Shop"))
		{
			
		}
		else if (PaymentMethod.equalsIgnoreCase("Bank Deposit"))
		{
			
		}
		
		try 
		{
			Thread.sleep(2000);
			String ActualText= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@resource-id='com.pakwheels.staging:id/lbl_screen_title']"))).getText();	
			if (ActualText.equalsIgnoreCase("Payment successful")) 
			{
				test.log(Status.INFO, "Payment has been done");
			}
			status= true;
		}catch(org.openqa.selenium.NoSuchElementException ex){  
			test.log(Status.FAIL, "Payment has been failed");
			status= false;
		}
		Thread.sleep(5000);
		return status;
	}



}
