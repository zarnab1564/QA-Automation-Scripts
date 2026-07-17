package Locators;
import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


public class Chat extends BaseHelper{

	AppiumDriver<MobileElement> driver= null;
	ExtentTest test= null;
	WebDriverWait wait= null;
	JSONObject Object = null;
	public Chat(AppiumDriver<MobileElement> driver, ExtentTest test, WebDriverWait wait, JSONObject Object) {
		super(driver, test, wait, Object);
		this.driver= driver;
		this.test= test;
		this.wait= wait;
		this.Object= Object;
	}
	public void openAdDeatil() throws Exception {
           
		String Value= (String) Object.get("Search");
		    ClickHomeButton();
		    if(Value.contains("Car"))
		    {
			test.log(Status.INFO, "User is navigated to Used Cars Page");
			DataPicker("Verify that User can navigate to <b>Used Cars Search landing screen</b>", "Search used cars", 0);	
		    }
		    if(Value.contains("Bike"))
		    {
		    	DataPicker("Verify that user can select <b>Bike</b> tab from headers", "Bikes", 0);
		    	DataPicker("Verify that User can navigate to <b>Used Bikes Search landing</b> screen", "Search used bikes", 0);
		    }
			DataPicker("Verify that user can select <b>Advanced Search</b>", "Advanced Search", 0);
			test.log(Status.INFO, "User is navigated to Advance Search Filters Screen");
			DataPicker("Verify that user can select <b>Apply Filters</b> button", "Apply Filters", 2);
			Thread.sleep(2000);
			SelectAd();
	}
	
	public void openAdViaADB() {
	    String adUrl = (String) Object.get("ADIdURL");

	    if (adUrl == null || adUrl.isEmpty()) {
	        System.out.println("Ad URL is empty or null.");
	        return;
	    }

	    System.out.println("Launching ad using ADB: " + adUrl);

	    try {
	        String adbPath = "/Users/anamzahid/android-sdk/platform-tools/adb";
	        String command = String.format(
	            "%s shell am start -a android.intent.action.VIEW -d \"%s\"",
	            adbPath,
	            adUrl
	        );

	        Process process = Runtime.getRuntime().exec(command);
	        process.waitFor();

	        System.out.println("ADB command executed successfully.");
	        Thread.sleep(3000);

	    } catch (Exception e) {
	        System.out.println("Error while launching ad URL via ADB: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	public void clickChatbutton() throws Exception {
		// TODO Auto-generated method stub
		DataPicker("Verify that user can Click on  <b>Chat</b> button","Chat", 2);
	//	DataPicker("Verify that user can Click on  <b>Chat</b> button","Chat", 0);
		
		try
		{
			if((driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.pakwheels.staging:id/bottomsheet_title']")).getText()).equals("We care about your safety"))
			{
				DataPicker("Verify that  <b>We care about your safety</b> bottom sheet appearing", "We care about your safety", 0);
				DataPicker("Verify that user can select <b>Okay, contact the seller</b> button", "Okay, contact the seller", 2);
			}
		}
		catch(org.openqa.selenium.NoSuchElementException e){
			test.log(Status.INFO,"<b> Verified that We care about your safety bottom sheet does not Found</b>");}
	}
	public void SendChat() throws Exception {
		// TODO Auto-generated method stub
		DataPicker("Verify that  <b>Send Message</b> Text Field appearing", "Send Message", 0);
		driver.findElement(By.xpath("//android.widget.EditText")).sendKeys("Chat Message for Testing");
		test.log(Status.INFO, "User send Chat Message");
		driver.findElement(By.xpath("//android.view.View[@content-desc='send']")).click();
	}

}
