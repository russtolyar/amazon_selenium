package com.amazon.services;

import com.amazon.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Locale;

public class CheckMethods extends AbstractPage {

    public static final String GOOD_DESIGN_LOCATOR = "//*[@id='nav-main']";

    public CheckMethods(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Verifying if Any element of 'whatList' present in All elements of 'whereList' up to element number 'numElementsToCheck'
     *
     * @param whereList
     * @param numElementsToCheck
     * @param whatList
     * @return
     */
    public static boolean isElementsPresentInList(List<String> whereList, int numElementsToCheck, List<String> whatList) {
        int countPresent = 0;
        for (int i = 0; i < numElementsToCheck; i++) {
            boolean elOfWhatPresentInElOfWhere = false;

            for (int j = 0; j < whatList.size(); j++) {
                String a = whereList.get(i).toLowerCase(Locale.ROOT);
                String signdisc = whatList.get(j);
                if (!a.contains(signdisc)) {
                    continue;
                } else {
                    elOfWhatPresentInElOfWhere = true;
                    countPresent++;
                    break;
                }
            }
        }
        return countPresent == numElementsToCheck;
    }

    /**
     * Verifying if element 'whatS' present in All elements of 'whereList'
     *
     * @param whereList
     * @param whatS
     * @return
     */
    public static boolean isStringPresentInList(List<String> whereList, String whatS) {
        boolean isPresent = false;

        for (String s : whereList) {
            if (s.toLowerCase(Locale.ROOT).contains(whatS)) {
                isPresent = true;
            } else {
                return isPresent;
            }
        }
        return isPresent;
    }

    /**
     * Verifying if ALL element of 'whatArr' present in All elements of 'whereList'
     *
     * @param whereList
     * @param whatArr
     * @return
     */
    public static boolean areAllElementsContainAllElements(List<String> whereList, String[] whatArr) {
        boolean whereListContainWhatArrEl = false;
        for (String s : whereList) {
            for (int i = 0; i < whatArr.length; i++) {

                if (s.toLowerCase(Locale.ROOT).contains(whatArr[i].toLowerCase(Locale.ROOT))) {
                    whereListContainWhatArrEl = true;
                    continue;
                } else {
                    return whereListContainWhatArrEl;
                }
            }
        }
        return whereListContainWhatArrEl;
    }

    /**
     * Verify if page design is incorrect and no menuTab(visible)
     *
     * @return
     */
    public boolean isWrongDesign() {
        return driver.findElements(By.xpath(GOOD_DESIGN_LOCATOR)).size() < 1;
    }
}