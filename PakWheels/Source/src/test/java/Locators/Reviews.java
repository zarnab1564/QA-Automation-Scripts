package Locators;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;


public class Reviews extends BaseHelper {

	AppiumDriver<MobileElement> driver= null;
	ExtentTest test= null;
	WebDriverWait wait= null;
	JSONObject Object = null;
	
	public Reviews(AppiumDriver<MobileElement> driver, ExtentTest test, WebDriverWait wait, JSONObject Object) 
	{
		super(driver, test, wait, Object);
		this.driver= driver;
		this.test= test;
		this.wait= wait;
		this.Object= Object;
	}
	
	//Method for Old More
	/*public void NavigateToReviews() throws Exception{
		ClickMoreButton();
		DataPicker("Verify that user can click <b>Products</b> tab from more screen", "Products", 0);
		DataPicker("Verify that user can click <b>New Cars</b> tab from Products", "New Cars", 0);
		DataPicker("Verify that user can click <b>Reviews</b> tab from New Cars", "Reviews", 0);
	}*/
	
	//Method for NEw More
	public void NavigateToReviews() throws Exception{
		ClickHomeButton();
		DataPicker("Verify that user can click <b>New Cars</b> tab on Home Screen", "New Cars", 0);
		ScrollToEnd();
		System.out.println("Wait.....");
		Thread.sleep(5000);
		System.out.println("Wait End..");
		ScrollToEnd();
		ScrollSecondView("Car Reviews");
		DataPicker("Verify that user scroll to  <b>Car Reviews</b> section on New Cars Screen", "Car Reviews", 0);
		DataPicker("Verify that user click on  <b>View All</b> Link for Car reviews section on New Cars Screen", "View All", 0);
		
	}
	
	public void VerifyDifferentSectionsOnCarReviews() throws Exception{
		if (Base.Testing.equalsIgnoreCase("System"))
		{
			test.log(Status.INFO, "User is navigated to Car Reviews page");
			try{
				String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Popular Reviewed Cars']")).getText();          
				assertEquals(text, "Popular Reviewed Cars");
				test.log(Status.PASS, "Verify Popular Reviewed Cars section is displayed");
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify Popular Reviewed Cars section is not displayed");
			}
			
			try{
				driver.findElement(By.id("com.pakwheels.staging:id/rl_popular_reviewed_cars_card")).click();   
				test.log(Status.PASS, "Verify that user can click first ad from Popular Reviewed Cars Carousel and is navigated to review detailed page");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can click first ad from Popular Reviewed Cars Carousel and is navigated to review detailed page");
			}
			
			try{
				driver.findElement(By.className(Back2)).click();   
				test.log(Status.PASS, "Verify that user can click back button from review detailed page");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can click back button from review detailed page");
			}catch(org.openqa.selenium.StaleElementReferenceException ex){
				driver.findElement(By.className(Back2)).click();   
				test.log(Status.PASS, "Verify that user can click back button from review detailed page");
			}
			
		   ScrollSecondView("Car Reviews");
			try{
				String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Car Reviews']")).getText();          
				assertEquals(text, "Car Reviews");
				test.log(Status.PASS, "Verify Car Reviews section is displayed");
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify Car Reviews section is displayed");
			}
			ScrollToEnd();
			
			try{
				driver.findElement(By.id("com.pakwheels.staging:id/review_row")).click();   
				test.log(Status.PASS, "Verify that user can click first review from Car Reviews listing and is navigated to review detailed page");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can click first review from Car Reviews listing and is navigated to review detailed page");
			}
			
			try{
				driver.findElement(By.xpath("//android.widget.Button[@content-desc='SHARE']")).click();   
				test.log(Status.PASS, "Verify that user can click share button from review detailed page");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can click share button from review detailed page");
			}
			
			ClickCrossButton();
			
			try{
				driver.findElement(By.className(Back2)).click();   
				test.log(Status.PASS, "Verify that user can click back button from review detailed page");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can click back button from review detailed page");
			}catch(org.openqa.selenium.StaleElementReferenceException ex){
				driver.findElement(By.className(Back2)).click();   
				test.log(Status.PASS, "Verify that user can click back button from review detailed page");
			}
			
			ScrollToBeginning();
		}
}
	
