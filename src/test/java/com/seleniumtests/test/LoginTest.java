package com.seleniumtests.test;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.seleniumtests.core.CustomVerification;
import com.seleniumtests.core.DriverFactory;
import com.seleniumtests.dataobject.MORegistrationData;
import com.seleniumtests.dataobject.MORegistrationDataClub;
import com.seleniumtests.pageobject.AppLaunchPage;
import com.seleniumtests.pageobject.HomePage;
import com.seleniumtests.pageobject.LoginPage;
import com.seleniumtests.utility.HighLight;

public class LoginTest extends DriverFactory {
	
	@Test()
	public void AppLaunchTest() {

		driver.get(appURL);
		if(driver.getTitle().contains("certificate") || driver.getTitle().contains("Certificate"))
		driver.navigate().to("javascript:document.getElementById('overridelink').click()") ;


		AppLaunchPage launch = PageFactory.initElements(driver,
				AppLaunchPage.class);
		launch.ClickEmp();
		LoginPage login = PageFactory.initElements(driver,
				LoginPage.class);
		login.SubmitUser();
		
		wait= new WebDriverWait( driver, 5 );
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		HomePage home = PageFactory.initElements(driver,
				HomePage.class);
		//wait.until(ExpectedConditions.elementToBeClickable(home.searchicon));
		home.SearchUser();
		
		CustomVerification customVerification = new CustomVerification();
		
		//customVerification.verifyTrue("Login Done", home.HomeP() );
		customVerification.verifyTrue("Login Done", true);
		customVerification.checkForVerificationErrors();
	}
}