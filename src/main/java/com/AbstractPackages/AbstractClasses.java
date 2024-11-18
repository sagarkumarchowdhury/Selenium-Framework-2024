package com.AbstractPackages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.InitialPageObjects.OrdersPage;

public class AbstractClasses {

	WebDriver driver;

	public AbstractClasses(WebDriver driver2) {
		this.driver = driver2;
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//button[@class ='btn btn-custom'])[2]")
	WebElement ordersButton;

	public OrdersPage goToOrderHistoryPage() {
		ordersButton.click();
		OrdersPage orders = new OrdersPage(driver);
		return orders;

	}
	
	public void waitForElementToAppear(By findBy) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForElementToAppear1(WebElement appear) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		explicitWait.until(ExpectedConditions.visibilityOf(appear));
	}

	public void waitForElementToDissppear(By findBy) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	
	

}
