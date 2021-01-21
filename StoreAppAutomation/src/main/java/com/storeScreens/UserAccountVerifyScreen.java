package com.storeScreens;

import java.io.IOException;
import java.util.ArrayList;
import com.base.Excel;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import java.util.List;

public class UserAccountVerifyScreen extends AppElements{

	Excel excl;
	ShoppingScreen shopObj;
	public UserAccountVerifyScreen()
	{
		AppElements appEleObj = new AppElements();
	}

	public void userAccessToStore(String countryName,String username,String gender)
	{
		setCountry(countryName);
		userNameEle.sendKeys(username);
		setGender(gender);

		if(toastMsgEle.isDisplayed())
			System.out.println("User account access denied");
		else
			System.out.println("User account accessed");
	}

	public void setCountry(String countryName)
	{
		countryDropdownEle.click();	
		String eleStatus="null";

		while(0<1)
		{
			List<MobileElement> countryEle = driver.findElementsById("android:id/text1");
			int listSize=(countryEle.size())/2;

			for(MobileElement ele: countryEle)
			{
				if(ele.getText().equalsIgnoreCase(countryName))
				{
					ele.click();
					eleStatus="found";
					System.out.println("Country found");
					break;
				}
				else
					eleStatus="not found";
			}

			if(eleStatus.equalsIgnoreCase("found"))
			{
				System.out.println("loop breaked");
				break;
			}
			else
			{
				Swipe swipe = new Swipe();
				swipe.eleSwipe(countryEle.get(listSize), "UP");

			}
		}}

	public void setGender(String gender)
	{
		if(gender.equalsIgnoreCase("female"))
			femaleGenderEle.click();
		else
			maleGenderEle.click();
		letsShopBtnEle.click();
	}

	public void userNameValidation(ArrayList<String> userNameTestData, int cellnum) throws IOException
	{
		int testDataCount = userNameTestData.size();
		int loopCount=0;
		String dataResult = null;
		shopObj = new ShoppingScreen();
		excl = new Excel();
		while(loopCount<testDataCount)
		{

			try 
			{
				userNameEle.sendKeys(userNameTestData.get(loopCount));
				letsShopBtnEle.click();
				System.out.println("Data "+userNameTestData.get(loopCount)+"at row count: "+loopCount);
				if(addToCartBtnEle.isDisplayed())
				{
					dataResult = "Pass";
					if(loopCount!=testDataCount-1)
						shopObj.appBack();
				}
				else
					dataResult = "Fail";
			}
			catch(Exception e)
			{
				dataResult = "Fail";
			}
			loopCount++;
			excl.setExcel("Files//UserData.xlsx", dataResult, loopCount, cellnum);
		}
	}

}
