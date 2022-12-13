package com.amazon.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UpTab extends AbstractPage {

    private static final Logger LOGGER = Logger.getLogger(HomePage.class);

    @FindBy(id = "nav-link-accountList-nav-line-1")
    private WebElement signInBtn;

    @FindBy(xpath = "//*[@id='twotabsearchtextbox']")
    private WebElement searchField;

    @FindBy(xpath = "//div[@class='a-box-inner a-padding-extra-large']")
    private WebElement signInBlock;

    @FindBy(id = "nav-logo-sprites")
    private WebElement homeBtn;

    public UpTab(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public SignInFormPage clickSignInBtn() {
        clickButton(signInBtn, "signInBtn");
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(signInBlock));
        return new SignInFormPage(driver);
    }

    public void clickSearchField() {
        clickButton(searchField, "searchField");
    }

    public ResultsPage inputTextInSearchField(String searchItem) {
        sendKeysMethod(searchField, searchItem);
        return new ResultsPage(driver);
    }

    public ResultsPage findItem(String searchItem) {
        clickSearchField();
        return inputTextInSearchField(searchItem);
    }

    public HomePage clickHomeBtn() {
        clickButton(homeBtn, "homeBtn");
        HomePage homePage = new HomePage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(homePage.getHomePageWebElement()));
        LOGGER.info("HomePage is opened : " + homePage.isHomePageOpen());
        return homePage;
    }

}