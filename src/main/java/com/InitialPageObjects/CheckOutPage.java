package com.InitialPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.AbstractPackages.AbstractClasses;


public class CheckOutPage extends AbstractClasses {

	WebDriver driver;

	public CheckOutPage(WebDriver driver2) {
		super(driver2);
		this.driver = driver2;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath = "//button[@class='ta-item list-group-item ng-star-inserted'][2]")
	WebElement selectCountry;

	By elementName = By.cssSelector(".ta-results.list-group.ng-star-inserted");
	By submitButton = By.cssSelector(".btnn.action__submit");
	
	@FindBy(css=".btnn.action__submit")
	WebElement submitBtn;


	public void enterAndSelectCountry(String countryName) {
		Actions mouseAction = new Actions(driver);
		mouseAction.sendKeys(country, countryName).build().perform();
		mouseAction.scrollByAmount(0, 500);
		waitForElementToAppear(elementName);
		mouseAction.moveToElement(selectCountry).click().build().perform();
		
	}
	
	public ConfirmationPage submit() {
		waitForElementToAppear(submitButton);
		submitBtn.click();
		ConfirmationPage confirm = new ConfirmationPage(driver);
		return confirm;
	}
}