	public void SelectCarYearModelVersion() throws Exception
	{			
		JSONObject CarObj = (JSONObject) Object.get("Car Information");
		String Model= (String) CarObj.get("Model");
		String Year= (String) CarObj.get("Year");
		String Version= (String) CarObj.get("Version");
		
		//DataPicker("Verify that user can select <b>Car Model</b> Field", "Select your car info", 0);
		driver.findElement(By.xpath("//android.widget.EditText[@resource-id='carSelectionField']")).click();
		test.log(Status.PASS, "Verify that user can select <b>Select your car info</b> Field");
		
		if (Year != null && !Year.isEmpty()) 
			DataPicker("Verify that user can select car year <br>Test Data: <b>"+Year+"</b>", Year, 0);
					
		driver.findElement(By.xpath("//android.widget.EditText[@resource-id='search-field']")).click();
		driver.findElement(By.xpath("//android.widget.EditText[@resource-id='search-field']")).sendKeys(Model);
		DataPicker("Verify that user can select car Model <br>Test Data: <b>"+Model+"</b>", Model, 0);   

		if (Version != null && !Version.isEmpty()) 
			driver.findElement(By.xpath("//android.widget.EditText[@resource-id='search-field']")).click();
		    driver.findElement(By.xpath("//android.widget.EditText[@resource-id='search-field']")).sendKeys(Version);
			DataPicker("Verify that user can select car Version <br>Test Data: <b>"+Version+"</b>", Version, 0);    				
	}
	
	public void SelectCarModel() throws Exception
	{
		JSONObject CarInfo = (JSONObject) Object.get("Car Information");
		String Model= (String) CarInfo.get("Model");
		
		DataPicker("Verify that user can select <b>Car Model</b> button", "Select Car Model", 0);
		SendText("Verify that user can enter text to refine Model search", "Search", Model);	
		DataPicker("Verify that user can select car model <br>Test Data: <b>"+Model+"</b>", Model, 0);			
		Thread.sleep(10000);
	}
	
	public void ClickSearchButton() throws Exception{
		DataPicker("Verify that user can select <b>Search</b> button", "Search", 2);
		Thread.sleep(2000);
	}
	
	public void SelectVariants() throws Exception{
		JSONObject CarInfo = (JSONObject) Object.get("Car Information");
		String Version= (String) CarInfo.get("Version");
				
		DataPicker("Verify that user can select <b>All Variants</b> button", "All Variants", 0);
		SendText("Verify that user can enter text to refine Variant search", "Search", Version);	
		DataPicker("Verify that user can select car model <br>Test Data: <b>"+Version+"</b>", Version, 0);	
		Thread.sleep(10000);
	}
	
	
	public void ClickReviewWidget() throws Exception{
		test.log(Status.INFO, "User is navigated to Review detail page");
		try{
			driver.findElement(By.id("com.pakwheels.staging:id/review_row")).click();   
			test.log(Status.PASS, "Verify that user can click first review and is navigated to review detailed page");
		}catch(org.openqa.selenium.NoSuchElementException e){
			test.log(Status.FAIL, "Verify that user can click first review and is navigated to review detailed page");
		}
		
		ScrollToEnd();
		try{
			driver.findElement(By.className(Back2)).click();   
			test.log(Status.PASS, "Verify that user can click back button from review detailed page");
		}catch(org.openqa.selenium.NoSuchElementException e){
			test.log(Status.FAIL, "Verify that user can click back button from review detailed page");
		}catch(org.openqa.selenium.StaleElementReferenceException ex){
			driver.findElement(By.className(Back2)).click();   
			test.log(Status.PASS, "Verify that user can click back button from review detailed page");
		}
		
	}
	
	public void ClickCrossButton() throws Exception{
		driver.findElement(By.id("com.transsion:id/chooser_close")).click();   
		test.log(Status.PASS, "Verify that user can click cross button from share pop up");
	}
	
	public void ClickWriteAReviewButtonOnReviewScreen() throws Exception{
		DataPicker("Verify that user can select <b>Write a Review</b> button", "Write a Review", 0);
		Thread.sleep(2000);
		FillReviewForm();
		ClickSubmitReviewButton();
	}
	
	public void ClickWriteAReviewButtonOnModelReview() throws Exception{
		DataPicker("Verify that user can select <b>Write a Review</b> button", "Write a review", 2);
		Thread.sleep(2000);
	}
	
	public void EnterReviewTitle() throws Exception{
		String Title= (String) Object.get("Title");
			try {
				
				driver.findElement(By.xpath("//android.widget.EditText[@resource-id='reviewTitle']")).sendKeys(Title);
				test.log(Status.PASS, "Verify that user can enter Review Title");
			}catch(org.openqa.selenium.NoSuchElementException ex){ 
				test.log(Status.FAIL, "Verify that user can enter Review Title"+ex);
			}	
		driver.hideKeyboard();
	}
	
