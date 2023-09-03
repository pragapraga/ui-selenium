package com.nazreen;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_01 {

    @Test
    public void findDriverName()  throws InterruptedException
    {
        // TODO Auto-generated method stub
        /*
         *
         * * "Test Steps:
         * 1. Login to https://login.salesforce.com
         * 2. Click on the toggle menu button from the left corner
         * 3. Click View All and click Individuals from App Launcher
         * 4. Click on the Dropdown icon in the Individuals tab
         * 5. Click on New Individual
         * 6. Enter the Last Name as 'Kumar'
         * 7.Click save and verify Individuals Name"
         *
         * DOM Freeze : setTimeout(function() {debugger;}, 3000);
         */
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        //1. Login to https://login.salesforce.com
        driver.get("https://login.salesforce.com/");
        driver.findElement(By.id("username")).sendKeys("mgovindarajm@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Qwerty@5016");
        driver.findElement(By.xpath("//input[@id='Login']")).click();

        //2. Click on the toggle menu button from the left corner
        driver.findElement(By.className("slds-icon-waffle")).click();

        //3. Click View All and click Individuals from App Launcher
        driver.findElement(By.xpath("//button[@class='slds-button']")).click();

        driver.findElement(By.xpath("(//input[@type='search'])[3]")).sendKeys("Individuals");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement individual = driver.findElement(By.xpath("//p[@class='slds-truncate']"));
        jsExecutor.executeScript("arguments[0].click();", individual);
        Thread.sleep(1000);

        //5. Click on New
        driver.findElement(By.xpath("//div[text()='New']")).click();

        //6. Enter the Last Name as 'Kumar'
        driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("Kumar");
        driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']"))));
        Assert.assertEquals(true, driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']")).getText().contains("Kumar"));
        driver.quit();

    }

}