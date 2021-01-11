package com.generalStoreScreens;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import com.base.Excel;
import com.base.TestBase;
import io.appium.java_client.MobileElement;


public class AppFlowScenario extends TestBase{

	AppFlow appObj;
	Excel excl;

	public void userNameValidation(ArrayList<String> userNameTestData, int cellnum) throws IOException
	{
		int testDataCount = userNameTestData.size();
		int loopCount=0;
		String dataResult = null;
		appObj= new AppFlow();
		excl = new Excel();
		MobileElement userNameEle,letsShopBtnEle,itemAddToCartEle;
System.out.println(testDataCount);
		while(loopCount<testDataCount)
		{
	
			try {
				userNameEle =	(MobileElement) driver.findElementById("com.androidsample.generalstore:id/nameField");
				userNameEle.sendKeys(userNameTestData.get(loopCount));
				letsShopBtnEle = (MobileElement) driver.findElementById("com.androidsample.generalstore:id/btnLetsShop");
				letsShopBtnEle.click();
				System.out.println("Data "+userNameTestData.get(loopCount)+"at row count: "+loopCount);
				itemAddToCartEle = (MobileElement) driver.findElementById("com.androidsample.generalstore:id/productAddCart");
				if(itemAddToCartEle.isDisplayed())
				{
					dataResult = "Pass";
					if(loopCount!= testDataCount-1)
					appObj.appBack();

				}
				else
				{
					dataResult = "Fail";
				}
			}
			catch(Exception e)
			{
				dataResult = "Fail";
			}
			loopCount++;
			excl.setExcel("Files//UserData.xlsx",dataResult, loopCount, cellnum);
		}
	}

	public void removeItemsFromCart()
	{
		MobileElement itemsListELe =	(MobileElement) driver.findElementById("com.androidsample.generalstore:id/rvProductList");
		List<MobileElement> removeItemListEle = itemsListELe.findElementsById("com.androidsample.generalstore:id/productAddCart"); 

		int i=0;
		while(i<removeItemListEle.size())
		{	
			if(removeItemListEle.get(i).getText().equalsIgnoreCase("ADDED TO CART"))
				removeItemListEle.get(i).click();
			i++;
		}

	}

}
