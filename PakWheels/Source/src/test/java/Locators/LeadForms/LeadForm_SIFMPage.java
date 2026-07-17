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

public class LeadForm_SIFMPage extends Leadforms
{
	AppiumDriver<MobileElement> driver= null;
	ExtentTest test= null;	
	WebDriverWait wait= null;
	JSONObject Object = null;
	public LeadForm_SIFMPage(AppiumDriver<MobileElement> driver, ExtentTest test, WebDriverWait wait, JSONObject Object) 
	{
		super(driver, test, wait, Object);
		this.driver= driver;
		this.test= test;
		this.wait= wait;
		this.Object= Object;
	}
	
	public void NavigateToSIFMPage() throws Exception{    	
		
		ClickHomeButton(); 
		DataPicker("Verify that user Scroll down to Pakwheels Offerings Section On Home Page", "PakWheels offerings", 0);  
		driver.findElement(By.xpath("//android.view.View[@resource-id='Sell It For Me']")).click();
		test.log(Status.INFO, "User Selects SIFM from Pakwheels Offerings");

	}
	
	public void ClickGetStartedButton() throws Exception{
		test.log(Status.INFO, "User is navigated to Sell It For Me page");	
		DataPicker("Verify that user can tap on <b>Get Started</b> Button", "Help me sell my car!", 2);
		test.log(Status.INFO, "User is navigated to Sell It For Me Form");	
	}
	
	public void SelectAssembly() throws Exception
	{
		String Assembly= (String) Object.get("Assembly");
		DataPicker("Verify that user can select assembly <br>Test Data: <b>"+Assembly+"</b>", Assembly, 3);   
	}
	
	public void SelectRegisteredIn() throws Exception
	{
		String RegisteredIn= (String) Object.get("RegisteredIn");
		DataPicker("Verify that user can select registered in city <br>Test Data: <b>"+RegisteredIn+"</b>", RegisteredIn, 3);   //Verify registration city
	}

