package Locators;
import static org.testng.Assert.assertEquals;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;


public class Comparisons extends BaseHelper {
 
	AppiumDriver<MobileElement> driver= null;
	ExtentTest test= null;
	WebDriverWait wait= null;
	JSONObject Object = null;
	public Comparisons(AppiumDriver<MobileElement> driver, ExtentTest test, WebDriverWait wait, JSONObject Object) 
	{
		super(driver, test, wait, Object);
		this.driver= driver;
		this.test= test;
		this.wait= wait;
		this.Object= Object;
	}
	
	public void NavigateToComparisons() throws Exception{
		ClickMoreButton();
		DataPicker("Verify that user can click <b>Products</b> tab from more screen", "Products", 0);
		DataPicker("Verify that user can click <b>New Cars</b> tab from Products", "New Cars", 0);
		DataPicker("Verify that user can click <b>Car Comparisons</b> tab from New Cars", "Car Comparisons", 0);
	}
	
	
	public void VerifyDifferentSectionsOnComparisonScreen() throws Exception
	{
		if (Base.Testing.equalsIgnoreCase("System"))
		{
			test.log(Status.INFO, "User is navigated to Comparison Page");			
			try{
				String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'Hot Comparisons']")).getText();          
				assertEquals(text, "Hot Comparisons");
				test.log(Status.PASS, "Verify that Hot Comparisons section is displayed");
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify that Hot Comparisons section is displayed");
			}	
			
			try{
				driver.findElement(By.id("com.pakwheels.staging:id/layout_compare_view")).click();   
				test.log(Status.PASS, "Verify that user can click first comparison and is navigated to comparison detail page");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can click first comparison and is navigated to comparison detail page");
			}
			
			try{
				driver.findElement(By.className(Back2)).click();   
				test.log(Status.PASS, "Verify that user can click back button from comparison detail page");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can click back button from comparison detail page");
			}catch(org.openqa.selenium.StaleElementReferenceException ex){
				driver.findElement(By.className(Back2)).click();   
				test.log(Status.PASS, "Verify that user can click back button from comparison detail page");
			}
	}	
	}
	
	public void SelectModelVersionForCompare() throws Exception
	{
		JSONObject CarObj = (JSONObject) Object.get("CompareInformation");
		String Model= (String) CarObj.get("Model");
		String Version= (String) CarObj.get("Version");
		
		if (!Model.isEmpty()) 
		{
			DataPicker("Verify that user can select car model button to open model listing", "Compare", 0);			
			SendText("Verify that user can enter text to refine Model search", "Type to refine search", Model);	
			DataPicker("Verify that user can select car model <br>Test Data: <b>"+Model+"</b>", Model, 0);	
			
			if (Version != null && !Version.isEmpty()) 
				DataPicker("Verify that user can select car Version <br>Test Data: <b>"+Version+"</b>", Version, 0); 
			
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
	
	public void SelectModelVersionForWith() throws Exception
	{
		JSONObject CarObj = (JSONObject) Object.get("WithInformation");
		String Model= (String) CarObj.get("Model");
		String Version= (String) CarObj.get("Version");
		
		if (!Model.isEmpty()) 
		{
			DataPicker("Verify that user can select car model button to open model listing", "With", 0);			
			SendText("Verify that user can enter text to refine Model search", "Type to refine search", Model);	
			DataPicker("Verify that user can select car model <br>Test Data: <b>"+Model+"</b>", Model, 0);	
			
			if (Version != null && !Version.isEmpty()) 
				DataPicker("Verify that user can select car Version <br>Test Data: <b>"+Version+"</b>", Version, 0);    		
			
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
	
	public void ClickCompareButton() throws Exception {
		DataPicker("Verify that user can select <b>Compare</b> button", "Compare", 2);
		Thread.sleep(2000);
	}
	
	public void VerifyComparisonDetailPage() throws Exception
	{
		if (Base.Testing.equalsIgnoreCase("System"))
		{
		  test.log(Status.INFO, "User is navigated to Comparison Detail Page");
			
			try{
				driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"SHARE\"]")).click();   
				test.log(Status.PASS, "Verify that user can click share button from comparison detail page");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can click share button from comparison detail page");
			}
			
			try {
			driver.findElement(By.id("com.transsion:id/chooser_close")).click();   
			test.log(Status.PASS, "Verify that user can click cross button from share pop up");
			}catch(org.openqa.selenium.NoSuchElementException e) {
				test.log(Status.FAIL, "Verify that user can click cross button from comparison detail page");
			}
			
			try {
				driver.findElement(By.id("com.pakwheels.staging:id/tv_compare_car_sale_car_comparison")).click();   
				test.log(Status.PASS, "Verify that user can click Used MakeModel link and navigated to Used car listings");
				}catch(org.openqa.selenium.NoSuchElementException e) {
					test.log(Status.FAIL, "Verify that user can click Used MakeModel link and navigated to Used car listings");
				}
			
			try{
				driver.findElement(By.id("com.pakwheels.staging:id/btnBackListing")).click();   
				test.log(Status.PASS, "Verify that user can click back button from Used car listings");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can click back button from Used car listings");
			}
			
			try{
				driver.findElement(By.id("com.pakwheels.staging:id/car_comparison_switch_specs")).click();   
				test.log(Status.PASS, "Verify that user can click Hide common specs");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can click Hide common specs");
			}
			
			try{
				driver.findElement(By.id("com.pakwheels.staging:id/rb_features")).click();   
				test.log(Status.PASS, "Verify that user can click Features");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can click Features");
			}
			
			try{
				driver.findElement(By.id("com.pakwheels.staging:id/car_comparison_switch_feature")).click();   
				test.log(Status.PASS, "Verify that user can click Hide common features");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can click Hide common features");
			}
			
			try{
				driver.findElement(By.id("com.pakwheels.staging:id/btn_toggle")).click();   
				test.log(Status.PASS, "Verify that user can click features detail button");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can click features detail button");
			}
			
			ScrollToEnd();
			try{
				String text= driver.findElement(By.xpath("//android.widget.TextView[@text= 'More Car Comparisons']")).getText();          
				assertEquals(text, "More Car Comparisons");
				test.log(Status.PASS, "Verify that More Car Comparisons is displayed");
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify that More Car Comparisons is displayed");
			}
			
	}
}
}
