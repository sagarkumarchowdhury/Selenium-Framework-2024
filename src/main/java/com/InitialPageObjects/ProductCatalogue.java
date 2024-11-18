package com.InitialPageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.AbstractPackages.AbstractClasses;

public class ProductCatalogue extends AbstractClasses {
	WebDriver driver;

	public ProductCatalogue(WebDriver driver2) {
		super(driver2);
		this.driver = driver2;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".col-lg-4")
	List<WebElement> products;
	By productsList = By.cssSelector(".col-lg-4");
	By addToCart = By.cssSelector("button[class='btn w-10 rounded']");
	By toastMessage = By.id("toast-container");
	By dissapearToastMessage = By.id("toast-container");
	
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement cartButton;

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsList);
		return products;
	}

	public WebElement getProductByName(String item) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(item)).findFirst()
				.orElse(null);
		return prod;

	}

	public void addItemToCart(String item) {
		WebElement prod = getProductByName(item);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDissppear(dissapearToastMessage);

	}
	
	public CartItems goToCart() {
		cartButton.click();
		CartItems cartItems = new CartItems(driver);
		return cartItems;
	}

}
