package Locators;
import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Search_NewBikes extends BaseHelper
{
	static AppiumDriver<MobileElement> driver= null;
	ExtentTest test= null;	
	WebDriverWait wait= null;
	JSONObject Object = null;
	public Search_NewBikes(AppiumDriver<MobileElement> driver, ExtentTest test, WebDriverWait wait, JSONObject Object) 
	{
		super(driver, test, wait, Object);
		this.driver= driver;
		this.test= test;
		this.wait= wait;
		this.Object= Object;
	}

	public void NavigateToNewBikeSearch() throws Exception
	{
		
			ClickHomeButton();
			DataPicker("Verify that user can select <b>Bike</b> tab from headers", "Bikes", 0);	
			
			ScrollSecondView("Browse More");
			DataPicker("Verify that user can navigate to New Bikes Page by selecting New Bikes Button", "New Bikes", 0);
		
	}

	
	public void SelectBikeMake() throws Exception
	{
		test.log(Status.INFO, "<h3>Bike Make Listing</h3>");	
		JSONObject BikeInfo = (JSONObject) Object.get("BikeInformation");
		String Make= (String) BikeInfo.get("Make");
		if(Base.Testing_Enviroment.equalsIgnoreCase("Staging"))
    	{
		   if((driver.findElement(By.id("com.pakwheels.staging:id/tv_titleText_actionbar_menu")).getText()).equalsIgnoreCase("Select Brand"))
	    	{
			     test.log(Status.INFO, "User is navigated to Bike Make Listing Page");	
     		}
    	}
		if(Base.Testing_Enviroment.equalsIgnoreCase("Production"))
    	{
		   if((driver.findElement(By.id("com.pakwheels:id/tv_titleText_actionbar_menu")).getText()).equalsIgnoreCase("Select Brand"))
	    	{
			     test.log(Status.INFO, "User is navigated to Bike Make Listing Page");	
     		}
    	}
		
		try
		{
		if(Base.Testing.equalsIgnoreCase("System"))
		{
			if(driver.findElement(By.id("com.pakwheels.staging:id/cell")).isDisplayed())
			{	
				if(driver.findElement(By.id("com.pakwheels.staging:id/item_image")).isDisplayed() && driver.findElement(By.id("com.pakwheels.staging:id/item_text")).isDisplayed())
				{	
				   String Make_Name=driver.findElement(By.id("com.pakwheels.staging:id/item_text")).getText();
				   test.log(Status.INFO, "Verify that bikes names and icons are displayed on new bikes landing page "+Make_Name  );	
				}
			}
		}
		}catch(NoSuchElementException e){
			test.log(Status.FAIL, "Verify that bikes names and icons are not displayed on new bikes landing");
		}

		Thread.sleep(3000);
		DataPicker("Verify that user can select Bike Make <br>Test Data: <b>"+Make+"</b>", Make, 0);
	}
	
	public void VerifyBikeModelListingPage() throws InterruptedException
	{
		test.log(Status.INFO, "<h3>Bike Model Listing</h3>");
		JSONObject BikeInfo = (JSONObject) Object.get("BikeInformation");
		String Make= (String) BikeInfo.get("Make");
		String h1=Make+" Models";
		if((driver.findElement(By.id("com.pakwheels.staging:id/tv_titleText_actionbar_menu")).getText()).equalsIgnoreCase(h1))
		{
			test.log(Status.INFO, "User is navigated to Bike Models Listing Page : " + h1);	
		}
	
		if (Base.Testing.equalsIgnoreCase("System"))
		{

			List<MobileElement> Models=driver.findElements(By.id("com.pakwheels.staging:id/tv_model_name"));
			for(int i=0;i<Models.size();i++)
			{
				test.log(Status.INFO, "Verify that Models are showing : "+Models.get(i).getText());
			}
			Thread.sleep(5000);
			String Model_Name=Models.get(0).getText();
			Models.get(0).click();
			test.log(Status.PASS, "Verify that User clicks on Model : "+Model_Name);
			Thread.sleep(5000);
			if((driver.findElement(By.id("com.pakwheels.staging:id/tv_model_name")).getText()).equalsIgnoreCase(Model_Name))
			{
				test.log(Status.PASS, "Verify that User Redirected to Model Detailed"+Model_Name);
				driver.navigate().back();
			}
			
			if(driver.findElement(By.id("com.pakwheels.staging:id/txtview_ad_price")).isDisplayed() && driver.findElement(By.id("com.pakwheels.staging:id/rb_rating_car_model")).isDisplayed())
			{
				String Price=driver.findElement(By.id("com.pakwheels.staging:id/txtview_ad_price")).getText();
				test.log(Status.INFO, "Verify that model thumbnail, name, price and rating are displayed "+Model_Name+" Price : "+Price+" Ratings Stars are showing");	
			}
			
			driver.findElement(By.id("com.pakwheels.staging:id/action_share_ad")).click();
			test.log(Status.INFO, "User Clicks on Share icon on Bike Model Listing");	
			Thread.sleep(3000);
			driver.navigate().back();
			
			Scroll("Videos");
			try {
				String text= driver.findElement(By.id("com.pakwheels.staging:id/tvTitle")).getText();          
				assertEquals(text, "Videos");
				test.log(Status.PASS, "Verify that Videos section is displayed");
				

				
				driver.findElement(By.id("com.pakwheels.staging:id/ivVideoHeader")).click();
				test.log(Status.PASS, "Verify that User clicks on Video");
				
				if(driver.findElement(By.id("com.pakwheels.staging:id/panel")).isDisplayed())
				{
					test.log(Status.PASS, "Verify that Video gets played");
					driver.navigate().back();
				}
				
				driver.findElement(By.id("com.pakwheels.staging:id/tvViewAll")).click();
				test.log(Status.PASS, "Verify that User Clicks on View All button");
				if(driver.findElement(By.xpath("//android.widget.TextView[@text='PakWheels Videos']")).isDisplayed())
				{
					test.log(Status.PASS, "Verify that User redirected to Videos Screen");
					driver.navigate().back();
				}
				
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify that Videos section is not  displayed on Bike Model Listing Page");
			}	
		}
	}
	
	public void SelectBikeModel() throws Exception
	{
		//ScrollToBeginning();
		JSONObject BikeInfo = (JSONObject) Object.get("BikeInformation");
		String Model= (String) BikeInfo.get("Model");
		Thread.sleep(10000);
		DataPicker("Verify that user can select Bike Model <br>Test Data: <b>"+Model+"</b>", Model, 0);
	}
	
	 public static void horizontalScroll() {
	        // Define the width and height of the screen
	        int width = driver.manage().window().getSize().getWidth();
	        int height = driver.manage().window().getSize().getHeight();

	        // Define the start and end points for the horizontal swipe
	        int startX = (int) (width * 0.9); // Start near the right edge of the screen
	        int endX = (int) (width * 0.1);   // End near the left edge of the screen
	        int startY = height / 2;          // Swipe vertically centered

	        // Create a TouchAction instance
	        TouchAction<?> action = new TouchAction<>(driver);

	        // Perform the swipe action
	        action.press(PointOption.point(startX, startY))
	              .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
	              .moveTo(PointOption.point(endX, startY))
	              .release()
	              .perform();
	    }
	 
	 
	    public static void zoomIn() {
	        // Define the width and height of the screen
	        int width = driver.manage().window().getSize().getWidth();
	        int height = driver.manage().window().getSize().getHeight();

	        // Define the pinch start and end points
	        int startX1 = (int) (width * 0.3); // First finger start X position
	        int startY1 = height / 2;          // First finger start Y position
	        int startX2 = (int) (width * 0.7); // Second finger start X position
	        int startY2 = height / 2;          // Second finger start Y position

	        // Create a TouchAction instance
	        TouchAction<?> action = new TouchAction<>(driver);

	        // Perform the zoom-in action
	        action.press(PointOption.point(startX1, startY1))
	              .moveTo(PointOption.point(startX2, startY2))
	              .release()
	              .press(PointOption.point(startX2, startY2))
	              .moveTo(PointOption.point(startX1, startY1))
	              .release()
	              .perform();
	    }

	    public static void zoomOut() {
	        // Define the width and height of the screen
	        int width = driver.manage().window().getSize().getWidth();
	        int height = driver.manage().window().getSize().getHeight();

	        // Define the pinch start and end points
	        int startX1 = (int) (width * 0.3); // First finger start X position
	        int startY1 = height / 2;          // First finger start Y position
	        int startX2 = (int) (width * 0.7); // Second finger start X position
	        int startY2 = height / 2;          // Second finger start Y position

	        // Create a TouchAction instance
	        TouchAction<?> action = new TouchAction<>(driver);

	        // Perform the zoom-out action
	        action.press(PointOption.point(startX1, startY1))
	              .moveTo(PointOption.point(startX2, startY2))
	              .release()
	              .press(PointOption.point(startX2, startY2))
	              .moveTo(PointOption.point(startX1, startY1))
	              .release()
	              .perform();
	    }
	
	public void VerifyBikeModelDetailedPage() throws InterruptedException
	{
		test.log(Status.INFO, "<h3>Bike Model Detail</h3>");
		JSONObject BikeInfo = (JSONObject) Object.get("BikeInformation");
		String Model= (String) BikeInfo.get("Model");
		Thread.sleep(5000);
		if (Base.Testing.equalsIgnoreCase("System"))
		{
			if((driver.findElement(By.id("com.pakwheels.staging:id/tv_titleText_actionbar_menu")).getText()).equalsIgnoreCase(Model))
			{
				test.log(Status.INFO, "User is navigated to Bike Model Detail Page"+Model);
			}
			
			driver.findElement(By.id("com.pakwheels.staging:id/action_share_ad")).click();
			test.log(Status.INFO, "User Clicks on Share icon on Bike Model detail page");	
			driver.navigate().back();
			
			Thread.sleep(2000);
			driver.findElement(By.id("com.pakwheels.staging:id/my_image_view")).click();
			test.log(Status.INFO, "User clicks on image view of Model Detail");
			Thread.sleep(2000);
			
			if(driver.findElement(By.id("com.pakwheels.staging:id/zoomableView")).isDisplayed())
			{
				test.log(Status.INFO, "User redirected to zoomable view of Image.");
				
				horizontalScroll();
				Thread.sleep(2000);
				zoomIn();
				Thread.sleep(3000);
				zoomOut();
				Thread.sleep(3000);
				
				driver.findElement(By.id("com.pakwheels.staging:id/closeFullScreen")).click();
			}
			
			String Model_Price=driver.findElement(By.id("com.pakwheels.staging:id/txtview_ad_price")).getText();
			test.log(Status.INFO, "Verify that Model Price is showing"+Model_Price);
			
			if(driver.findElement(By.id("com.pakwheels.staging:id/rb_rating_car_model")).isDisplayed())
			{
				test.log(Status.INFO, "Verify that Rating Stars against bike Model is showing");
			}
			
			try {
				String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Specifications']")).getText();        
				assertEquals(text, "Specifications");
				test.log(Status.PASS, "Verify that Specifications section is displayed");
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify that Specifications section is displayed");
			}
		
			Scroll("Videos");
			try {
				String text= driver.findElement(By.id("com.pakwheels.staging:id/tvTitle")).getText();          
				assertEquals(text, "Videos");
				test.log(Status.PASS, "Verify that Videos section is displayed");
				
				
				driver.findElement(By.id("com.pakwheels.staging:id/ivVideoHeader")).click();
				test.log(Status.PASS, "Verify that User clicks on Video");
				
				if(driver.findElement(By.id("com.pakwheels.staging:id/panel")).isDisplayed())
				{
					test.log(Status.PASS, "Verify that Video gets played");
					driver.navigate().back();
				}
				
				driver.findElement(By.id("com.pakwheels.staging:id/tvViewAll")).click();
				test.log(Status.PASS, "Verify that User Clicks on View All button");
				if(driver.findElement(By.xpath("//android.widget.TextView[@text='PakWheels Videos']")).isDisplayed())
				{
					test.log(Status.PASS, "Verify that User redirected to Videos Screen");
					driver.navigate().back();
				}
				
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify that Videos section is not  displayed on Bike Model Detailed Page");
			}	
			
			Scroll("Share");
			try {
				String text= driver.findElement(By.id("com.pakwheels.staging:id/btn_share")).getText();         
				assertEquals(text, "Share");
				test.log(Status.PASS, "Verify that Share button is displayed");
				
				driver.findElement(By.id("com.pakwheels.staging:id/btn_share")).click();
				test.log(Status.INFO, "User Clicks on Share icon on Bike Model detail page");	
				driver.navigate().back();
				
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify that Share button is not displayed");
			}		
		}
	}
}
