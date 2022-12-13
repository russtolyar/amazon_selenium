package com.amazon.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInFormPage extends AbstractPage {

    private static final Logger LOGGER = Logger.getLogger(SignInFormPage.class);

    @FindBy(xpath = "//h1")
    private WebElement header;

    @FindBy(xpath = "//a[@class='a-link-nav-icon']")
    private WebElement homeBtn;

    @FindBy(xpath = "//*[@aria-label='Amazon']")
    private WebElement homeBtn1;

    private String titleName = "Sign in";

    public SignInFormPage(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isHeaderSignIn() {
        String headerText = header.getText();
        boolean isHeaderGood = headerText.equals(titleName);
        LOGGER.info("Verifying the header of window (that is - " + headerText + ") have title - 'Sign in' . This is - " + isHeaderGood);
        return isHeaderGood;
    }

    public void clickHomeBtn() {
        if (homeBtn.isDisplayed()) {
            clickButton(homeBtn, "homeBtn");
        } else if (homeBtn1.isDisplayed()) {
            clickButton(homeBtn1, "homeBtn1");
        }
        HomePage homePage = new HomePage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(homePage.getHomePageWebElement()));
        LOGGER.info("click Home-Btn. And HomePage is open - " + homePage.isHomePageOpen());

    }
}