package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterationPage;
import pageObjects.HomePage;
import testBase.BaseClass;



public class TC001_AccountRegistrationTest  extends BaseClass{
	

	@Test(groups={"Sanity","Master"})
	public void verifyAccountRegistration() {
	
		logger.info("**** Starting TC001_AccountRegistrationTest *****");
		
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MYAccount Link...");
		hp.clickRegister();
		logger.info("Clicked on Register Link...");
		AccountRegisterationPage regPage=new AccountRegisterationPage(driver);
		
		logger.info("Providing custoer derails...");
		regPage.setTxtFirstName(randomeString().toUpperCase());
		regPage.setTxtLastName(randomeString().toUpperCase());
		regPage.setTxtEmail(randomeString()+"@gmail.com");
		regPage.setTxtTelephone(randomeNumber());
		String password=randomeAlphaNumeric();
		regPage.setTxtPassword(password);
		regPage.setTxtConfirm(password);
		
		regPage.setChkdPolicy();
		regPage.clickContinue();
		
		logger.info("Validating expected messages after customer details...");
		String confmsg=regPage.getConfirmationMsg();
		if(confmsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test failed");
			//logger.debug("Debug logs...");
			Assert.assertTrue(false);
		}
	
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("**** Finished TC001_AccountRegistrationTest *****");	
	}

	

}
