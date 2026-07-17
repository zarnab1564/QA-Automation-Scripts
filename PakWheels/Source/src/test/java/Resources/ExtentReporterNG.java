package Resources;
import java.io.File;

import org.testng.IReporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG implements IReporter 
{
	 public static ExtentReports extent =new ExtentReports();    //initiating here is very important
     public static ExtentSparkReporter htmlReporter;

	@BeforeSuite
	public void beforeSuiteSetup() 
	{
		
        String filepath = System.getProperty("user.dir");
        String reportPath = filepath + File.separator + "Reports" + File.separator + "Report.html";

        // Ensure the Reports directory exists
        File reportsDir = new File(filepath + File.separator + "Reports");
        if (!reportsDir.exists()) {
            boolean dirCreated = reportsDir.mkdirs(); 
            if (dirCreated) {
                System.out.println("Reports directory created successfully.");
            } else {
                System.out.println("Failed to create Reports directory.");
                return; 
            }
        }

        htmlReporter = new ExtentSparkReporter(reportPath);
        htmlReporter.config().setReportName("Android Smoke Automation Results");
        htmlReporter.config().setDocumentTitle("Test_Results_Smoke_Automation");

        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Tester", "Anam");

	}
	
	@AfterMethod(alwaysRun = true)
	public void afterSuite() 
	{
	     extent.flush();
	}
}