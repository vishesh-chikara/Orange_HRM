package com.orangeHRM_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangeHRM_Actiondriver.ActionDriver;

public class LoginPage {

	private ActionDriver actiondriver;

	// define locators using by class

	private By usernameField = By.name("username");
	private By passwordField = By.name("password");
	private By loginButton = By.xpath("//button[normalize-space()='Login']");
	private By errormsg = By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']");

	// initialization the actionDriver object by passing WebDriver instance
	public LoginPage(WebDriver driver) {
		this.actiondriver = new ActionDriver(driver);
	}

	// method perform login

	public void login(String username, String password) {
		actiondriver.enterText(usernameField, username);
		actiondriver.enterText(passwordField, password);
		actiondriver.click(loginButton);
	}

	public boolean isErrorMessageDisplayed() {
		return actiondriver.isDisplayed(errormsg);
	}

	// method to get the text from error message
	public void getErrorMsg() {
		actiondriver.getText(errormsg);
	}

	// verify if error is correct or not
	public boolean VerifyErrorMsg(String ExpectedError) {
		return actiondriver.compareText(errormsg, ExpectedError);
	}

}
