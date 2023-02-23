package com.packt.tests;



import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.packt.base.BaseTest;

public class RegistrationTest extends BaseTest {
	
	@Test
	public void registrationTest() {
		
		driver.get("https://demoqa.com/automation-practice-form");
		WebElement firstName = driver.findElement(By.id("firstName"));
		WebElement lastName =  driver.findElement(By.id("lastName"));
		WebElement email =  driver.findElement(By.id("userEmail"));
		WebElement maleradio =  driver.findElement(By.xpath("//input[@id='gender-radio-1']"));
		WebElement submitbutton =  driver.findElement(By.id("submit"));
		WebElement hobbies =  driver.findElement(By.id("hobbies-checkbox-1"));
		WebElement number =  driver.findElement(By.id("userNumber"));
//		WebElement successMsg =  driver.findElement(By.id("example-modal-sizes-title-lg"));
		
		try {
			Thread.sleep(8000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		
		
		firstName.sendKeys("Sam");
		lastName.sendKeys("West"+ getTime());
		email.sendKeys(getTime()+"sab@gmail.com");
		try {
		//maleradio.click();
			
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", maleradio);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		//hobbies.click();
		JavascriptExecutor executor1 = (JavascriptExecutor)driver;
		executor1.executeScript("arguments[0].click();", hobbies);
		number.sendKeys("9876567654");
		//submitbutton.click();
		JavascriptExecutor executor2 = (JavascriptExecutor)driver;
		executor2.executeScript("arguments[0].click();", submitbutton);
		try {
		Thread.sleep(3000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		WebElement successMsg =  driver.findElement(By.id("example-modal-sizes-title-lg"));
	Assert.assertTrue(successMsg.getText().equals("Thanks for submitting the form"),"Success msg is not correct");
	}	
	
	private String getTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyyHHmmss");
		Date date = new Date();
		return formatter.format(date);
	}

}
