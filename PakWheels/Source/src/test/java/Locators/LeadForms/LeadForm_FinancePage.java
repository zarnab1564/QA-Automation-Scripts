package Locators.LeadForms;
import java.util.List;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.Dimension;
import java.time.Duration;
import Locators.Base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class LeadForm_FinancePage extends Leadforms
{
	AppiumDriver<MobileElement> driver= null;
	ExtentTest test= null;	
	WebDriverWait wait= null;
	JSONObject Object = null;
	public LeadForm_FinancePage(AppiumDriver<MobileElement> driver, ExtentTest test, WebDriverWait wait, JSONObject Object) 
	{
		super(driver, test, wait, Object);
		this.driver= driver;
		this.test= test;
		this.wait= wait;
		this.Object= Object;
	}
	
	public void NavigateToFinancePage() throws Exception{ 
		ClickHomeButton();
		DataPicker("Verify that user Scroll down to Pakwheels Offerings Section On Home Page", "PakWheels offerings", 0);  
		ScrollForward();
		//ScrollSecondView("PakWheels Certified Cars");
		ScrollSecondViewWithID("Car Finance");
		
 
		driver.findElement(By.xpath("//android.view.View[@resource-id='Car Finance']")).click();
		test.log(Status.INFO, "User is navigated to Finance page");	
	}
	
	public void FillForm() throws Exception{
		
		String CarStatus= (String) Object.get("CarStatus");
		if (CarStatus.equalsIgnoreCase("Used")){
			DataPicker("Verify that user can select Used Cars tab on finance page", "USED CARS", 0);
		}		
	
		JSONObject locationObj = (JSONObject) Object.get("Location");
		String City= (String) locationObj.get("City");
		
		DataPicker("Verify that user can select <b>Location</b> button", "Location", 0);	
		SendText("Verify that user can enter text to refine City search", "Type to refine search", City);		
		DataPicker("Verify that user can select City <br>Test Data: <b>"+City+"</b>", City, 0);
		
		DataPicker("Verify that user can select car model button to open model listing", "Car Model", 0);	
		SelectCarYearMakeModelVersion();

		String Price= (String) Object.get("Price");
		if (CarStatus.equalsIgnoreCase("Used")){		
			SendText("Verify that user can enter price <br>Test Data: <b>"+Price+"</b>", "Set a price", Price);	
			Scroll("Why Choose PakWheels Car Finance?");
		}	
	}
	
	public void SelectTenureAndDownPayment() throws Exception{	
		String Tenure= (String) Object.get("Tenure");
		String DownPayment= (String) Object.get("DownPayment");
		
		DataPicker("Verify that user can select Tenure button to open tenure listing", "Tenure", 0);
		DataPicker("Verify that user can select Tenure <br>Test Data: <b>"+Tenure+"<years /b>", Tenure, 0);
		DataPicker("Verify that user can select Down Payment button to open down-payment listing", "Down Payment", 0);
		DataPicker("Verify that user can select Down Payment <br>Test Data: <b>"+DownPayment+"</b>", DownPayment, 0);
	}
	
	public void ClickShowPlansButton() throws Exception{
		DataPicker("Verify that user can select Show Plans button", "Show Plans", 2);
	}
	
	public void SelectBank() throws Exception{
		String Bank= (String) Object.get("BankName");
		test.log(Status.INFO, "User is navigated to Bank packages listing");	
		Thread.sleep(5000);
		DataPicker("Verify that user can select Bank <br> Test Data: <b>"+Bank+"</b>", Bank, 0);			
		DataPicker("Verify that user can tap on Apply Now button after selecting Bank", "Apply Now", 2);
	}
	
	public void EnterInformation() throws Exception{
		test.log(Status.INFO, "User is navigated to Finance form");	
		EnterContactInformation();	
		
		String Email= (String) Object.get("Email");
		SendText("Verify that user can enter email <br>Test Data: <b>"+Email+"</b>", "Enter Email", Email);
		
		String CNIC= (String) Object.get("CNIC");
		SendText("Verify that user can enter CNIC <br>Test Data: <b>"+CNIC+"</b>", "xxxxx-xxxxxxx-x", CNIC);
		
		SelectLocation();	
		
		String Address= (String) Object.get("Address");
		SendText("Verify that user can enter Address <br>Test Data: <b>"+Address+"</b>", "Enter Address", Address);		
	}
	
	public void EnterFinancialInformation() throws Exception{
		
		JSONObject FinancialObj = (JSONObject) Object.get("FinancialInformation");
		String SourceOfIncome= (String) FinancialObj.get("SourceOfIncome");
		String MonthlyIncome= (String) FinancialObj.get("MonthlyIncome");
		String Bank= (String) FinancialObj.get("Bank");
		String CreditCardLoan= (String) FinancialObj.get("CreditCardLoan");
		String IntendToAcquire= (String) FinancialObj.get("IntendToAcquire");
		
		DataPicker("Verify that user can select Source Of Income <br>Test Data: <b>"+SourceOfIncome+"</b>", SourceOfIncome, 3);
		DataPicker("Verify that user can select Monthly Income Button to open income listing", "What is Your Monthly Income?", 0);	
		DataPicker("Verify that user can select Source Of Income <br>Test Data: <b>"+SourceOfIncome+"</b>", MonthlyIncome, 0);
		Thread.sleep(2000);
	/*	List<MobileElement> data= driver.findElements(By.className("android.widget.TextView"));
		int size = data.size();
		for (int i = 0; i < size; i++) {
			text = driver.findElements(By.className("android.widget.TextView")).get(i).getText();
			text = text.replace(",","");
			if(text.equalsIgnoreCase(MonthlyIncome)) {			
				try {
					driver.findElements(By.className("android.widget.TextView")).get(i).click();
					test.log(Status.PASS, "Verify that user can select Monthly Income <br>Test Data: <b>"+MonthlyIncome+"</b>");
					break;
				}catch(org.openqa.selenium.NoSuchElementException e){ 
					test.log(Status.FAIL, "Verify that user can select Monthly Income <br>Test Data: <b>"+MonthlyIncome+"</b>");
				}			
			}
		}		*/
		
		Thread.sleep(2000);
		DataPicker("Verify that user can tap on Bank button to open bank listing", "Where Do You Bank?", 0);	
		DataPicker("Verify that user can select bank <br>Test Data: <b>"+Bank+"</b>", Bank, 0);				
		DataPicker("Verify that user can select Loan status <br>Test Data: <b>"+CreditCardLoan+"</b>", CreditCardLoan, 3);
		DataPicker("Verify that user can select Intend to Acquire Button to open listing", "How Soon Would You Like To Acquire The Loan?", 0);
		DataPicker("Verify that user can select Intend to Acquire <br>Test Data: <b>"+IntendToAcquire+"</b>", IntendToAcquire, 0);
	}
}
