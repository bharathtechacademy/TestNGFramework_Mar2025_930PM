package com.orangehrm.crm.application.tests;

import org.testng.annotations.Test;

/**
 * This class contains automated TestNG test cases for verifying core functionalities
 * of the OrangeHRM application, including login, error handling, UI components, 
 * and navigation validations post-login.
 *
 * <p>Each test case is annotated with @Test and makes use of data-driven testing
 * via the TestNG `dataProvider`. Tests are executed based on priority order to 
 * ensure application stability checks are done before deeper functionality is validated.</p>
 *
 * <p>Test coverage includes:</p>
 * <ul>
 *   <li>Login page header and logo verification</li>
 *   <li>Valid and invalid login attempts</li>
 *   <li>Forgot password functionality</li>
 *   <li>Home page UI verification (menus, profile image)</li>
 * </ul>
 *
 * <p>Pre-requisite: The BaseTest class initializes all required page objects and
 * configuration setup like WebDriver, properties, etc.</p>
 * 
 * @author Bharath
 * @since 2025-06-15
 */
public class ApplicationTest extends BaseTest {

    /**
     * Test to verify that the login page displays the expected logo and header text.
     *
     * @param expHeader Expected header text to be verified on the login page.
     */
    @Test(priority = 1, dataProvider = "data")
    public void verifyLoginPageLogoAndHeader(String expHeader) {
        loginPage.launchApplication();
        loginPage.verifyLoginPageLogo();
        loginPage.verifyLoginPageHeader(expHeader);
    }

    /**
     * Test to verify successful login with valid user credentials.
     *
     * @param username Valid username as per data provider.
     * @param password Valid password corresponding to the username.
     */
    @Test(priority = 2, dataProvider = "data")
    public void verifyLoginWithValidCredentials(String username, String password) {
        loginPage.launchApplication();
        loginPage.enterCredentials(username, password);
        loginPage.clickOnLoginButton();
        homePage.verifyLoginSuccessful();
    }

    /**
     * Test to verify error handling on login attempt with invalid credentials.
     *
     * @param username Invalid username.
     * @param password Invalid password.
     */
    @Test(priority = 3, dataProvider = "data")
    public void verifyInvalidLogin(String username, String password) {
        loginPage.launchApplication();
        loginPage.enterCredentials(username, password);
        loginPage.clickOnLoginButton();
        loginPage.verifyLoginErrorMessage();
    }

    /**
     * Test to verify the functionality of the "Forgot Password" feature.
     * This test checks if the reset password page appears and the reset
     * operation completes with success message.
     */
    @Test(priority = 4)
    public void VerifyForgotLoginFeature() {
        loginPage.launchApplication();
        loginPage.clickOnForgotPasswordLink();
        forgotLogin.verifyForgotLoginPageIsDisplayed();
        forgotLogin.enterUsernameTorestThePassword(prop.getProperty("APP_USER"));
        forgotLogin.clickResetPasswordButton();
        forgotLogin.verifyPasswordResetSuccessMessage();
    }

    /**
     * Test to verify the presence of the user profile image on the home page
     * after successful login, followed by logout and verification.
     *
     * @param username Valid username.
     * @param password Corresponding valid password.
     */
    @Test(priority = 5, dataProvider = "data")
    public void VerifyProfileImageInHomePage(String username, String password) {
        loginPage.launchApplication();
        loginPage.enterCredentials(username, password);
        loginPage.clickOnLoginButton();
        homePage.verifyLoginSuccessful();
        homePage.verifyUserProfileImageDisplayed();
        homePage.logout();
        loginPage.verifyLogoutSuccessful();
    }

    /**
     * Test to verify that all expected menu items are displayed on the home page
     * after a successful login.
     *
     * @param username Valid username.
     * @param password Corresponding valid password.
     */
    @Test(priority = 6, dataProvider = "data")
    public void VerifyHomePageMenus(String username, String password) {
        loginPage.launchApplication();
        loginPage.enterCredentials(username, password);
        loginPage.clickOnLoginButton();
        homePage.verifyLoginSuccessful();
        homePage.verifyMenuItemsDisplayed();
        homePage.logout();
        loginPage.verifyLogoutSuccessful();
    }
}
