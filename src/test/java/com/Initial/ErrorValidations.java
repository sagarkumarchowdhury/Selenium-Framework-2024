package com.Initial;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.TestComponents.BaseTest;


public class ErrorValidations extends BaseTest {

	@Test(groups = {"Error Handling"}, retryAnalyzer = com.TestComponents.Retry.class)
	public void wrongLogin() throws IOException {
		// TODO Auto-generated method stub
		loginPage.loginApplication("sagar729876@gmail.com", "Naruto@1");
		Assert.assertEquals(loginPage.errorMessageValidation(), "Incorrect email or password.");
		System.out.println("Changes done by Y after latest pull");
		System.out.println("Changes done by XX");
		System.out.println("New changes done by XX and pushed to develop branch");
		System.out.println("Nnew chnages done by Y and pushed to Develop branch");
		System.out.println("Nnew chnages done by XX on today");
	}
	
	@Test
	public void wrongLogin2() throws IOException {
		// TODO Auto-generated method stub
		loginPage.loginApplication("sagar729876@gmail.com", "Narut@1");
		Assert.assertEquals(loginPage.errorMessageValidation(), "Incorrect email or password.");
	}
}
