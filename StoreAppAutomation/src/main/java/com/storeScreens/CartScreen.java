package com.storeScreens;

import java.io.IOException;

import com.base.Excel;

public class CartScreen extends AppElements{

	Excel excl;
	public CartScreen()
	{
		AppElements appEleObj = new AppElements();
	}
	public void getCartValue() throws NumberFormatException, IOException 
	{
		String FilePath=prop.getProperty("cartValueCheckFile");

		cartBtnEle.click(); 

		try{
			String toastmessage = toastMsgEle.getText();
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
