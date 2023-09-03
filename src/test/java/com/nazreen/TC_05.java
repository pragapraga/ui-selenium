package com.nazreen;

import java.time.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_05 {

    @Test
    public void findDriverName()  throws InterruptedException
    {
        // TODO Auto-generated method stub
        /*
         *
         * * "Test StepS:
         * 1. Login to https://login.salesforce.com
         * 2. Click on the toggle menu button from the left corner
         * 3. Click View All and click Individuals from App Launcher
         * 4. Click on the Individuals tab
         * 5. Click the sort arrow in the Name
         * 6. Verify the Individuals displayed in ascending order by Name."
         * */

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

        //5. Click the sort arrow in the Name
        JavascriptExecutor jsExecutor1 = (JavascriptExecutor) driver;
        WebElement name = driver.findElement(By.xpath("//span[@title='Name']"));
        jsExecutor1.executeScript("arguments[0].click();", name);

        //6. Verify the Individuals displayed in ascending order by Name."
        List<WebElement> beforeSort = driver.findElements(By.xpath("//tbody/tr/th//a"));
        List<String> before = new ArrayList<>();
        for (WebElement webElement : beforeSort)
        {
            before.add(webElement.getText());
        }
        Collections.sort(before);
        WebElement sort = driver.findElement(By.xpath("//span[@title='Name']"));
        driver.executeScript("arguments[0].click();", sort);
        Thread.sleep(2000);
        List<WebElement> afterSort = driver.findElements(By.xpath("//tbody/tr/th//a"));
        List<String> after = new ArrayList<>();
        for (WebElement webElement : afterSort)
        {
            after.add(webElement.getText());
        }
        Assert.assertEquals(after, before);
        driver.quit();

    }

}