package com.nazreen;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_04 {

    @Test
    public void findDriverName()  throws InterruptedException
    {
        // TODO Auto-generated method stub
        /*
         * * "Test Steps:
         * 1. Login to https://login.salesforce.com
         * 2. Click on the toggle menu button from the left corner
         * 3. Click View All and click Individuals from App Launcher
         * 4. Click on the Dropdown icon in the Individuals tab
         * 5. Click on New Individual
         * 7.Select Salutation as 'Mr'
         * 8.Enter the first name as 'Ganesh'.
         * 9. Click on Save
         * 10. Verify the Alert message (Complete this field) displayed for the Last Name"
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
        Thread.sleep(1000);

        //5. Click on New
        driver.findElement(By.xpath("//div[text()='New']")).click();

        //7.Select Salutation as 'Mr'
        WebElement salutation = driver.findElement(By.xpath("(//a[@role='button'][normalize-space()='--None--'])[1]"));
        JavascriptExecutor js1 = (JavascriptExecutor)driver;
        js1.executeScript("arguments[0].click();", salutation);
        driver.findElement(By.xpath("(//a[text()='Mr.'])[1]")).click();

        //8.Enter the first name as 'Ganesh'
        driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Ganesh");

        //9. Click on Save
        driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();

        //10. Verify the Alert message (Complete this field) displayed for the Last Name"
        String expected = "Complete this field.";
        String actual = driver.findElement(By.xpath("//li[@class='form-element__help']")).getText();
        Assert.assertEquals(expected, actual);

    }

}