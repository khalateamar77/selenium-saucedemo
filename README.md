# Selenium SauceDemo Automation Project

## Overview
This project is an automation framework built using Selenium WebDriver, TestNG, and Java to automate the testing of the SauceDemo application. It includes functional tests for login, product selection, cart operations, and checkout flows. Test reports and logs are generated automatically.

## Tech Stack Used
- **Programming Language:** Java
- **Automation Tool:** Selenium WebDriver
- **Test Framework:** TestNG
- **Build Tool:** Maven
- **Version Control:** Git / GitHub
- **Reporting:** Extent Reports / TestNG Reports
- **Bug Tracking:** JIRA (if applicable)

## Project Structure
```
selenium-saucedemo/
│
├─ src/main/java/         # Page Objects & Utilities
├─ src/test/java/         # Test Classes
├─ testdata/              # Test data files (Excel, CSV)
├─ reports/               # Extent Reports and TestNG reports
├─ TestScenarios.docx     # Test scenarios documentation
├─ BugReports.docx        # Bug reports (if any)
├─ pom.xml                # Maven dependencies
├─ README.md              # Project overview
```

## Prerequisites
1. Java JDK 8 or higher
2. Eclipse IDE or IntelliJ IDEA
3. Maven installed
4. Chrome/Firefox browser with WebDriver
5. Internet connection (for web automation)
6. Git (if cloning from GitHub)

## How to Run
1. Clone or download the repository:
```
git clone https://github.com/khalateamar77/selenium-saucedemo.git
```
2. Open Eclipse → File → Import → Existing Maven Project → Select cloned folder → Finish
3. To run all tests:
   - Open `testng.xml` → Right-click → Run As → TestNG Suite
4. To run individual tests:
   - Right-click the Java test class → Run As → Java Application

## Notes
- Test reports will be generated in the `reports/` or `test-output/` folder.
- Ensure browser drivers are up-to-date.
- Some tests may fail if the website under test is down or network is slow.
