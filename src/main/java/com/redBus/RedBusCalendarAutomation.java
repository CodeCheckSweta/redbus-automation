package com.redBus;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RedBusCalendarAutomation {
	public static void main(String[] args) throws InterruptedException {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("start-maximized");
		chromeOptions.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(chromeOptions);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));		
		driver.get("https://www.redbus.in/");
		
		By overlayLocator = By.xpath("//div[contains(@class,'srcDestWrapper')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(overlayLocator));
        
        WebElement sourceInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("srcinput")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].focus();", sourceInput); 
	
		WebElement searchTextBoxElement = driver.switchTo().activeElement();
		searchTextBoxElement.sendKeys("Kolkata");
		
		By searchCategory = By.xpath("//div[contains(@class,'searchCategory')]");
		List<WebElement> searchList = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(searchCategory,2));
		System.out.println(searchList.size());

		WebElement locationSearchResult = searchList.get(0);
		By locationNameLocator = By.xpath(".//div[contains(@class,'listHeader')]");
		List<WebElement> locationList = locationNameLocator.findElements(locationSearchResult);
		System.out.println(locationList.size());
		
		for(WebElement loc:locationList) {
			String lName = loc.getText();
			if(lName.equalsIgnoreCase("Kolkata")) {
				loc.click();
				break;
			}
		}
		
		WebElement toTextBox = driver.switchTo().activeElement();
		toTextBox.sendKeys("Delhi");
		By toSearchCategory = By.xpath("//div[contains(@class,'searchCategory')]");
		List<WebElement> toSearchList = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(toSearchCategory,2));
		System.out.println(toSearchList.size());
		
		WebElement toLocation = toSearchList.get(0);
		By toLocationNameLocator = By.xpath(".//div[contains(@class,'listHeader')]");
		List<WebElement> toLocationList = toLocationNameLocator.findElements(toLocation);
		System.out.println(toLocationList.size());
		
		for(WebElement loc:toLocationList) {
			String lName = loc.getText();
			if(lName.equalsIgnoreCase("delhi")) {
				loc.click();
				break;
			}
		}
	}
}
