package com.orangehrm.crm.framework.listeners;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.orangehrm.crm.framework.base.BasePage;
import com.orangehrm.crm.framework.reports.Reports;
import com.orangehrm.crm.framework.web.commons.WebCommons;


public class TestListeners extends Reports implements ITestListener {

	public void onTestStart(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		startReporting(testName);	
		Reports.logger.info("Test Case Execution Started: " + testName);
	}

	
	public void onTestSuccess(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		Reports.logger.pass("Test Case Execution Passed: " + testName);
		stopReporting();
	}

	
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		Reports.logger.fail("Test Case Execution Failed: " + testName);
		Reports.logger.fail("Test Case Execution Failed due to Error: " + result.getThrowable().getLocalizedMessage());
		try {
			Reports.logger.addScreenCaptureFromPath(WebCommons.windowScreenshot(new BasePage().getDriver(), testName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		stopReporting();
	}

}
