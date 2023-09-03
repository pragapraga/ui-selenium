package com.gokul;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Case_TC001 {
    @Test
    public void createContactTest() throws InterruptedException{
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.navigate().to("https://login.salesforce.com");
        driver.findElement(By.id("username")).sendKeys("gokuls2381@gmail.com",Keys.TAB,"Roulette@123",Keys.TAB,Keys.ENTER);
        WebElement global = driver.findElement(By.xpath("//a[@class='globalCreateTrigger slds-button slds-button_icon slds-button_icon slds-button_icon-container slds-button_icon-small slds-global-actions__task slds-global-actions__item-action']"));
        global.click();
        driver.findElement(By.xpath("//ul/li/a/span[contains(text(),'New Case')]")).click();
        driver.findElement(By.xpath("//div[@title='Govind Raj']")).click();
        driver.findElement(By.xpath("//span[normalize-space()='Status']/parent::span/following-sibling::div//a")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Escalated']")).click();
        driver.findElement(By.xpath("//input[@class=' input']")).sendKeys("Testing",Keys.TAB,"Dummy",Keys.TAB,Keys.ENTER);
        Assert.assertEquals(true, driver.findElement(By.xpath("//div[@aria-label='Success']")).isDisplayed());
        driver.quit();
    }

}