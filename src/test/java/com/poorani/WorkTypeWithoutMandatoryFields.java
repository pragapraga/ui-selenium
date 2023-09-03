package com.poorani;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WorkTypeWithoutMandatoryFields extends Base {

	@Test
	public void withoutMandatoryFields() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement hamburgerMenu = driver.findElement(By.xpath("//div[@role = 'navigation']/button"));
		hamburgerMenu.click();
		driver.findElement(By.xpath("//button[text() = 'View All']")).click();
		driver.findElement(By.xpath("//input[contains(@placeholder, 'Search apps')]")).sendKeys("Work Type Groups");
		driver.findElement(By.xpath("//p/mark[text() = 'Work Type Groups']")).click();
		driver.findElement(By.xpath(
				"//div[@class='slds-context-bar__label-action slds-p-left--none slds-p-right--x-small']//a[@role='button']//lightning-primitive-icon//*[name()='svg']"))
				.click();

		WebElement groupButton = driver.findElement(By.xpath("//span[text() = 'New Work Type Group']"));
		jsExecutor.executeScript("arguments[0].click();", groupButton);

		driver.findElement(By.xpath("//input[@type = 'text']")).sendKeys(Keys.TAB, editGroupName);

		driver.findElement(By.xpath("//button[@role = 'combobox']")).click();
		driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value = 'Capacity']")).click();
		WebElement checkCondition = driver.findElement(By.xpath("//div[text() = 'Complete this field.']"));
		boolean flag = checkCondition.isDisplayed();
		Assert.assertTrue(flag);
	}
}
