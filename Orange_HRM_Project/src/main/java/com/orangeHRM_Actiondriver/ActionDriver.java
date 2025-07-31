package com.orangeHRM_Actiondriver;

import java.time.Duration;

import org.apache.hc.core5.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangeHRM_Base.BaseClassHRM;

public class ActionDriver {

	private WebDriver driver;
	private WebDriverWait wait;

	// Creating A constructor class here to initialize the value in it
	public ActionDriver(WebDriver driver) {
		this.driver = driver;
	int explicitWait =	Integer.parseInt(BaseClassHRM.getPr().getProperty("ExplicitWait"));
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Initialize driver and wait
	}

	// method to click an element
	public void click(By by) {
		try {
			waitForElementToBeClickable(by);
			driver.findElement(by).click();
		} catch (Exception e) {
			System.out.println("unable to click element :" + e.getMessage());
		}
	}
	// method to enter text into input fields

	public void enterText(By by, String value) {
		try {
			waitForElementToBevisible(by);
			// driver.findElement(by).clear();
			// driver.findElement(by).sendKeys(value);
			WebElement element = driver.findElement(by);
			element.clear();
			element.sendKeys(value);
		} catch (Exception e) {

			System.out.println("Unbale to enter the value in input box :" + e.getMessage());
		}
	}

	// method to get text from input fields
	public String getText(By by) {
		try {
			waitForElementToBevisible(by);
			return driver.findElement(by).getText();
		} catch (Exception e) {
			System.out.println("unable to get the text :" + e.getMessage());
		}
		return "";

	}

	// method to compare two text  -- Change the return type 
	public boolean compareText(By by, String ExpectedText) {
		try {
			waitForElementToBevisible(by);
			String actualText = driver.findElement(by).getText();
			if (ExpectedText.equals(actualText)) {
				System.out.println("Text are match :" + actualText + "equals" + ExpectedText);
				return true ;
			} 
			else {
				System.out.println("Text are match :" + actualText + "Not equals" + ExpectedText);
				return false ;
			}
		} catch (Exception e) {
			System.out.println("unable to compare text :" + e.getMessage());
		}
		return false;
	}

	// wait for element to be clickable
	private void waitForElementToBeClickable(By by) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {

			System.out.println("element is not clickable :" + e.getMessage());
		}
	}

	// method to check if the element is displayed
	public boolean isDisplayed(By by) {
		try {
			waitForElementToBevisible(by);
			return driver.findElement(by).isDisplayed();
		} catch (Exception e) {
			System.out.println("element is not displayed :" + e.getMessage());
			return false;
		}
	}

	// scroll to an element
	public void scrollElement(By by) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(by);
			js.executeScript("argument[0], scrollintoView(true);", element);
		} catch (Exception e) {
			System.out.println("unable to locater element " + e.getMessage());
		}
	}

	// wait for the page to load
	public void waitForPageLoad(int TimeOutInSec) {
		try {
			wait.withTimeout(Duration.ofSeconds(TimeOutInSec)).until(WebDriver -> (JavascriptExecutor) WebDriver)
					.executeScript("return doument.ready state").equals("complete");
			System.out.println("page load succussfully");
		} catch (Exception e) {
			System.out.println("page did not load with" + TimeOutInSec + "second .Execption :" + e.getMessage());

		}

	}

	// wait for element to visible
	private void waitForElementToBevisible(By by) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			System.out.println("element is not visible :" + e.getMessage());
		}
	}

}
