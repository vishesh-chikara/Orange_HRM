package com.orangeHRM_Tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.orangeHRM_Base.BaseClassHRM;
import com.orangeHRM_pages.HomePage;
import com.orangeHRM_pages.LoginPage;

public class HomePageTest extends BaseClassHRM{
	

	private LoginPage loginPage ;
	private HomePage homePage;
	
	@BeforeTest
	public void setupPages()
	{
		loginPage = new LoginPage(getDriver());
		homePage = new HomePage(getDriver());
	}
	
	@Test(priority=3)
	public void VerifyOrangeHRMlogo()
	{
		loginPage.login("admin", "admin123");
		Assert.assertTrue(homePage.verifyOrangeHRMLogo(),"Logo is not visible");
		staticwait(3);
	}

}
