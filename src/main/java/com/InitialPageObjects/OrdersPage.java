package com.InitialPageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.AbstractPackages.AbstractClasses;

public class OrdersPage extends AbstractClasses {
	public WebDriver driver;

	public OrdersPage(WebDriver driver2) {
		super(driver2);
		this.driver = driver2;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//tbody/tr/td[2]")
	List<WebElement> orderlist;

	public Boolean checkForOrder(String item) {

		return orderlist.stream().anyMatch(orderName -> orderName.getText().equals(item));
	}



}
