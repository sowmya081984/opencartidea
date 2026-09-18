package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterationPage extends BasePage {

	public AccountRegisterationPage(WebDriver driver) {
		super(driver);
	}


@FindBy(xpath="//input[@id='input-firstname']") WebElement txtFirstName;
@FindBy(xpath="//input[@id='input-lastname']") WebElement txtLastName;
@FindBy(xpath="//input[@id='input-email']") WebElement txtEmail;
@FindBy(xpath="//input[@id='input-telephone']") WebElement txtTelephone;
@FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
@FindBy(xpath="//input[@id='input-confirm']") WebElement txtConfirm;
@FindBy(xpath="//input[@name='agree']") WebElement chkdPolicy;
@FindBy(xpath="//input[@value='Continue']") WebElement btnContinue;
@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgConfirmation;

public void setTxtFirstName(String txtFirstName) {
	this.txtFirstName.sendKeys(txtFirstName);
}
public void setTxtLastName(String txtLastName) {
	this.txtLastName.sendKeys(txtLastName);
}
public void setTxtEmail(String txtEmail) {
	this.txtEmail.sendKeys(txtEmail);
}
public void setTxtTelephone(String txtTelephone) {
	this.txtTelephone.sendKeys(txtTelephone);
}
public void setTxtPassword(String txtPassword) {
	this.txtPassword.sendKeys(txtPassword);
} 
public void setTxtConfirm(String string) {
	this.txtConfirm.sendKeys(string);
}
public void setChkdPolicy() {
	this.chkdPolicy.click();
}
public void clickContinue() {
	this.btnContinue.click();
}
public String getConfirmationMsg() {
try {
	return msgConfirmation.getText();
}catch(Exception e) {
	return(e.getMessage());
}
}
}
