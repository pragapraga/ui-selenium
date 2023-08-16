package com.tests.ui;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Forumla {
	
	/*
	 * * https://www.formula1.com/ DONE
* Access the top menu => Results DONE
* Driver Standings - DONE
* Check for the name of "Oscar Piastri"
* Verify if the position is less than 10. If yes, then print "<The Driver name> is within 10 positions"
* Else print "<Driver Name> is out of 10 position by <difference between the driver position minus 10>"
	 */
	
	WebDriver driver;

	@BeforeSuite
	public void setup() {
		System.setProperty("webdriver.chrome.driver",
				"/Users/pragadeeswarangnanasekaran/eclipse-workspace/uiauto/src/test/resources/chromedriver/chromedriver");
		driver = new ChromeDriver();
	}
	
	@Test
	public void testxpathandcss() {
		
		String currentDriverName = "Fernando";
		driver.get("https://www.formula1.com/");
		driver.findElement(By.cssSelector("button#truste-consent-button")).click();
		
		WebElement Results = driver.findElement(By.xpath("//a/span[contains(text(),'Results')]"));
		
		
		//Creating object of an Actions class
		Actions action = new Actions(driver);

		//Performing the mouse hover action on the target element.
		action.moveToElement(Results).perform();
		WebElement DriverStanding = driver.findElement(By.xpath("//div[@class='nav-header']//a[contains(@href,'driver-standings')]"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(DriverStanding));
		DriverStanding.click();
		
		WebElement driverNameElem = driver.findElement(By.xpath("(//span[contains(text(),'"+currentDriverName+"')])[1]"));
		String driverName = driverNameElem.getText();
		int position = Integer.parseInt(driver.findElement(By.xpath("(//span[contains(text(),'"+currentDriverName+"')])[3]//parent::a//parent::td//parent::tr/td[2]")).getText());
		System.out.println(position);
		
		/*WebElement driverNameElement = driver.findElement(By.xpath("//tr/td/a[contains(@data-ajax-url,'oscar')]//ancestor::tr/td[2]"));
		String driverName = driverNameElement.getText();
		wait.until(ExpectedConditions.invisibilityOf(driverNameElement));
		int position =Integer.parseInt(driver.findElement(By.xpath("//tr/td/a[contains(@data-ajax-url,'oscar')]//ancestor::tr/td[2]//ancestor::tr/td[2]")).getText());*/
		
		System.out.println(driverName);
		//String position = driver.findElement(By.xpath("//tr/td/a[contains(@data-ajax-url,'+\"+"currentDriverName+""\"+')]//ancestor::tr/td[2]")).getText();
		if(position<10) {
			System.out.println(currentDriverName+" is within 10 positions and the position is "+position);
		}
		else {
			System.out.println(currentDriverName+" is out of 10 position by "+(position-10));
		
		
	}

}
}
