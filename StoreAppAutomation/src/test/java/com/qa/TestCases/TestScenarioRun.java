package com.qa.TestCases;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.testng.annotations.Test;

import com.base.Excel;
import com.base.TestBase;
import com.generalStoreScreens.AppFlow;
import com.generalStoreScreens.AppFlowScenario;

public class TestScenarioRun {
	public TestBase tbObj;
	public AppFlow appObj;
	public AppFlowScenario appscenarioObj;
	public Excel excl;


	@Test(priority=1, enabled = true)
	public void setAppiumServerTest() throws MalformedURLException 
	{
		tbObj = new TestBase();
		tbObj.appConfig();

	}
	@Test(priority=2, enabled = true)
	public void userNameValidationTest() throws IOException
	{
		excl = new Excel();
		appscenarioObj= new AppFlowScenario();
		ArrayList<String> ExcelTestData = excl.getExcel("Files//USerData.xlsx", 1);

		System.out.println();
		appscenarioObj.userNameValidation(ExcelTestData, 2);
		

	}

	@Test(priority=3, enabled = true)
	public void removeCartItemTest() throws IOException
	{
		appObj = new AppFlow();
		appscenarioObj= new AppFlowScenario();
		
		appObj.addItemsTocart("Air Jordan 4 Retro","clearSheetTrue");
		appObj.addItemsTocart("Air Jordan 1 Mid SE","clearSheetFasle");
		appscenarioObj.removeItemsFromCart();
	}
}
