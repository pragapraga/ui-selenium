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

public class TC_02 {

    @Test
    public void findDriverName()  throws InterruptedException
    {
        // TODO Auto-generated method stub
		/*

		 * "Test Steps:
1. Login to https://login.salesforce.com
2. Click on the toggle menu button from the left corner
3. Click View All and click Individuals from App Launcher
4. Click on the Individuals tab
5. Search the Individuals 'Kumar'
6. Click on the Dropdown icon and Select Edit
7. Select Salutation as 'Mr'
8. Enter the first name as 'Ganesh'.
9. Click on Save and Verify the first name as 'Ganesh'"
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

        //6. Click on the Dropdown icon and Select Edit
        Thread.sleep(1000);
        WebElement java = driver.findElement(By.xpath("//span[@class='slds-icon_container slds-icon-utility-down']//span[1]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", java);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@title='Edit']")).click();

        //7.Select Salutation as 'Mr'
        WebElement salutation = driver.findElement(By.xpath("(//a[@role='button'][normalize-space()='--None--'])[1]"));
        JavascriptExecutor js1 = (JavascriptExecutor)driver;
        js1.executeScript("arguments[0].click();", salutation);
        driver.findElement(By.xpath("(//a[text()='Mr.'])[1]")).click();

        //8.Enter the first name as 'Ganesh'
        driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Ganesh");

        //9. Click on Save and Verify the first name as 'Ganesh'"
        driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']"))));
        Assert.assertEquals(true, driver.findElement(By.xpath("//a[@data-refid='recordId']")).getText().contains("Ganesh") );
        driver.quit();
    }

}