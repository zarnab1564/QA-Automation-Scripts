package Locators;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;


public class BaseHelper {
	AppiumDriver<MobileElement> driver= null;
	ExtentTest test= null;
	WebDriverWait wait= null;
	JSONObject Object = null;
	public BaseHelper(AppiumDriver<MobileElement> driver, ExtentTest test, WebDriverWait wait, JSONObject Object) 
	{
		this.driver= driver;
		this.test= test;
		this.wait= wait;
		this.Object= Object;
	}
	
	public String text;
	public String Back1= "com.pakwheels.staging:id/btnBackListingCollapsed";
	public String Back2=  "android.widget.ImageButton";
			
	public void DataPicker(String TestCaseName, String Value, int Type) throws Exception 
	{
		String ClassType = null;
		if (Type==0)
			ClassType="android.widget.TextView";
		else if (Type==1)
			ClassType= "android.widget.CompoundButton";
		else if (Type==2)
			ClassType= "android.widget.Button";
		else if (Type==3)
			ClassType= "android.widget.RadioButton";
		else if (Type==4)
			ClassType= "android.widget.EditText";
		else if (Type==5)
			ClassType= "android.view.ViewGroup";
		else if(Type==6)
			ClassType="android.widget.CheckBox";
		
	
		try {			
			driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))" + ".scrollIntoView(new UiSelector().className(\""+ClassType+"\").text(\""+Value+"\"))"));			
			driver.findElement(By.xpath("//"+ClassType+"[@text='"+Value+"']")).click();
			test.log(Status.PASS, TestCaseName);
		}
		catch(org.openqa.selenium.NoSuchElementException e){
			test.log(Status.FAIL, TestCaseName+"<b> does not Found</b>");
			throw new Exception("Element not found");
		}
	}
	
	public void SendText(String TestCaseName, String Locator, String Value) throws InterruptedException
	{
		Thread.sleep(2000);
		List<MobileElement> data= driver.findElements(By.className("android.widget.EditText"));
		int size = data.size();
		for (int i = 0; i < size; i++) {
			text = driver.findElements(By.className("android.widget.EditText")).get(i).getText();
			if(text.equalsIgnoreCase(Locator)) {
				driver.findElements(By.className("android.widget.EditText")).get(i).sendKeys(Value);
				test.log(Status.PASS, TestCaseName);
				break;
			}
		}
		
	}
	


	
	//============================================= Application Start =========================================================
	
	public void SetApplicationLanguage() throws Exception
	{	
		test.log(Status.INFO, "<b><h3>Test Started</h3></b>");
		driver.findElement(By.xpath("//android.widget.RadioButton[@index='0']")).click();     
		test.log(Status.PASS, "Verify that user can set application language (Test Data: English)");
		//driver.hideKeyboard();
		DataPicker("Verify that user can select Next button", "Next", 2);                       	
	}
	
	public void ClickMoreButton() throws Exception
	{
		try {
			
			if(Base.Testing_Enviroment.equalsIgnoreCase("Staging"))
			{
				driver.findElement(By.id("com.pakwheels.staging:id/menu_item_more")).click(); 
			}
			if(Base.Testing_Enviroment.equalsIgnoreCase("Production"))
			{	
			  driver.findElement(By.id("com.pakwheels:id/menu_item_more")).click(); 
			}

			test.log(Status.PASS, "Verify that user can navigate to More page");
		}catch(org.openqa.selenium.NoSuchElementException e){			
			test.log(Status.FAIL, "Verify that user can navigate to More page");
			throw new Exception("Element not found");
		}	
	}
	
	public void ClickHomeButton() throws Exception
	{
		Thread.sleep(2000);
		try {
			
			DataPicker("Verify that user can select Home button", "Home", 0); 
			test.log(Status.PASS, "Verify that user can navigate to Home page");
		}catch(org.openqa.selenium.NoSuchElementException e){
			test.log(Status.FAIL, "Verify that user can navigate to Home page");
			throw new Exception("Element not found");
		}		
	}
	
	public void ClickSignInButton() throws Exception{	
		ScrollToEnd();
		ClickMoreButton();
		DataPicker("Verify that user can tap on Log in / Sign up Button", "Log in / Sign up", 0);              
	}
	public void SelectContinueButton() throws Exception
	{
		DataPicker("Verify that user can select continue button", "Continue", 2);   
		Thread.sleep(1000);

	}

	public void Login() throws Exception
	{		
		SetApplicationLanguage();
		
		try{
			driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_button")).click();
			test.log(Status.PASS, "Verify that user can select Allow button to give access to Gallery");  		
		}catch(org.openqa.selenium.NoSuchElementException e){
			test.log(Status.INFO, "Gallery permission is already granted to the application"); 
		}
		
		if (((String) Object.get("Login")).equals("Yes"))
		{	
			test.log(Status.INFO, "<h3><b>Login Screen</b></h3>");
			ClickSignInButton();			
			DataPicker("Verify that select Login method <br>Test Data: <b>Continue with Email</b>", "Continue with Email", 2); 
			
			
			//MobileElement emailField = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email_address_input")));
			MobileElement emailField = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText[@resource-id='email_address_input']")));			
			emailField.clear();
			emailField.sendKeys(Base.Email);
			
			MobileElement Password = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText[@resource-id='password_input']")));
			Password.clear();
			Password.sendKeys(Base.Password);
			//driver.findElement(By.id("email_address_input")).sendKeys(Base.Email);
			//driver.findElement(By.id("//android.widget.EditText[@resource-id='password_input']")).sendKeys(Base.Password); 
			/*SendText("Verify that user can enter Email <br>Test Data: <b>"+Base.Email+"</b>", "username@email.com", Base.Email);
			SendText("Verify that user can enter Password <br>Test Data: <b>"+Base.Password+"</b>", "Enter password", Base.Password);*/			
			DataPicker("Verify that user can login", "Sign in", 0);  
			try
			{
				if(driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/message']")).isDisplayed())
				{
					String message = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/message']")).getText();
					test.log(Status.FAIL, "<b style='color: red;'>Verify that user is unable to login up due to "+message+"</b>");
					System.out.println(message);
				}
			}
			catch(org.openqa.selenium.NoSuchElementException e){}

			//checkUserLoggedIn();
		}
		else if(((String) Object.get("Login")).equals("No"))
		{
			ScrollToEnd();
			ClickMoreButton();
			Thread.sleep(2000);
		}
	}
	public void ClickSellButton() throws Exception
	{
		try {
			driver.findElement(By.id("com.pakwheels.staging:id/fab_home_layout")).click();                       
			test.log(Status.PASS, "Verify that user can tap on <b>Sell</b> Button to select Ad type");
		}catch(org.openqa.selenium.NoSuchElementException e){
			test.log(Status.FAIL, "Verify that user can tap on <b>Sell</b> Button to select Ad type");
			throw new Exception("Element not found");
		}
	}
	
	public void checkUserLoggedIn() {
        // Explicit wait to check if the element is visible
        WebDriverWait wait = new WebDriverWait(driver, 50);  // Wait for 30 seconds
        try {
            // Wait for the element to be visible
            MobileElement profileTextView = (MobileElement) wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("com.pakwheels.staging:id/myProfileTextViewClickable")));
            System.out.println(profileTextView.isDisplayed());
            if (profileTextView.isDisplayed()) {
            	
            	System.out.println("In if pass condition");
                test.log(Status.INFO, "User has successfully logged in.");
            } else {
                test.log(Status.FAIL, "User has not successfully logged in.");
            }
        } catch (Exception e) {
            // If the element is not visible within the wait time, log failed login
            test.log(Status.FAIL, "User has not successfully logged-in");
        }
    }
	
	public void SelectMakeModelVersion() throws Exception
	{			
		JSONObject CarObj = (JSONObject) Object.get("CarInformation");
		String MakeModel= (String) CarObj.get("Model");
		String Version= (String) CarObj.get("Version");
		
		//DataPicker("Verify that user can select car model button to open model listing", "Select Brand Model", 0);
				
		SendText("Verify that user can enter text to refine Make Model search", "Type to refine search", MakeModel);
		DataPicker("Verify that user can select car Make and Model <br>Test Data: <b>"+MakeModel+"</b>", MakeModel, 0);   

		if (Version != null && !Version.isEmpty()) 
			DataPicker("Verify that user can select car Version <br>Test Data: <b>"+Version+"</b>", Version, 0);    				
	}
	

	
	/*public void SelectLocation() throws Exception
	{
		 JSONObject locationObj = (JSONObject) Object.get("Location");
		 String City= (String) locationObj.get("City");
		 String CityArea= (String) locationObj.get("CityArea");
		 
		
		if (!City.isEmpty())
		 {
			DataPicker("Verify that user can select <b>Location</b> button", "Location", 0);
			SendText("Verify that user can enter text to refine City search", "Type to refine search", City);		
			DataPicker("Verify that user can select City <br>Test Data: <b>"+City+"</b>", City, 0);
	
		}
	
	}*/
	public void SelectLocation() throws Exception {
	    JSONObject locationObj = (JSONObject) Object.get("Location");

	    String city = (String) locationObj.get("City");
	    String cityArea = (String) locationObj.get("CityArea");

	    if (city != null && !city.trim().isEmpty()) {
	        DataPicker("Verify that user can select <b>Location</b> button", "Location", 0);
	        SendText("Verify that user can enter text to refine City search", "Type to refine search", city);
	        DataPicker("Verify that user can select City <br>Test Data: <b>" + city + "</b>", city, 0);
	    }

	    if (cityArea == null || cityArea.trim().isEmpty()) {
	        waitForPushNotificationToDisappear();
	        DataPicker("Verify that user can select cancel button to not select any city area", "Cancel", 0);
	    } else {
	        SendText("Verify that user can enter text to refine City Area search", "Type to refine search", cityArea);
	        DataPicker("Verify that user can select City Area <br>Test Data: <b>" + cityArea + "</b>", cityArea, 0);
	    }
	}
	// Better than Thread.sleep() – allows dynamic waiting
	private void waitForPushNotificationToDisappear() {
	    try {
	        Thread.sleep(5000); // Replace with explicit wait if possible
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }
	}
	
	public void SelectCheckoutButton() throws Exception 
	{
		DataPicker("Verify that User can select <b>Checkout</b> Button", "Checkout", 2);
	}
	
	public void EnterContactInformation() throws InterruptedException
	{
		SendText("Verify that user can enter Name <br>Test Data: <b>"+Base.Name+"</b>", "Enter Name", Base.Name);
		SendText("Verify that user can enter Mobile Number <br>Test Data: <b>"+Base.MobileNumber+"</b>", "Enter Mobile Number", Base.MobileNumber);
		SendText("Verify that user can enter Email <br>Test Data: <b>"+Base.Email+"</b>", "Email (Optional)", Base.Email); 
		SendText("Verify that user can enter Email <br>Test Data: <b>"+Base.Email+"</b>", "Enter Email", Base.Email); 
		driver.hideKeyboard();
	}
	
	public void NavigateToNewCarSearch() throws Exception{  
		ClickHomeButton();
		DataPicker("Verify that user can click <b>New Cars</b> tab from headers", "New Cars", 3);
		test.log(Status.INFO, "User is navigated to New Cars page");
	}
	
	public void SelectCarMake() throws Exception
	{	
		JSONObject CarInfo = (JSONObject) Object.get("CarInformation");
		String Make= (String) CarInfo.get("Make");
		
		Thread.sleep(2000);
		DataPicker("Verify that user can select car make <br>Test Data: <b>"+Make+"</b>", Make, 0);
	}
	
	public void SelectCarModel() throws Exception
	{
		JSONObject CarInfo = (JSONObject) Object.get("CarInformation");
		String Model= (String) CarInfo.get("Model");
		
		ScrollToBeginning();		
		Thread.sleep(10000);;
		DataPicker("Verify that user can select car model <br>Test Data: <b>"+Model+"</b>", Model, 0);			
	}
	
	//======================================================== ** Ad Posting ** ============================================================

	public void SelectInspectionSlotB() throws Exception
	{
		Boolean flag=false,Slot=false,flag_noSlot=false;
		MobileElement Date= null;
		 Thread.sleep(5000);
		  try
		  {  
			try {
					Date= driver.findElement(By.id("com.pakwheels.staging:id/rv_inspection_days"));  // Parent of date chip
					System.out.println(Date);
				}
			    catch(org.openqa.selenium.NoSuchElementException e){ 				
					test.log(Status.FAIL, "User is unable to find parent of date chip");
					throw new Exception("Element not found");			
				}
			  System.out.println("Click on Date"+Date);
			  for(int i=1;i<=4;i++)
			  {
				  System.out.println("Index Loop"+i);
				  Date.findElement(By.xpath("//android.widget.FrameLayout[@index='"+i+"']")).click();
				  System.out.println("Test Click on Date");
				  test.log(Status.INFO, "User clicks on Date chip at index "+i);
				  ScrollForward();
				  System.out.println("Test Click on Date B");
				  Thread.sleep(2500);
				  try
                  {
					  flag_noSlot=driver.findElement(By.id("com.pakwheels.staging:id/ll_no_slots_available")).isDisplayed();
					  System.out.println(flag_noSlot);
                  }
                  catch(org.openqa.selenium.NoSuchElementException e)
                  {
                 	 System.out.println("No Slot Available Widget appears "+flag_noSlot);
                  }
                  try
                  {
                	  
                    flag=driver.findElement(By.id("com.pakwheels.staging:id/inspection_slot_time_card")).isDisplayed();
                    System.out.println(flag);
    				//MobileElement Parent= driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"com.pakwheels.staging:id/inspection_slot_time_card\"]"));	
    				//MobileElement element= Parent.findElement(By.xpath("//android.widget.FrameLayout[@index='"+i+"']"));	
                  }
                  catch(org.openqa.selenium.NoSuchElementException e)
                  {
                	  System.out.println("Slot Available Widget not appears "+flag);
                  }
                  
                  if(flag.equals(true))
				  {
                	System.out.println("In Slot Selction at index "+i);
                    driver.findElement(By.id("com.pakwheels.staging:id/lbl_slot_time")).click();
    				//MobileElement Parent= driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"com.pakwheels.staging:id/inspection_slot_time_card\"]"));	
    				//MobileElement element= Parent.findElement(By.xpath("//android.widget.FrameLayout[@index='"+i+"']"));	
		            test.log(Status.PASS,"Verify that Slot is selected");
		            Slot=true;
		            break;
				  }
                  System.out.println(flag + "Index" + i);
                  System.out.println("out of  Slot Selction at index "+i);
               
			   } 
               if(Slot!=true || driver.findElement(By.id("com.pakwheels.staging:id/ll_no_slots_available")).isDisplayed())
               {
                	  DataPicker("Verify that User can select <b>I can�t find my slot here</b> Button", "I can�t find my slot here",6 );
                	  test.log(Status.INFO,"Verify that Slot not found and selected");
                	  System.out.println("Not  slot Selected Displayed");  
               }
		  }
		  catch(org.openqa.selenium.NoSuchElementException e)
		  {    
			   //Click Checkbox if slot not available
		       test.log(Status.INFO, "Verify that no slot available");
		  }
	}
	public void SelectInspectionSlot() throws Exception
	{
		/*DataPicker("Verify that user can select Inspection Slot button", "Inspection Slot", 0);
		Thread.sleep(2000);
		int flag=0;
		
		for (int i=1; i<=7; i++) {   // Finding Data that has any slot
			try 
			{
				MobileElement Parent= driver.findElement(By.xpath("//android.widget.RelativeLayout[@index='4']"));  // If no slot is available
				Parent.findElement(By.xpath("//android.widget.TextView[@text='Slots not available']"));		
							
				MobileElement element= driver.findElement(By.className("androidx.recyclerview.widget.RecyclerView"));	// Switch to next date
				element.findElement(By.xpath("//android.widget.TextView[@index='"+i+"']")).click();
				Thread.sleep(2000);			
			}catch(org.openqa.selenium.NoSuchElementException e){      // If slot found, break
				flag=1;
				test.log(Status.INFO, "User found an available slot");
				break;
			}			
		}
		
		if (flag == 1)  // Slot found
		{
			for (int i=0; i<16; i++)    // Clicking first available time
			{
				MobileElement Parent= driver.findElement(By.xpath("//android.widget.RelativeLayout[@index='4']"));	
				MobileElement element= Parent.findElement(By.xpath("//android.widget.TextView[@index='"+i+"']"));		
				if(element.isEnabled()){     // Time is available to select
					element.click();
					test.log(Status.PASS, "Verify that user can select a time for his slot");
					break;
				}		
			}
		}
		else if (flag == 0)   // No slot found against any date
		{
			test.log(Status.INFO, "User found did not found any available slot");			
			try{
				driver.findElement(By.className("android.widget.CheckBox")).click();       // Click I can't find my slot
				test.log(Status.PASS, "Verify that user can select I can't find my slot");
			}catch(org.openqa.selenium.NoSuchElementException e){ 				
				test.log(Status.FAIL, "Verify that user can select I can't find my slot");
				throw new Exception("Element not found");			
			}
		}
		
		DataPicker("Verify that user can select Confirm button", "Confirm", 2);*/
		
		Thread.sleep(2000);
		int flag=0;	
		MobileElement DateChip= null;
		MobileElement SlotText= null;
		
		try {
			DateChip= driver.findElement(By.id("com.pakwheels.staging:id/rv_inspection_days"));  // Parent of date chip
		}catch(org.openqa.selenium.NoSuchElementException e){ 				
			test.log(Status.FAIL, "User is unable to find parent of date chip");
			throw new Exception("Element not found");			
		}
		
		try {
			SlotText= driver.findElement(By.id("com.pakwheels.staging:id/card_container"));  // Parent of no slot text
		}catch(org.openqa.selenium.NoSuchElementException e){ 				
			test.log(Status.FAIL, "User is unable to find parent of no slot text");
			throw new Exception("Element not found");			
		}
		
		for (int i=2; i<4; i++) 
		{   // Finding Data that has any slot
			try 
			{			
				DateChip.findElement(By.xpath("//android.widget.FrameLayout[@index='"+i+"']")).click();  // Select date		
				ScrollForward();
				//SlotText.findElement(By.xpath("//android.widget.TextView[@text='Slots not available']"));			
				Thread.sleep(2000);			
			}catch(org.openqa.selenium.NoSuchElementException e){      // If slot found, break
				flag=1;
				test.log(Status.INFO, "User found an available slot");
				break;
			}			
		}
		
		if (flag == 1)  // Slot found
		{
			for (int i=1; i<5; i++)    // Clicking first available time
			{
				MobileElement Parent= driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='com.pakwheels.staging:id/inspection_slot_time_card']"));	
				MobileElement element= Parent.findElement(By.xpath("//android.widget.FrameLayout[@index='"+i+"']"));		
				if(element.isEnabled()){     // Time is available to select
					element.click();
					test.log(Status.PASS, "Verify that user can select a time for his slot");
					break;
				}		
			}
		}
		else if (flag == 0)   // No slot found against any date
		{
			test.log(Status.INFO, "User found did not found any available slot");			
			try{
				driver.findElement(By.id("com.pakwheels.staging:id/checkbox_no_slot")).click();       // Click I can't find my slot
				test.log(Status.PASS, "Verify that user can select I can't find my slot");
			}catch(org.openqa.selenium.NoSuchElementException e){ 				
				test.log(Status.FAIL, "Verify that user can select I can't find my slot");
				throw new Exception("Element not found");			
			}
		}
		
		
	}	
	
	
	public void SlotSelection() throws Exception
	{
		test.log(Status.INFO, "<b>Slot Selection</b>");
    // Check if slots are available based on the presence of time slot elements
    boolean slotsAvailable = areSlotsAvailable();
    

    
    if (slotsAvailable) {
        // Slot is available - handle normal slot selection
        handleAvailableSlots();
    } else {
        // No slots available - handle the no-slot scenario
        handleNoSlotsAvailable();
    }
    
      if (slotsAvailable) 
      {
    	System.out.println("Slot Gets Selected .......");
      } 
      else 
      {
    	System.out.println("Slot not Available ........");
      }
    
	}
	
    private boolean areSlotsAvailable() {
        try {
            // Check if time slots are present

            List<MobileElement> timeSlots = driver.findElements(By.xpath("(//android.widget.FrameLayout[@resource-id='com.pakwheels.staging:id/inspection_slot_time_card'])"));
            if (!timeSlots.isEmpty()) {
                return true;
            }
            
            // If no time slots are found, check for "Slot not found" widget
            return driver.findElements(By.id("com.pakwheels.staging:id/ll_no_slots_available")).isEmpty();
        } catch (NoSuchElementException e) {
            // If an exception occurs, assume no slots are available
            return false;
        }
    }
    
    private void handleAvailableSlots() {
        // Verify that a date is auto-selected
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//android.widget.FrameLayout[@resource-id='com.pakwheels.staging:id/slot_day_card'])[1]/android.view.ViewGroup")));
        MobileElement selectedDate = driver.findElement(By.xpath("(//android.widget.FrameLayout[@resource-id='com.pakwheels.staging:id/slot_day_card'])[1]/android.view.ViewGroup"));
        Assert.assertTrue(selectedDate.isDisplayed(), "A date should be auto-selected after city area selection");
       // selectedDate.click();
        
        // Verify that a time slot is auto-selected
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//android.widget.FrameLayout[@resource-id='com.pakwheels.staging:id/inspection_slot_time_card'])[1]/android.widget.LinearLayout")));
        MobileElement selectedTimeSlot = driver.findElement(By.xpath("(//android.widget.FrameLayout[@resource-id='com.pakwheels.staging:id/inspection_slot_time_card'])[1]/android.widget.LinearLayout"));
        Assert.assertTrue(selectedTimeSlot.isDisplayed(), "A time slot should be auto-selected");
        selectedTimeSlot.click();
        test.log(Status.PASS, "Verify that user selected  1st available slot");
    }
    
    private void handleNoSlotsAvailable() {
        // Verify "Slot not found" widget is displayed
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.pakwheels.staging:id/ll_no_slots_available")));
        MobileElement slotNotFoundWidget = driver.findElement(By.id("com.pakwheels.staging:id/ll_no_slots_available"));
        Assert.assertTrue(slotNotFoundWidget.isDisplayed(), "Slot not found widget should be displayed");
        
        // Check "No slot available" checkbox
        MobileElement noSlotCheckbox = driver.findElement(By.id("com.pakwheels.staging:id/checkbox_no_slot"));
        noSlotCheckbox.click();
        test.log(Status.PASS, "Verify that user selectedno slot check box in case of no slot found");
    }
	
	
	public void SelectRegisteredCity() throws Exception
	{
		Thread.sleep(2000);
		String City= (String) Object.get("RegisteredIn");
		if (!City.isEmpty()) 
		{
			DataPicker("Verify that user can select <b>Registered-In</b> button to open city listing", "Registered In", 0);       
			SendText("Verify that user can enter text to refine Registered City search", "Type to refine search", City);		
			DataPicker("Verify that user can select Registered City <br>Test Data: <b>"+City+"</b>", City, 0);
		}
	}
	
	public void EnterVerificationCode() throws Exception
	{	Thread.sleep(2000);
		try 
		{
			//System.out.println("Number verification in if condition");
			if(driver.findElement(By.xpath("//android.widget.TextView[@text='Verify phone number']")).isDisplayed())
			{		//System.out.println("Number verification in if condition");
				test.log(Status.INFO, "<b>Number verification screen</b>");
				try {
					if(driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.pakwheels.staging:id/otpField']")).isDisplayed())
					{
						//System.out.println("Number verification Appearing....");
						driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.pakwheels.staging:id/otpField']")).click();
					}
					Thread.sleep(2000);
					driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.pakwheels.staging:id/otpField']")).sendKeys(Base.VerificationCode);
					test.log(Status.PASS, "Verify that user can enter Verification code");
				}catch(org.openqa.selenium.NoSuchElementException ex){ 
					test.log(Status.FAIL, "Verify that user can enter Verification code");
				}	
			}
		}catch(org.openqa.selenium.NoSuchElementException ex){  // Catch in case number is already verified
			test.log(Status.INFO, "Number is already Verified");
		}
	}
	
	public void ApplyDiscountCode(String DiscountCode) throws Exception
	{
		if (DiscountCode != null && !DiscountCode.isEmpty()) 
		{
			SendText("Verify that user can enter Discount Code", "Enter Discount Code", DiscountCode);
			DataPicker("Verify that User can select <b>Apply</b> Button", "Apply", 2);
			
			try {
				driver.findElement(By.xpath("//android.widget.Button[@text='OK']")).click();
				test.log(Status.PASS, "Verify that user can select OK button incase of Invalid Discount Code");
			}catch(org.openqa.selenium.NoSuchElementException ex){  
				test.log(Status.INFO, "Discount Code Applied");
			}
		}
	}
	
	public boolean PaymentFlow(String PaymentMethod) throws Exception
	{
		//String PaymentMethod= (String) Object.get("PaymentMethod");
		//DataPicker("Verify that user can select Payment Method <br> Test Data: <b>"+PaymentMethod+"</b>", PaymentMethod, 0);
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+PaymentMethod+"']")).click();
		DataPicker("Verify that user can select <b>Place Order</b> Button", "Place Order", 2);
		
		Boolean status= false;
		if (PaymentMethod.equalsIgnoreCase("JazzCash Mobile Account"))
		{
			test.log(Status.INFO, "<b>Payment Screen (Jazz cash mobile account)</b>");
			test.log(Status.INFO, "User is on JAZZ CASH Screen");
			SendText("Verify that user can Enter Mobile Number", "03001234567", Base.JazzCashNumber);
			SendText("Verify that user can Enter CNIC", "00000-0000000-0", Base.JazzCashCNIC);
			driver.hideKeyboard();
			
			DataPicker("Verify that User can select Continue Button", "Continue", 2);
			wait = new WebDriverWait(driver,150);			
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Button[@text='Done']"))).click();
				test.log(Status.INFO, "Payment has been done");
				status= true;
			}catch(org.openqa.selenium.NoSuchElementException ex){  
				test.log(Status.FAIL, "Payment failed");
				status= false;
			}
			Thread.sleep(5000);
		}
		else if (PaymentMethod.equalsIgnoreCase("EasyPay Mobile Account"))
		{
			
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
		return status;
	}
	
	public void SelectAd() throws Exception
	{
		try {
			if(Base.Testing_Enviroment.equalsIgnoreCase("Staging"))
	    	{
			driver.findElement(By.id("com.pakwheels.staging:id/txtview_ad_title_search_action_item")).click(); 
	    	}
			else if(Base.Testing_Enviroment.equalsIgnoreCase("Production"))
	    	{
			driver.findElement(By.id("com.pakwheels:id/txtview_ad_title_search_action_item")).click(); 
	    	}
			test.log(Status.PASS, "Verify that user can select Ad from Listing Page");
		 }catch(org.openqa.selenium.NoSuchElementException e){
			 test.log(Status.FAIL, "Verify that user can select Ad from Listing Page");
			 throw new Exception("Element not found");
		 }
	}
	
	
	// ================================================= ** Lead Forms ** ===================================================================
	
	public void ClickSubmitButton() throws Exception
	{	
		DataPicker("Verify that user can select Submit button", "Submit", 2);
		Thread.sleep(2000);
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
	public void CheckThankyouScreen() throws Exception
	{			
		try {
			driver.findElement(By.xpath("//android.widget.Button[@text='Done']"));
			test.log(Status.INFO, "User is navigated to thank you screen");
		}catch(org.openqa.selenium.NoSuchElementException e){
			test.log(Status.FAIL, "User failed to navigate to thank you screen");
			throw new Exception("Element not found");
		}		
		DataPicker("Verify that user can click Done button", "Done", 2);			
	}
	
	//=====================================================** Search **=======================================================================
	
	public void NavigateToLandingScreen() throws Exception{
		//ScrollToBeginning(); 
		DataPicker("Verify that User can navigate to <b>Used Cars Search landing screen</b>", "Search used cars", 0);
		
		/*if (CarModel != null && !CarModel.trim().isEmpty()){
			test.log(Status.PASS, "Verify that user can tap on Search Bar to enter text");			
			test.log(Status.INFO, "User is navigated to Used Cars Search landing screen");	
			SendText("Search used cars", CarModel);
			test.log(Status.PASS, "Verify that user can enter Car Make Model in Search Bar (Test Data: "+CarModel+")");	
			DataPicker(CarModel, 0);
			test.log(Status.PASS, "Verify that user can select Car Model from search result (Test Data: "+CarModel+")");
			Thread.sleep(2000);
			driver.navigate().back();
		}*/
	}
	
	
	
	public void SelectCarYearMakeModelVersion() throws Exception
	{			
		JSONObject CarObj = (JSONObject) Object.get("CarInformation");
		String MakeModel= (String) CarObj.get("Model");
		String Year= (String) CarObj.get("Year");
		String Version= (String) CarObj.get("Version");
		
		DataPicker("Verify that user can select car model button to open model listing", "Select Brand Model", 0);
		
		if (Year != null && !Year.isEmpty()) 
			DataPicker("Verify that user can select car year <br>Test Data: <b>"+Year+"</b>", Year, 0);
					
		SendText("Verify that user can enter text to refine Make Model search", "Type to refine search", MakeModel);
		DataPicker("Verify that user can select car Make and Model <br>Test Data: <b>"+MakeModel+"</b>", MakeModel, 0);   

		if (Version != null && !Version.isEmpty()) 
			DataPicker("Verify that user can select car Version <br>Test Data: <b>"+Version+"</b>", Version, 0);    				
	}

	
	public void SelectMakeModel() throws Exception
	{
		JSONObject CarObj = (JSONObject) Object.get("CarInformation");
		String MakeModel= (String) CarObj.get("Model");
		String Version= (String) CarObj.get("Version");
		
		if (!MakeModel.isEmpty()) 
		{
			DataPicker("Verify that user can select car model button to open model listing", "Car Model", 0);			
			SelectMakeModelVersion();
			 
			if (Version == null || Version.isEmpty()) 
			{	
				try{
					driver.navigate().back();
					test.log(Status.PASS, "Verify that user can Skip selecting Version");
				}catch(org.openqa.selenium.NoSuchElementException ex){
					test.log(Status.FAIL, "Verify that user can Skip selecting Version");
					throw new Exception("Element not found");
				}
			}
		}
	}
	
	public void Scroll (String text)
	{
		try {
			driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))"));
		}
		catch (InvalidSelectorException e) {}
		catch(org.openqa.selenium.NoSuchElementException e){
			test.log(Status.INFO, "No element found while scrolling");
		}
	}
	
	public void ScrollSecondView (String text)
	{
		try {
			driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(1))" + ".scrollIntoView(new UiSelector().text(\""+text+"\"))"));
		}
		catch (InvalidSelectorException e) {}
		catch(org.openqa.selenium.NoSuchElementException e){}
	}
	
	public void ScrollSecondViewWithID (String ID)
	{
		try {
			 driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(1))" + ".scrollIntoView(new UiSelector().resourceIdMatches(\""+ID+"\"))"));
		}
		catch (InvalidSelectorException e) {}
		catch(org.openqa.selenium.NoSuchElementException e){
			test.log(Status.INFO, "No element found while scrolling");
		}
	}
	public void ScrollForward()
	{
		try {
		    driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"));
		}
		catch (InvalidSelectorException e) {}
		catch(org.openqa.selenium.NoSuchElementException e){}
	}
	public void JazzMobileAccount() throws Exception {
		test.log(Status.INFO, "<b>Payment Screen (Jazz cash mobile account)</b>");
		test.log(Status.INFO, "User is on JAZZ CASH Screen");
		SendText("Verify that user can Enter Mobile Number", "03XX-XXXXXXX", Base.JazzCashNumber);
		SendText("Verify that user can Enter CNIC", "00000-0000000-0", Base.JazzCashCNIC);
		driver.hideKeyboard();
		
		DataPicker("Verify that user can select continue button", "Continue", 2);   
		Thread.sleep(60000);
		wait = new WebDriverWait(driver,150);
		
	}
	
	public void EasyPayMobileAccount() throws Exception {
		test.log(Status.INFO, "<b>Payment Screen (EasyPaisa Mobile account)</b>");
		test.log(Status.INFO, "User is on EasyPaisa Screen");
		SendText("Verify that user can Enter Mobile Number", "03001234567", Base.JazzCashNumber);
		driver.hideKeyboard();
		
		DataPicker("Verify that user can select continue button", "Continue", 2);  
		EnterVerificationCode();
		Thread.sleep(60000);
		wait = new WebDriverWait(driver,150);
		
	}
	public void ScrollToBeginning()
	{
		try {
		    driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).flingToBeginning(10)"));
		} 
		catch (InvalidSelectorException e) {}
		catch(org.openqa.selenium.NoSuchElementException e){}
	}
	
	public void ScrollToEnd()
	{
		try {
		    driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).flingToEnd(10)"));
		} 
		catch (InvalidSelectorException e) {}
		catch(org.openqa.selenium.NoSuchElementException e){}	
	}
	
}