package com.InitialPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.AbstractPackages.AbstractClasses;

public class LoginPage extends AbstractClasses {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "userEmail")
	WebElement userEmail;
	@FindBy(id = "userPassword")
	WebElement userPassword;
	@FindBy(id = "login")
	WebElement login;
	@FindBy(css=".toast-message")
	WebElement wrongLoginMessage;
	
	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
		ProductCatalogue productList = new ProductCatalogue(driver);
		return productList;
	}
	
	public String errorMessageValidation() {
		waitForElementToAppear1(wrongLoginMessage);
		String msg = wrongLoginMessage.getText();
		return msg;
	}
	
	public void goToPage() {
		driver.get("https://rahulshettyacademy.com/client");
	}

}
