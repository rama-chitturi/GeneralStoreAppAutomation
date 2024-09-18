package com.storeScreens;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.base.Excel;
import com.base.TestBase;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class CartScreen extends TestBase{

	/** Get Cart Value **/

	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	protected
	static WebElement cartBtnEle;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	static private WebElement totalCartValue;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_back")
	static private WebElement appBackBtnEle;


	
	Excel excl;
	public CartScreen()
	{
		PageFactory.initElements(driver, this);
	}
	public void getCartValue() throws NumberFormatException, IOException 
	{
		String FilePath=prop.getProperty("cartValueCheckFile");

		cartBtnEle.click(); 

		try{
			String toastmessage = UserAccountVerifyScreen.toastMsgEle.getText();
			if(toastmessage.equalsIgnoreCase("Please add some product at first"))
				System.out.println("No items selected to cart");
		}

		catch(Exception e)
		{
			excl = new Excel();
			String totalValue = totalCartValue.getText();
			String totalCount = totalValue.substring(2);
			float fetchedCartvalue = Float.parseFloat(totalCount);
			System.out.println(fetchedCartvalue);
			excl.getCartTotal(FilePath, fetchedCartvalue);
		}
	}



}
