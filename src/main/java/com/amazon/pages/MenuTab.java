package com.amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuTab extends AbstractPage {

    @FindBy(xpath = "//*[@id='nav-xshop']//a[1]")
    private WebElement todaysDealsBtn;

    @FindBy(xpath = "//*[@class='hm-icon nav-sprite']")
    private WebElement filterMenuBtn;

    public MenuTab(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public FilterMenuPage clickFilterMenuBtn() {
        clickButton(filterMenuBtn, "filterMenuBtn");
        return new FilterMenuPage(driver);
    }

    public TodaysDealPage clickTodaysDealsBtn() {
        clickButton(todaysDealsBtn, "todaysDealsBtn");
        return new TodaysDealPage(driver);
    }
}