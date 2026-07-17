package TestSuite;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import Locators.Base;
import Locators.DataProviders;
import Locators.Reviews;


public class TestCases_Reviews extends Base {

	@Test(priority = 1, dataProvider= "Reviews", dataProviderClass= DataProviders.class)
	//@Test(priority = 1, dataProvider= "Reviews", dataProviderClass= DataProviders.class, retryAnalyzer= Resources.RetryAnalyzer.class)
 	public void Reviews(JSONObject Object) throws Exception
 	{  
		if(Testing.equalsIgnoreCase("Smoke"))
			test= extent.createTest("Reviews");
		else if (Testing.equalsIgnoreCase("System"))
			test= extent.createTest("Reviews (Test Number: "+(String) Object.get("TestNumber")+")");

		Reviews R = new Reviews(driver, test, wait, Object); 		
		R.Login();
		R.NavigateToReviews();
		R.VerifyDifferentSectionsOnCarReviews();
		R.ClickWriteAReviewButtonOnReviewScreen();
		R.VerifyReviewDetail();
		R.SelectCarModel();
		R.ClickSearchButton();
		R.SelectVariants();
		R.ScrollToEnd();
		R.ClickReviewWidget();
		R.ClickWriteAReviewButtonOnModelReview();
		R.FillReviewForm();
		R.ClickSubmitReviewButton();
		System.out.println("Reviews is completed");
 	}
}

