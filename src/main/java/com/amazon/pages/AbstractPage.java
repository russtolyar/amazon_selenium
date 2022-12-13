package com.amazon.pages;

import com.amazon.services.NavigationService;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractPage {

    private static final Logger LOGGER = Logger.getLogger(AbstractPage.class);

    //    WebDriver driver; // this is needed for starting test in one (chrome) browser in one thread
    public RemoteWebDriver driver = null;

    @FindBy(xpath = "//span[@class='a-color-state a-text-bold']")
    private WebElement searchElementIndicator;


    public AbstractPage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickButton(WebElement webElement, String btn) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(webElement)).click();
        LOGGER.info("Element Abstract " + btn + " is clicked");
    }

    public void sendKeysMethod(WebElement whereTo, String inputItem) {
        whereTo.sendKeys(inputItem, Keys.ENTER);
        LOGGER.info("key " + inputItem + " is inputed in search field and entered");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(searchElementIndicator));
        elementPresentAndContainsTextCheck(whereTo, inputItem);
    }

    public void elementPresentAndContainsTextCheck(WebElement webElement, String text) {
        boolean presentAndContains = webElement.isDisplayed() && webElement.getText().equals(text);
        if (presentAndContains) {
            LOGGER.info("key " + text + " is present on the page ");
        } else {
            LOGGER.info("key " + text + " is NOT present on the page! ");
        }
    }

    public void goToHomePage(RemoteWebDriver driver) {
        NavigationService.goHome(driver);
    }
}