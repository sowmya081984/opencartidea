package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterationPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups="DataDriven")
	public void verify_loginDDT(String email, String pwd, String exp) throws Exception {

		logger.info("**** Starting TC003_LoginTestDDT *****");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on MyAccount Link...");
			hp.clickLogin();
			logger.info("Clicked on Login Link...");
			LoginPage loginPage = new LoginPage(driver);

			logger.info("Providing customer details...");
			System.out.println("Email "+ email+" : "+pwd+" : "+exp);
			loginPage.setEmail(email);
			logger.info("Set email "+ email);

			loginPage.setPassword(pwd);
			logger.info("Set password "+ pwd);
			System.out.println("Password "+ pwd);
			loginPage.clickLogin();

			logger.info("Validating expected messages after customer login...");
			MyAccountPage myAccount = new MyAccountPage(driver);

			boolean loginSuccess = myAccount.isMyAccountPageExists();

			if (exp.equalsIgnoreCase("Valid")) {
				if (loginSuccess==true) {
					myAccount.clickLogout();
					Assert.assertTrue(true);
					}
				else {
					Assert.assertTrue(false);
				}
			
			} else {
				if (loginSuccess==true) {
					myAccount.clickLogout();
					Assert.assertTrue(false);
				}
					
				else {
					Assert.assertTrue(true);
				}
			}
			// Assert.assertEquals(myAccount.isMyAccountPageExists(), true,"Login failed");
		} catch (Exception e) {
			System.out.print(e.toString());
			Assert.fail();
		}
		Thread.sleep(3000);
		logger.info("**** Finished TC003_LoginTestDDT *****");
	}

}