	public void VerifySIFMPage() throws Exception {
		
		String VerifyLanding= (String) Object.get("VerifyLanding");
		if(Base.Testing.equalsIgnoreCase("System") && VerifyLanding.equalsIgnoreCase("Yes"))
		{
          test.log(Status.PASS, "<h2>SIFM Landing Page</h2>");
			
			String h1=driver.findElement(By.id("com.pakwheels.staging:id/tv_titleText_actionbar_menu")).getText();
			if(h1.equalsIgnoreCase("PakWheels Sell It For Me"))
			{
				test.log(Status.PASS, "Verified That H1 is showing."+h1);
			}
			
			DataPicker("Verify that CTA button is showing with text  <b>"+"Help me sell my car!"+"</b> ", "Help me sell my car!", 2);
			
				if(driver.findElement(By.xpath("//android.widget.TextView[@text='Your basic info']")).isDisplayed())
				{
					
					test.log(Status.PASS, "Verified That user redirected to Basic Info Screen after clicking Help me sell my car! button");
					driver.navigate().back();
				}
			
			DataPicker("Verify that Sell It For Me onboarding charges section is showing", "Sell It For Me onboarding charges",0);
			DataPicker("Verify that How Sell It For Me works? section is showing", "How Sell It For Me works?",0);
			DataPicker("Verify that Why choose PakWheels Sell It For Me? section is showing", "Why choose PakWheels Sell It For Me?",0);
			DataPicker("Verify that Sell it for me success stories section is showing", "Sell it for me success stories",0);
			
			
			DataPicker("Verify that Managed By PakWheels section is showing", "Managed by PakWheels",0);
			Thread.sleep(2000);
			DataPicker("Verify that View All Button is showing and User Clicks on View All Button", "View All",0);
			if(driver.findElement(By.id("com.pakwheels.staging:id/iv_managed_by_pakwheels")).isDisplayed() && (driver.findElement(By.id("com.pakwheels.staging:id/tv_selected_filters_count")).getText()).equals("1"))
			{
				test.log(Status.PASS, "Verified that user redirected to Used Car Search listing with applied filter of Managed by Pakwheels.");
				driver.navigate().back();
			}
			//ScrollToBeginning();
			Thread.sleep(2000);
			//Scroll("Managed By PakWheels");
		    //DataPicker("Verify that Managed By PakWheels section is showing after naviagting from search listing", "Managed By PakWheels",0);
			String Ad_Car=driver.findElement(By.id("com.pakwheels.staging:id/tv_similar_ad_title")).getText();
			String city=driver.findElement(By.id("com.pakwheels.staging:id/tv_similar_ad_city")).getText();
			
			driver.findElement(By.id("com.pakwheels.staging:id/tv_similar_ad_title")).click();
			test.log(Status.PASS, "Verified That user Clicks on Managed By Pakwheels Ad "+Ad_Car);
			String AdDetailCar=driver.findElement(By.id("com.pakwheels.staging:id/txt_view_ad_title_detailpage")).getText();
			String AdDetailCity=driver.findElement(By.id("com.pakwheels.staging:id/tv_area_city")).getText();
			
			if(city.equalsIgnoreCase(AdDetailCity))
			{
				test.log(Status.PASS, "Verified User Redirected to exact selected ad from Managed By Pakwheels Carousal");
			}
			driver.navigate().back();
		//	ScrollToBeginning();
			Thread.sleep(2000);
			
			DataPicker("Verify that Frequently Asked Questions section is showing", "Frequently Asked Questions",0);
			DataPicker("Verify that PakWheels offerings is showing", "PakWheels offerings",0);
			List<MobileElement> Offerings = driver.findElements(By.id("com.pakwheels.staging:id/tvOurProductTitle"));
			String navigation_Offering=Offerings.get(1).getText();
			System.out.println(navigation_Offering+" " +Offerings.size());
			Offerings.get(1).click();
			Thread.sleep(2500);
			String heading=driver.findElement(By.id("com.pakwheels.staging:id/header_heading")).getText();
			if(heading.equalsIgnoreCase(navigation_Offering))
			{
				test.log(Status.PASS, "Verified That User Navigated to ."+heading);
				driver.navigate().back();
			}
			
			DataPicker("Verify that sticky CTA button is showing bottom of page  <b>"+"Help me sell my car!"+"</b> ", "Help me sell my car!", 2);
			if(driver.findElement(By.xpath("//android.widget.TextView[@text='Your basic info']")).isDisplayed())
			{
				
				test.log(Status.PASS, "Verified That user redirected to Basic Info Screen after clicking sticky Help me sell my car! button");
				driver.navigate().back();
			}
		}
	}
	


	public void VerifySIFMBasicInfoForm() throws Exception {
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
			 if(driver.findElement(By.xpath("//android.widget.TextView[@text='Please tell us where you live']")).isDisplayed())
			 {
				 test.log(Status.PASS, "Verified That Validation Message appaears for <b>Please tell us where you live</b> Field.");
			 }
			 
			}
			catch(org.openqa.selenium.NoSuchElementException e){}

		}
		
	}
	public void VerifySIFMCarInfoform() throws Exception {
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
			 if(driver.findElement(By.xpath("//android.widget.TextView[@text='Tell us about your car']")).isDisplayed())
			 {
				 test.log(Status.PASS, "Verified That Validation Message appaears for <b>Car Info</b> Field.");
			 }
			 if(driver.findElement(By.xpath("//android.widget.TextView[@text='Choose assembly']")).isDisplayed())
			 {
				 test.log(Status.PASS, "Verified That Validation Message appaears for <b>Choose assembly</b> Field.");
			 }
			 if(driver.findElement(By.xpath("//android.widget.TextView[@text='Select place of registration']")).isDisplayed())
			 {
				 test.log(Status.PASS, "Verified That Validation Message appaears for <b>Select place of registration</b> Field.");
			 }
		}
		
	}
}
