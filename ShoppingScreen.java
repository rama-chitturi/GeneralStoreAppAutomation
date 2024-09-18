package com.storeScreens;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.base.Excel;
import com.base.TestBase;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ShoppingScreen extends TestBase{


	@AndroidFindBy(id = "com.androidsample.generalstore:id/rvProductList")
	static private WebElement productCardListELe;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
	static private List<WebElement> productNameList;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productAddCart")
	protected static WebElement addToCartBtnEle;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	static private WebElement productValueEle;


	@AndroidFindBy(id="com.androidsample.generalstore:id/counterText")
	static private WebElement cartCountEle;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_back")
	static private WebElement appBackBtnEle;

	
	Excel excl;
	public ShoppingScreen()
	{
		PageFactory.initElements(driver, this);
	}

	public void addItemsTocart(String itemName, String sheetCreationStatus) throws IOException
	{

		WebElement addToCartBtnEle,itemValueEle;

		List<WebElement>	itemsList = productCardListELe.findElements(By.id("com.androidsample.generalstore:id/productName")); 
		int i=0; 
		String itemCost = "";
		while(i<itemsList.size())
		{	
			if(itemsList.get(i).getText().equalsIgnoreCase(itemName))
			{	
				addToCartBtnEle =(WebElement) productCardListELe.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i);
				addToCartBtnEle.click();
				itemValueEle = (WebElement) productCardListELe.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i);
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
		List<WebElement> removeItemListEle = productCardListELe.findElements(By.id("com.androidsample.generalstore:id/productAddCart")); 

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
