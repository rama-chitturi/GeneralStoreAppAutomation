package com.base;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v122.input.model.MouseButton;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;


public class TestBase {

	public static AppiumDriver driver;
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


	public void androidAppConfig() throws MalformedURLException
	{
		UiAutomator2Options uiOptions = new  UiAutomator2Options();
		uiOptions.setDeviceName(prop.getProperty("deviceName"));
		uiOptions.setPlatformName(prop.getProperty("platformName"));
		uiOptions.setPlatformVersion(prop.getProperty("version"));
		uiOptions.setAppPackage(prop.getProperty("appPackage"));
		uiOptions.setAppActivity(prop.getProperty("appActivity"));
				
		driver = new AndroidDriver(new URL(prop.getProperty("remoteAddrs")), uiOptions);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
	}
	
	public void iOSAppConfig() throws MalformedURLException
	{
		XCUITestOptions xcuiOptions = new XCUITestOptions();
		xcuiOptions.setDeviceName(prop.getProperty("deviceName"));
		xcuiOptions.setPlatformName(prop.getProperty("platformName"));
		xcuiOptions.setPlatformVersion(prop.getProperty("version"));
		xcuiOptions.setApp(prop.getProperty("appPath"));
			
		driver = new IOSDriver(new URL(prop.getProperty("remoteAddrs")), xcuiOptions);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
			
	}
	
	public void waitForEleToTap(WebElement eleToWait)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(eleToWait));
	}
	public void waitForEleToVisible(WebElement eleToWait)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(eleToWait));
	}
	
	public void typeInput(WebElement eleToType, String toInput)
	{
		waitForEleToVisible(eleToType);
		eleToType.sendKeys(toInput);
	}
	
	public void tapElement(WebElement eleToTap)
	{
		waitForEleToTap(eleToTap);
		eleToTap.click();
	}
	public void longTapElement(AppiumDriver driver, WebElement ele)
	{
		PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
		Sequence sequenceObj = new Sequence(finger1, 0);
		sequenceObj
		.addAction(finger1.createPointerMove(Duration.ofMillis(0),Origin.viewport(),(ele.getSize().width/2),ele.getSize().height/2))
		.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
		.addAction(new Pause(finger1, Duration.ofMillis(200)))
		.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Collections.singleton(sequenceObj));
	}
	
	public void switchToApp()
	{
	driver.executeScript("mobile:startActivity", ImmutableMap.of("intent", "com.android.settings/.settings"));
	}
	

}



