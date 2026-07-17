package Locators;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import Resources.ExtentReporterNG;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;


public class Base extends ExtentReporterNG
{
	public static AppiumDriver<MobileElement> driver = null;
	public static String DeviceType, Testing_Enviroment, Testing, DeviceName, UDID, DeviceVersion, Name, Password, Email, MobileNumber, VerificationCode, JazzCashNumber, JazzCashCNIC;
	static String ProjectLcation = System.getProperty("user.dir");
	protected static JSONObject ElementObj = null;
	public WebDriverWait wait;
	public static ExtentTest test;

	public static String errorMessage;
	public static Throwable errorCause;

	// FIX 1: Changed "throws MalformedURLException" to "throws Exception"
	//        so that the rethrown exception in the catch block compiles correctly.
	@BeforeMethod(alwaysRun = true)
	public void SetCapabilities() throws Exception
	{
		try
		{
			ReadPropertiesFile();
			DesiredCapabilities cap = new DesiredCapabilities();

			if (Testing_Enviroment.equalsIgnoreCase("Staging"))
			{
				cap.setCapability("appPackage", "com.pakwheels.staging");
			}
			else if (Testing_Enviroment.equalsIgnoreCase("Production"))
			{
				cap.setCapability("appPackage", "com.pakwheels");
			}
			else
			{
				System.out.println("Please Specify Testing Enviroment in properties file : Staging or Production");
				System.out.println("Thats why unable to execute test cases");
			}

			cap.setCapability("appActivity", "com.pakwheels.activities.other.SplashScreenActivity");
			cap.setCapability("platformName", "android");
			cap.setCapability("automationName", "UiAutomator2");
			cap.setCapability("newCommandTimeout", 60000);
			cap.setCapability("Appium:maxInstances", 1);
			cap.setCapability("appium:ignoreHiddenApiPolicyError", true);
			cap.setCapability("deviceName", DeviceName);

			// FIX 2: Removed cap.setCapability("udid", UDID)
			//        UDID is commented out in ReadPropertiesFile() so it was always null,
			//        which caused Appium session creation to fail silently.

			// FIX 3: Removed cap.setCapability("platformVersion", DeviceVersion)
			//        DeviceVersion is also commented out in ReadPropertiesFile() so it was null too.

			cap.setCapability("autoGrantPermissions", true);

			int maxRetries = 3;
			int retryCount = 0;
			boolean isDriverInitialized = false;

			while (!isDriverInitialized && retryCount < maxRetries)
			{
				try
				{
					driver = new AppiumDriver<MobileElement>(new URL("http://127.0.0.1:4723"), cap);
					if (driver != null)
					{
						isDriverInitialized = true;
					}
				}
				catch (Exception e)
				{
					errorMessage = e.getMessage();
					errorCause = e.getCause();
					System.out.println(e.getMessage() + " Error on Driver " + e.getCause());
					System.out.println("Failed to initialize the driver. Retrying...");
					retryCount++;
					Thread.sleep(2000);
				}
			}

			if (!isDriverInitialized)
			{
				System.out.println("Failed to initialize the driver after " + maxRetries + " attempts.");

				throw new RuntimeException("Driver failed to initialize after " + maxRetries + " attempts. Check Appium server and device connection.");
			}
			
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 20);
		}
		catch (Exception exp)
		{
			System.out.println("Cause is: " + exp.getCause());
			System.out.println("Message is: " + exp.getMessage());
			exp.printStackTrace();

			// FIX 6: Rethrow the exception — this is the most important fix.
			//        Previously exceptions were swallowed here, so TestNG thought
			//        @BeforeMethod passed and went ahead running the @Test with null driver.
			throw exp;
		}

