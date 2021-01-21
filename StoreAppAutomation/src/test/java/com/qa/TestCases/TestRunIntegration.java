package com.qa.TestCases;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.storeScreens.CartScreen;
import com.storeScreens.ShoppingScreen;
import com.storeScreens.Swipe;
import com.storeScreens.UserAccountVerifyScreen;


public class TestRunIntegration extends TestBase{

	public TestBase tbObj;
	public UserAccountVerifyScreen useracntObj;
	public ShoppingScreen shopObj;
	public CartScreen cartObj;

	@Test(priority=1, enabled = true)
	public void runAppiumServerTest() throws MalformedURLException 
	{
		tbObj = new TestBase();
		tbObj.appConfig();
	}

	@Test(priority=2, enabled = true)
	@Parameters({"countryName","userName","gender"})
	public void runAccountAccess(String countryName, String userName, String gender) throws InterruptedException, IOException
	{
		useracntObj = new UserAccountVerifyScreen();
		useracntObj.userAccessToStore(countryName,userName,gender);
	}

	@Test(priority=3, enabled = true)
	@Parameters({"product1","product2" })
	public void runProductSelection(String product1, String product2 ) throws IOException
	{
		shopObj = new ShoppingScreen();
		shopObj.addItemsTocart(product1,"clearSheetTrue");
		shopObj.addItemsTocart(product2,"clearSheetFasle");
	}

	@Test(priority=4, enabled = true )
	public void runCartValueCheck() throws NumberFormatException, IOException
	{
		cartObj = new CartScreen();
		cartObj.getCartValue();
	}
}

