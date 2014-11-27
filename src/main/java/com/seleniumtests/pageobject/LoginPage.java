package com.seleniumtests.pageobject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	@CacheLookup
	@FindBy(id = "elLoginUser")
	WebElement username;

	@CacheLookup
	@FindBy(id = "elLoginPass")
	WebElement password;

	@CacheLookup
	@FindBy(id = "elLoginSubmit")
	WebElement loginButton;
	
	@CacheLookup
	@FindBy(id = "elLoginSubmit")
	WebElement loginDiv;

	public HomePage SubmitUser() {
		username.sendKeys("test_user");
		password.sendKeys("password");
		loginButton.click();
		return PageFactory.initElements(driver, HomePage.class);

	}

}
