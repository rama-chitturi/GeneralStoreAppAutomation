package com.qa.TestCases;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.base.TestBase;
import com.generalStoreScreens.AppFlow;
import com.generalStoreScreens.Swipe;


public class TestRun extends TestBase{

	public TestBase tbObj;
	public AppFlow appObj;
	public Swipe swipe;

	@Test(priority=1, enabled = true)
	public void runAppiumServerTest() throws MalformedURLException 
	{
		tbObj = new TestBase();
		tbObj.appConfig();
	}

	@Test(priority=2, enabled = true)
	@Parameters({"countryName","userName","gender" })
	public void runAppFlowTest(String countryName, String userName, String gender ) throws InterruptedException, IOException
	{
		appObj = new AppFlow();
		appObj.userAccessToStore(countryName,userName,gender);
		appObj.addItemsTocart("Air Jordan 4 Retro","clearSheetTrue");
		appObj.addItemsTocart("Air Jordan 1 Mid SE","clearSheetFasle");
		appObj.getCartValue();
	}
	
	@Test(priority=3, enabled = false)
	public void runSwipe()
	{
		System.out.println("swipe method started");
		appObj = new AppFlow();
		swipe = new Swipe();
		appObj.appBack(); 
		swipe.swipeScreen("DOWN");
		swipe.swipeScreen("UP");
		swipe.swipeScreen("DOWN");
	}

}
