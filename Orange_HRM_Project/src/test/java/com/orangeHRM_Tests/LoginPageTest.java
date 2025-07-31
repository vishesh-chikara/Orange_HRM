package com.orangeHRM_Tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangeHRM_Base.BaseClassHRM;
import com.orangeHRM_pages.HomePage;
import com.orangeHRM_pages.LoginPage;

public class LoginPageTest extends BaseClassHRM {
	
	private LoginPage loginPage ;
	private HomePage homePage;
	
	@BeforeMethod
		public void setupPages()
		{
			loginPage = new LoginPage(getDriver());
			homePage = new HomePage(getDriver());
		}
			@Test
		public void verifyvalidloginTest()
		{
			loginPage.login("admin", "admin123");
			Assert.assertTrue(homePage.isAdminTabVisible(), "admin tab should be visible after succesfull login");
			homePage.logout();
			staticwait(2);
		}
			@Test
			public void invalidlogin()
			{
				loginPage.login("admin", "admin");
				String expectedError = "Invalid credentials";
				Assert.assertTrue(loginPage.VerifyErrorMsg(expectedError), "Test Failed : Invalid Error Msg");
				staticwait(2);
			}
}
