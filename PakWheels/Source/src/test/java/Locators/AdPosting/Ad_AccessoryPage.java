package Locators.AdPosting;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Ad_AccessoryPage extends AdPosting
{
	AppiumDriver<MobileElement> driver= null;
	ExtentTest test= null;	
	WebDriverWait wait= null;
	JSONObject Object = null;
	public Ad_AccessoryPage(AppiumDriver<MobileElement> driver, ExtentTest test, WebDriverWait wait, JSONObject Object) 
	{
		super(driver, test, wait, Object);
		this.driver= driver;
		this.test= test;
		this.wait= wait;
		this.Object= Object;
	}
		
	public void NavigateToAccessoryAdPost() throws Exception
	{	
		ClickSellButton();
		DataPicker("Verify that user can Select Ad Type <br>Test Data: <b>Auto Parts</b>", "Auto Parts", 0);     
	}
	
	public void SelectCategory() throws Exception
	{	
		String Category= (String) Object.get("Category");	
		DataPicker("Verify that user can select category button to open category listing", "Category", 0);
		SendText("Verify that user can enter text to refine Category search", "Type to refine search", Category);
		DataPicker("Verify that user can select category <br>Test Data: <b>"+Category+"</b>", Category, 0);       	
	}
	
	public void EnterTitle() throws InterruptedException
	{		
		String Title= (String) Object.get("Title");
		SendText("Verify that user can enter Title <br>Test Data: <b>"+Title+"</b>", "Enter Title", Title);
	}
	
	public void EnterPrice() throws InterruptedException
	{  
		Scroll("Set a price");
		String PriceValue= (String) Object.get("Price");
		SendText("Verify that user can enter Price <br>Test Data: <b>"+PriceValue+"</b>", "Set a price", PriceValue);		
	}
	
	public void EnterDescription() throws InterruptedException
	{	
		ScrollForward();
		String Description= (String) Object.get("Description");
		SendText("Verify that user can enter Description <br>Test Data: <b>"+Description+"</b>", "For Example: Alloy Rims, First Owner, etc.", Description);	
	}
	
	public void checkAdStatus() throws Exception
	{
		Thread.sleep(3000);	
		
		try
		{
			if (driver.findElement(By.xpath("//android.widget.TextView[@text='Edit']")).isDisplayed())
			{
				test.log(Status.INFO, "Ad has been posted successfully and is <b>Active</b>");
				isActive=true;
			}
		}
		catch(org.openqa.selenium.NoSuchElementException e) 
		{
			try
			{
				if (driver.findElement(By.xpath("//android.widget.TextView[@text='Your Free Ad Limit has Exceeded']")).isDisplayed())
				{
					test.log(Status.INFO, "Ad has been posted but is in <b>Limit Exceed</b>");
					isLimitExceed= true;
				}
			}
			catch(org.openqa.selenium.NoSuchElementException ex) 
			{
				test.log(Status.FAIL, "Ad Failed to post");
				throw new Exception("Element not found");
			}
		}
	}
}
