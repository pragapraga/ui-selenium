package com.poorani;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class CreateWorkTypeGroup extends Base {

	@Test
	public void workTypeGroupCreation() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement hamburgerMenu = driver.findElement(By.xpath("//div[@role = 'navigation']/button"));
		hamburgerMenu.click();
		driver.findElement(By.xpath("//button[text() = 'View All']")).click();
		driver.findElement(By.xpath("//input[contains(@placeholder, 'Search apps')]")).sendKeys("Work Type Groups");
		driver.findElement(By.xpath("//p/mark[text() = 'Work Type Groups']")).click();
		driver.findElement(By.xpath(
				"//div[@class='slds-context-bar__label-action slds-p-left--none slds-p-right--x-small']//a[@role='button']//lightning-primitive-icon//*[name()='svg']"))
				.click();
		
		WebElement groupButton = driver.findElement(By.xpath("//span[contains(text(), \"New Work Type Group\")]"));
		jsExecutor.executeScript("arguments[0].click();", groupButton);

		driver.findElement(By.xpath("//input[@type = 'text']")).sendKeys(groupName);
		driver.findElement(By.xpath("//button[text() = 'Save']")).click();
		
		
		if (driver.findElement(By.xpath("//div[@role = 'alertdialog']")).getText().equals("Work Type Group" +groupName+ "was created.")) {
			System.out.println("Verified");
		}

	}

}
