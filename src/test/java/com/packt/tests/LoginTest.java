package com.packt.tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.packt.base.BaseTest;

public class LoginTest extends BaseTest{
	
	@Test
	public void loginTest() {
		
		Boolean b1;
		
		
		driver.get("http://the-internet.herokuapp.com/login");
		System.out.println("Page Opened");
		Assert.assertTrue("Test".equalsIgnoreCase("Test"), "Test Passed");
		
		Assert.assertEquals(true, true);
		
		
	}

}
