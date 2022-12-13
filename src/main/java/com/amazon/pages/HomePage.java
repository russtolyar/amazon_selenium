package com.amazon.pages;

import com.amazon.constants.Const;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends AbstractPage {
    private static final Logger LOGGER = Logger.getLogger(HomePage.class);

    @FindBy(xpath = "//div[@id='desktop-banner-stripe']")
    private WebElement desktopBannerStripe;

    @FindBy(xpath = "//div[@id='gw-card-layout']")
    private WebElement desktopCardLayout;

    public HomePage(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isHomePageOpen() {
        FilterMenuPage filterMenuPage = new FilterMenuPage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.and(
                        ExpectedConditions.visibilityOf(desktopCardLayout),
                        ExpectedConditions.invisibilityOf(filterMenuPage.getCloseFilterMenuBtn())));
        boolean isHomePageOpened = driver.getCurrentUrl().equals(Const.HOME_URL)
                || driver.getCurrentUrl().equals(Const.HOME_LOGO_URL)
                || desktopCardLayout.isDisplayed();
        LOGGER.info("Verifying Home-page is opened: " + isHomePageOpened);
        return isHomePageOpened;
    }

    public WebElement getHomePageWebElement() {
        return desktopCardLayout;
    }
}