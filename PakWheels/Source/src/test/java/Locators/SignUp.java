package Locators;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class SignUp extends BaseHelper
{
	AppiumDriver<MobileElement> driver= null;
	ExtentTest test= null;	
	WebDriverWait wait= null;
	JSONObject Object = null;
	String OTP="";
	public SignUp(AppiumDriver<MobileElement> driver, ExtentTest test, WebDriverWait wait, JSONObject Object) 
	{
		super(driver, test, wait, Object);
		this.driver= driver;
		this.test= test;
		this.wait= wait;
		this.Object= Object;
	}
	
	public void SelectContinueWithEmail() throws Exception{
		String SignUpMethod= (String) Object.get("SignUpMethod");
		
		Scroll(SignUpMethod);
		DataPicker("Verify that select SignUp method <br>Test Data: <b>Continue with Email</b>", SignUpMethod, 2);
	}
	
	public void ClickSignUpLink() throws Exception{	
		try {
			driver.findElement(By.xpath("//android.widget.TextView[@text='Sign up']")).click();
			test.log(Status.PASS, "Verify that user can select Sign-up link");	
		}catch(org.openqa.selenium.NoSuchElementException e){
			test.log(Status.FAIL, "Verify that user can select Sign-up link");
			throw new Exception("Element not found");
		}	
		Thread.sleep(1000);
		driver.navigate().back();
	}
	
	public void FillData() throws InterruptedException{		
		/*SendText("Verify that user can enter Email <br>Test Data: <b>"+Base.Email+"</b>", "Enter Email", Base.Email);
		SendText("Verify that user can enter Name <br>Test Data: <b>"+Base.Name+"</b>", "Enter Name", Base.Name);
		SendText("Verify that user can enter Password <br>Test Data: <b>"+Base.Password+"</b>", "Enter password", Base.Password);	*/
		
try {
		
		MobileElement emailField = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText[@resource-id='email_address_input']")));			
		emailField.clear();
		emailField.sendKeys(Base.Email);
		
	    // Handle Name field
	    MobileElement nameField = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText[@resource-id='name_input']")));
	    nameField.clear();
	    nameField.sendKeys(Base.Name);
		//driver.findElement(By.xpath("//android.widget.EditText[@resource-id='name_input']")).sendKeys(Base.Name);
		MobileElement Password = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText[@resource-id='password_input']")));
		Password.clear();
		Password.sendKeys(Base.Password);
}
catch(org.openqa.selenium.NoSuchElementException e)
{
	test.log(Status.FAIL, "Verify that user cannot "+e.getMessage());
}
	}
	
	public void ClickSignUpButton() throws Exception{	
		DataPicker("Verify that user can tap on Sign-Up Button", "Sign up", 0);
		Thread.sleep(5000);
		try
		{
			if(driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/message']")).isDisplayed())
			{
				String message = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/message']")).getText();
				test.log(Status.FAIL, "<b style='color: red;'>Verify that user is unable to Sign up due to "+message+"</b>");
				System.out.println(message);
			}
		}
		catch(org.openqa.selenium.NoSuchElementException e){}
		
		/*
		try
		{
			
			if(driver.findElement(By.xpath("//android.widget.TextView[@text='Email Verification']")).isDisplayed())
			{
	
				if(Base.Email.contains("mailinator.com"))
                {

					//OTPmailinatordrop;
					
                }
				if(Base.Email.contains("maildrop.cc"))
                {
				     //OTPmaildrop();
                }				
		        driver.findElement(By.id("com.pakwheels.staging:id/otpField")).sendKeys(OTP);
			}    
		}
		catch(org.openqa.selenium.NoSuchElementException e){}*/
		
		
		
	}
	
	  public static void switchToNewTab(AppiumDriver<MobileElement> driver) {
	        // Get the current window handle (for the previous tab)
	        String currentWindowHandle = driver.getWindowHandle();

	        // Get all window handles (for all open tabs)
	        Set<String> windowHandles = driver.getWindowHandles();

	        // Find the handle of the new tab by excluding the handle of the previous tab
	        String newTabHandle = null;
	        for (String handle : windowHandles) {
	            if (!handle.equals(currentWindowHandle)) {
	                newTabHandle = handle;
	                break;
	            }
	        }

	        // Switch to the new tab
	        if (newTabHandle != null) {
	            driver.switchTo().window(newTabHandle);
	        }
	    }
	    public static String getEmailTextBeforeAt(String email) {
	        String[] parts = email.split("@");
	        if (parts.length > 0) {
	            return parts[0];
	        }
	        return null;
	    }
	    

		  public static void switchToPreviousTab(AppiumDriver<MobileElement> driver) {
		        // Get all window handles (for all open tabs)
		        Set<String> windowHandles = driver.getWindowHandles();

		        // Find the handle of the previous tab
		        String previousTabHandle = null;
		        for (String handle : windowHandles) {
		            if (!handle.equals(driver.getWindowHandle())) {
		                previousTabHandle = handle;
		                break;
		            }
		        }

		        // Switch to the previous tab
		        if (previousTabHandle != null) {
		            driver.switchTo().window(previousTabHandle);
		        }
		    }
		  
		    public static String extractNumberFromString(String input) {
		        Pattern pattern = Pattern.compile("\\d+");
		        Matcher matcher = pattern.matcher(input);
		        //System.out.println("function to get OTP");
		        if (matcher.find()) {
		        	//System.out.println("function to get OTP");
		            return matcher.group();
		            
		        }
		        return null;
		    }

	private void OTPmailinatordrop() throws InterruptedException {
		try {
			
			((JavascriptExecutor) driver).executeScript("window.open();");
			switchToNewTab(driver);
			
		     driver.navigate().to("https://www.mailinator.com/");
	           // driver.get("https://www.mailinator.com/");
		     test.log(Status.PASS, "user naviagted to mailinator.com");
		     String textBeforeAt = getEmailTextBeforeAt(Base.Email);
		     driver.findElement(By.xpath("//input[@id='search-mobile']")).sendKeys(textBeforeAt);
		     test.log(Status.PASS, "user search email address at mailinator.com");
		     driver.findElement(By.xpath("//button[@aria-label='Search for inbox']")).click();
		     test.log(Status.PASS, "user naviagted to inbox of mailinator.com");
       
		Thread.sleep(6000);
		if(driver.findElement(By.xpath("//div[normalize-space()='PakWheels']")).isDisplayed())
		{
			test.log(Status.PASS, "user naviagted to inbox of mailinator.com");
			Thread.sleep(2000);
				String OTPtext=driver.findElement(By.xpath("//a[@class='text-decoration-none color-main ng-binding'][contains(.,'Your email verification code is')]")).getText();
				System.out.println("Number: " + OTPtext);
				Thread.sleep(2000);
				String number = extractNumberFromString(OTPtext);
				System.out.println("Number: " + number);
				OTP=number;
				switchToPreviousTab(driver);
		}
	} catch (org.openqa.selenium.NoSuchElementException e) {}
		
	}
}
