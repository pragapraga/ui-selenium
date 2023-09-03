package com.poorani;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteWorkTypeGroup extends Base {

	@Test
	public void deleteWorkTypeGroup()
	{
		WebElement hamburgerMenu = driver.findElement(By.xpath("//div[@role = 'navigation']/button"));
		hamburgerMenu.click();
		driver.findElement(By.xpath("//button[text() = 'View All']")).click();
		driver.findElement(By.xpath("//input[contains(@placeholder, 'Search apps')]")).sendKeys("Work Type Groups");
		driver.findElement(By.xpath("//p/mark[text() = 'Work Type Groups']")).click();
		driver.findElement(By.xpath("//input[@type='search' and @placeholder = 'Search this list...']")).sendKeys(groupName);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		
		WebElement arrow = driver.findElement(By.xpath("//a[normalize-space()='Salesforce Automation']/ancestor::tr/td[5]//a"));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", arrow);
		driver.findElement(By.xpath("//a[@title = 'Delete']")).click();
		String actual = driver.findElement(By.xpath("//div[@role = 'alertdialog']")).getText();
		
		Assert.assertEquals(actual, "Work Type Group" +editGroupName+ "was deleted.");
	}

}
