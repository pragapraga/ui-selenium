package com.govind;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC002_EditLegalEntity {

    WebDriver driver;
    String expectedLegalEntityName="Salesforce Automation by Govind";
    String status="Active";

    @Test
    public void findDriverName() throws InterruptedException {

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

        driver.findElement(By.xpath("//input[@aria-label='Search Recently Viewed list view.']")).sendKeys(expectedLegalEntityName,Keys.TAB,Keys.ENTER);

        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[normalize-space()='"+expectedLegalEntityName+"']/ancestor::tr/td[5]")).click();

        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[@title='Edit']")).click();


        driver.findElement(By.xpath("//input[@name='CompanyName']")).sendKeys("Testleaf");
        driver.findElement(By.xpath("//label[text()='Description']//following::div[1]/textarea")).sendKeys("SalesForce");
        WebElement statusElement=driver.findElement(By.xpath("//label[text()='Status']//following::div/lightning-base-combobox/div/div/button"));
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("arguments[0].click();", statusElement);

        driver.findElement(By.xpath("//span[text()='"+status+"']")).click();
        driver.findElement(By.xpath("//button[text()='Save']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[normalize-space()='"+expectedLegalEntityName+"']")).click();
        String expectedStatus=driver.findElement(By.xpath("//p[text()='Status']/following::p[1]")).getText();
        Assert.assertEquals(status, expectedStatus);
        driver.close();
    }

}