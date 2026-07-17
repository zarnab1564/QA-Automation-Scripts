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

public class LeadForm_CarRegistration extends Leadforms
{
	AppiumDriver<MobileElement> driver= null;
	ExtentTest test= null;	
	WebDriverWait wait= null;
	JSONObject Object = null;
	
	public LeadForm_CarRegistration(AppiumDriver<MobileElement> driver, ExtentTest test, WebDriverWait wait, JSONObject Object) 
	{
		super(driver, test, wait, Object);
		this.driver= driver;
		this.test= test;
		this.wait= wait;
		this.Object= Object;
	}
	
	public void NavigateToCarRegistrationPage() throws Exception
	{
		DataPicker("Verify that user can tap on Auto Services button to open Drop-Down", "Auto Services", 0);    
		DataPicker("Verify that user can navigate to Car Registration page by cliking on Car Registration button", "Car Registration", 0);
		test.log(Status.INFO, "User is navigated to Car Registration page");	
	}
	
	public void CickOnCarRegistrationButon() throws Exception
	{
		DataPicker("Verify that user can click on <b>Get my car registered now button</b>", "Get my car registered now", 2);
		test.log(Status.INFO, "User is navigated to Car Registration form");	
		
		//SendText("Verify that user can enter Name <br>Test Data: <b>"+Base.Name+"</b>", "Enter full name", Base.Name);
		//SendText("Verify that user can enter Mobile Number <br>Test Data: <b>"+Base.MobileNumber+"</b>", "03xxxxxxxxx", Base.MobileNumber);	
	}
	
	public void EnterBasicInfo() throws Exception
	{
		SendText("Verify that user can enter Name <br>Test Data: <b>"+Base.Name+"</b>", "Enter full name", Base.Name);
		SendText("Verify that user can enter Mobile Number <br>Test Data: <b>"+Base.MobileNumber+"</b>", "03xxxxxxxxx", Base.MobileNumber);	
		
		String Filer= (String) Object.get("Filer");
		DataPicker("Verify that user can select filer status <br>Test Data: <b>"+Filer+"</b>", Filer, 3);
	}
	
	public void SelectRegistrationCity() throws Exception
	{	   	 
		String RegistrationCity= (String) Object.get("RegistrationCity");
		DataPicker("Verify that user can select Registration City <br>Test Data: <b>"+RegistrationCity+"</b>", RegistrationCity, 3);
	}
	
	public void CheckThankyouScreen() throws InterruptedException
	{
		Thread.sleep(1000);
		String VerifySummary= (String) Object.get("VerifySummary");
		String ActualText= driver.findElement(By.id("com.pakwheels.staging:id/lbl_screen_title")).getText();
		
		if (ActualText.equalsIgnoreCase("Thank you for choosing PakWheels")) 
		{
			test.log(Status.INFO, "User is navigated to thank you screen");
			test.log(Status.PASS, "Car registration request has been submitted successfully");
			
			if(Base.Testing.equalsIgnoreCase("System") && VerifySummary.equalsIgnoreCase("Yes"))
			{
				
				JSONObject CarObj = (JSONObject) Object.get("CarInformation");
				String Make= (String) CarObj.get("Make");
				String Model= (String) CarObj.get("MakeModel");
				String Year= (String) CarObj.get("Year");
				String Version= (String) CarObj.get("Version");
				String Registered= (String) Object.get("RegistrationCity");

			String Car_Info=driver.findElement(By.id("com.pakwheels.staging:id/lbl_car_information_body")).getText();
			
			System.out.println(Car_Info);
			String Car=Make+" "+Model+" "+Year;
			if(Car_Info.equals(Car) || Car_Info.contains(Car))
			{
				test.log(Status.PASS, "Verified That same Selected Car information showing.");
			}
			else
			{
				test.log(Status.FAIL, "Verified That same Selected Car  information are not showing.");
			}
			String RegisteredIn=driver.findElement(By.id("com.pakwheels.staging:id/lblRegistrationBody")).getText();
			System.out.println(RegisteredIn);
			if(RegisteredIn.contains(Registered))
			{
				test.log(Status.PASS, "Verified That same Selected Registered In option are showing."+RegisteredIn);
			}
			else
			{
				test.log(Status.FAIL, "Verified That same Selected Registered In are not showing.");
			}
			String UserName=driver.findElement(By.id("com.pakwheels.staging:id/lblBuyerName")).getText();

			if(UserName.equals(Base.Name))
			{
				test.log(Status.PASS, "Verified That same Name is  showing.");
			}
			else
			{
				test.log(Status.FAIL, "Verified That same Name is not showing.");
			}
			String UserNumber=driver.findElement(By.id("com.pakwheels.staging:id/lblBuyerNumber")).getText();
			if(UserNumber.equals(Base.MobileNumber))
			{
				test.log(Status.PASS, "Verified That same Mobile Number is  showing.");
			}
			else
			{
				test.log(Status.FAIL, "Verified That same mobile number is not showing.");
			}
			Thread.sleep(2000);
		}
			
			driver.findElement(By.id("com.pakwheels.staging:id/action_close")).click();
			
			test.log(Status.PASS, "Verified That user can click on cross icon and redirected back to Home Screen page");
			
		}
		else
		{
			test.log(Status.FAIL, "Car registration request has been FAILED");
		}
	}

