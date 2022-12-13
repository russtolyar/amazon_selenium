package com.amazon.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FilterMenuPage extends AbstractPage {

    private static final Logger LOGGER = Logger.getLogger(FilterMenuPage.class);

    @FindBy(xpath = "//div[text()='Smart Home']")
    private WebElement smartHomeBtn;

    @FindBy(xpath = "//a[@class ='hmenu-item'][text()='Pet']")
    private WebElement petBtn;

    @FindBy(xpath = "//div[@class ='nav-sprite hmenu-close-icon']")
    private WebElement closeFilterMenuBtn;

    //    @FindBy(xpath = "//*[@id='hmenu-content']") // the same locator but upper and wider (needed depends on page design)
    @FindBy(xpath = "//ul[@class='hmenu hmenu-visible']")
    private WebElement filterBlock;

    @FindBy(xpath = "//*[@id='hmenu-content']//following::div[contains(text(),'smart home')]")
    private WebElement smartHomeTitle;

    @FindBy(xpath = "//*[text()='Smart Pet | Smart Home']")
    private WebElement titleSmartPet;

    public FilterMenuPage(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public FilterMenuPage clickSmartHomeBtn() {
        clickButton(smartHomeBtn, "smartHomeBtn");
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(smartHomeTitle));
        return new FilterMenuPage(driver);
    }

    public FilterResultPage clickPetBtn() {
        clickButton(petBtn, "petBtn");
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(titleSmartPet));
        return new FilterResultPage(driver);
    }

    public boolean isFMPageOpen() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(closeFilterMenuBtn));
        return closeFilterMenuBtn.isDisplayed() || filterBlock.isDisplayed();
    }

    public boolean isSmartTitlePresent() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(smartHomeTitle));
        boolean isSmartHomeTitleDisplayed = smartHomeTitle.isDisplayed();
        LOGGER.info("Verifying 'Smart Home' Presence on title of page : " + isSmartHomeTitleDisplayed);
        return isSmartHomeTitleDisplayed;
    }

    public HomePage clickCloseBtn() {
        new WebDriverWait(driver, Duration.ofSeconds(9)).until(ExpectedConditions.visibilityOf(closeFilterMenuBtn));
        clickButton(closeFilterMenuBtn, "closeFilterMenuBtn");
        boolean isCloseFilterMenuBtnDisplayed = closeFilterMenuBtn.isDisplayed();
        new WebDriverWait(driver, Duration.ofSeconds(9)).until(ExpectedConditions.invisibilityOf(closeFilterMenuBtn));
        LOGGER.info("closeFilterMenuBtn disappeared after clicking on it - " + !isCloseFilterMenuBtnDisplayed);
        return new HomePage(driver);
    }

    public WebElement getCloseFilterMenuBtn(){
        return closeFilterMenuBtn;
    }
}