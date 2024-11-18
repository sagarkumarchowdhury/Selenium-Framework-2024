package com.Initial;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.TestComponents.BaseTest;
import com.sun.net.httpserver.Authenticator.Retry;

public class ErrorValidations extends BaseTest {

	@Test(groups = {"Error Handling"}, retryAnalyzer = com.TestComponents.Retry.class)
	public void wrongLogin() throws IOException {
		// TODO Auto-generated method stub
		loginPage.loginApplication("sagar729876@gmail.com", "Naruto@1");
		Assert.assertEquals(loginPage.errorMessageValidation(), "Incorrect email or password.");
		System.out.println("CHanges done by Y after clone");
	}
	
	@Test
	public void wrongLogin2() throws IOException {
		// TODO Auto-generated method stub
		loginPage.loginApplication("sagar729876@gmail.com", "Narut@1");
		Assert.assertEquals(loginPage.errorMessageValidation(), "Incorrect email or password.");
	}
}
