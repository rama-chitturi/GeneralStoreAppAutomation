package com.generalStoreScreens;

import java.io.IOException;
import java.util.List;

import com.base.Excel;
import com.base.TestBase;

import io.appium.java_client.MobileElement;

public class AppFlow extends TestBase{

	Excel excl;

	public void userAccessToStore(String countryName,String Username,String Gender)
	{
		MobileElement userNameEle,genderEle,letsShopBtnEle;
		setCountry(countryName);

		userNameEle = (MobileElement) driver.findElementById("com.androidsample.generalstore:id/nameField");
		genderEle = (MobileElement) driver.findElementById("com.androidsample.generalstore:id/radio"+Gender+"");
		letsShopBtnEle = (MobileElement) driver.findElementById("com.androidsample.generalstore:id/btnLetsShop");

		userNameEle.sendKeys(Username);
		genderEle.click();
		letsShopBtnEle.click();
	}

	public void addItemsTocart(String itemName, String sheetCreationStatus) throws IOException
	{

		MobileElement itemsListELe,addToCartBtnEle,itemValueEle;

		itemsListELe =	(MobileElement) driver.findElementById("com.androidsample.generalstore:id/rvProductList");
		List<MobileElement>	itemsList = itemsListELe.findElementsById("com.androidsample.generalstore:id/productName"); 
		int i=0; 
		String itemCost = "";
		while(i<itemsList.size())
		{	
			if(itemsList.get(i).getText().equalsIgnoreCase(itemName))
			{	
				addToCartBtnEle =(MobileElement) itemsListELe.findElementsById("com.androidsample.generalstore:id/productAddCart").get(i);
				addToCartBtnEle.click();
				itemValueEle = (MobileElement) itemsListELe.findElementsById("com.androidsample.generalstore:id/productPrice").get(i);
				itemCost = itemValueEle.getText();
				break;
			}
			i++;
		}
		excl = new Excel();
		excl.addItemDetailsToExcel(itemName,itemCost,sheetCreationStatus);

	}


	public void getCartValue() throws NumberFormatException, IOException 
	{
		MobileElement cartBtnEle,totalCartValue;
		String FilePath="Files//Cart_Items_Details.xlsx";

		cartBtnEle= (MobileElement) driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart");
		cartBtnEle.click(); 

		totalCartValue = (MobileElement) driver.findElementById("com.androidsample.generalstore:id/totalAmountLbl");
		String totalvalue = totalCartValue.getText();
		String totalCount = totalvalue.substring(2);
		float fetchedCartvalue = Float.parseFloat(totalCount);
		System.out.println(fetchedCartvalue);
		excl.getCartTotal(FilePath, fetchedCartvalue);
	}

	public void appBack()
	{
		MobileElement appBackBtnEle = (MobileElement) driver.findElementById("com.androidsample.generalstore:id/appbar_btn_back");
		appBackBtnEle.click();

	}


	public void setCountry(String countryName)
	{
		MobileElement	countryDropdownEle = (MobileElement) driver.findElementById("com.androidsample.generalstore:id/spinnerCountry");
		countryDropdownEle.click();	
		MobileElement	countryListFrameEle = (MobileElement) driver.findElementByClassName("android.widget.FrameLayout");
		System.out.println("dropdown clicked");

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
		}
	}


}





