package TestSuite;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Locators.SignUp;
import Locators.Base;
import Locators.DataProviders;

public class TestCases_SignUp extends Base
{
	@Test(priority= 1,  dataProvider= "SignUp", dataProviderClass= DataProviders.class, retryAnalyzer= Resources.RetryAnalyzer.class)
	public void SigningUp(JSONObject Object) throws Exception 
	{
		test= extent.createTest("SignUp");
		if(driver==null)
		{
			test.log(Status.FAIL, "Driver failed to initialize <br><b>Logs are: </b><br> "+ Base.errorCause + "<br>" + Base.errorMessage);
		}
		SignUp S = new SignUp(driver, test, wait, Object); 
			
		S.SetApplicationLanguage();
		S.ClickHomeButton();
		S.ClickSignInButton();
		S.SelectContinueWithEmail();
		S.ClickSignUpLink();             
		S.FillData();  
		S.ClickSignUpButton();
		System.out.println("Sign Up to new account is completed");
	}
}
