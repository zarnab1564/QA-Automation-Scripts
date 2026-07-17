package Locators;
import static org.testng.Assert.assertEquals;

import java.util.List;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Search_NewCars extends BaseHelper
{
	String CompareFirstSample= "com.pakwheels.staging:id/fl_compare";
	String SampleAd= "com.pakwheels.staging:id/iv_popular_reviewed_cars";
	
	AppiumDriver<MobileElement> driver= null;
	ExtentTest test= null;	
	WebDriverWait wait= null;
	JSONObject Object = null;
	public Search_NewCars(AppiumDriver<MobileElement> driver, ExtentTest test, WebDriverWait wait, JSONObject Object) 
	{
		super(driver, test, wait, Object);
		this.driver= driver;
		this.test= test;
		this.wait= wait;
		this.Object= Object;
	}

	public void NavigateToNewCarSearch() throws Exception{  
		ClickHomeButton();
		DataPicker("Verify that user can click <b>New Cars</b> tab from headers", "New Cars", 0);
		test.log(Status.INFO, "User is navigated to New Cars page");
	}
	
	public void VerifyDifferentSectionsOnNewCarPage(){
		if (Base.Testing.equalsIgnoreCase("Smoke"))
		{
			test.log(Status.INFO, "User is navigated to New Cars page");
			try{
				String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'New Cars By Make']")).getText();          
				assertEquals(text, "New Cars By Make");
				test.log(Status.PASS, "Verify New Cars By Make section is displayed");
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify New Cars By Make section is displayed");
			}
				
			ScrollSecondView("Popular New Cars");
			try{
				String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Popular New Cars']")).getText();          
				assertEquals(text, "Popular New Cars");
				test.log(Status.PASS, "Verify Popular New Cars section is displayed");
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify Popular New Cars section is displayed");
			}
			
			try{
				driver.findElement(By.id(SampleAd)).click();   
				test.log(Status.PASS, "Verify that user can click first ad from Popular New Cars Carousel and is navigated to Ad detailed page");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can click first ad from Popular New Cars Carousel and is navigated to Ad detailed page");
			}
			
			try{
				driver.findElement(By.className(Back2)).click();   
				test.log(Status.PASS, "Verify that user can click back button from Ad detailed page");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can click back button from Ad detailed page");
			}catch(org.openqa.selenium.StaleElementReferenceException ex){
				driver.findElement(By.className(Back2)).click();   
				test.log(Status.PASS, "Verify that user can click back button from Ad detailed page");
			}
			
			/*ScrollSecondView("Newly Launched Cars");
			try{
				String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Newly Launched Cars']")).getText();          
				assertEquals(text, "Newly Launched Cars");
				test.log(Status.PASS, "Verify Newly Launched Cars section is displayed");
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify Newly Launched Cars section is displayed");
			}
			
			try{
				driver.findElement(By.id(SampleAd)).click(); 
				test.log(Status.PASS, "Verify that user can click first ad from Newly Launched Cars Carousel and is navigated to Ad detailed page");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can click first ad from Newly Launched Cars Carousel and is navigated to Ad detailed page");
			}
			
			try{
				driver.findElement(By.className(Back2)).click();   
				test.log(Status.PASS, "Verify that user can click back button from Ad detailed page");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can click back button from Ad detailed page");
			}*/
			
			ScrollSecondView("Upcoming New Cars");
			try{
				String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Upcoming New Cars']")).getText();          
				assertEquals(text, "Upcoming New Cars");
				test.log(Status.PASS, "Verify Upcoming New Cars section is displayed");
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify Upcoming New Cars section is displayed");
			}
					
			try{
				driver.findElement(By.id(SampleAd)).click();   
				test.log(Status.PASS, "Verify that user can click first ad from Upcoming New Cars Carousel and is navigated to Ad detailed page");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can click first ad from Upcoming New Cars Carousel and is navigated to Ad detailed page");
			}
			
			try{
				driver.findElement(By.className(Back2)).click();   
				test.log(Status.PASS, "Verify that user can click back button from Ad detailed page");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can click back button from Ad detailed page");
			}catch(org.openqa.selenium.StaleElementReferenceException ex){
				driver.findElement(By.className(Back2)).click();   
				test.log(Status.PASS, "Verify that user can click back button from Ad detailed page");
			}
			
			try {		
				String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Hot Comparisons']")).getText();          
				assertEquals(text, "Hot Comparisons");
				test.log(Status.PASS, "Verify Hot Comparisons section is displayed");
			}catch(org.openqa.selenium.NoSuchElementException e){
				ScrollForward();
				String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Hot Comparisons']")).getText();          
				assertEquals(text, "Hot Comparisons");
				test.log(Status.PASS, "Verify Hot Comparisons section is displayed");
			}catch(AssertionError ex){
				test.log(Status.FAIL, "Verify Hot Comparisons section is displayed");	
			}
			
			try{
				driver.findElement(By.id(CompareFirstSample)).click();   
				test.log(Status.PASS, "Verify that user can click on compare sample");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can click on compare sample");
			}
			
			try{
				driver.findElement(By.className(Back2)).click();   
				test.log(Status.PASS, "Verify that user can click back button from Ad detailed page");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can click back button from Ad detailed page");
			}catch(org.openqa.selenium.StaleElementReferenceException ex){
				driver.findElement(By.className(Back2)).click();   
				test.log(Status.PASS, "Verify that user can click back button from Ad detailed page");
			}
			
			try{
				String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Recent Car Reviews']")).getText();          
				assertEquals(text, "Recent Car Reviews");
				test.log(Status.PASS, "Verify Recent Car Reviews section is displayed");
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify Recent Car Reviews section is displayed");
			}
			
			ScrollToEnd();
			try{
				String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Browse More']")).getText();          
				assertEquals(text, "Browse More");
				test.log(Status.PASS, "Verify that Browse More section is displayed");
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify that Browse More section is displayed");
			}
		}
	}
	
	public void SelectCarMake() throws Exception
	{	
		JSONObject CarInfo = (JSONObject) Object.get("CarInformation");
		String Make= (String) CarInfo.get("Make");
		
		Thread.sleep(2000);
		DataPicker("Verify that user can select car make <br>Test Data: <b>"+Make+"</b>", Make, 0);
	}
	
	public void VerifyDifferentSectionsOnModelListingPage()
	{
		if (Base.Testing.equalsIgnoreCase("System"))
		{
			test.log(Status.INFO, "User is navigated to Car Model listing page");			
			try{
				String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Local']")).getText();          
				assertEquals(text, "Local");
				test.log(Status.PASS, "Verify Local section is displayed");
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify Local section is displayed");
			}
			
			Scroll("Videos");
			try{
				String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Videos']")).getText();          
				assertEquals(text, "Videos");
				test.log(Status.PASS, "Verify that Videos section is displayed");
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify that Videos section is displayed");
			}
		}
	}
	
	public void SelectCarModel() throws Exception
	{
		JSONObject CarInfo = (JSONObject) Object.get("CarInformation");
		String Model= (String) CarInfo.get("Model");
		
		ScrollToBeginning();		
		Thread.sleep(10000);;
		DataPicker("Verify that user can select car model <br>Test Data: <b>"+Model+"</b>", Model, 0);			
	}
	
	public void VerifyDifferentSectionsOnModelDetailedPage()
	{
		test.log(Status.INFO, "User is navigated to Car Model detailed page");		
		Scroll("Versions");
		try{
			String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Versions']")).getText();          
			assertEquals(text, "Versions");
			test.log(Status.PASS, "Verify that Versions section is displayed");
		}catch(AssertionError e){
			test.log(Status.FAIL, "Verify that Versions section is displayed");
		}
		
		Scroll("Videos");
		try{
			String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Videos']")).getText();          
			assertEquals(text, "Videos");
			test.log(Status.PASS, "Verify that Videos section is displayed");
		}catch(AssertionError e){
			test.log(Status.FAIL, "Verify that Videos section is displayed");
		}
	}
		
	public void ClickOnRoadPrice() throws Exception{
		//ScrollToBeginning();  
		DataPicker("Verify that user can select <b>On Road Price</b> button", "Get on road price", 2);
	}
	
	public void FillForm() throws Exception
	{
		JSONObject CarInfo = (JSONObject) Object.get("CarInformation");
		String Version= (String) CarInfo.get("Version");
		
		DataPicker("Verify that user can select Version button to open version listing", "Version", 0);
		DataPicker("Verify that user can select version <br> Test Data: <b>"+Version+"</b>", Version, 0);	
		SelectLocation();
		DataPicker("Verify user can select Done button", "Done", 2);
	}
	
	public void SelectPayementMethod() throws Exception{
		
		DataPicker("Verify user can select <b>Cash</b> button", "Cash", 3);	
		SendText("Verify that user can enter Email", "username@email.com", Base.Email);
	}
	
	public void SelectBookThisCarNow() throws Exception{
		DataPicker("Verify user can select <b>Book This Car Now</b> button", "Book This Car Now", 2);
		Thread.sleep(3000);
	}

	public void BrowseNewCarByBrand() throws Exception {
		JSONObject CarInfo = (JSONObject) Object.get("CarInformation");
		String Make= (String) CarInfo.get("Make");
		try{
			if(driver.findElement(By.xpath("//android.widget.TextView[@text= 'Browse New Cars By Brand']")).isDisplayed())
			{
				test.log(Status.PASS, "Verify New Cars By Make/Brand section is displayed");
				SelectCarMake();
				Thread.sleep(10000);
				
				String mk=driver.findElement(By.id("com.pakwheels.staging:id/tv_titleText_actionbar_menu")).getText();
				if(mk.contains(Make))
				{
					test.log(Status.PASS, "Verified that user lands to "+Make+" Page");
					driver.navigate().back();
				}
			}
			
		}catch(AssertionError e){
			test.log(Status.FAIL, "Verify New Cars By Make/Brand section is not displayed");
		}
	}

	public void PopularNewCars() {
	
		ScrollSecondView("Popular New Cars");
		try{
			if(driver.findElement(By.xpath("//android.widget.TextView[@text= 'Popular New Cars']")).isDisplayed())
			{
				test.log(Status.PASS, "Verify Popular New Cars section is displayed");
                
				String ele=driver.findElement(By.xpath("//android.widget.TextView[@resource-id= 'com.pakwheels.staging:id/tv_heading']")).getText();
				driver.findElement(By.xpath("//android.widget.TextView[@resource-id= 'com.pakwheels.staging:id/tv_heading']")).click();
				test.log(Status.PASS, "Verify that user clicks on popular search car."+ele);
				
				String model=driver.findElement(By.id("com.pakwheels.staging:id/tv_model_name")).getText();
				if(model.contains(ele))
				{
					test.log(Status.PASS, "Verified that user redirected to "+model+" Page");
					driver.navigate().back();
				}
				
			}
			
		}catch(AssertionError e){
			test.log(Status.FAIL, "Verify Popular New Cars section is not displayed");
		}
		
	}

	public void NewlyLaunchedCars() {
		ScrollSecondView("Newly Launched Cars");
		try{
			String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Newly Launched Cars']")).getText();          
			assertEquals(text, "Newly Launched Cars");
			test.log(Status.PASS, "Verify Newly Launched Cars section is displayed");
			
			String ele=driver.findElement(By.xpath("//android.widget.TextView[@resource-id= 'com.pakwheels.staging:id/tv_heading']")).getText();
			driver.findElement(By.xpath("//android.widget.TextView[@resource-id= 'com.pakwheels.staging:id/tv_heading']")).click();
			test.log(Status.PASS, "Verify that user clicks on Newly Launched Car."+ele);
			
			String model=driver.findElement(By.id("com.pakwheels.staging:id/tv_model_name")).getText();
			if(model.contains(ele))
			{
				test.log(Status.PASS, "Verified that user redirected to "+model+" Page");
				driver.navigate().back();
			}			
		}catch(AssertionError e){
			test.log(Status.FAIL, "Verify Newly Launched Cars section is not displayed");
		}
	}

	public void UpcomingCars() {
		ScrollSecondView("Upcoming New Cars");
		try{
			String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Upcoming New Cars']")).getText();          
			assertEquals(text, "Upcoming New Cars");
			test.log(Status.PASS, "Verify Upcoming New Cars section is displayed");
			
			String ele=driver.findElement(By.xpath("//android.widget.TextView[@resource-id= 'com.pakwheels.staging:id/tv_heading']")).getText();
			driver.findElement(By.xpath("//android.widget.TextView[@resource-id= 'com.pakwheels.staging:id/tv_heading']")).click();
			test.log(Status.PASS, "Verify that user clicks on Upcoming New Car."+ele);
			
			String model=driver.findElement(By.id("com.pakwheels.staging:id/tv_model_name")).getText();
			if(model.contains(ele))
			{
				test.log(Status.PASS, "Verified that user redirected to "+model+" Page");
				driver.navigate().back();
			}			
		}catch(AssertionError e){
			test.log(Status.FAIL, "Verify Upcoming Cars section is not displayed");
		}
		
	}

	public void CarComparison() {
		ScrollSecondView("Car Comparison");	
		try{
			String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Car Comparison']")).getText();          
			assertEquals(text, "Car Comparison");
			test.log(Status.PASS, "Verify Car Comparison section is displayed");
			driver.findElement(By.xpath("//android.widget.TextView[@text= 'View All']")).click();
			test.log(Status.PASS, "Verify that user click on View All for Car Comparison widget");
			
			if(driver.findElement(By.xpath("//android.widget.TextView[@text= 'Car Comparison']")).isDisplayed())
			{
				test.log(Status.PASS, "Verify that user redirected to Car Comparison Screen");
				driver.navigate().back();
			}
			
			String car1=driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.pakwheels.staging:id/tv_car_feature_text']")).getText();
			String car2=driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.pakwheels.staging:id/tv_car_feature_text']")).getText();

			System.out.println(car1 +"VS" +car2);
			
			driver.findElement(By.xpath("//android.widget.TextView[@text= 'VS']")).click();
			driver.navigate().back();
			
		}catch(AssertionError e){
			test.log(Status.FAIL, "Verify Comparison Car section is not displayed");
		}
	}

	public void Reviews() {
		
		ScrollSecondView("Car Reviews");
		try{
			String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Car Reviews']")).getText();          
			assertEquals(text, "Car Reviews");
			test.log(Status.PASS, "Verify Car Reviews section is displayed");
			driver.findElement(By.xpath("//android.widget.TextView[@text= 'View All']")).click();
			test.log(Status.PASS, "Verify that user click on View All for Car Reviews widget");
			
			if(driver.findElement(By.xpath("//android.widget.TextView[@text= 'Car Reviews']")).isDisplayed())
			{
				test.log(Status.PASS, "Verify that user redirected to Car Reviews Screen");
				driver.navigate().back();
			}
			
            driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.pakwheels.staging:id/reviewCard']")).click();
            driver.navigate().back();
			
		}catch(AssertionError e){
			test.log(Status.FAIL, "Verify Car Reviews section is not displayed");
		}
		
	}

	public void ToolsSection() {
		ScrollToEnd();
		try{
			if(driver.findElement(By.xpath("//android.widget.TextView[@text='Tools You May Need']")).isDisplayed())
			{
				test.log(Status.PASS, "Verify that Tools You May Need section is displayed");
				List<MobileElement> tools=driver.findElements(By.xpath("android.widget.LinearLayout"));
				
				for(int i=0;i<tools.size();i++)
				{
					String t_ele=tools.get(i).getText();
					tools.get(i).click();
					test.log(Status.PASS, "Verify that user clicks on "+t_ele+" Tool on You May Need section ");
					
					String heading=driver.findElement(By.id("com.pakwheels.staging:id/tv_titleText_actionbar_menu")).getText();
					if(t_ele.contains(heading))
					{
						test.log(Status.PASS, "Verify that User redirected to "+heading+"Screen");
						driver.navigate().back();
					}
				}
			}          
		}catch(AssertionError e){
			test.log(Status.FAIL, "Verify that Tools You May Need section is displayed");
		}
		
	}

	public void LatestVideos() {
		ScrollSecondView("Latest Videos");
		try{
			String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Latest Videos']")).getText();          
			assertEquals(text, "Latest Videos");
			test.log(Status.PASS, "Verify Latest Videos section is displayed");
			driver.findElement(By.xpath("//android.widget.TextView[@text= 'View All']")).click();
			test.log(Status.PASS, "Verify that user click on View All for Latest Videos widget");
			if(driver.findElement(By.xpath("//android.widget.TextView[@text= 'Pakwheels Videos']")).isDisplayed())
			{
				test.log(Status.PASS, "Verify that user redirected to Pakwheels Videos Screen");
				driver.navigate().back();
			}
            driver.navigate().back();	
            String title=driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.pakwheels.staging:id/tvTitleHeader']")).getText();
            driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.pakwheels.staging:id/iv']")).click();
            driver.navigate().back();
      
			
		}catch(AssertionError e){
			test.log(Status.FAIL, "Verify Car Reviews section is not displayed");
		}
		
	}
}
