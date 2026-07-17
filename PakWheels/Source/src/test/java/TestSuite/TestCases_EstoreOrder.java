package TestSuite;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import Locators.Base;
import Locators.DataProviders;
import Locators.EstoreOrder;

public class TestCases_EstoreOrder extends Base
{
	@Test(priority= 1, dataProvider= "EstoreOrder", dataProviderClass= DataProviders.class, retryAnalyzer= Resources.RetryAnalyzer.class)
 	public void EstoreOrders(JSONObject Object) throws Exception
 	{
		if(Testing.equalsIgnoreCase("Smoke"))
			test= extent.createTest("Estore Order");		
		else if (Testing.equalsIgnoreCase("System"))			
			test= extent.createTest("Estore Order (Test Number: "+(String) Object.get("TestNumber")+")");
		
		EstoreOrder ES = new EstoreOrder(driver, test, wait, Object); 		
		ES.Login();
		ES.ClickAccessoriesTab();     
		ES.SelectAccessory();
		ES.SelectAutoStoreAd();
		ES.AddItemToCart();
		ES.CartCalculations();
		//ES.RemoveItemfromCart();
		ES.SelectContinueButton();
		ES.EnterInformation();
		ES.SelectContinueButton();
		ES.EnterVerificationCode();
		ES.SelectPaymentMethod();
		ES.SelectContinueButton();
		ES.OrderSummary();
		System.out.println("Estore order placement is completed");
 	}
}
