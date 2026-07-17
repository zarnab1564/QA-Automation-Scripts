package Resources;
import java.io.IOException;
import java.util.Iterator;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import Locators.Base;

public class Listeners implements ITestListener
{

	public void onTestStart(ITestResult result) {}
	
	public void onTestSuccess(ITestResult result) {}

	public void onTestFailure(ITestResult result) {
		String s=result.getName();
		try {
			Base.getScreenshot(s);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}				
	}


	public void onTestSkipped(ITestResult result) {}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
	
	public void onStart(ITestContext context) {}

	
    public void onFinish(ITestContext context) {
        Iterator<ITestResult> skippedTestCases = context.getSkippedTests().getAllResults().iterator();
        while (skippedTestCases.hasNext()) {
            ITestResult skippedTestCase = skippedTestCases.next();
            ITestNGMethod method = skippedTestCase.getMethod();
            if (context.getSkippedTests().getResults(method).size() > 0) {
                System.out.println("Removing:" + skippedTestCase.getTestClass().toString());
                skippedTestCases.remove();
            }
        }
    }
	
	/*@Override
    public void onFinish(ITestContext context) {
        Iterator<ITestResult> skippedTestCases = context.getSkippedTests().getAllResults().iterator();
        while (skippedTestCases.hasNext()) {
            ITestResult skippedTestCase = skippedTestCases.next();
            ITestNGMethod method = skippedTestCase.getMethod();
            if (context.getSkippedTests().getResults(method).size() > 0) {
                skippedTestCases.remove();
            }
        }
    }*/
	
	/*@Override
	   public void onFinish(ITestContext context) {
	        Set<ITestResult> failedTests = context.getFailedTests().getAllResults();
	        for (ITestResult temp : failedTests) {
	            ITestNGMethod method = temp.getMethod();
	            if (context.getPassedTests().getResults(method).size() > 0) {
                    failedTests.remove(temp);
                }
	            if (context.getFailedTests().getResults(method).size() > 1) {
	                failedTests.remove(temp);
	            } 
	            
	        }
	    }*/
	
	/*@Override
   public void onFinish(ITestContext context) {
        Set<ITestResult> failedTests = context.getFailedTests().getAllResults();
        for (ITestResult temp : failedTests) {
            ITestNGMethod method = temp.getMethod();
            if (context.getFailedTests().getResults(method).size() > 1) {
                failedTests.remove(temp);
            } else {
                if (context.getPassedTests().getResults(method).size() > 0) {
                    failedTests.remove(temp);
                }
            }
        }
    }*/
	
	 /* public void onFinish(ITestContext context)
		{	
			for(int i=0;i<context.getAllTestMethods().length;i++)
			{
				if(context.getAllTestMethods()[i].getCurrentInvocationCount()==2)
				{
					if (context.getFailedTests().getResults(context.getAllTestMethods()[i]).size() == 2 || context.getPassedTests().getResults(context.getAllTestMethods()[i]).size() == 1)
					{					
							context.getFailedTests().removeResult(context.getAllTestMethods()[i]);
					}
				}
			}
		}	*/
}
