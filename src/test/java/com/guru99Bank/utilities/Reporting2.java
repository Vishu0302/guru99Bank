package com.guru99Bank.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting2 extends TestListenerAdapter{
	
	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public ExtentSparkReporter spark;
	
	public void onstart(ITestContext testContext) throws Exception
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName = "Test Report - " + timeStamp + ".html";
		
//		htmlReporter = new ExtentSparkReporter(System.setProperty("user.dir", repName) + "\\test-output\\"+ repName);
//		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
		
		extent = new ExtentReports();
		
		spark = new ExtentSparkReporter(System.getProperty("user.dir") + repName + "/test-output/STMExtentReport.html");
		spark.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
		extent.attachReporter(spark);
		
		extent.setSystemInfo("Host Name", "Localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Vishal");
        spark.config().setDocumentTitle("Guru99 Bank Project");
        spark.config().setReportName("Functional Automation Report");
        spark.config().setTheme(Theme.DARK);
		
//		extent.attachReporter(htmlReporter);
//		extent.setSystemInfo("Hostname", "localhost");
//		extent.setSystemInfo("Environment", "QA");
//		extent.setSystemInfo("Name", "Vishal");
//		
//		htmlReporter.config().setDocumentTitle("Guru99 Bank Project");
//		htmlReporter.config().setReportName("Functional Automation Report");
//		htmlReporter.config().setTheme(Theme.DARK);
		
	}
	public void onSucccess(ITestResult tr)
	{
		logger = extent.createTest(tr.getName());
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	}
	public void onFailure(ITestResult tr) throws IOException
	{
		logger = extent.createTest(tr.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName() + " - Test Case Failed", ExtentColor.RED));
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getThrowable() + " - Test Case Failed", ExtentColor.RED));
		
		String Screenshotpath = System.getProperty(("user.dir") + "\\Screenshots\\"+ tr.getName()+ ".jpg");
		
		File f = new File(Screenshotpath);
		if(f.exists())
		{
			logger.fail("Screenshot below as : " + logger.addScreenCaptureFromPath(Screenshotpath));
		}
	}
	public void onTestSkipped(ITestResult tr)
	{
		logger = extent.createTest(tr.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}

}
