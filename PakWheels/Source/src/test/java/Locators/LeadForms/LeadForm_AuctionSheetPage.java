package Locators.LeadForms;
import org.json.simple.JSONObject;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class LeadForm_AuctionSheetPage extends Leadforms
{
	AppiumDriver<MobileElement> driver= null;
	ExtentTest test= null;	
	WebDriverWait wait= null;
	JSONObject Object = null;
	public LeadForm_AuctionSheetPage(AppiumDriver<MobileElement> driver, ExtentTest test, WebDriverWait wait, JSONObject Object) 
	{
		super(driver, test, wait, Object);
		this.driver= driver;
		this.test= test;
		this.wait= wait;
		this.Object= Object;
	}
	
	public void NavigateToAuctionSheetPage() throws Exception{	
		DataPicker("Verify that user can tap on Auto Services button to open Drop-Down", "Auto Services", 0);        
		DataPicker("Verify that user can navigate to Auction Sheet page by cliking on Auction Sheet button", "Auction Sheet Verification", 0);
	}
	
	public void EnterChasisNumber() throws Exception{
		String Chassis= (String) Object.get("Chassis");
		//System.out.println(Chassis);
		
		test.log(Status.INFO, "User is navigated to Auction Sheet page");
		SendText("Verify that user can enter Chasis Number <br>Test Data: <b>"+Chassis+"</b>", "Enter Chassis Number(e.g ZZT240–316982)", Chassis);
		DataPicker("Verify that user can click on <b>Get Auction Sheet</b> Button", "Verify Auction Sheet", 2);	
		test.log(Status.INFO, "User is navigated to Auction Sheet Form");
	}
	
	public void ProceedToCheckOut() throws Exception{	
		DataPicker("Verify that user can click on <b>Buy Now</b> button", "Buy Now", 2);
		Thread.sleep(2000);
	}
}