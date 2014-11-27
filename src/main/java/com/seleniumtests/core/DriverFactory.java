package com.seleniumtests.core;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class DriverFactory {

	protected WebDriver driver;
	protected String appURL;
	public static WebDriverWait wait=null;
	public static int waitTime=5;

	@BeforeMethod()
	@Parameters("appURL")
	public void setEnv(
			@Optional("http://dev.mathmagicians.dk:8080/mo") String appURL) {
		this.appURL = appURL;
	}

	@BeforeMethod()
	@Parameters({ "browser" })
	public void launchBrowser(@Optional("FF") String browser)
			throws MalformedURLException {
		if (browser.equalsIgnoreCase("Chrome")) {
			final File file = new File(
					"src/test/resources/drivers/chromeDriver/chromedriver.exe");
			System.setProperty("webdriver.chrome.driver",
					file.getAbsolutePath());

			DesiredCapabilities chcapabilities = new DesiredCapabilities();
			chcapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("-test-type");
			chcapabilities.setCapability(ChromeOptions.CAPABILITY, options);

			driver = new ChromeDriver(chcapabilities);
			driver.manage().window().maximize();

		} else if (browser.equalsIgnoreCase("FF")) {
			driver = new FirefoxDriver();
			driver.manage().window().maximize();

		} else if (browser.equalsIgnoreCase("IE")) {
			final File file = new File(
					"src/test/resources/drivers/ieDriver/IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			DesiredCapabilities iecapabilities = DesiredCapabilities
					.internetExplorer();
			iecapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			iecapabilities.setJavascriptEnabled(true);

			driver = new InternetExplorerDriver(iecapabilities);
			// driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
	}

	@AfterMethod
	public void closeBrowser(ITestResult result) throws IOException {

		if (!result.isSuccess()) {
			File imageFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);

			String failureImageFileName = imageFile.getName()
					+ result.getMethod().getMethodName()
					+ new SimpleDateFormat("MM-dd-yyyy_HH-ss")
							.format(new GregorianCalendar().getTime()) + ".png";
			File failureImageFile = new File(
					"C:\\D\\DATA_driven_BB\\BBT\\screenshots\\"
							+ failureImageFileName);
			FileUtils.copyFile(imageFile, failureImageFile);

		}

		driver.close();
		driver.quit();
	}
}
