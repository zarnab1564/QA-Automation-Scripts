package Locators;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;

public class DataProviders extends Base
{

	@DataProvider(name= "SignUp")
	public JSONObject[] ReadSignUpJson() throws IOException, ParseException
	{
		SetJsonReader("SignUp");	
		JSONArray Array= (JSONArray) ElementObj.get("SignUp");	
		JSONObject elements[] = new JSONObject[Array.size()];
		for(int i=0; i< Array.size(); i++)
			elements[i] = null;
		
		for(int i=0; i< Array.size(); i++)
			elements[i] = (JSONObject) Array.get(i);
		return elements;		
	}
	
	//----------------------------------------------- Ad Posting -------------------------------------------------------
	
	@DataProvider(name= "CarAd")
	public JSONObject[] ReadCarAdJson() throws IOException, ParseException
	{
		SetJsonReader("AdPost");	
		JSONArray Array= (JSONArray) ElementObj.get("CarAdPost");	
		JSONObject elements[] = new JSONObject[Array.size()];
		for(int i=0; i< Array.size(); i++)
			elements[i] = null;
		
		for(int i=0; i< Array.size(); i++)
			elements[i] = (JSONObject) Array.get(i);
		return elements;		
	}
	
	@DataProvider(name= "BikeAd")
	public JSONObject[] ReadBikeAdJson() throws IOException, ParseException
	{
		SetJsonReader("AdPost");	
		JSONArray Array= (JSONArray) ElementObj.get("BikeAdPost");	
		JSONObject elements[] = new JSONObject[Array.size()];
		for(int i=0; i< Array.size(); i++)
			elements[i] = null;
		
		for(int i=0; i< Array.size(); i++)
			elements[i] = (JSONObject) Array.get(i);
		return elements;		
	}
	
	@DataProvider(name= "AccessoryAd")
	public JSONObject[] ReadAccessoryAdJson() throws IOException, ParseException
	{
		SetJsonReader("AdPost");	
		JSONArray Array= (JSONArray) ElementObj.get("AccessoryAdPost");	
		JSONObject elements[] = new JSONObject[Array.size()];
		for(int i=0; i< Array.size(); i++)
			elements[i] = null;
		
		for(int i=0; i< Array.size(); i++)
			elements[i] = (JSONObject) Array.get(i);
		return elements;	
	}
	
	//----------------------------------------------- Lead Forms -------------------------------------------------------
	
	@DataProvider(name= "SIFM")
	public JSONObject[] ReadSIFMJson() throws IOException, ParseException
	{
		SetJsonReader("LeadForms");	
		JSONArray Array= (JSONArray) ElementObj.get("SellItForMe");	
		JSONObject elements[] = new JSONObject[Array.size()];
		for(int i=0; i< Array.size(); i++)
			elements[i] = null;
		
		for(int i=0; i< Array.size(); i++)
			elements[i] = (JSONObject) Array.get(i);
		return elements;	
	}
	
	@DataProvider(name= "Inspection")
	public JSONObject[] ReadSInspectionJson() throws IOException, ParseException
	{
		SetJsonReader("LeadForms");	
		JSONArray Array= (JSONArray) ElementObj.get("InspectionRequest");	
		JSONObject elements[] = new JSONObject[Array.size()];
		for(int i=0; i< Array.size(); i++)
			elements[i] = null;
		
		for(int i=0; i< Array.size(); i++)
			elements[i] = (JSONObject) Array.get(i);
		return elements;	
	}
	
	@DataProvider(name= "Auction")
	public JSONObject[] ReadSAuctionJson() throws IOException, ParseException
	{
		SetJsonReader("LeadForms");	
		JSONArray Array= (JSONArray) ElementObj.get("AuctionSheet");	
		JSONObject elements[] = new JSONObject[Array.size()];
		for(int i=0; i< Array.size(); i++)
			elements[i] = null;
		
		for(int i=0; i< Array.size(); i++)
			elements[i] = (JSONObject) Array.get(i);
		return elements;	
	}
	
