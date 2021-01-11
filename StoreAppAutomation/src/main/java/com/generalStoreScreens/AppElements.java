package com.generalStoreScreens;

import com.base.TestBase;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AppElements extends TestBase{
	
	
	/** User Register validation Page  **/
	
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
    static public AndroidElement countryDropdownEle;
	
	@AndroidFindBy(className = "android.widget.FrameLayout")
    static public AndroidElement countryListFrameEle;
	
	@AndroidFindBy(className = "android:id/text1")
    static public AndroidElement countryEle;
	
	
	
	
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    static public AndroidElement userNameEle;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
    static public AndroidElement genderEle;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    static public AndroidElement letsShopBtnEle;
	
	/** Items Page **/
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/rvProductList")
    static public AndroidElement productCardListELe;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
    static public AndroidElement itemsNameList;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productAddCart")
    static public AndroidElement addToCartBtnEle;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    static public AndroidElement itemValueEle;
	
	/** Get Cart Value **/
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    static public AndroidElement cartBtnEle;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    static public AndroidElement totalCartValue;
	

	/** App back arrow[<] element**/
	
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_back")
    static public AndroidElement appBackBtnEle;
	
	
	
	
	/** Cart Page **/
	

}
