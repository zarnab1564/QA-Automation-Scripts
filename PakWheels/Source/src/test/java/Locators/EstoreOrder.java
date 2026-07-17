package Locators;
import java.util.List;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class EstoreOrder extends BaseHelper
{
	AppiumDriver<MobileElement> driver= null;
	ExtentTest test= null;
	WebDriverWait wait= null;
	JSONObject Object = null;
	int Calculalte_Checkout_total=0;
	public EstoreOrder(AppiumDriver<MobileElement> driver, ExtentTest test, WebDriverWait wait, JSONObject Object) 
	{
		super(driver, test, wait, Object);
		this.driver= driver;
		this.test= test;
		this.wait= wait;
		this.Object= Object;
	}
	
	public void ClickAccessoriesTab() throws Exception{
		ClickHomeButton();
		DataPicker("Verify that user can select <b> Auto Parts </b> tab from headers", "Autostore", 0);	
		test.log(Status.INFO, "User is navigated to Auto Parts Page");	
	}
	
	public void SelectAccessory() throws Exception{	
		String Category= (String) Object.get("Category");
		DataPicker("Verify user can select Category <br> Test Data: <b>"+Category+"</b>", Category, 0);
	}
	
	public void AddItemToCart() throws Exception	{
		test.log(Status.INFO, "User is navigated to Ad detailed page");				
		DataPicker("Verify that user can select <b>Add to Cart</b> button", "Buy Now", 0);  
		//DataPicker("Verify that user can select <b>View cart</b> button", "View cart", 2);	
	}
	public void SelectAutoStoreAd() throws Exception {
		//android.widget.TextView[@resource-id="0_adTitle"]
		
		 By adLocator = By.xpath("//android.widget.TextView[@resource-id='0_adTitle']");
		    WebDriverWait wait = new WebDriverWait(driver, 10);

		    try {
		        // Retry logic in case of stale element
		        for (int attempt = 0; attempt < 3; attempt++) {
		            try {
		            	
		                wait.until(ExpectedConditions.elementToBeClickable(adLocator)).click();
		                test.log(Status.PASS, "Verify that user can select Ad from Listing Page");
		                return; // success
		            } catch (StaleElementReferenceException e) {
		                if (attempt == 2) throw e; // fail on last attempt
		                Thread.sleep(500); // brief pause before retry
		            }
		        }
		    } catch (TimeoutException e) {
		        test.log(Status.FAIL, "Element not found or not clickable within timeout");
		        throw new Exception("Timeout while waiting for element", e);
		    } catch (StaleElementReferenceException e) {
		        test.log(Status.FAIL, "Element became stale before click after retries");
		        throw new Exception("Stale element issue", e);
		    }
	}
	
	public void SelectCheckOutButton() throws Exception{	
		DataPicker("Verify that user can select <b>Checkout button</b>", "Checkout", 2);
	}
	
	public void EnterInformation() throws Exception
	{	
		String Address= (String) Object.get("Address");
		System.out.println("Address" +Address);

		JSONObject locationObj = (JSONObject) Object.get("Location");
		String City= (String) locationObj.get("City");
		String CityArea= (String) locationObj.get("CityArea");
		
		String Landmark= (String) Object.get("Landmark");
		System.out.println("Landmark" +Landmark);
		
		test.log(Status.INFO, "User is navigated to Shipping Address Information Page");	
		
		
		//Address already added
		try {
			 if(driver.findElement(By.id("com.pakwheels.staging:id/service_card")).isDisplayed())
			 {
				 String number=driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.pakwheels.staging:id/number']")).getText();
				 if(number.equalsIgnoreCase(Base.MobileNumber))
				 {
				 driver.findElement(By.id("com.pakwheels.staging:id/service_card")).click();
				 }
				 else
				 {
					 driver.findElement(By.id("com.pakwheels.staging:id/addNew")).click();
				        List<MobileElement> fields = driver.findElements(By.xpath("//android.widget.EditText[@resource-id='com.pakwheels.staging:id/et_content']"));

				        for (MobileElement field : fields) {
				            field.clear();
				        }
					 SendText("Verify that user can enter Name <br>Test Data: <b>"+Base.Name+"</b>", "Enter full name", Base.Name);
					 SendText("Verify that user can enter Mobile Number <br>Test Data: <b>"+Base.MobileNumber+"</b>", "03xxxxxxxxx", Base.MobileNumber);	
						
						SendText("Verify that user can enter Address <br>Test Data: <b>"+Address+"</b>","House No, Street, Area",Address);
						if(Landmark!=null)
						{	
						  SendText("Verify that user can enter Address <br>Test Data: <b>"+Address+"</b>","Landmark",Landmark);
						}
						DataPicker("Verify that user can select City button", "Select city area", 4);                    
						SendText("Verify that user can enter text to refine City search <br>Test Data: <b>"+City+"</b>", "Type to refine search", City);
						DataPicker("Verify that user can select city <br>Test Data: <b>"+City+"</b>", City, 0);
						
						SendText("Verify that user can enter text to refine City search <br>Test Data: <b>"+City+"</b>", "Type to refine search", CityArea);
						DataPicker("Verify that user can select city Area <br>Test Data: <b>"+City+"</b>", CityArea, 0);
						
						
						
						
						Thread.sleep(2000);
						//driver.findElement(By.xpath("//android.widget.EditText[@text='Enter Address']")).sendKeys(Address);
						   
						SelectContinueButton();
							
				 }
				 
			 }
			  
		}catch(org.openqa.selenium.NoSuchElementException e){
			SendText("Verify that user can enter Name <br>Test Data: <b>"+Base.Name+"</b>", "Enter full name", Base.Name);
			SendText("Verify that user can enter Mobile Number <br>Test Data: <b>"+Base.MobileNumber+"</b>", "03xxxxxxxxx", Base.MobileNumber);	
			
			
			SendText("Verify that user can enter Address <br>Test Data: <b>"+Address+"</b>","House No, Street, Area",Address);
			if(Landmark!=null)
			{	
			  SendText("Verify that user can enter Address <br>Test Data: <b>"+Address+"</b>","Landmark",Landmark);
			}
			DataPicker("Verify that user can select City button", "Select city area", 4);                    
			SendText("Verify that user can enter text to refine City search <br>Test Data: <b>"+City+"</b>", "Type to refine search", City);
			DataPicker("Verify that user can select city <br>Test Data: <b>"+City+"</b>", City, 0);
			
			SendText("Verify that user can enter text to refine City search <br>Test Data: <b>"+City+"</b>", "Type to refine search", CityArea);
			DataPicker("Verify that user can select city Area <br>Test Data: <b>"+City+"</b>", CityArea, 0);
			
			
			
			
			Thread.sleep(2000);
			//driver.findElement(By.xpath("//android.widget.EditText[@text='Enter Address']")).sendKeys(Address);
			   
			SelectContinueButton();
		}                
	}
	
	public void SelectProceedToPayment() throws Exception{
		DataPicker("Verify that user can select <b>Proceed To Payment</b>", "Proceed to Payment", 2);
		Thread.sleep(2000);
	}
	
	public void SelectPaymentMethod() throws Exception{
		String Payment = (String) Object.get("PaymentMethod");
		test.log(Status.INFO, "User is navigated to Payment Methods Screen");	 
		DataPicker("Verify that user can select Payment Method <br>Test Data: <b>"+Payment+"</b>", Payment, 0);			
	}
	
	public void PlaceOrder() throws Exception{
		DataPicker("Verify that user can select <b>Place order</b> button", "Place Order", 2);			
		DataPicker("Verify that user can select on <b>Done</b> button", "Done", 2);					
		test.log(Status.INFO, "User has placed order successfully");
	}



	public void CartCalculations() throws Exception {
		String discount= (String) Object.get("Discount");
		
	    if(Base.Testing.equalsIgnoreCase("System"))
	    {
	         
	    	test.log(Status.PASS, "<h5>Cart Calculations</h5>");
			Thread.sleep(5000);
	    	
            String Item_Price=driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.pakwheels.staging:id/tv_service_charges_detail_a']")).getText();
			String Cart_Price= Item_Price.replaceAll("[^0-9]", "");
			int price_cart_int=Integer.parseInt(Cart_Price);
			System.out.println("Unit Price of Item "+price_cart_int);
			
			
			//Increase Quantity of Cart and get quantity
			driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.pakwheels.staging:id/iv_icon_add_more_btn_checkout']")).click();
			test.log(Status.PASS, "Verify that Quantity is incrmeneted in Cart Item");
			Thread.sleep(2000);
			String count=driver.findElement(By.id("com.pakwheels.staging:id/cart_item_count")).getText();
			int quantity=Integer.parseInt(count);
			System.out.println("Quantity of Cart Button "+quantity);
			
			//Calculate Sub total price Quantity into Price
			int total=quantity*price_cart_int;
			System.out.println("Calculated total amount through formula "+total);
			
			//Get Sub-Total amount from Screen to Compare with calculated
			String Screen_calculated=driver.findElement(By.id("com.pakwheels.staging:id/suborder_total_price")).getText();
			int Screen_calculated_int=Integer.parseInt(Screen_calculated.replaceAll("[^0-9]", ""));
			System.out.println("Get  Subtotoal amount from Screen "+Screen_calculated);
			
			if(total==Screen_calculated_int)
			{
				test.log(Status.PASS,"Verify that Calculted amount is Equal Amount to Subtotal amount of screen");
				
			 try{
				 if(driver.findElement(By.id("com.pakwheels.staging:id/ic_delivery")).isDisplayed())
			     {
					Calculalte_Checkout_total=total;
					
			         //Apply Discount Code
	                if(!discount.isEmpty())
	                {
	                SendText("Verify that user can enter Address <br>Test Data: <b>"+discount+"</b>","Enter voucher code",discount);
	                test.log(Status.PASS, "Verify that Discount is Entered");
	                driver.hideKeyboard();
	                Thread.sleep(1000);
	                DataPicker("Verify that user can select on <b>Apply</b> button","Apply", 2);		
	                test.log(Status.PASS, "Verify that Discount is Applied");
	                Calculalte_Checkout_total=Calculalte_Checkout_total-1200;
	                System.out.println("Discout is applies : "+Calculalte_Checkout_total);
	                Thread.sleep(5000);
	                }
					
					   String Checkout_Amount_Free_Shiping=driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.pakwheels.staging:id/cart_total_price']")).getText();
					   int Checkout_Amount_Total_Free_Ship=Integer.parseInt(Checkout_Amount_Free_Shiping.replaceAll("[^0-9]", ""));
					   System.out.println("ChecKout amount if No Free shiping :"+Checkout_Amount_Total_Free_Ship);
					
					 try
				      {
					     Assert.assertEquals(Calculalte_Checkout_total,Checkout_Amount_Total_Free_Ship);
				         System.out.println("Verifed that Cart Calculations are Equal in case of Free Shpping Fee "+Calculalte_Checkout_total+" = "+Checkout_Amount_Total_Free_Ship);
				         test.log(Status.PASS,"Verifed that Cart Calculations are Equal in case of Free Shpping Fee "+Calculalte_Checkout_total+" = "+Checkout_Amount_Total_Free_Ship); 
				       }
				    catch(AssertionError E)
				       {
				    	 test.log(Status.FAIL,"UnEqual Assertion ! "+"Verifed that Cart Calculations are not Equal in case of Free Shpping Fee "+Screen_calculated_int+" != "+Checkout_Amount_Total_Free_Ship);
				       }
			     }
			}
			catch(org.openqa.selenium.NoSuchElementException e){
				
		
                   //Get Shipping Fee for Cart
			    	 String shipfee=driver.findElement(By.id("com.pakwheels.staging:id/shipping_value")).getText();
			         int Shipping_fee=Integer.parseInt(shipfee.replaceAll("[^0-9]", ""));
			         System.out.println("Shipping Fee: "+shipfee);
			         
			         //Added Shipping Fee in Total
			         Calculalte_Checkout_total=Shipping_fee+total;
	                 System.out.println("Checkout Total amount  after Adding Shipping fee "+Calculalte_Checkout_total);
	                 test.log(Status.INFO, "Verify that shipping added to total amount");
			         //Apply Discount Code
	                 if(!discount.isEmpty())
	                 {
	                 SendText("Verify that user can enter Address <br>Test Data: <b>"+discount+"</b>","Enter voucher code",discount);
	                 test.log(Status.PASS, "Verify that Discount is Entered");
	                 driver.hideKeyboard();
	                 Thread.sleep(1000);
	                 DataPicker("Verify that user can select on <b>Apply</b> button","Apply", 2);		
	                 test.log(Status.PASS, "Verify that Discount is Applied");
	                 Calculalte_Checkout_total=Calculalte_Checkout_total-1200;
	                 System.out.println("Discout Code is applied  : "+Calculalte_Checkout_total);
	                 Thread.sleep(5000);
	                 }
	                  
	                 //Compare Amount Calculated and Checkout
					   String Checkout_Amount=driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.pakwheels.staging:id/cart_total_price']")).getText();
					   int Checkout_Total=Integer.parseInt(Checkout_Amount.replaceAll("[^0-9]", ""));
					   System.out.println("ChecKout amount if No Free shiping :"+Checkout_Total);
			           try
			             {
				           Assert.assertEquals(Calculalte_Checkout_total,Checkout_Total);
				           System.out.println("Verifed that Calculted Checkout Amount : "+Calculalte_Checkout_total+" = Checkout Total : "+Checkout_Total);
			             }
			           catch(AssertionError E)
			             {
				           test.log(Status.FAIL,"UnEqual Assertion"+"Verifed that  "+Screen_calculated_int+" != "+Checkout_Total);
			             }
			     
				
			}

	    }
	 }		
}

	public void SelectSoldPW() throws Exception {
		String ShopNow= (String) Object.get("ShopNow");
		Scroll("Sold By PakWheels"); 
		DataPicker("Verify that user can select show now options <br>Test Data: <b>"+ShopNow+"</b>", ShopNow, 2);
	}

	public void RemoveItemfromCart() throws Exception {
		
		 test.log(Status.PASS, "<h2>Remove Item from Cart</h2>");
		 System.out.println("Remove Item ");

		 String Remove= (String) Object.get("RemoveItem");
		 if(Base.Testing.equalsIgnoreCase("Smoke") && Remove.equals("Yes"))
		   {
				
				Thread.sleep(2000);
				String count=driver.findElement(By.id("com.pakwheels.staging:id/cart_item_count")).getText();
				int quantity=Integer.parseInt(count);
				System.out.println("Quantity of Cart Button "+quantity);
				for(int i=0;i<quantity;i++)
				{	
				 driver.findElement(By.id("com.pakwheels.staging:id/iv_icon_minus_more_btn_checkout")).click();
				 test.log(Status.PASS, "Verify that Quantity is decremeneted in Cart Item");
				    System.out.println("Removed Item ");
					Thread.sleep(2000);
					String dec_count=driver.findElement(By.id("com.pakwheels.staging:id/cart_item_count")).getText();
					int dec_quantity=Integer.parseInt(count);
					System.out.println("Quantity of Cart Button and index "+dec_quantity +" Index "+ i);

			   }
				if(driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/message']")).isDisplayed())
				{
					String message=driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/message']")).getText();
				    if(message.equalsIgnoreCase("Are you sure you want to remove this item from the cart?"))
				    {
				    	DataPicker("Verify that user can select show now options <br>Test Data: <b>Yes I am  SURE</b>","YES, I'M SURE", 2);
				    	Thread.sleep(2000);
				    }
				}
				
		    	try {
			    	
		    	if(driver.findElement(By.xpath("com.pakwheels.staging:id/btn_add_ride")).isDisplayed())
		    	{
		    		String msge=driver.findElement(By.xpath("com.pakwheels.staging:id/btn_add_ride")).getText();
				    if(msge.equalsIgnoreCase("Let's go shopping !"))
				    {
				    	test.log(Status.PASS, "Cart is Empty Now");
				    	driver.findElement(By.xpath("com.pakwheels.staging:id/btn_add_ride")).click();
						SelectAd();
						AddItemToCart();
						CartCalculations();
				    }
		    	}
		     }
		     catch(org.openqa.selenium.NoSuchElementException e){}
			}
	}

	public void OrderSummary() throws Exception {
		DataPicker("Verify that user redirected to Order summary screen", "Order placed", 0);
	}
}