	@DataProvider(name= "Insurance")
	public JSONObject[] ReadSInsuranceJson() throws IOException, ParseException
	{
		SetJsonReader("LeadForms");	
		JSONArray Array= (JSONArray) ElementObj.get("Insurance");	
		JSONObject elements[] = new JSONObject[Array.size()];
		for(int i=0; i< Array.size(); i++)
			elements[i] = null;
		
		for(int i=0; i< Array.size(); i++)
			elements[i] = (JSONObject) Array.get(i);
		return elements;
	}
	
	@DataProvider(name= "Finance")
	public JSONObject[] ReadSFinanceJson() throws IOException, ParseException
	{
		SetJsonReader("LeadForms");	
		JSONArray Array= (JSONArray) ElementObj.get("Finance");	
		JSONObject elements[] = new JSONObject[Array.size()];
		for(int i=0; i< Array.size(); i++)
			elements[i] = null;
		
		for(int i=0; i< Array.size(); i++)
			elements[i] = (JSONObject) Array.get(i);
		return elements;	
	}
	
	@DataProvider(name= "CarRegistration")
	public JSONObject[] ReadSCarRegistrationJson() throws IOException, ParseException
	{
		SetJsonReader("LeadForms");	
		JSONArray Array= (JSONArray) ElementObj.get("CarRegistration");	
		JSONObject elements[] = new JSONObject[Array.size()];
		for(int i=0; i< Array.size(); i++)
			elements[i] = null;
		
		for(int i=0; i< Array.size(); i++)
			elements[i] = (JSONObject) Array.get(i);
		return elements;
	}
	
	@DataProvider(name= "OwnerShipTransfer")
	public JSONObject[] ReadOwnerShipTransferJson() throws IOException, ParseException
	{
		SetJsonReader("LeadForms");	
		JSONArray Array= (JSONArray) ElementObj.get("OwnerShipTransfer");	
		JSONObject elements[] = new JSONObject[Array.size()];
		for(int i=0; i< Array.size(); i++)
			elements[i] = null;
		
		for(int i=0; i< Array.size(); i++)
			elements[i] = (JSONObject) Array.get(i);
		return elements;
	}
	
	@DataProvider(name= "PartnerWorkshop")
	public JSONObject[] ReadPartnerWorkshopJson() throws IOException, ParseException
	{
		SetJsonReader("LeadForms");	
		JSONArray Array= (JSONArray) ElementObj.get("PartnerWorkshop");	
		JSONObject elements[] = new JSONObject[Array.size()];
		for(int i=0; i< Array.size(); i++)
			elements[i] = null;
		
		for(int i=0; i< Array.size(); i++)
			elements[i] = (JSONObject) Array.get(i);
		return elements;
	}
	
	//----------------------------------------------- Search -------------------------------------------------------
	
	@DataProvider(name= "UsedCars")
	public JSONObject[] ReadUsedCarJson() throws IOException, ParseException
	{
		SetJsonReader("Search");	
		JSONArray Array= (JSONArray) ElementObj.get("UsedCar");	
		JSONObject elements[] = new JSONObject[Array.size()];
		for(int i=0; i< Array.size(); i++)
			elements[i] = null;
		
		for(int i=0; i< Array.size(); i++)
			elements[i] = (JSONObject) Array.get(i);
		return elements;	
	}
	
	@DataProvider(name= "UsedBike")
	public JSONObject[] ReadUsedBikeJson() throws IOException, ParseException
	{
		SetJsonReader("Search");	
		JSONArray Array= (JSONArray) ElementObj.get("UsedBike");	
		JSONObject elements[] = new JSONObject[Array.size()];
		for(int i=0; i< Array.size(); i++)
			elements[i] = null;
		
		for(int i=0; i< Array.size(); i++)
			elements[i] = (JSONObject) Array.get(i);
		return elements;	
	}

