package com.storeScreens;

import java.io.IOException;
import java.util.List;

import com.base.Excel;

import io.appium.java_client.MobileElement;

public class ShoppingScreen extends AppElements{

	Excel excl;
	public ShoppingScreen()
	{
		AppElements appEleObj = new AppElements();
	}

	public void addItemsTocart(String itemName, String sheetCreationStatus) throws IOException
	{

		MobileElement addToCartBtnEle,itemValueEle;

		List<MobileElement>	itemsList = productCardListELe.findElementsById("com.androidsample.generalstore:id/productName"); 
		int i=0; 
		String itemCost = "";
		while(i<itemsList.size())
		{	
			if(itemsList.get(i).getText().equalsIgnoreCase(itemName))
			{	
				addToCartBtnEle =(MobileElement) productCardListELe.findElementsById("com.androidsample.generalstore:id/productAddCart").get(i);
				addToCartBtnEle.click();
				itemValueEle = (MobileElement) productCardListELe.findElementsById("com.androidsample.generalstore:id/productPrice").get(i);
				itemCost = itemValueEle.getText();
				break;
			}
			i++;
		}
		excl = new Excel();
		excl.addItemDetailsToExcel(itemName,itemCost,sheetCreationStatus);
		System.out.println("number of products added:" + cartCountEle.getText());
	}


	public void removeItemsFromCart()
	{
		List<MobileElement> removeItemListEle = productCardListELe.findElementsById("com.androidsample.generalstore:id/productAddCart"); 

		int i=0;
		while(i<removeItemListEle.size())
		{	
			if(removeItemListEle.get(i).getText().equalsIgnoreCase("ADDED TO CART"))
				removeItemListEle.get(i).click();
			i++;
		}

	}

	public void cartCountValidation()
	{
		try
		{
			if(cartCountEle.isDisplayed())
			{
				String totalcartCountText= cartCountEle.getText();
				int totalcartCount = Integer.parseInt(totalcartCountText);
				System.out.println("Number of items seelcted: "+ totalcartCount);
			}
		}
		catch(Exception e)
		{
			cartBtnEle.click();
			String toastMessageText = toastMsgEle.getText();
			System.out.println("No items seelcted. \n"+toastMessageText);
		}

	}

	public void appBack()
	{
		appBackBtnEle.click();
	}

}
