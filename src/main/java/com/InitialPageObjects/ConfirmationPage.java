package com.InitialPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.AbstractPackages.AbstractClasses;

public class ConfirmationPage extends AbstractClasses {
	WebDriver driver;

	public ConfirmationPage(WebDriver driver2) {
		super(driver2);
		this.driver = driver2;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".hero-primary")
	WebElement message;
	
	public String confrimationMessage() {
		String messageText = message.getText();
		return messageText;
	
	}

}
