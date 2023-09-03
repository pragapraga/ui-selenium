package com.poorani;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class EditWorkTypeGroup extends Base{
	
	@Test
	public void editWorkTypeGroup() {
	JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	WebElement hamburgerMenu = driver.findElement(By.xpath("//div[@role = 'navigation']/button"));
	hamburgerMenu.click();
	driver.findElement(By.xpath("//button[text() = 'View All']")).click();
	driver.findElement(By.xpath("//input[contains(@placeholder, 'Search apps')]")).sendKeys("Work Type Groups");
	driver.findElement(By.xpath("//p/mark[text() = 'Work Type Groups']")).click();
	driver.findElement(By.xpath("//input[@type='search' and @placeholder = 'Search this list...']")).sendKeys(groupName);
	Actions action = new Actions(driver);
	action.sendKeys(Keys.ENTER).build().perform();
		
	WebElement arrow = driver.findElement(By.xpath("//a[normalize-space()='Salesforce Automation']/ancestor::tr/td[5]//a"));
	jsExecutor.executeScript("arguments[0].click();", arrow);
	
	WebElement edit = driver.findElement(By.xpath("//a[@title = 'Edit']"));
	jsExecutor.executeScript("arguments[0].click();", edit);
	WebElement textBox = driver.findElement(By.xpath("//input[@name = 'Name']"));
	textBox.clear();
	textBox.sendKeys("Automation");
	WebElement groupType = driver.findElement(By.xpath("//button[@role = 'combobox']"));
	jsExecutor.executeScript("arguments[0].click();", groupType);
	driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value = 'Capacity']")).click();
	driver.findElement(By.xpath("//button[text() = 'Save']")).click();
	
	if (driver.findElement(By.xpath("//div[@role = 'alertdialog']")).getText().equals("Work Type Group" +editGroupName+ "was saved.")) {
		System.out.println("Verified");
	}
	
	

}
}
