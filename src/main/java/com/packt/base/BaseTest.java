package com.packt.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
	
	protected RemoteWebDriver driver;
	
	@BeforeMethod(alwaysRun = true)
	@Parameters({"browser", "environment", "platform"})
	protected void setUp(@Optional("chrome") String browser, @Optional("local") String environment,
	@Optional String platform, ITestContext ctx) {
		String testName = ctx.getCurrentXmlTest().getName();
	BrowserDriverFactory factory = new BrowserDriverFactory(browser);
	
	 if(environment.equals("grid"))  {
		 //driver = factory.createDriverGrid();
	 } else if (environment.equals("sauce")) {
		 driver = factory.createDriverSauce(platform, testName);
		 
	 }else {
		 //driver = factory.createDriver();
	 }
	
	
	driver.manage().window().maximize();
	}
	
	@AfterMethod(alwaysRun = true)
	protected void tearDown(ITestResult result) {
		String status = result.isSuccess() ? "passed" : "failed";
	    driver.executeScript("sauce:job-result=" + status);
		System.out.println("[Closing Browser]");
		driver.quit();
	}
	

}
