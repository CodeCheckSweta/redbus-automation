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

public class RedBusAutomation {
	public static void main(String[] args) {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("start-maximized");
		WebDriver driver = new ChromeDriver(chromeOptions);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));		
		driver.get("https://www.redbus.in/");
		
		By overlayLocator = By.xpath("//div[contains(@class,'srcDestWrapper')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(overlayLocator));
        
        WebElement sourceInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("srcinput")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].focus();", sourceInput); 
        
		selectLocation(driver, wait, "Mumbai");
		selectLocation(driver, wait, "Pune");
		
		By searchButtonLocator = By.xpath("//button[contains(@class,'searchButtonWrapper')]");
		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(searchButtonLocator));
		searchButton.click();
		
		By primoButtonLocator = By.xpath("//div[contains(text(),'Primo')]");
		WebElement primoButton = wait.until(ExpectedConditions.elementToBeClickable(primoButtonLocator));
		primoButton.click();		

		By tuppleWrapperLocator = By.xpath("//li[contains(@class,'tupleWrapper')]");
		By busesNameLocator = By.xpath(".//div[contains(@class,'travelsName')]");
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tuppleWrapperLocator));
		
		By eveningButtonLocator = By.xpath("//div[contains(text(),'18:00-24:00')]");
		WebElement eveningButton = wait.until(ExpectedConditions.elementToBeClickable(eveningButtonLocator));
		eveningButton.click();
		
		By subTitleLocator = By.xpath("//span[contains(@class,'subtitle')]");
		WebElement subTitle = null;
		if(wait.until(ExpectedConditions.textToBePresentInElementLocated(subTitleLocator,"buses"))) {
			subTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(subTitleLocator));
		}
		System.out.println(subTitle.getText());
		
		By endOfListLocator = By.xpath("//span[contains(text(),'End of list')]");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		int index = 0;
		int step = 5;
		
		while(true) {
			
			if (!driver.findElements(endOfListLocator).isEmpty()) {
		        break;
		    }
			
			List<WebElement> busesList = driver.findElements(tuppleWrapperLocator);
		    if (busesList.isEmpty()) {
		        break; 
		    }
		    
		    if (index >= busesList.size()) {
		        index = busesList.size() - 1;
		    }
		    
		    WebElement target = busesList.get(index);
		    js.executeScript(
		        "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
		        target
		    );
		    index += step;
			
		}
		List<WebElement> busesList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tuppleWrapperLocator));

		System.out.println("Total Number of buses: "+busesList.size());
		for(WebElement bus:busesList) {
			System.out.println(bus.findElement(busesNameLocator).getText());
		}
		driver.quit(); 
	}

	private static void selectLocation(WebDriver driver, WebDriverWait wait, String location) {
		WebElement searchTextBoxElement = driver.switchTo().activeElement();
		searchTextBoxElement.sendKeys(location);
		
		By searchCategory = By.xpath("//div[contains(@class,'searchCategory')]");
		List<WebElement> searchList = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(searchCategory,2));
		System.out.println(searchList.size());

		WebElement locationSearchResult = searchList.get(0);
		//Chaining of WebElements
		By locationNameLocator = By.xpath(".//div[contains(@class,'listHeader')]");
		List<WebElement> locationList = locationNameLocator.findElements(locationSearchResult);
		System.out.println(locationList.size());
		
		for(WebElement loc:locationList) {
			String lName = loc.getText();
			if(lName.equalsIgnoreCase(location)) {
				loc.click();
				break;
			}
		}
	}
}
