package com.poorani;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class SortOrderByName extends Base {
	
	@Test
	public void checkSorting()
	{
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement hamburgerMenu = driver.findElement(By.xpath("//div[@role = 'navigation']/button"));
		hamburgerMenu.click();
		driver.findElement(By.xpath("//button[text() = 'View All']")).click();
		driver.findElement(By.xpath("//input[contains(@placeholder, 'Search apps')]")).sendKeys("Work Type Groups");
		driver.findElement(By.xpath("//p/mark[text() = 'Work Type Groups']")).click();
		WebElement sort = driver.findElement(By.xpath("//span[@title = 'Work Type Group Name']"));
		jsExecutor.executeScript("arguments[0].click();", sort);
		List<WebElement> groupNames = driver.findElements(By.xpath("//span[@title = 'Work Type Group Name']/following::tr/th/span"));
		
		List<String> getText = new ArrayList<>();
		for (WebElement groupNameElement : groupNames) {
			getText.add(groupNameElement.getText());
		}
		
		List<String> sortedGroupNames = new ArrayList<>(getText);
		Collections.sort(sortedGroupNames);
		
		if (getText.equals(sortedGroupNames)) {
		    System.out.println("Displayed in ascending order.");
		} else {
		    System.out.println("Not displayed in ascending order.");
		}
	}

}
