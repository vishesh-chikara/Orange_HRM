package com.orangeHRM_Base;

import java.io.FileInputStream;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClassHRM {

	protected static WebDriver driver;
	protected static Properties pr;

	@BeforeSuite
	public void loadConfig() throws IOException {
		pr = new Properties();
		{
			FileInputStream fr = new FileInputStream("C:\\Users\\admin\\git\\Orange_HRM\\Orange_HRM_Project\\src\\main\\resources\\config.properties");
			pr.load(fr);
		}
	}

	@BeforeMethod
	public void setup() {
		System.out.println("Setting up webDriver for : " + this.getClass().getSimpleName());
		launchbrowser();
		configBrowser();
		staticwait(2);

	}

	private void launchbrowser() {

		String browser = pr.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		}

		else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		else {
			throw new IllegalArgumentException("Browser Not supported :" + browser);

		}

	}

	private void configBrowser() {
		{
			int implicitWait = Integer.parseInt(pr.getProperty("implicitWait"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
			driver.manage().window().maximize();
			try {
				driver.get(pr.getProperty("url"));
			} catch (Exception e) {
				System.out.println("Failer to navigaet to Url:" + e.getMessage());
			}
		}
	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		if (driver != null) {
			try {
				driver.quit();
			} catch (Exception e) {
				System.out.println("unable to quit the driver :" + e.getMessage());
			}
		}

	}

	// getter method for prop
	public static Properties getPr() {
		return pr;
	}

	// driver getter method
	public WebDriver getDriver() {
		return driver;
	}

	// driver setter method
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	// static wait for pause
	public void staticwait(int seconds) {
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
	}

}