	@DataProvider(name= "Accessory")
	public JSONObject[] ReadAccessoryJson() throws IOException, ParseException
	{
		SetJsonReader("Search");	
		JSONArray Array= (JSONArray) ElementObj.get("UsedAccessory");	
		JSONObject elements[] = new JSONObject[Array.size()];
		for(int i=0; i< Array.size(); i++)
			elements[i] = null;
		
		for(int i=0; i< Array.size(); i++)
			elements[i] = (JSONObject) Array.get(i);
		return elements;	
	}
	
	@DataProvider(name= "NewCar")
	public JSONObject[] ReadNewCarJson() throws IOException, ParseException
	{
		SetJsonReader("Search");	
		JSONArray Array= (JSONArray) ElementObj.get("NewCar");	
		JSONObject elements[] = new JSONObject[Array.size()];
		for(int i=0; i< Array.size(); i++)
			elements[i] = null;
		
		for(int i=0; i< Array.size(); i++)
			elements[i] = (JSONObject) Array.get(i);
		return elements;	
	}
	
	@DataProvider(name= "NewBike")
	public JSONObject[] ReadNewBikeJson() throws IOException, ParseException
	{
		SetJsonReader("Search");	
		JSONArray Array= (JSONArray) ElementObj.get("NewBike");	
		JSONObject elements[] = new JSONObject[Array.size()];
		for(int i=0; i< Array.size(); i++)
			elements[i] = null;
		
		for(int i=0; i< Array.size(); i++)
			elements[i] = (JSONObject) Array.get(i);
		return elements;	
	}
	
	//-----------------------------------------------------eStore Order ------------------------------------------------------
	@DataProvider(name= "EstoreOrder")
	public JSONObject[] ReadEstoreJson() throws IOException, ParseException
	{
		SetJsonReader("EstoreOrder");	
		JSONArray Array= (JSONArray) ElementObj.get("EstoreOrder");	
		JSONObject elements[] = new JSONObject[Array.size()];
		for(int i=0; i< Array.size(); i++)
			elements[i] = null;
		
		for(int i=0; i< Array.size(); i++)
			elements[i] = (JSONObject) Array.get(i);
		return elements;	
	}
	
		//-----------------------------------------------------Comparisons ------------------------------------------------------
	
		@DataProvider(name= "Comparisons")
		public JSONObject[] ReadComparisonsJson() throws IOException, ParseException
		{
			SetJsonReader("Comparisons");	
			JSONArray Array= (JSONArray) ElementObj.get("Comparisons");	
			JSONObject elements[] = new JSONObject[Array.size()];
			for(int i=0; i< Array.size(); i++)
				elements[i] = null;
			
			for(int i=0; i< Array.size(); i++)
				elements[i] = (JSONObject) Array.get(i);
			return elements;	
		}
		
		//-----------------------------------------------------Reviews ------------------------------------------------------
		
		@DataProvider(name= "Reviews")
		public JSONObject[] ReadReviewsJson() throws IOException, ParseException
		{
			SetJsonReader("Reviews");	
			JSONArray Array= (JSONArray) ElementObj.get("Reviews");	
			JSONObject elements[] = new JSONObject[Array.size()];
			for(int i=0; i< Array.size(); i++)
				elements[i] = null;
			
			for(int i=0; i< Array.size(); i++)
				elements[i] = (JSONObject) Array.get(i);
			return elements;	
		}	
		
		@DataProvider(name= "Chat")
		public JSONObject[] ReadChatJson() throws IOException, ParseException
		{
			SetJsonReader("Chat");	
			JSONArray Array= (JSONArray) ElementObj.get("Chat");	
			JSONObject elements[] = new JSONObject[Array.size()];
			for(int i=0; i< Array.size(); i++)
				elements[i] = null;
			
			for(int i=0; i< Array.size(); i++)
				elements[i] = (JSONObject) Array.get(i);
			return elements;	
		}
}
