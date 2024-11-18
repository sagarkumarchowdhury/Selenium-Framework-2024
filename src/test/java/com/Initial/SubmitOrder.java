package com.Initial;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.InitialPageObjects.CartItems;
import com.InitialPageObjects.CheckOutPage;
import com.InitialPageObjects.ConfirmationPage;
import com.InitialPageObjects.OrdersPage;
import com.InitialPageObjects.ProductCatalogue;
import com.TestComponents.BaseTest;

public class SubmitOrder extends BaseTest {

	String item = "IPHONE 13 PRO";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrderIphone(HashMap<String, String> input) throws IOException {
		// TODO Auto-generated method stub

		String countryName = "Ind";
		ProductCatalogue productList = loginPage.loginApplication(input.get("email"), input.get("password"));
		productList.addItemToCart(input.get("productName"));
		CartItems cartItems = productList.goToCart();
		cartItems.checkAddedItemName(input.get("productName"));
		CheckOutPage check = cartItems.checkOut();
		check.enterAndSelectCountry(countryName);
		ConfirmationPage confirm = check.submit();
		String message = confirm.confrimationMessage();
		Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
	}

	@Test(dependsOnMethods = { "submitOrderIphone" })
	public void orderHistoryIphone() {
		ProductCatalogue productList = loginPage.loginApplication("sagar729876@gmail.com", "Naruto55@1");
		OrdersPage orders = productList.goToOrderHistoryPage();
		Assert.assertTrue(orders.checkForOrder(item));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "sagar729876@gmail.com");
//		map.put("password", "Naruto55@1");
//		map.put("product", "IPHONE 13 PRO");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "jamesHORNER@gmail.com");
//		map1.put("password", "James55@1");
//		map1.put("product", "ADIDAS ORIGINAL");
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\com\\TestData\\PurchaseOrder.json");
		
		return new Object[][] { { data.get(0) }, { data.get(1) } };

//		return new Object[][] { { "sagar729876@gmail.com", "Naruto55@1", "IPHONE 13 PRO" },
//				{ "jamesHORNER@gmail.com", "James55@1", "ADIDAS ORIGINAL" } };
	}

}
