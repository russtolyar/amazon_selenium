package com.amazon.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LocationAlert extends AbstractPage {

    private static final Logger LOGGER = Logger.getLogger(LocationAlert.class);
    public static final String LOCATION_POP_UP_CSS_LOCATOR = ".a-section.glow-toaster.glow-toaster-theme-default.glow-toaster-slot-default.nav-coreFlyout.nav-flyout";


    @FindBy(css = ".a-button.a-spacing-top-base.a-button-base.glow-toaster-button.glow-toaster-button-dismiss")
    private WebElement dismissPopUp;

    @FindBy(css = ".a-section.glow-toaster.glow-toaster-theme-default.glow-toaster-slot-default.nav-coreFlyout.nav-flyout")
    private WebElement locationPopUp;

    public LocationAlert(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifyAlert() {
        if (!isLocAlertOpen()) {
            LOGGER.info("Location Alert is closed");
        } else {
            closeLocPopUp();
        }
    }

    public boolean isLocAlertOpen() {
        boolean isLocationAlertOpen = driver.findElements(By.cssSelector(LOCATION_POP_UP_CSS_LOCATOR)).size() > 0;
        LOGGER.info("Verifying if Location alert is Present - " + isLocationAlertOpen + ". If 'True' - then close it ");
        return isLocationAlertOpen;
    }

    public void closeLocPopUp() {
        clickButton(dismissPopUp, "dismissPopUp");
        if (!isLocAlertOpen()) {
            LOGGER.info("Location Alert closed");
        }
    }
}