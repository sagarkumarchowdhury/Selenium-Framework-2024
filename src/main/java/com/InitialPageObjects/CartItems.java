package com.InitialPageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.AbstractPackages.AbstractClasses;

public class CartItems extends AbstractClasses {
	
	WebDriver driver;

	public CartItems(WebDriver driver2) {
		super(driver2);
		this.driver = driver2;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='cart'] //h3")
	List<WebElement> cartItems;
	
	@FindBy(css = "ul .btn.btn-primary:last-of-type")
	WebElement checkoutBtn;
	
	public void checkAddedItemName(String item) {
		Assert.assertTrue(cartItems.stream().anyMatch(cartProducts -> cartProducts.getText().equals(item)));
	}
	
	public CheckOutPage checkOut() {
		checkoutBtn.click();
		CheckOutPage check = new CheckOutPage(driver);
		return check;

	}

}
