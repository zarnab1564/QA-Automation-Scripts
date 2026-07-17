package Locators.LeadForms;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Locators.Base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class LeadForm_InspectionRequestPage extends Leadforms
{
	AppiumDriver<MobileElement> driver= null;
	ExtentTest test= null;	
	WebDriverWait wait= null;
	JSONObject Object = null;
	public LeadForm_InspectionRequestPage(AppiumDriver<MobileElement> driver, ExtentTest test, WebDriverWait wait, JSONObject Object) 
	{
		super(driver, test, wait, Object);
		this.driver= driver;
		this.test= test;
		this.wait= wait;
		this.Object= Object;
	}
	
	public void NavigateToInspectionPage() throws Exception{
		//DataPicker("Verify that user can tap on Services button to open Drop-Down", "Services", 0);               
		DataPicker("Verify that user can navigate to Inspection page by cliking on Order Car Inspection button", "Order Car Inspection", 0);
	}
	
	public void ClickOnScheduleInspectionButton() throws Exception{
		test.log(Status.INFO, "User is navigated to Inspection page");    	
		DataPicker("Verify that user can tap on <b>Order Car Inspection</b> button", "Order car inspection", 2);
		test.log(Status.INFO, "User is navigated to Inspection form");     
	}

	public void VerifyInspectionProductPage() throws Exception {
		String VerifyLanding= (String) Object.get("VerifyLanding");
		if(Base.Testing.equalsIgnoreCase("System") && VerifyLanding.equalsIgnoreCase("Yes"))
		{
          test.log(Status.PASS, "<h2>Inspection Landing Page</h2>");
          Thread.sleep(3000);
			DataPicker("Verify that user redirected to PakWheels Car Inspection Product Page", "PakWheels Car Inspection", 0); 
			  Thread.sleep(3000);
		

				DataPicker("Verify that user click on Book Car Inspection button <b>Book Car Inspection</b>", "Book car inspection", 2); 
				
				if(driver.findElement(By.xpath("//android.widget.TextView[@text='Basic info']")).isDisplayed())
				{
					
					test.log(Status.PASS, "Verified That user redirected to Basic Info Screen");
					driver.navigate().back();
				}
			
			
			DataPicker("Verify that What are car inspection charges section is showing", "What are car inspection charges?",0);
			DataPicker("Verify that Basic inspection charges section is showing", "Basic",0);
			driver.navigate().back();
			DataPicker("Verify that Basic inspection charges is showing PKR 4,950", "PKR 4,950",0);
			driver.navigate().back();
			DataPicker("Verify that Standard inspection charges section is showing", "Standard",0);
			driver.navigate().back();
			DataPicker("Verify that Standard inspection charges  is showing PKR 6,950 ", "PKR 6,950",0);
			driver.navigate().back();
			DataPicker("Verify that Premium inspection charges section is showing", "Premium",0);
			driver.navigate().back();
			DataPicker("Verify that Premium inspection charges  is showing PKR 8,950", "PKR 8,950",0);
			driver.navigate().back();
			Thread.sleep(5000);
			
			
			DataPicker("Verify that What is included in car inspection? section is showing", "What is included in car inspection?",0);
			DataPicker("Verify that How car inspection works? section is showing", "How car inspection works?",0);
			Thread.sleep(5000);
			DataPicker("Verify that Sample PakWheels Inspection Report section is showing", "Sample PakWheels Inspection Report",0);

				DataPicker("Verify that user click on View sample inspection report button <b>View sample inspection report</b>", "View sample inspection report", 2); 
				Thread.sleep(2000);
				DataPicker("Verify that Sample Inspection Report section is showing", "Sample Inspection Report",0);
				driver.navigate().back();
				
			Thread.sleep(2000);
			DataPicker("Verify that PakWheels inspected cars section is showing", "PakWheels inspected cars",0);
			DataPicker("Verify that see All Button is showing and User Clicks on See All Button", "See all",0);
			if(driver.findElement(By.id("com.pakwheels:id/tv_certified_inspected")).isDisplayed() && (driver.findElement(By.id("com.pakwheels.staging:id/tv_selected_filters_count")).getText()).equals("1"))
			{
				test.log(Status.PASS, "Verified that user redirected to Used Car Search listing with applied filter of Pakwheels Inspected Cars");
				driver.navigate().back();
			}
			DataPicker("Verify that PakWheels inspected cars section is showing", "PakWheels inspected cars",0);
			String Ad_Car=driver.findElement(By.id("com.pakwheels.staging:id/tv_similar_ad_title")).getText();
			String city=driver.findElement(By.id("com.pakwheels.staging:id/tv_similar_ad_city")).getText();
			
			driver.findElement(By.id("com.pakwheels.staging:id/tv_similar_ad_title")).click();
			test.log(Status.PASS, "Verified That user Clicks on Inspected Car  Ad "+Ad_Car);
			String AdDetailCar=driver.findElement(By.id("com.pakwheels.staging:id/txt_view_ad_title_detailpage")).getText();
			String AdDetailCity=driver.findElement(By.id("com.pakwheels.staging:id/tv_area_city")).getText();
			
			if(city.equalsIgnoreCase(AdDetailCity))
			{
				test.log(Status.PASS, "Verified User Redirected to exact selected ad from Inspected Cars Carousal");
			}
			driver.navigate().back();
			ScrollToBeginning();
			Thread.sleep(2000);
			

			
			DataPicker("Verify that Car inspection success stories  section is showing", "Car inspection success stories",0);
			DataPicker("Verify that FAQs section is showing","FAQs",0);
		   driver.findElement(By.id("com.pakwheels:id/dropdown_image")).click();
		   test.log(Status.PASS, "Verified User Clicks on Question dropdown icon and question answer maximizied");
		   Thread.sleep(2000);
		   driver.findElement(By.id("com.pakwheels:id/dropup_image")).click();
		   test.log(Status.PASS, "Verified User Clicks on Question dropUp icon and question answer gets closed");

		}
		
	}
}
