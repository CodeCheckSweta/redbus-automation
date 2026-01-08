<h1 align="left"> 🚍 RedBus Automation Framework </h1>  <br>
This project automates key user flows on the RedBus website using Selenium WebDriver with Java. It is designed as a practice/portfolio project to demonstrate handling dynamic elements, filters, and infinite scrolling on a modern web application.
​

# Features
Select source and destination cities dynamically (e.g. Mumbai → Pune).                          <br>
Handle auto‑suggest/location dropdowns with explicit waits.                                     <br>
Apply filters (e.g. Primo, departure time slots like 18:00–24:00).                              <br>
Scroll through dynamically loaded bus results and detect “End of list”.                         <br>
Capture and print total number of buses and their operator names for the selected criteria.     <br>

# Tech Stack

Language: Java  <br>
Test Framework: TestNG  <br>
Automation Library: Selenium WebDriver  <br>
Build Tool: Maven  <br>
Browser: Chrome 

# Project Structure

redbus-automation/                        <br>
├─ src/                                   <br>
│  └─ main/                               <br>
│     └─ java/                            <br>
│        └─ com/                          <br>
│           └─ redBus/                    <br>
│              └─ RedBusAutomation.java   <br>
├─ pom.xml / build.gradle                 <br>
├─ README.md                              <br>
└─ .gitignore                             
