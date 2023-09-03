package com.govind;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001_CreateLegalEntity {

    WebDriver driver;
    String expectedLegalEntityName="Salesforce Automation by Govind";
    @Test
    public void findDriverName() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        driver=new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("https://login.salesforce.com/");
        driver.findElement(By.id("username")).sendKeys("mgovindarajm@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Qwerty@5016");
        driver.findElement(By.id("Login")).click();
        driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
        driver.findElement(By.xpath("//button[text()='View All']")).click();

        WebElement element=driver.findElement(By.xpath("//p[text()='Legal Entities']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);

        driver.findElement(By.xpath("//span[text()='Legal Entities']//following::one-app-nav-bar-item-dropdown//div/one-app-nav-bar-menu-button/a")).click();
        WebElement newLegalEntityElement=driver.findElement(By.xpath("//span[text()='New Legal Entity']"));
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("arguments[0].click();", newLegalEntityElement);

        driver.findElement(By.xpath("//label[text()='Name']//following::input[1]")).sendKeys(expectedLegalEntityName);
        driver.findElement(By.xpath("//button[text()='Save']")).click();
        String legalEntityName=driver.findElement(By.xpath("//div[text()='Legal Entity']//following::slot[1]/lightning-formatted-text")).getText();
        Assert.assertEquals(legalEntityName, expectedLegalEntityName);
        driver.close();
    }

}