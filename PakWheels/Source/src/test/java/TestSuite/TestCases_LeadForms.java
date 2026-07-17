package TestSuite;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import Locators.Base;
import Locators.DataProviders;
import Locators.LeadForms.LeadForm_AuctionSheetPage;
import Locators.LeadForms.LeadForm_CarRegistration;
import Locators.LeadForms.LeadForm_FinancePage;
import Locators.LeadForms.LeadForm_InspectionRequestPage;
import Locators.LeadForms.LeadForm_InsurancePage;
import Locators.LeadForms.LeadForm_OwnerShipTransfer;
import Locators.LeadForms.LeadForm_PartnerWorkshop;
import Locators.LeadForms.LeadForm_SIFMPage;

public class TestCases_LeadForms extends Base
{	   
 	@Test(priority = 1, dataProvider= "SIFM", dataProviderClass= DataProviders.class ,retryAnalyzer= Resources.RetryAnalyzer.class)
	public void SellItForMeRequest(JSONObject Object) throws Exception
	{	
		if(Testing.equalsIgnoreCase("Smoke"))
			test= extent.createTest("Sell It For Me Request");			
		else if (Testing.equalsIgnoreCase("System"))
			test= extent.createTest("Sell It For Me Request (Test Number: "+(String) Object.get("TestNumber")+")");
		
		LeadForm_SIFMPage SIFM = new LeadForm_SIFMPage(driver, test, wait, Object); 		
		SIFM.Login();		
		SIFM.NavigateToSIFMPage(); 
		SIFM.VerifySIFMPage();
		SIFM.ClickGetStartedButton();
		SIFM.VerifySIFMBasicInfoForm();
		SIFM.EnterContactInformation();
		SIFM.SelectCity();
		SIFM.SelectContinueButton();	
		SIFM.EnterVerificationCode();
		SIFM.VerifySIFMCarInfoform();
		SIFM.SelectCarInformation();
		SIFM.SelectAssembly();
		SIFM.SelectRegisteredIn();
		SIFM.SelectContinueButton();	
		SIFM.SelectCityArea();
		SIFM.EnterAddress();
		SIFM.SlotSelection();  
		SIFM.SelectContinueButton();
		SIFM.PaymentFlow();
		
		System.out.println("Sell It For Me request is completed");
	}
	
	@Test(priority = 2, dataProvider= "Inspection", dataProviderClass= DataProviders.class, retryAnalyzer= Resources.RetryAnalyzer.class)
	//@Test(priority = 2, dataProvider= "Inspection", dataProviderClass= DataProviders.class)
	public void InspectionRequest(JSONObject Object) throws Exception
	{		
		if(Testing.equalsIgnoreCase("Smoke"))
			test= extent.createTest("Inspection Request");		
		else if (Testing.equalsIgnoreCase("System"))
			test= extent.createTest("Inspection Request (Test Number: "+(String) Object.get("TestNumber")+")");

		LeadForm_InspectionRequestPage IR = new LeadForm_InspectionRequestPage(driver, test, wait, Object); 				
		IR.Login();	
		IR.NavigateToInspectionPage();
		//IR.VerifyInspectionProductPage();
		IR.ClickOnScheduleInspectionButton();
		IR.EnterContactInformation();		
		IR.SelectCity();
		IR.SelectCarInformation();	
		IR.SelectContinueButton();		        
		IR.EnterVerificationCode();	
		IR.SelectCityArea();
		IR.EnterAddress();
		//IR.SelectInspectionSlot(); 
		IR.SlotSelection();
		IR.SelectContinueButton();
		IR.PaymentFlow();
		System.out.println("Inspection request is completed");
	}
	
	@Test(priority = 3, dataProvider= "Auction", dataProviderClass= DataProviders.class, retryAnalyzer= Resources.RetryAnalyzer.class)
	public void AuctionSheetRequest(JSONObject Object) throws Exception
	{	
		if(Testing.equalsIgnoreCase("Smoke"))
			test= extent.createTest("Auction Sheet Request");		
		else if (Testing.equalsIgnoreCase("System"))
			test= extent.createTest("Auction Sheet Request (Test Number: "+(String) Object.get("TestNumber")+")");
		
		LeadForm_AuctionSheetPage AS = new LeadForm_AuctionSheetPage(driver, test, wait, Object); 			
		AS.Login();	
		AS.NavigateToAuctionSheetPage();
		AS.EnterChasisNumber();
		AS.EnterContactInformation();        
		AS.SelectContinueButton();
		AS.SelectContinueButton();
		AS.EnterVerificationCode();
		AS.PaymentFlow();
		System.out.println("Auction sheet request is completed");
	}
	@Test(priority = 4, dataProvider= "Insurance", dataProviderClass= DataProviders.class, retryAnalyzer= Resources.RetryAnalyzer.class)
	public void InsuranceRequest(JSONObject Object) throws Exception
	{
		if(Testing.equalsIgnoreCase("Smoke"))
			test= extent.createTest("Insurance");
		else if (Testing.equalsIgnoreCase("System"))
			test= extent.createTest("Insurance (Test Number: "+(String) Object.get("TestNumber")+")");
		
		LeadForm_InsurancePage I = new LeadForm_InsurancePage(driver, test, wait, Object); 		
		I.Login();	
		I.NavigateToInsurancePage(); 
		I.InsurancethroughProductPage();
		I.NavigatetoNewCarModel();
		I.InsurancethroughModelPage();
		I.InsurancethroughVersionPage();
		I.InsurancethroughUsedCarAd();
		I.FillForm();
		I.SelectContinueButton();			
		I.EnterVerificationCode();
		I.SummaryScreen();
		System.out.println("Insurance request is completed");
	}