		String FaileddirectoryPath = System.getProperty("user.dir") + File.separator + "ScreenShots_Failed" + File.separator;
	}

	public static void deleteFailedScreenshots(String directoryPath)
	{
		File directory = new File(directoryPath);
		if (directory.exists() && directory.isDirectory())
		{
			File[] files = directory.listFiles();
			if (files != null)
			{
				for (File file : files)
				{
					if (file.isFile() && file.getName().endsWith(".png"))
					{
						boolean deleted = file.delete();
						if (deleted)
						{
							System.out.println("Deleted failed screenshot: " + file.getName());
						}
						else
						{
							System.out.println("Failed to delete: " + file.getName());
						}
					}
				}
			}
		}
		else
		{
			System.out.println("Directory doesn't exist: " + directoryPath);
		}
	}

	public static void getScreenshot(String testcasename) throws IOException
	{
		System.out.println("Get Screenshot of Failed TestCases");
		File scrfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotPath = System.getProperty("user.dir") + File.separator + "ScreenShots_Failed" + File.separator + testcasename + ".png";
		System.out.println("ScreenshotPath : " + screenshotPath);
		try
		{
			FileUtils.copyFile(scrfile, new File(screenshotPath));
			System.out.println("Screenshot saved at: " + screenshotPath);
		}
		catch (WebDriverException we)
		{
			System.out.println("WebDriverException while taking screenshot: " + we.getMessage());
		}
		catch (IOException ioe)
		{
			System.out.println("IOException while saving screenshot: " + ioe.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("Unexpected error while taking screenshot: " + e.getMessage());
		}
	}

	// FIX 7: Merged the old TearDown() @AfterMethod into this one.
	//        Having two @AfterMethod annotations caused TestNG to quit the driver
	//        twice — once after logging, once in TearDown() — leaving driver in a
	//        dead state for the next test's @BeforeMethod.
	@AfterMethod(alwaysRun = true)
	public void getResult(Method method, ITestResult testResult) throws IOException
	{
		if (testResult.getStatus() == ITestResult.FAILURE)
		{
			test.log(Status.FAIL, "<b>Test Failed</b><br><b>Logs are: </b><br>" + testResult.getThrowable().fillInStackTrace());
		}
		else if (testResult.getStatus() == ITestResult.SUCCESS)
		{
			test.log(Status.INFO, "Test Passed");
		}
		else if (testResult.getStatus() == ITestResult.SKIP)
		{
			test.log(Status.SKIP, "Test Skip");
			extent.removeTest(test);
		}

		// FIX 8: Driver quit moved here (was in the deleted TearDown() method).
		// FIX 9: driver = null after quit so the next @BeforeMethod starts completely fresh
		//        and there is no risk of accidentally reusing a dead session reference.
		System.out.println("Quit driver");
		if (driver != null)
		{
			test.log(Status.INFO, "Closing driver");
			driver.quit();
			driver = null;
		}
	}

	// FIX 7 (continued): The old TearDown() method has been DELETED from here.
	// It was:
	//   @AfterMethod
	//   public void TearDown() { driver.quit(); }
	// Keeping it caused driver.quit() to run twice after every single test.

	@AfterSuite
	public void tearDownAndSendReport(ITestContext context)
	{
		String currentSuiteName = context.getSuite().getName();

		if (!"Suite".equalsIgnoreCase(currentSuiteName))
		{
			System.out.println("⚠ Skipping email because it's not run via testing.xml");
			return;
		}
		if (extent != null)
		{
			extent.flush();
			System.out.println("✅ Extent report flushed successfully");
		}

		try
		{
			Thread.sleep(3000);
			System.out.println("⏳ Waiting for reports to be finalized...");
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		String reportPath = System.getProperty("user.dir") + "/Reports/Report.html";
		String file1Path = System.getProperty("user.dir") + "/test-output/emailable-report.html";
		forceFileSystemSync(reportPath);
		forceFileSystemSync(file1Path);

		Scanner scanner = new Scanner(System.in);
		System.out.print("Do you want to proceed? (yes/no): ");
		String input = scanner.nextLine().trim().toLowerCase();

		try
		{
			if (input.equals("yes"))
			{
				EmailSender.sendReport(reportPath, file1Path);
				System.out.println("✅ Email report sent successfully.");
			}
			else
			{
				System.out.println("!!!! Email report not sent by tester's choice.");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("❌ Failed to send email report.");
		}
		finally
		{
			scanner.close();
		}
	}

	private static void forceFileSystemSync(String filePath)
	{
		try
		{
			File file = new File(filePath);
			if (file.exists())
			{
				FileInputStream fis = new FileInputStream(file);
				byte[] buffer = new byte[1024];
				fis.read(buffer);
				fis.close();
				System.out.println("🔄 File system sync forced for: " + file.getName());
			}
		}
		catch (Exception e)
		{
			System.out.println("⚠️ Could not force sync for: " + filePath);
		}
	}

	public static void ReadPropertiesFile()
	{
		Properties prop = new Properties();
		try
		{
			String currentDirectory = System.getProperty("user.dir");
			String inputFileName = currentDirectory + "/src/test/java/Locators/Config.Properties";
			InputStream input = new FileInputStream(inputFileName);
			prop.load(input);
			Testing_Enviroment = prop.getProperty("Enviroment");
			Testing = prop.getProperty("Testing");
			DeviceName = prop.getProperty("DeviceName");
			// UDID = prop.getProperty("UDID");          // Uncomment if your device needs UDID
			// DeviceVersion = prop.getProperty("Version"); // Uncomment if Appium needs platformVersion
			Name = prop.getProperty("Name");
			Password = prop.getProperty("Password");
			Email = prop.getProperty("Email");
			MobileNumber = prop.getProperty("MobileNumber");
			VerificationCode = prop.getProperty("VerificationCode");
			DeviceType = prop.getProperty("DeviceType");
			JazzCashNumber = prop.getProperty("JazzCashNumber");
			JazzCashCNIC = prop.getProperty("JazzCashCNIC");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void SetJsonReader(String Value) throws IOException, ParseException
	{
		JSONParser jsonParser = new JSONParser();
		FileReader reader = null;

		ReadPropertiesFile();

		String currentDirectory = System.getProperty("user.dir");
		String inputFileName = currentDirectory + "/DataProvider/" + Testing + "/" + Value + ".json";
		File inputFile = new File(inputFileName);
		reader = new FileReader(inputFile);

		Object obj = jsonParser.parse(reader);
		ElementObj = (JSONObject) obj;
	}
}