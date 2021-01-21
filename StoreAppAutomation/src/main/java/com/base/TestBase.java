package com.base;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


public class TestBase {

	public static AppiumDriver<MobileElement> driver;
	public static Properties prop;

	public TestBase()
	{
		try
		{
			prop = new Properties();
			FileInputStream fis = new FileInputStream("src\\main\\java\\com\\config\\Configuration.properties");
			prop.load(fis);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}


	public void appConfig() throws MalformedURLException
	{
		DesiredCapabilities capObj = new DesiredCapabilities();
		capObj.setCapability("deviceName",prop.getProperty("deviceName"));
		capObj.setCapability("platformName",prop.getProperty("platformName"));
		capObj.setCapability("version", prop.getProperty("version"));
		capObj.setCapability("appPackage", prop.getProperty("appPackage"));
		capObj.setCapability("appActivity",prop.getProperty("appActivity")); 
		driver = new AndroidDriver<MobileElement>(new URL(prop.getProperty("remoteAddrs")), capObj);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

}



