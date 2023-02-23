package com.packt.base;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserDriverFactory {
	
	private ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();
	private String browser;
	
	public BrowserDriverFactory(String browser) {
		this.browser = browser.toLowerCase();
	}
	
	
		
	public WebDriver createDriver() {
		System.out.println("[Setting up Driver: "+ browser + "]");
		
		switch(browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "src//main//resources//chromedriver.exe");
			driver.set(new ChromeDriver());
			break;
			
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			driver.set(new FirefoxDriver());
			break;
		
		case "iex":
			System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver.exe");
			driver.set(new EdgeDriver());
			break;
			
		default:
			System.out.println("[Couldn't set: "+ browser + "Its unknown. Hence starting Chrome browser]");
			System.setProperty("webdriver.chrome.driver", "src//main//resources//chromedriver.exe");
			driver.set(new ChromeDriver());
			break;
		}
		
		return driver.get();
		
	}
	 public RemoteWebDriver createDriverSauce(String platform, String testName) {
		 System.out.println("Set up browser "+browser +"on Saucelab");
		 String username = "oauth-sourish.cse-80ad8";
		 String password = "347f5819-fce5-4ce4-b72e-d6f7f3877a28";
		 String url = "https://" + username + ":" + password + "@ondemand.eu-central-1.saucelabs.com:443/wd/hub";
		 
		 DesiredCapabilities caps = new DesiredCapabilities();
		 caps.setCapability("browserName", browser);
		 
		 if(platform == null) {
			 caps.setCapability("platform", "Windows 10");
		 } else {
			 caps.setCapability("platform", platform);
		 }
		 
		 caps.setCapability("name", testName);
		 //caps.setCapability("browserVersion", "latest");
		 
		 try {
			 driver.set(new RemoteWebDriver(new URL(url), caps));
		 } catch (MalformedURLException e) {
			 e.printStackTrace();
		 }
		 return driver.get();
	 }
 
}
