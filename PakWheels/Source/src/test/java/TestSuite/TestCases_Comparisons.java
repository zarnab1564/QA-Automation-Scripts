package TestSuite;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import Locators.Base;
import Locators.DataProviders;
import Locators.Comparisons;
public class TestCases_Comparisons extends Base
{

	@Test(priority = 1, dataProvider= "Comparisons", dataProviderClass= DataProviders.class, retryAnalyzer= Resources.RetryAnalyzer.class)
 	public void Comparisons(JSONObject Object) throws Exception
 	{  
		if(Testing.equalsIgnoreCase("Smoke"))
			test= extent.createTest("Comparisons");
		else if (Testing.equalsIgnoreCase("System"))
			test= extent.createTest("Comparisons (Test Number: "+(String) Object.get("TestNumber")+")");

		Comparisons C = new Comparisons(driver, test, wait, Object); 		
		C.Login();
		C.NavigateToComparisons();
		C.VerifyDifferentSectionsOnComparisonScreen();
		C.SelectModelVersionForCompare();
		C.SelectModelVersionForWith();
		C.ClickCompareButton();
		C.VerifyComparisonDetailPage();
		System.out.println("Comparisons is completed");
 	}
}
