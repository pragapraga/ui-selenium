package com.raji;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Test;

public class TC004_Salesforce_Worktype {
    @Test
    public void findDriverName()  throws InterruptedException{

        // TODO Auto-generated method stub

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();


// Launch URL  //input[@id=""]   //input[@id="password"] //input[@id="Login"]
        driver.get("https://ionixx-dev-ed.develop.my.salesforce.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.id("username")).sendKeys("rajalakshmisdet@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Raji@3693");
        driver.findElement(By.id("Login")).click();
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

        driver.findElement(By.xpath("//a[@title = 'Delete']")).click();
        driver.findElement(By.xpath("//Span[contains(text(),'Delete')]")).click();

        Assert.assertEquals(true, driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']")).isDisplayed());
        String ActualText = driver.findElement(By.xpath("//span[@data-aura-class=\"forceActionsText\"]")).getText();
        System.out.println(ActualText);
        Assert.assertEquals("Work Type \"Salesforce Project\" was deleted. Undo", ActualText);
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
8) Click on Delete
9) Click on Delete
10) Verify the message

 */