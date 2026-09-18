package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterationPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups={"Regression","Master"})
	public void verify_login() {

		logger.info("**** Starting TC002_LoginTest *****");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on MyAccount Link...");
			hp.clickLogin();
			logger.info("Clicked on Login Link...");
			LoginPage loginPage = new LoginPage(driver);

			logger.info("Providing customer details...");
			loginPage.setEmail(p.getProperty("email"));
			loginPage.setPassword(p.getProperty("password"));
			loginPage.clickLogin();

			logger.info("Validating expected messages after customer login...");
			MyAccountPage myAccount = new MyAccountPage(driver);

			// Assert.assertEquals(myAccount.isMyAccountPageExists(), true,"Login failed");
			Assert.assertTrue(myAccount.isMyAccountPageExists());

		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("**** Finished TC002_LoginTest *****");
	}

}