	public void ReviewRating() throws Exception{
		int ratingToSelect=3;
		
		/*MobileElement Styleparent = (MobileElement) driver.findElement(By.id("com.example.app:id/styleRating"));
		 WebDriverWait wait = new WebDriverWait(driver, 10);
	        wait.until(ExpectedConditions.elementToBeClickable(Styleparent));
	        if(ratingToSelect>=1 || ratingToSelect<=5)
	        {
	          MobileElement ratingStar = (MobileElement) driver.findElement(By.xpath("(//android.view.View[@resource-id='RatingStar'])[" + ratingToSelect + "]"));
	          wait.until(ExpectedConditions.elementToBeClickable(ratingStar));
	          ratingStar.click();
	        }
	        else
	        {
	        	test.log(Status.FAIL, "Invalid Rating selection value  it should be in range of 1 to 5 ");
	        }*/
		
		
		driver.findElement(By.xpath("(//android.view.View[@resource-id='RatingStar'])[2]")).click();
		driver.findElement(By.xpath("(//android.view.View[@resource-id='RatingStar'])[9]")).click();
		driver.findElement(By.xpath("(//android.view.View[@resource-id='RatingStar'])[13]")).click();
		driver.findElement(By.xpath("(//android.view.View[@resource-id='RatingStar'])[18]")).click();
	    driver.findElement(By.xpath("(//android.view.View[@resource-id='RatingStar'])[23]")).click();
	        
	      /*  MobileElement ratingSection = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@resource-id='styleRating']")));
	        System.out.println(ratingSection.isDisplayed());
	        System.out.println(ratingSection.isEnabled());
	        
	            // Get all the RatingStar views inside the rating section
	            List<MobileElement> stars = ratingSection.findElements(By.id("com.pakwheels.staging:id/RatingStar"));
	            System.out.println(stars.size());

	            if (stars.size() >= ratingToSelect) 
	            {
	                // Click the star that corresponds to the rating
	            	    stars.get(ratingToSelect).click();
	                    
	            } 
	            else 
	            {
	                System.out.println("Star " + (ratingToSelect) + " is not clickable or not visible.");
	                System.out.println("Also Not enough stars to select in section:");
	                    
	            }

		
		/*try {
			for (int i=0; i<5; i++)
				driver.findElements(By.id("com.pakwheels.staging:id/rtb_review_ratingbar")).get(i).click();                                      			
			test.log(Status.PASS, "Verify that user can click on rating stars");
		}catch(org.openqa.selenium.NoSuchElementException e){
			test.log(Status.FAIL, "Verify that user can on rating stars");
		}	*/	 	
	}
	
	public void WriteAReview() throws Exception{
		String Review= (String) Object.get("Review");
		
		try {
			driver.findElement(By.xpath("//android.widget.EditText[@resource-id='reviewContent']")).sendKeys(Review);
			test.log(Status.PASS, "Verify that user can enter Review");
			
		}catch(org.openqa.selenium.NoSuchElementException ex){ 
			test.log(Status.FAIL, "Verify that user can not  enter Review");
		}	
	driver.hideKeyboard();
	}
	
	public void FillReviewForm() throws Exception {
		
		if (Base.Testing.equalsIgnoreCase("Smoke"))
		{
			test.log(Status.PASS, "Verify that user can navigated to Review Form");
		   try {
			  test.log(Status.PASS, "Verify that user can Fill Review Form");
			  SelectCarYearModelVersion();
		      EnterReviewTitle();
		      WriteAReview();
		      ReviewRating();
		      ClickSubmitReviewButton();
		}catch(org.openqa.selenium.NoSuchElementException ex){ 
			test.log(Status.FAIL, "Verify that user can enter Fill Review Form");
		}	
	}
	}
	
	public void ClickSubmitReviewButton() throws Exception {
		DataPicker("Verify that user can select <b>Submit Review</b> button", "Submit review", 0);
		Thread.sleep(2000);
	}
	
	public void VerifyReviewDetail() throws Exception {
		if(Base.Testing.equals("System"))
		{
		 try{
			driver.findElement(By.xpath("//android.widget.Button[@content-desc='SHARE']")).click();   
			test.log(Status.PASS, "Verify that user can click share button from review detailed page");
		 }catch(org.openqa.selenium.NoSuchElementException e){
			test.log(Status.FAIL, "Verify that user can click share button from review detailed page");
		 }
		 ClickCrossButton();		
		 try{
			driver.findElement(By.className(Back2)).click();   
			test.log(Status.PASS, "Verify that user can click back button from review detailed page");
		 }catch(org.openqa.selenium.NoSuchElementException e){
			test.log(Status.FAIL, "Verify that user can click back button from review detailed page");
		 }catch(org.openqa.selenium.StaleElementReferenceException ex){
			driver.findElement(By.className(Back2)).click();   
			test.log(Status.PASS, "Verify that user can click back button from review detailed page");
		 }
		}
	}
	
	
}
