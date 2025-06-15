package com.orangehrm.crm.application.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import com.orangehrm.crm.application.steps.ForgotLoginSteps;
import com.orangehrm.crm.application.steps.HomePageSteps;
import com.orangehrm.crm.application.steps.LoginPageSteps;
import com.orangehrm.crm.framework.base.BasePage;
import com.orangehrm.crm.framework.utilities.PropUtil;

public class BaseTest extends BasePage {
	
	public Properties prop = PropUtil.readData("Config.properties");
	
	public LoginPageSteps loginPage;
	public HomePageSteps homePage;
	public ForgotLoginSteps forgotLogin;

	@BeforeMethod(alwaysRun = true,dependsOnMethods = "setupBrowser")
	public void initializePages() {
		WebDriver driver = BasePage.getDriver();
		loginPage = new LoginPageSteps(driver);
		homePage = new HomePageSteps(driver);
		forgotLogin = new ForgotLoginSteps(driver);
	}	

}
