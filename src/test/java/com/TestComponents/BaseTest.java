package com.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.InitialPageObjects.LoginPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LoginPage loginPage;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\Resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
		System.out.println(browserName);
		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;

	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchURL() throws IOException {
		driver = initializeDriver();
		loginPage = new LoginPage(driver);
		loginPage.goToPage();
		return loginPage;
	}

	@AfterMethod(alwaysRun = true)
	public void closeBroswer() {
		driver.close();
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// read json to String
		String jsonContent = FileUtils.readFileToString(
				new File(filePath));
		// String to HashMap (Jackson Databind)

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}
	
	public String getScreenshot(String testcasename, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+"//reports//" + testcasename +".png");
		FileUtils.copyFile(src, dest);
		return System.getProperty("user.dir")+"//reports//" + testcasename +".png";
	}

}
