package genericUtilities;

import java.io.IOException;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenersImplementation implements ITestListener {
	
	 ExtentReports report;
	 ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		
		String methodname = result.getMethod().getMethodName();
         System.out.println(methodname+" - @Test execution started");
         
         //intimate the start of @Test to extent reports
        test =report.createTest(methodname);
         
		

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		String methodname = result.getMethod().getMethodName();
        System.out.println(methodname+" - @Test execution PASS");
        
        //intimate the extent reports #test is pass- log the status
        test.log(Status.PASS, methodname+" -@Test execution PASS");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		String methodname = result.getMethod().getMethodName();
        System.out.println(methodname+" - @Test execution FAIL");

        //capture the exception
        System.out.println(result.getThrowable());
        
        //log the status as fail in extent report
        test.log(Status.FAIL, methodname+" -@Test execution FAIL");
        
        //log the exception in extent report
        test.log(Status.WARNING, result.getThrowable());
        
        //capture screenshot
        SeleniumUtility s = new SeleniumUtility();
        JavaUtility j = new JavaUtility();
        
        //configure screenshot name
        String screenShotName = methodname+"-"+j.getSystemDateInformat();
          
        try {
			String path = s.captureScreenshot(BaseClass.sdriver, screenShotName);
			
		//attach the screenshot to extent report
			test.addScreenCaptureFromPath(path);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
       
        System.out.println(screenShotName);
        
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		
		String methodname = result.getMethod().getMethodName();
        System.out.println(methodname+" - @Test execution SKIP");

        //capture the exception
        System.out.println(result.getThrowable());
        
        
        //log the status as skip in extent report
        test.log(Status.SKIP, methodname+" -@Test execution SKIP");
        
        //log the exception in extent report
        test.log(Status.WARNING, result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
		String methodname = result.getMethod().getMethodName();
        System.out.println(methodname+" - @Test execution started");
	
	}

	@Override
	public void onStart(ITestContext context) {
		
	
        System.out.println(" Suite execution started");
        
        //configuration of extent report
        ExtentSparkReporter esr= new ExtentSparkReporter(".\\ExtentReports\\Report - "+new JavaUtility().getSystemDateInformat()+".html");
        esr.config().setDocumentTitle("SauceLabs Execution Report");
        esr.config().setReportName("Execution Report");
        esr.config().setTheme(Theme.STANDARD);
        
        //load the basic configuration to extent reports class
        report = new ExtentReports();
        report.attachReporter(esr);
        report.setSystemInfo("Base Browser", "Chrome");
        report.setSystemInfo("Base Platform", "Windows");
        report.setSystemInfo("Base URL", "Testing Environment");
        report.setSystemInfo("Reporter Name", "Ayush");
        
	
	}

	@Override
	public void onFinish(ITestContext context) {
		
		System.out.println(" Suite execution finished");
		
		//flush the report
		report.flush();
	}

}
