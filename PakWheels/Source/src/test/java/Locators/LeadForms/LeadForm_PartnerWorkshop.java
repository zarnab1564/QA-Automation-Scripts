package Locators.LeadForms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import Locators.Base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class LeadForm_PartnerWorkshop extends Leadforms
{
	AppiumDriver<MobileElement> driver= null;
	ExtentTest test= null;	
	WebDriverWait wait= null;
	JSONObject Object = null;
	
	public LeadForm_PartnerWorkshop(AppiumDriver<MobileElement> driver, ExtentTest test, WebDriverWait wait, JSONObject Object) 
	{
		super(driver, test, wait, Object);
		this.driver= driver;
		this.test= test;
		this.wait= wait;
		this.Object= Object;
	}
	
	public void NavigateToPartnerworkshopPage() throws Exception{
		DataPicker("Verify that user can tap on Auto Services button to open Drop-Down", "Auto Services", 0);   
		DataPicker("Verify that user can navigate to Partner workshop page by cliking on Partner workshop button", "Partner Workshop", 0);
		test.log(Status.INFO, "User is navigated to Partner workshop page");	
	}
	
	public void CickOnPartnerWorkshopButon() throws Exception
	{
		DataPicker("Verify that user can click on <b>Book an appointment now</b>", "Book an appointment now", 2);
		test.log(Status.INFO, "User is navigated to Partner Workshop form");	
	}
	
	public void EnterContactInfo() throws Exception
	{
		SendText("Verify that user can enter Name <br>Test Data: <b>"+Base.Name+"</b>", "Enter full name", Base.Name);
		SendText("Verify that user can enter Mobile Number <br>Test Data: <b>"+Base.MobileNumber+"</b>", "03xxxxxxxxx", Base.MobileNumber);	
	}
	
	public void SelectCarInformation() throws Exception
	{	   	 
		DataPicker("Verify that user can select car model button to open model listing", "Select your car info", 4);			
		SelectCarYearMakeModelVersion();
	}
	
	public void SelectServiceIssues() throws Exception
	{	
		String ServiceObj= (String) Object.get("ServiceIssue");
		String Message= (String) Object.get("Message");
		//DataPicker("Verify that user can select issue <br>Test Data: <b>"+ServiceObj+"</b>", ServiceObj, 0);
		
		try {
			String[] filters = ServiceObj.split(",");

			for (String filter : filters) 
			{
			    DataPicker("Verify that user can select issue <br>Test Data: <b>"+filter+"</b>", filter, 0);
			    test.log(Status.PASS, "Verify that user select "+filter+" option");
			   // System.out.println(filter);
			    if(filter.equalsIgnoreCase("Other repair services"))
			    {
			    	if(driver.findElement(By.id("com.pakwheels.staging:id/other_issues_here")).isDisplayed())
			    	{
			    		test.log(Status.PASS, "Please enter any other repairs needed field gets displayed");
			    		if(Message!=null &&!Message.isEmpty())
			    		{
			    			SendText("Verify that user can enter Name <br>Test Data: <b>"+Message+"</b>", "Please write down other issues here", Message);
			    		}
			    		else
			    		{
			    			test.log(Status.PASS, "Please enter any other repairs needed field gets displayed and user does not enter any text");
			    		}
			    	}
			    }
			}
			test.log(Status.PASS, "Verify that user can select (Test Data:"+ServiceObj+" button");
			Thread.sleep(2000);
		}catch(org.openqa.selenium.NoSuchElementException e){
			test.log(Status.FAIL, "Verify that user can not select Issues facing (Test Data: "+ServiceObj+")");	
		}
	}
	
	
	public void AddNotes() throws InterruptedException {
		String Notes= (String) Object.get("Notes");
		if(Notes!=null &&!Notes.isEmpty())
		{	
			SendText("Verify that user can enter Name <br>Test Data: <b>"+Notes+"</b>", "You can add additional notes here",Notes);
		    test.log(Status.PASS, "Verify that user can enter text on Notes Field");
		}
	}
	
	public void CheckThankyouScreen() throws InterruptedException
	{
		
		
		Thread.sleep(1000);
		String ActualText= driver.findElement(By.id("com.pakwheels.staging:id/lbl_screen_title")).getText();
		
		if (ActualText.equalsIgnoreCase("Thank you for choosing PakWheels")) 
		{
			test.log(Status.INFO, "User is navigated to thank you screen");
			test.log(Status.PASS, "Partner Workshop request has been submitted successfully");
			
			Thread.sleep(5000);
			String VerifySummary=(String) Object.get("VerifySummary");
			if(Base.Testing.equalsIgnoreCase("System") && VerifySummary.equals("Yes"))
			{

				JSONObject CarObj = (JSONObject) Object.get("CarInformation");
				String Make= (String) CarObj.get("Make");
				String Model= (String) CarObj.get("MakeModel");
				String Year= (String) CarObj.get("Year");
				String Version= (String) CarObj.get("Version");
				String ServiceObj= (String) Object.get("ServiceIssue");
				String Notes= (String) Object.get("Notes");
				 JSONObject locationObj = (JSONObject) Object.get("Location");
				 String City= (String) locationObj.get("City");
		
				
				test.log(Status.INFO, "<h3>Verification of Summary Screen</h3>");
				String Car_Info=driver.findElement(By.id("com.pakwheels.staging:id/lbl_car_information_body")).getText();
				
				//System.out.println(Car_Info);
				String Car=Make+" "+Model+" "+Year;
				if(Car_Info.equals(Car) || Car_Info.contains(Car))
				{
					test.log(Status.PASS, "Verified That same Selected Car information showing.");
				}
				else
				{
					test.log(Status.FAIL, "Verified That same Selected Car information are <b>not</b> showing.");
				}
				List<MobileElement> ServiceRequested = driver.findElements(By.id("com.pakwheels.staging:id/service_item"));

				List<String> serviceRequestedText = new ArrayList<>();
				
		        for (MobileElement element : ServiceRequested) {
		            serviceRequestedText.add(element.getText());
		        }		
				
				String[] filters = ServiceObj.split(",");
		        List<String> IssuesList = Arrays.asList(filters);
		        
		        System.out.println("Issues List"+IssuesList);
		    
		        Boolean containsSome=false;
		        for (String element : serviceRequestedText) {
		            if (IssuesList.contains(element)) {
		                containsSome = true;
		            }
		        }
		        
		        if (containsSome) {
		            System.out.println("Verified that Same Issues selected");
		            test.log(Status.PASS, "Verified That same Selected Issues are showing."+serviceRequestedText); 
		        } else {
		            System.out.println("Verified that Same Issues are not selected"+serviceRequestedText);
		            test.log(Status.PASS, "Verified That same Selected Issues are not showing."); 
		        }

				
				String UserInfo=driver.findElement(By.id("com.pakwheels.staging:id/lblBuyerName")).getText();
				
			//	System.out.println(UserInfo);
				String[] words = UserInfo.split("\n");
			//	System.out.println(words[0]);
				if(words[0].equals(Base.Name))
				{
					test.log(Status.PASS, "Verified That same Name is  showing.");
				}
				else
				{
					test.log(Status.FAIL, "Verified That same Name is not showing.");
				}
				//System.out.println(words[1]);
				if(words[1].equals(Base.MobileNumber))
				{
					test.log(Status.PASS, "Verified That same Mobile Number is  showing.");
				}
				else
				{
					test.log(Status.FAIL, "Verified That same mobile number is not showing.");
				}
				Thread.sleep(2000);
				
		  try {
				if(Notes!=null && !Notes.isEmpty())
				{	
					if(driver.findElement(By.id("com.pakwheels.staging:id/lbl_additional_info")).isDisplayed())
					{
						String nt=driver.findElement(By.id("com.pakwheels.staging:id/lbl_additional_info_body")).getText();
						System.out.println(nt);
						if(nt.equalsIgnoreCase(Notes))
						{
							test.log(Status.PASS, "Verified That same notes information showing.");
						}
						else
						{
							test.log(Status.FAIL, "Verified That same notes information are not showing.");
						}
					}
				
				}
		   }catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can not find notes");	
			}	
		  //Perfered Location
			String Location=driver.findElement(By.id("com.pakwheels.staging:id/lbl_preferred_location_body")).getText();
		//	System.out.println(Location);
			
           if(Location.contains(City))
           {
        	   test.log(Status.PASS, "Verified That same Selected Loaction is showing."+Location);
           }
           else
           {
        	   test.log(Status.PASS, "Verified That same Selected Loaction is not showing."+Location);
           }
		}
			
			
		}
		else
		{
			test.log(Status.FAIL, "Partner Workshop request has been FAILED");
		}
	}
	
	public void SelectLocation() throws Exception
	{
		 JSONObject locationObj = (JSONObject) Object.get("Location");
		 String City= (String) locationObj.get("City");
		 String CityArea= (String) locationObj.get("CityArea");
		
		if (!City.isEmpty())
		 {
			DataPicker("Verify that user can select <b>Location</b> button", "Select city", 0);
			SendText("Verify that user can enter text to refine City search", "Type to refine search", City);		
			DataPicker("Verify that user can select City <br>Test Data: <b>"+City+"</b>", City, 0);

			if (CityArea != null && !CityArea.isEmpty())
			{
				SendText("Verify that user can enter text to refine City Area search", "Type to refine search", CityArea);	
				
				DataPicker("Verify that user can select City Area <br>Test Data: <b>"+CityArea+"</b>", CityArea, 0);
			}	
		}
	}
}


