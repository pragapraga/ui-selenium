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
	 * * https://www.formula1.com/ DONE Access the top menu => Results DONE Driver
	 * Standings - DONE Check for the name of "Oscar Piastri" Verify if the position
	 * is less than 10. If yes, then print
	 * "<The Driver name> is within 10 positions" Else print
	 * "<Driver Name> is out of 10 position by <difference between the driver position minus 10>"
	 */

	WebDriver driver;

	@BeforeSuite
	public void setup() {
		System.setProperty("webdriver.chrome.driver",
				"/Users/pragadeeswarangnanasekaran/eclipse-workspace/uiauto/src/test/resources/chromedriver/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://www.formula1.com/");

	}

	@Test
	public void testxpathandcss() throws InterruptedException {
		String currentDriverName = "Oscar";
		WebDriverWait waitBtn = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		int size = driver.findElements(By.tagName("iframe")).size();
		/*
		 * for(int i=0;i<size;i++) { driver.switchTo().frame(i); int
		 * total=driver.findElements(By.xpath("//button[contains(@title,'ACCEPT')]")).
		 * size(); System.out.println(total); driver.switchTo().defaultContent();}
		 */
		driver.switchTo().frame(1);
		WebElement acceptBtn = driver.findElement(By.xpath("//button[contains(@title,'ACCEPT')]"));
		waitBtn.until(ExpectedConditions.visibilityOf(acceptBtn));
		System.out.println("iFrame size::" + size);

		acceptBtn.click();
		waitBtn.until(ExpectedConditions.invisibilityOf(acceptBtn));
		Thread.sleep(5000);
		driver.switchTo().defaultContent();
		WebElement Results = driver.findElement(By.xpath("//a/span[contains(text(),'Results')]"));

		// Creating object of an Actions class
		Actions action = new Actions(driver);

		// Performing the mouse hover action on the target element.
		action.moveToElement(Results).perform();
		Thread.sleep(1000);
		WebElement driverStandingSubMenu = driver
				.findElement(By.xpath("//div[@class='nav-header']/a[contains(@href,'driver-standings')]/i"));
		waitBtn.until(ExpectedConditions.visibilityOf(driverStandingSubMenu));
		driverStandingSubMenu.click();

		waitBtn.until(ExpectedConditions.urlContains("drivers.html"));
		WebElement driverNameElem = driver
				.findElement(By.xpath("(//span[contains(text(),'" + currentDriverName + "')])[1]"));
		String driverName = driverNameElem.getText();
		int position = Integer.parseInt(driver.findElement(By.xpath(
				"(//span[contains(text(),'" + currentDriverName + "')])[3]//parent::a//parent::td//parent::tr/td[2]"))
				.getText());
		System.out.println(position);

		System.out.println(driverName);
		if (position < 10) {
			System.out.println(currentDriverName + " is within 10 positions and the position is " + position);
		} else {
			System.out.println(currentDriverName + " is out of 10 position by " + (position - 10));

		}
	}
}
