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

public class TC_03 {

    @Test
    public void findDriverName()  throws InterruptedException
    {
        // TODO Auto-generated method stub
        /*
         * * "Test Steps:
         * 1. Login to https://login.salesforce.com
         * 2. Click on the toggle menu button from the left corner
         * 3. Click View All and click Individuals from App Launcher
         * 4. Click on the Individuals tab
         * 5. Search the Individuals 'Kumar'
         * 6. Click on the Dropdown icon and Select Delete
         * 7.Click on the Delete option in the displayed popup window.
         * 8. Verify Whether Individual is Deleted using Individual last name"
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
        driver.findElement(By.xpath("//input[@part='input']")).sendKeys("individual");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement individual = driver.findElement(By.xpath("//span[@class='slds-truncate label-display']"));
        jsExecutor.executeScript("arguments[0].click();", individual);

        //5. Search the Individuals 'Kumar'
        driver.findElement(By.xpath("(//input[@type='search'])[2]")).sendKeys("Kumar");

        //6. Click on the Dropdown icon and Select Delete
        driver.findElement(By.xpath("//a[normalize-space()='Ganesh Kumar']/ancestor::tr/td[6]")).click();
        //JavascriptExecutor js = (JavascriptExecutor)driver;
        //js.executeScript("arguments[0].click();", java);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@title='Delete']"))));
        driver.findElement(By.xpath("//a[@title='Delete']")).click();

        //7.Click on the Delete option in the displayed popup window.
        driver.findElement(By.xpath("//span[text()='Delete']")).click();

        //8. Verify Whether Individual is Deleted using Individual last name"
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']"))));
        Assert.assertEquals(true, driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']")).getText().contains("Ganesh") );
        driver.quit();

    }

}