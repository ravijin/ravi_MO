package com.seleniumtests.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AppLaunchPage {
	private WebDriver driver;

	public AppLaunchPage(WebDriver driver) {
		this.driver = driver;
	}

	@CacheLookup
	@FindBy(id = "employeeLogin")
	WebElement employeeIcon;

	public LoginPage ClickEmp() {

		employeeIcon.click();
		return PageFactory.initElements(driver, LoginPage.class);
	}

}
