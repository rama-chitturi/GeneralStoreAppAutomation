package com.qa.TestCases;

import org.testng.annotations.DataProvider;

public class DataProviderFile {

	@DataProvider(name="userDetails")
	public static Object[][] userDetailsDataprovider()
	{
		return new Object[][]{
			{"India", "Female" },

		};
	}

}
