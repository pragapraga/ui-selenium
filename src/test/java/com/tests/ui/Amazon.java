package com.tests.ui;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


/*
 * 1. Goto https://www.amazon.in/ - DONE
2. Cick on Books - DONE
3. In Search Bar, search for "Atomic Habits" - DONE
3. Print all the name of the books displayed - DONE
4. Click on the first result - DONE
5. Goto the result window and print the author name - 
 * 
 */
public class Amazon {
	WebDriver driver;

	@BeforeSuite
	public void setup() {
		System.setProperty("webdriver.chrome.driver",
				"/Users/pragadeeswarangnanasekaran/eclipse-workspace/uiauto/src/test/resources/chromedriver/chromedriver");
		driver = new ChromeDriver();
	}

	@Test
	public void testMe() throws InterruptedException {
		driver.get("https://www.amazon.in/");
		driver.findElement(By.cssSelector("div select.searchSelect")).click();
		Select dropDown = new Select(driver.findElement(By.cssSelector("div select.searchSelect")));
		dropDown.selectByVisibleText("Books");
		driver.findElement(By.cssSelector("input#twotabsearchtextbox")).sendKeys("Atomic Habits");
		driver.findElement(By.cssSelector("input#nav-search-submit-button")).click();
		int totalBooks = driver.findElements(By.xpath(("//div[@data-component-type='s-search-result']"))).size();
		System.out.println(totalBooks);
	
		List<String> listOfTitles = new ArrayList<String>();
		WebElement wdtitle=driver.findElement(By.xpath("(//div[@data-component-type='s-search-result']//div[contains(@class,'s-title-instructions-style')])[last()]/h2/a/span[1]"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(wdtitle));
		
		for (int i = 1; i < totalBooks; i++) {
			WebElement wdtitle_1=driver.findElement(By.xpath("(//div[@data-component-type='s-search-result']//div[contains(@class,'s-title-instructions-style')])["+i+"]/h2"));
			String fullTitle = wdtitle_1.getText();
			listOfTitles.add(fullTitle);
		}
		System.out.println(listOfTitles);
		System.out.println(listOfTitles.size());
		
		String parentHandle = driver.getWindowHandle();
		driver.findElement(By.xpath("(//div[@data-component-type='s-search-result']//div[contains(@class,'s-title-instructions-style')])[1]/h2/a/span[1]")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		String currentHandle="";
		Iterator iterator = windowHandles.iterator();
		while(iterator.hasNext()) {
			if(!(iterator.next()==parentHandle)) {
				currentHandle = (String) iterator.next();
			}
		}
		driver.switchTo().window(currentHandle);
		String bookTitle = driver.findElement(By.cssSelector("span#productTitle")).getText();
		Assert.assertEquals(bookTitle, listOfTitles.get(0));
	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}

}
