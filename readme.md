# SauceDemo Automation Framework

## Overview
This repository contains an automation framework for testing the SauceDemo website. It includes automated tests for login, product sorting, cart operations, and checkout processes.

## Tech Stack Used
- Java 21
- Selenium WebDriver 4.x
- TestNG
- Maven
- Page Object Model (POM)
- ExtentReports for test reporting

## Prerequisites
1. Java 21 installed
2. Maven installed
3. Chrome browser and compatible ChromeDriver
4. Clone the repository

## How to Run
1. Navigate to the project root directory.
2. Run `mvn clean test` to execute all tests.
3. Test reports will be generated in the `test-output` folder (`ExtentReport.html`).

## Notes
- Screenshots are captured automatically for failed tests.
- Known issues: Checkout test may fail if the product is out of stock.
- All tests are implemented using POM for maintainability.

