package com.anand;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CreateNewOpportunity {
	/*
	 * Test Steps:
1. Login to https://login.salesforce.com
2. Click on toggle menu button from the left corner
3. Click view All and click Sales from App Launcher
4. Click on Opportunity tab
5. Click on New button
6. Enter Opportunity name as 'Salesforce Automation by *Your Name*,Get the text and Store it
7. Choose close date as Today
8. Select 'Stage' as Need Analysis
9. click Save and VerifyOppurtunity Name

	 *
	 */

    WebDriver driver;
    private String username ="praga@praga.com";
    private String password="Hello24Hello";
    //nazreenf@testleaf.com
    //Testleaf@1234



    @BeforeTest
    public void lanuchChrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        //chromeoption.addArguments(null)
        // driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://qeagle-d-dev-ed.develop.my.salesforce.com");

    }

    @Test
    public void login() {
        WebElement name =driver.findElement(By.xpath("//input[@name='username']"));
        name.sendKeys(username);
        WebElement password1 =driver.findElement(By.xpath("//input[@id='password']"));
        password1.sendKeys(password);
        driver.findElement(By.id("Login")).click();
        driver.manage().timeouts().implicitlyWait( Duration.ofSeconds(5));
        driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
        driver.findElement(By.xpath("//button[text()='View All']")).click();
        driver.findElement(By.xpath("//p[text()='Sales']")).click();
        ////span[normalize-space()='Opportunities']
        //driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
        WebElement element = driver.findElement(By.xpath("//span[normalize-space()='Opportunities']"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
        driver.findElement(By.xpath("//div[@title='New']")).click();
        //driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
        WebElement enterName = driver.findElement(By.xpath("//label[text()='Opportunity Name']/following::input"));
        enterName. sendKeys("Salesforce Automation by Anand");
        driver.findElement(By.xpath("//label[text()='Close Date']/following::input")).sendKeys("27.8.2023");
        driver.findElement(By.xpath("(//div[@class='slds-form-element__control'])[3]")).click();
        driver.findElement(By.xpath("//span[@title='Needs Analysis']")).click();

        //driver.findElement(By.xpath("//button[@id='combobox-button-1832']")).click();
        driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
        WebElement findName = driver.findElement(By.xpath("//lightning-formatted-text[@slot='primaryField']"));
        String attribute = findName.getText();
        if(attribute.endsWith("Anand")) {
            System.out.println("Created by Anand");

        }else {
            System.out.println("Invalid user");
        }
    }

}