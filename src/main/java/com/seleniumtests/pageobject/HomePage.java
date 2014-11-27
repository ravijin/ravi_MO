package com.seleniumtests.pageobject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	private WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	@CacheLookup
	@FindBy(css = "input[name='empCPR']")
	WebElement search;

	@CacheLookup
	//@FindBy(id = "btn btn-default search")
	//@FindBy(className = "add-user ng-binding")
	@FindBy(css = "div[class='col-md-1 col-sm-1'] div p:nth-of-type(1) [id='elEmpNewBtn'][class^='add-user']")
	public
	WebElement searchicon;

	public UpdateEmpPage SearchUser() {

		Actions act = new Actions(driver);
		act.moveToElement(searchicon).click().build().perform();
		//searchicon.click();
		return PageFactory.initElements(driver, UpdateEmpPage.class);
	}
}