	public void VerifyRegisterationLandingPage() throws Exception {
		
		if(Base.Testing.equalsIgnoreCase("System"))
		{
			String h1=driver.findElement(By.id("com.pakwheels.staging:id/header_heading")).getText();
			if(h1.equalsIgnoreCase("PakWheels Car Registration"))
			{
				test.log(Status.PASS, "Verified That H1 is showing."+h1);
			}
			
			String button=driver.findElement(By.id("com.pakwheels.staging:id/btn_new_product_page")).getText();
			if(button.equalsIgnoreCase("Get my car registered now"))
			{
				test.log(Status.PASS, "Verified That "+button+" button is showing.");
				
				CickOnCarRegistrationButon();
				
				if(driver.findElement(By.xpath("//android.widget.TextView[@text='Your basic info']")).isDisplayed())
				{
					
					test.log(Status.PASS, "Verified That user redirected to Basic Info Screen");
					driver.navigate().back();
				}
				
				List<MobileElement> h2_elements = driver.findElements(By.id("com.pakwheels.staging:id/tv_header_title"));

		        String[] h2_texts = new String[h2_elements.size()];
		        for (int i = 0; i < h2_elements.size(); i++) {
		            h2_texts[i] = h2_elements.get(i).getText();
		        }
		        for (String text : h2_texts) 
		        {
		        	if(text.equals("How Car Registeration works?"))
		        	{
		        		test.log(Status.PASS,"Verified that "+text+" section is showing");
		        	}
		        
		        	if(text.equals("Why choose PakWheels Car Registeration?"))
		        	{
		        		test.log(Status.PASS,"Verified that "+text+" section is showing");
		        	}

		        	if(text.equals("Car Registeration FAQs"))
		        	{
		        		test.log(Status.PASS,"Verified that "+text+" section is showing");
		        	}
		        }
		      	
		     // Scroll to the end of the screen
		        ScrollToEnd();

		        
		    	if(driver.findElement(By.id("com.pakwheels.staging:id/btn_new_product_page_bottom_sticky")).isDisplayed())
	        	{
		    		String Stickybutton=driver.findElement(By.id("com.pakwheels.staging:id/btn_new_product_page_bottom_sticky")).getText();
	        		test.log(Status.PASS,"Verified that Sticky button is showing contains text "+Stickybutton);
	        		
	        		driver.findElement(By.id("com.pakwheels.staging:id/btn_new_product_page_bottom_sticky")).click();
					test.log(Status.PASS, "Verify that user can click on Get My Car Registered  button from Sticky button on Car Registeration landing page.");
					
					if(driver.findElement(By.xpath("//android.widget.TextView[@text='Your basic info']")).isDisplayed())
					{
						
						test.log(Status.PASS, "Verified That user redirected to Basic Info Screen");
						driver.navigate().back();
					}
	        	}
				
			}
		}
		
	}

	
}