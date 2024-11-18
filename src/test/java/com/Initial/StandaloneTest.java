package com.Initial;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.InitialPageObjects.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String item = "IPHONE 13 PRO";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/cl");
		driver.findElement(By.id("userEmail")).sendKeys("sagar729876@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Naruto55@1");
		driver.findElement(By.id("login")).click();
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
		List<WebElement> ls = driver.findElements(By.cssSelector(".col-lg-4"));
		WebElement prod = ls.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(item))
				.findFirst().orElse(null);
		prod.findElement(By.cssSelector("button[class='btn w-10 rounded']")).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container")));
		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
		
		List<WebElement> cartItems =driver.findElements(By.xpath("//div[@class='cart'] //h3"));
		Assert.assertTrue(cartItems.stream().anyMatch(cartProducts -> cartProducts.getText().equals(item)));
		driver.findElement(By.cssSelector("ul .btn.btn-primary:last-of-type")).click();
		
		Actions act = new Actions(driver);
		act.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "Ind").build().perform();
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results.list-group.ng-star-inserted")));
		act.moveToElement(driver.findElement(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted'][2]"))).click().build().perform();
		act.scrollByAmount(0, 500).build().perform();
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".btnn.action__submit")));
		driver.findElement(By.cssSelector(".btnn.action__submit")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector(".hero-primary")).getText().equalsIgnoreCase("Thankyou for the order."));

	}

}
