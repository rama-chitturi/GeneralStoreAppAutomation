package com.storeScreens;

import java.util.List;

import org.openqa.selenium.support.PageFactory;

import com.base.TestBase;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AppElements extends TestBase{
	public PageFactory pfObj;

	public AppElements()
	{
		pfObj = new PageFactory();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	/** User Register validation Page  **/


	@AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
	public AndroidElement countryDropdownEle;

	@AndroidFindBy(className = "android.widget.FrameLayout")
	public AndroidElement countryListFrameEle;

	@AndroidFindBy(className = "android:id/text1")
	public List<MobileElement>  countryEle;


	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	public AndroidElement userNameEle;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
	public AndroidElement femaleGenderEle;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
	public AndroidElement maleGenderEle;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	public AndroidElement letsShopBtnEle;

	@AndroidFindBy(xpath="//android.widget.Toast[1]")
	public AndroidElement toastMsgEle;

	/** Items Page **/

	@AndroidFindBy(id = "com.androidsample.generalstore:id/rvProductList")
	static public AndroidElement productCardListELe;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
	static public List<AndroidElement> productNameList;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productAddCart")
	static public AndroidElement addToCartBtnEle;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	static public AndroidElement productValueEle;


	@AndroidFindBy(id="com.androidsample.generalstore:id/counterText")
	static public AndroidElement cartCountEle;

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
