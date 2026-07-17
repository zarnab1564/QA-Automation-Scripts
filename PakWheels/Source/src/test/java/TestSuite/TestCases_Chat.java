package TestSuite;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import Locators.Base;
import Locators.Chat;
import Locators.DataProviders;
import Locators.Reviews;


public class TestCases_Chat extends Base {

	@Test(priority = 1, dataProvider= "Chat", dataProviderClass= DataProviders.class , retryAnalyzer= Resources.RetryAnalyzer.class)
 	public void Sender(JSONObject Object) throws Exception
 	{  
		if(Testing.equalsIgnoreCase("Smoke"))
			test= extent.createTest("Chat");
		else if (Testing.equalsIgnoreCase("System"))
			test= extent.createTest("Chat (Test Number: "+(String) Object.get("TestNumber")+")");

	    Chat sender= new Chat(driver, test, wait, Object); 		
	    sender.Login();
	    sender.openAdViaADB();
	    sender.clickChatbutton();
	    sender.SendChat();
        
		System.out.println("Test Case Chat  is completed");
 	}
}

