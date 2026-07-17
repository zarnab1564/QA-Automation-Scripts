package Locators.UsedSearch;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Locators.Base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Search_UsedCars extends UsedSearch
{	
	AppiumDriver<MobileElement> driver= null;
	ExtentTest test= null;	
	WebDriverWait wait= null;
	JSONObject Object = null;
	public Search_UsedCars(AppiumDriver<MobileElement> driver, ExtentTest test, WebDriverWait wait, JSONObject Object) 
	{
		super(driver, test, wait, Object);
		this.driver= driver;
		this.test= test;
		this.wait= wait;
		this.Object= Object;
	}

	public void NavigateToCarSearch() throws Exception{
		ClickHomeButton();
		test.log(Status.INFO, "User is navigated to Used Cars Page");
	}
	
	public void VerifyDifferentSectionsOnCarSearch() throws InterruptedException
	{
		if (Base.Testing.equalsIgnoreCase("System"))
		{
			try{
				Scroll("Browse Used Cars");
				test.log(Status.PASS, "Verify that Browse Used Cars section is displayed");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that Browse Used Cars section is displayed");
			}
			
			try{
				Scroll("PakWheels Products");
				test.log(Status.PASS, "Verify that PakWheels Products section is displayed");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that PakWheels Products section is displayed");
			}
					
			try{		
				Scroll("PakWheels Certified");
				test.log(Status.PASS, "Verify that PakWheels Certified section is displayed");
				driver.findElements(By.className("androidx.recyclerview.widget.RecyclerView")).get(0).findElements(By.className("android.widget.LinearLayout")).get(0).click();
				Thread.sleep(2000);
				test.log(Status.PASS, "Verify that user can click first ad from PakWheels Certified Carousel and is navigated to Ad detailed page");
				driver.findElement(By.id(Back1)).click();   
				test.log(Status.PASS, "Verify that user can click back button from Ad detailed page");
			}catch(org.openqa.selenium.NoSuchElementException ex){
				test.log(Status.FAIL, "Verify that PakWheels Certified section is displayed");
			}	
						
			try{
				Scroll("Managed By PakWheels");				
				test.log(Status.PASS, "Verify that Managed By PakWheels section is displayed");
				driver.findElements(By.className("androidx.recyclerview.widget.RecyclerView")).get(1).findElements(By.className("android.widget.LinearLayout")).get(0).click();
				Thread.sleep(2000);
				test.log(Status.PASS, "Verify that user can click first ad from Managed By PakWheels Carousel and is navigated to Ad detailed page");
				driver.findElement(By.id(Back1)).click();   
				test.log(Status.PASS, "Verify that user can click back button from Ad detailed page");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that Managed By PakWheels section is displayed");
			}	
		
			try{
				Scroll("Featured Used Cars");
				test.log(Status.PASS, "Verify that Featured Used Cars section is displayed");
				driver.findElements(By.id("com.pakwheels.staging:id/iv_feature_icon_feature_ads")).get(0).click();
				Thread.sleep(2000);
				test.log(Status.PASS, "Verify that user can click first ad from Featured Used Cars Carousel and is navigated to Ad detailed page");
				driver.findElement(By.id(Back1)).click();   
				test.log(Status.PASS, "Verify that user can click back button from Ad detailed page");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that Featured Used Cars section is displayed");
			}
			
		
			try{
				Scroll("Latest Videos");
				test.log(Status.PASS, "Verify that Latest Videos section is displayed");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that Latest Videos section is displayed");
			}
		
			try{
				Scroll("Latest News");
				test.log(Status.PASS, "Verify that Latest News section is displayed");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that Latest News section is displayed");
			}
			
			try{
				Scroll("Browse More");
				test.log(Status.PASS, "Verify that Browse More section is displayed");
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify that Browse More section is displayed");
			}
		}
	}
	

	

	
	public void SelectEngineTypeInformation() throws Exception
	{
		JSONObject EngineObj = (JSONObject) Object.get("EngineInformation");
		String EngineType= (String) EngineObj.get("EngineType");
		String Assembly= (String) EngineObj.get("Assembly");
		
		JSONObject CapacityObj = (JSONObject) EngineObj.get("Capacity");
		String MinCapacity= (String) CapacityObj.get("MinCapacity");
		String MaxCapacity= (String) CapacityObj.get("MaxCapacity");
		
		if (!EngineType.isEmpty()) {
			ScrollSecondView(EngineType);		
			DataPicker("Verify that user can select engine type <br>Test Data: <b>"+EngineType+"</b>", EngineType, 2);                           		
		}
		
		if (!MinCapacity.isEmpty()) {
			ScrollSecondView("400");
			try{
				driver.findElement(By.xpath("//android.widget.EditText[@text= '400']")).sendKeys(MinCapacity);   
				test.log(Status.PASS, "Verify that user can enter Min Engine Capacity <br>Test Data: <b>"+MinCapacity+"</b>");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can enter Min Engine Capacity <br>Test Data: <b>"+MinCapacity+"</b>");
			}	
		}
		
		if (!MaxCapacity.isEmpty()) {
			try{
				driver.findElement(By.xpath("//android.widget.EditText[@text= 'Any']")).sendKeys(MaxCapacity);   
				test.log(Status.PASS, "Verify that user can enter Max Engine Capacity <br>Test Data: <b>"+MaxCapacity+"</b>");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can enter Max Engine Capacity <br>Test Data: <b>"+MaxCapacity+"</b>");
			}
		}
		
		if (!Assembly.isEmpty()) {
			ScrollSecondView(Assembly);		
			DataPicker("Verify that user can select assembly <br>Test Data: <b>"+Assembly+"</b>", Assembly, 2);                               				
		}
	}
	
	public void SelectModelCategory() throws Exception
	{	
		String ModelCategory= (String) Object.get("ModelCategory");
		if (!ModelCategory.isEmpty()) {
			ScrollSecondView("Model Category");
			try{
				driver.findElement(By.xpath("//android.widget.TextView[@text= 'Model Category']")).click();                
				test.log(Status.PASS, "Verify that user can select <b>Model Category</b> button to open model listing");
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that user can select <b>Model Category</b> button to open model listing");	
			}
			
			DataPicker("Verify that user can select Model Category <br>Test Data: <b>"+ModelCategory+"</b>", ModelCategory, 0);
				
		}
	}
	
	public void SelectTransmission() throws Exception
	{
		String Transmission= (String) Object.get("Transmission");
		if (!Transmission.isEmpty()) {
			ScrollSecondView(Transmission);	
			Thread.sleep(3000);
			DataPicker("Verify that user can select transmission <br>Test Data: <b>"+Transmission+"</b>", Transmission, 2);		
		}
	}
	
	public void SelectBodyColor() throws Exception	
	{
		String Color= (String) Object.get("Color");
		if (!Color.isEmpty()) {
			ScrollSecondView(Color);	
			DataPicker("Verify that user can select body color <br>Test Data: <b>"+Color+"</b>", Color, 0);			
		}
	}
	
	public void CheckPakweelsServicesSection() throws InterruptedException{
		if (Base.Testing.equalsIgnoreCase("System")){
			try{ 
				Scroll("PakWheels Services");
				test.log(Status.PASS, "Verify that PakWheels Services section is displayed");			
			}catch(org.openqa.selenium.NoSuchElementException e){
				test.log(Status.FAIL, "Verify that PakWheels Services section is displayed");	
			}
		}
	}
	
	public void SelectSellerType() throws Exception
	{
		String SellerType= (String) Object.get("SellerType");
		if (!SellerType.isEmpty()) {
			ScrollSecondView(SellerType);		
			DataPicker("Verify that user can select Seller Type <br>Test Data: <b>"+SellerType+"</b>", SellerType, 2);
			
		}
	}
	
	public void SelectAdProperties() throws Exception
	{
		String AdProperties= (String) Object.get("AdProperties");	
		if (!AdProperties.isEmpty()) {
			ScrollSecondView(AdProperties);		
			DataPicker("Verify that user can select Ad Properties <br>Test Data: <b>"+AdProperties+"</b>", AdProperties, 2);	
		}
	}
	
	public void VerifyWantToSellCarSection() throws Exception{
		if (Base.Testing.equalsIgnoreCase("System")){
			try{
				Scroll("Want to Sell Your Car?");
				test.log(Status.PASS, "Verify Want to Sell Your Car section is displayed");
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify Want to Sell Your Car section is displayed");
			}
		}
	}
	
	public void CheckFeaturePartnerSection() throws Exception{
		if (Base.Testing.equalsIgnoreCase("System")){
			try{
				Scroll("Featured Partner");
				test.log(Status.PASS, "Verify Featured Partner section is displayed");
			}catch(AssertionError e){
				test.log(Status.FAIL, "Verify Featured Partner section is displayed");
			}
		}
	}
}
