package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//span[normalize-space()='My Account']") WebElement lnkMyaccount;
	@FindBy(xpath="//a[normalize-space()='Register']")  WebElement lnkRegister;
	@FindBy(xpath="//a[normalize-space()='Login']")  WebElement lnkLogin;
	@FindBy(xpath="//input[@placeholder='Search']") WebElement txtSearch;
	@FindBy(xpath = " //i[@class='fa fa-search']") WebElement btnClickSearch;
	
	public void clickMyAccount() {
		lnkMyaccount.click();
	}
	public void clickRegister() {
		lnkRegister.click();
	}
	public void clickLogin() {
		lnkLogin.click();
	}

	public void clickSearch() {
		btnClickSearch.click();
	}

	public void setTxtSearch(String searchtext){
		txtSearch.clear();
		txtSearch.sendKeys(searchtext);
	}
}
