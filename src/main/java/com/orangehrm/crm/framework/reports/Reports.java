package com.orangehrm.crm.framework.reports;

import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

/**
 * Utility class for managing test reports using ExtentReports.
 * Provides methods to initialize, start, and stop reporting for automated test cases.
 * 
 * <p>This class uses the ExtentReports library to generate HTML reports for test execution.
 * It includes functionality to set up the report, create test logs, and flush the report data.</p>
 */
public class Reports {
	
	// Initialize all classes
	
	/** Represents the HTML reporter used to generate the report file. */
	public static ExtentHtmlReporter html; // white paper
	
	/** Represents the ExtentReports instance used to manage the report. */
	public static ExtentReports extent; // printer
	
	/** Represents the ExtentTest instance used to log test details. */
	public static ExtentTest logger; // ink
	
	/**
	 * Sets up the ExtentReports configuration and initializes the HTML reporter.
	 * 
	 * <p>This method is annotated with `@BeforeSuite` to ensure it runs before any test suite execution.
	 * It creates an HTML report file in the `Reports` directory under the user's working directory.</p>
	 */
	@BeforeSuite(alwaysRun = true)
	public static void setupReport() {
		html = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\Reports\\AutomationTestReport.html");
		extent = new ExtentReports();
		extent.attachReporter(html);
	}
	
	/**
	 * Starts reporting for a specific test case.
	 * 
	 * <p>This method creates a new test entry in the report using the provided test name.
	 * It initializes the `logger` object to log details for the test case.</p>
	 * 
	 * @param testName The name of the test case to be reported.
	 */
	public static void startReporting(String testName) {
		logger = extent.createTest(testName);
	}
	
	/**
	 * Stops reporting and flushes the report data to the file.
	 * 
	 * <p>This method ensures that all logged details are written to the report file.
	 * It should be called after all test cases have been executed.</p>
	 */
	public static void stopReporting() {
		extent.flush();
	}
}