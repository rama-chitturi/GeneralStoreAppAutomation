package com.qa.TestCases;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import com.base.Excel;
import com.base.TestBase;
import com.storeScreens.CartScreen;
import com.storeScreens.ShoppingScreen;
import com.storeScreens.UserAccountVerifyScreen;

public class TestRunFunctional {

	public TestBase tbObj;
	public UserAccountVerifyScreen uavsObj;
	public ShoppingScreen shopObj;
	public CartScreen cartObj;
	public Excel excl;



	@Test(priority=1, enabled = true, alwaysRun=true)
	public void setAppiumServerTest() throws MalformedURLException 
	{
		tbObj = new TestBase();
		tbObj.appConfig();
	}

	@Test(priority=2, enabled=true, dataProvider="userDetails", dataProviderClass=DataProviderFile.class, groups="userAccessScreen")
	public void runCountryAndGenderValidation(String countryName, String gender)
	{
		uavsObj= new UserAccountVerifyScreen();
		uavsObj.setCountry(countryName);
		uavsObj.setGender(gender);

	}

	@Test(priority=3, enabled = true, groups= {"userAccessScreen","smokeTest"})
	public void runUserNameValidation() throws IOException
	{
		excl = new Excel();
		uavsObj= new UserAccountVerifyScreen();
		ArrayList<String> ExcelTestData = excl.getExcel(TestBase.prop.getProperty("userNameValueGetFile"), 1);
		uavsObj.userNameValidation(ExcelTestData, 2);
	}

	@Test(priority=4, enabled = true, groups="shoppingScreen")
	@Parameters({"product1","product2"})
	public void runAddItemsToCart(String productName1, String productName2) throws IOException
	{
		shopObj = new ShoppingScreen();
		shopObj.addItemsTocart(productName1,"clearSheetTrue");
		shopObj.addItemsTocart(productName2,"clearSheetFalse");
	}

	@Test(priority=5, enabled = false, groups="shoppingScreen", dependsOnMethods= {"runAddItemsToCart"})
	public void runRemoveCartItem() 
	{
		shopObj = new ShoppingScreen();
		shopObj.removeItemsFromCart();
	}

	@Test(priority=6, enabled = true, groups="shoppingScreen")
	public void runCartCountValidation()
	{
		shopObj = new ShoppingScreen();
		shopObj.cartCountValidation();
	}

	@Test(priority=7, enabled=true, groups= {"cartScreen","smoke"})
	public void runGetCartValue() throws NumberFormatException, IOException
	{
		cartObj= new CartScreen();
		cartObj.getCartValue();
	}





}
