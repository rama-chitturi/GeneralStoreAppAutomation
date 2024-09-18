package com.storeScreens;

import java.io.IOException;
import java.util.ArrayList;
import com.base.Excel;
import com.base.TestBase;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class UserAccountVerifyScreen extends TestBase{

	/** User Register validation Page  **/


	@AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
	private WebElement countryDropdownEle;

	@AndroidFindBy(className = "android.widget.FrameLayout")
	private WebElement countryListFrameEle;

	@AndroidFindBy(className = "android:id/text1")
	private List<WebElement>  countryEle;


	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement userNameEle;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
	private WebElement femaleGenderEle;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
	private WebElement maleGenderEle;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	private WebElement letsShopBtnEle;

	@AndroidFindBy(xpath="//android.widget.Toast[1]")
	protected static WebElement toastMsgEle;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_back")
	static private WebElement appBackBtnEle;

	
	Excel excl;
	ShoppingScreen shopObj;
	public UserAccountVerifyScreen()
	{
		PageFactory.initElements(driver, this);
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
			List<WebElement> countryEle = driver.findElements(By.id("android:id/text1"));
			int listSize=(countryEle.size())/2;

			for(WebElement ele: countryEle)
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
