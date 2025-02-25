# Nixonex
Project Overview

This project involves automating the entire commerce flow of the Demo Blaze e-commerce platform using Selenium WebDriver and TestNG in Java. The goal is to ensure the website's key functionalities work correctly.
Features
Automated Web testing using Selenium WebDriver
Implements Page Object Model (POM) for better maintainability
Generates Extent Reports with screenshots for failed test cases
Utilizes Hooks  for efficient test management
Tech Stack
Language: Java
Web Automation: Selenium WebDriver
IDE: IntelliJ IDEA
Build Tool: Maven
Unit Testing Framework: TestNG
Reporting: Extent Reports, Default Cucumber HTML Reports





Folder Structure
Nixonex
│── .idea
│── screenshots
│── src
│   ├── main
│   │   ├── java
│   │   │   ├── Config
│   │   │   │   ├── config.properties
│   │   │   ├── Pages
│   │   │   │   ├── CheckoutPage.java
│   │   │   │   ├── LoginPage.java
│   │   │   │   ├── ProductPage.java
│   │   │   │   ├── RegistrationPage.java
│   ├── test
│   │   ├── java
│   │   │   ├── Base
│   │   │   │   ├── BaseClass.java
│   │   │   ├── Tests
│   │   │   │   ├── AddToCartTest.java
│   │   │   │   ├── CheckoutTest.java
│   │   │   │   ├── LoginTest.java
│   │   │   │   ├── RegistrationTest.java
│── target
│── .gitignore
│── ExtentReport.html
│── pom.xml
│── testng.xml
│── External Libraries
│── Scratches and Consoles


Configuration
Update config.properties for signup, login and shipping details.


Running Tests
Execute the entire project  from testng.xml or with the following command

mvn test -DsuiteXmlFile=testng.xml

Extent Report
The extent report can be found with the name `ExtentReport.html` where each test will have its respective screenshot attached.
