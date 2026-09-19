package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC004_Search extends BaseClass {

    @Test(groups={"Sanity","Master"})
    public void verifySearch(){
        logger.info("**** Starting TC004_SearchTest *****");
        HomePage hp=new HomePage(driver);
        logger.info("Searching the product");
        hp.setTxtSearch("samsung");
        logger.info("Clicking search button");
        hp.clickSearch();

        SearchPage sp=new SearchPage(driver);
        logger.info("Validate the Search page exitst");
        Assert.assertTrue(sp.isSearchPageExists());

        logger.info("Validate the Results available");
        Assert.assertTrue(sp.isResultsExists());

        logger.info("Searching via Search page object");
        sp.setSearchText("iphone");
        logger.info("Clicking search button");
        sp.clickSearch();

        logger.info("Validate the Results available");
        Assert.assertTrue(sp.isResultsExists());

        logger.info("Searching via Negative Search page object");
        sp.setSearchText("nokia");
        logger.info("Clicking search button");
        sp.clickSearch();


        logger.info("Validate the Results not available");
        Assert.assertTrue(sp.checkNotExists());
        logger.info("**** Finished TC004_SearchTest *****");
    }
}
