package com.orangeHRM_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangeHRM_Actiondriver.ActionDriver;

public class HomePage {
	
	private ActionDriver actiondriver ;
	
	//define locators using by class
	private By adminTab = By.xpath("//a[normalize-space()='']");
	private By userIDButton = By.className("oxd-userdropdown-name");
	private By LogOutButton = By.xpath("//a[normalize-space()='Logout']");
	private By OrangeHrm_logo = By.xpath("//img[@alt='client brand banner']");
	
	//initialization the actionDriver object by passing WebDriver instance  
	public HomePage(WebDriver driver)
	{
		this.actiondriver = new ActionDriver(driver);
	}

		//method to verify if admin tab is visible 
		public boolean isAdminTabVisible()
		{
			return actiondriver.isDisplayed(adminTab);
		}
	
		public boolean verifyOrangeHRMLogo()
		{
			return actiondriver.isDisplayed(OrangeHrm_logo);	
		}
	
		//method to perform logout operation
		public void logout()
		{
			actiondriver.click(userIDButton);
			actiondriver.click(LogOutButton);
		}
}
