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

public class TC003_CreateLegalEntityWithoutMandatoryFields {

    WebDriver driver;
    String actualAlertText="Review the following fields";

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

        driver.findElement(By.xpath("//input[@name='CompanyName']")).sendKeys("Testleaf");
        driver.findElement(By.xpath("//label[text()='Description']//following::div[1]/textarea")).sendKeys("SalesForce");
        WebElement statusElement=driver.findElement(By.xpath("//label[text()='Status']//following::div/lightning-base-combobox/div/div/button"));
        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("arguments[0].click();", statusElement);

        driver.findElement(By.xpath("//span[text()='Active']")).click();
        driver.findElement(By.xpath("//button[text()='Save']")).click();
        String expectedText=driver.findElement(By.xpath("//strong[text()='"+actualAlertText+"']")).getText();
        Assert.assertEquals(actualAlertText, expectedText);
        driver.close();
    }

}