	@Test(priority = 5, dataProvider= "Finance", dataProviderClass= DataProviders.class, retryAnalyzer= Resources.RetryAnalyzer.class)
	//@Test(priority = 5, dataProvider= "Finance", dataProviderClass= DataProviders.class)

	public void FinanceRequest(JSONObject Object) throws Exception
	{	
	
        String Status = (String) Object.get("CarStatus");
        String testName = "Finance Request - " + Status;
		if(Testing.equalsIgnoreCase("Smoke"))
		{
				test= extent.createTest(testName);		
		}			
		else if (Testing.equalsIgnoreCase("System"))
		{			
				test= extent.createTest(testName+" (Test Number: "+(String) Object.get("TestNumber")+")");
	
		}

		LeadForm_FinancePage F = new LeadForm_FinancePage(driver, test, wait, Object); 		
		F.Login();			
		F.NavigateToFinancePage();  			
		F.FillForm();  
		F.SelectTenureAndDownPayment();
		F.ClickShowPlansButton();
		F.FillBasicInfo();
		F.EnterVerificationCode();
		F.SelectBank();
		F.EnterInformation();  
		F.EnterFinancialInformation();  
		F.ClickSubmitButton();			
		F.EnterVerificationCode();
		F.CheckThankyouScreen();
		System.out.println("Finance-"+Object.get("CarStatus")+" request is completed");
	}
	
	@Test(priority = 6, dataProvider= "CarRegistration", dataProviderClass= DataProviders.class, retryAnalyzer= Resources.RetryAnalyzer.class)
	public void CarRegistration(JSONObject Object) throws Exception
	{
		if(Testing.equalsIgnoreCase("Smoke"))
			test= extent.createTest("CarRegistration");
		else if (Testing.equalsIgnoreCase("System"))
			test= extent.createTest("CarRegistration (Test Number: "+(String) Object.get("TestNumber")+")");
		
		LeadForm_CarRegistration R = new LeadForm_CarRegistration(driver, test, wait, Object); 		
		R.Login();	
		R.NavigateToCarRegistrationPage();
		R.VerifyRegisterationLandingPage();
		R.CickOnCarRegistrationButon();
		R.VerifyBasicInfoForm();
		R.EnterBasicInfo();
		R.SelectContinueButton();
		R.EnterVerificationCode();
		R.VerifyCarInfoform ();
		R.SelectCarInformation();
		R.SelectRegistrationCity();
		R.SelectContinueButton();
		R.CheckThankyouScreen();
		
		System.out.println("Car Registration request is completed");
	}
	
	@Test(priority = 7, dataProvider= "OwnerShipTransfer", dataProviderClass= DataProviders.class, retryAnalyzer= Resources.RetryAnalyzer.class)
	public void OwnerShipTransfer(JSONObject Object) throws Exception
	{
		if(Testing.equalsIgnoreCase("Smoke"))
			test= extent.createTest("OwnerShipTransfer");
		else if (Testing.equalsIgnoreCase("System"))
			test= extent.createTest("OwnerShipTransfer (Test Number: "+(String) Object.get("TestNumber")+")");
		
		LeadForm_OwnerShipTransfer T = new LeadForm_OwnerShipTransfer(driver, test, wait, Object); 		
		T.Login();	
		T.NavigateToOwnerShipTransferPage();
		T.VerifyTransferLandingPage();
		T.CickOnOwnerShipTransferButon();
		T.VerifyBasicInfoForm();
		T.EnterBasicInfo();
		T.SelectContinueButton();
		T.EnterVerificationCode();
		T.VerifyCarInfoform ();
		T.SelectCarInformation();
		T.SelectRegistrationCity();
		T.SelectContinueButton();
		T.CheckThankyouScreen();
		
		System.out.println("OwnerShip Transfer request is completed");
	}
	
	/*@Test(priority = 8, dataProvider= "PartnerWorkshop", dataProviderClass= DataProviders.class, retryAnalyzer= Resources.RetryAnalyzer.class)
	public void PartnerWorkshop(JSONObject Object) throws Exception
	{
		if(Testing.equalsIgnoreCase("Smoke"))
			test= extent.createTest("PartnerWorkshop");
		else if (Testing.equalsIgnoreCase("System"))
			test= extent.createTest("PartnerWorkshop (Test Number: "+(String) Object.get("TestNumber")+")");
		
		LeadForm_PartnerWorkshop P = new LeadForm_PartnerWorkshop(driver, test, wait, Object); 		
		P.Login();	
		P.NavigateToPartnerworkshopPage();
		P.CickOnPartnerWorkshopButon();
		P.EnterContactInfo();
		P.SelectCity();
		P.SelectCityAreaPartner();
		P.SelectContinueButton();
		P.EnterVerificationCode();
		P.SelectCarInformation();
		P.SelectServiceIssues();
		P.AddNotes();
		P.SelectContinueButton();
		P.CheckThankyouScreen();
		System.out.println("Partner Workshop request is completed");
	}*/
	
}
