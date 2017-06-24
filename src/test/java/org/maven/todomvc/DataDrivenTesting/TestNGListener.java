package org.maven.todomvc.DataDrivenTesting;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Created by user on 6/24/2017.
 */
public class TestNGListener implements ITestListener {

    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Test Case started and test case details are " + iTestResult.getName());
    }


    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test Case successfull and test case details are " + iTestResult.getName());
    }


    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test Case Failure and test case details are " + iTestResult.getName());
    }


    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Test Case Skipped and test case details are " + iTestResult.getName());
    }


    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    public void onStart(ITestContext iTestContext) {

    }


    public void onFinish(ITestContext iTestContext) {

    }
}
