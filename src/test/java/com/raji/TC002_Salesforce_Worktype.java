package com.raji;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Test;

public class TC002_Salesforce_Worktype {


    @Test
    public void findDriverName()  throws InterruptedException{

        // TODO Auto-generated method stub
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();


// Launch URL  //input[@id=""]   //input[@id="password"] //input[@id="Login"]
        driver.get("https://ionixx-dev-ed.develop.my.salesforce.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.id("username")).sendKeys("rajalakshmisdet@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Raji@3693");
        driver.findElement(By.id("Login")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath("//div[@class=\"slds-icon-waffle\"]")).click();
        driver.findElement(By.xpath("//button[contains(text(),'View All')]")).click();
        driver.findElement(By.xpath("//input[@class=\"slds-input\"]")).click();
        driver.findElement(By.xpath("//input[@class=\"slds-input\"]")).sendKeys("Work Types");
        driver.findElement(By.xpath("//p/mark[text() = 'Work Types']")).click();

        WebElement java = driver.findElement(By.xpath("//span[contains(text(),'Show Actions')]"));
        // Create a reference
        JavascriptExecutor js = (JavascriptExecutor)driver;

        // Call the JavascriptExecutor methods
        js.executeScript("arguments[0].click();", java);

        driver.findElement(By.xpath("//a[@title = 'Edit']")).click();

        driver.findElement(By.xpath("(//input[@data-aura-class=\"uiInputSmartNumber\"])[4]")).sendKeys("9");
        driver.findElement(By.xpath("(//input[@data-aura-class=\"uiInputSmartNumber\"])[5]")).clear();
        driver.findElement(By.xpath("(//input[@data-aura-class=\"uiInputSmartNumber\"])[5]")).sendKeys("6");
        driver.findElement(By.xpath(" (//span[contains(text(),'Save')])[3]")).click();

        String ActualText = driver.findElement(By.xpath("//li[contains(text(),'Timeframe Start')]")).getText();
        Assert.assertEquals("Enter a Timeframe End number that is greater than the Timeframe Start number.: Timeframe Start", ActualText);
        driver.quit();
    }
}

/*
 Test Steps:
1) Launch the app
2) Click Login
3) Login with the credentials
4) Click on the App Laucher Icon left to Setup
5) Click on View All
6) Click on Work Types
7) Click on the Arrow button at the end of the first result
8) Click on Edit
9) Enter Time Frame Start as '9'
10) Enter Time Frame End as '6'
11) Click on Save
12) Verify the error message

 */