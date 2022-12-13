package com.amazon.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTestRunAttempts implements IRetryAnalyzer {
    /**
     * This class set the number of test starts expecting test succeed before reporting of test fail
     * annotation above needed test should be --- @Test(retryAnalyzer = RetryTestRunAttempts.class)
     */
    private int actualRetry = 0;
    private static final int MAX_RETRY = 3;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (actualRetry < MAX_RETRY) {
            actualRetry++;
            return true;
        } else {
            return false;
        }
    }
}