package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;

public class SearchPage extends BasePage {

	public SearchPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h1[contains(text(),'Search - ')]")
	WebElement searchHeading;
	@FindBy(xpath = "//p[contains(text(),'There is no product that matches')]")
	WebElement searchNoResult;
    @FindBy(xpath = "//div[@class='product-thumb']//img")
	List<WebElement> searchResult;
	@FindBy(xpath="//input[@placeholder='Search']") WebElement txtSearch;
	@FindBy(xpath = " //i[@class='fa fa-search']") WebElement btnClickSearch;
	@FindBy(xpath = "//div[@class='col-sm-6 text-right']") WebElement pageinfo;

	public boolean isSearchPageExists() {
		try {
			return searchHeading.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void  getPageinfo(){
		String pagedetails=pageinfo.getText();
		System.out.println("Page information details "+pagedetails);
		int pages=Integer.parseInt(pagedetails.substring(pagedetails.indexOf("of")+3,pagedetails.indexOf("(")-1));
		System.out.println("No. of Results "+pages);
		}
	public boolean checkNotExists(){
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			System.out.println("checking the SearchResult");
			//getPageinfo();
			return searchNoResult.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	public boolean isResultsExists() {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			System.out.println("checking the SearchResult");
			getPageinfo();
			for (WebElement searchItem : searchResult) {
					System.out.println("Search results "+searchItem.getAttribute("title"));
				}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void setSearchText(String searchText) {
		txtSearch.clear();
		txtSearch.sendKeys(searchText);
	}
	public void clickSearch()  {
		btnClickSearch.click();
	}

}
