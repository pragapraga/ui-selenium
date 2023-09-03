package com.raji;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


public class TC001_Salesforce_Worktype {

    @Test
    public void findDriverName()  throws InterruptedException{
        // TODO Auto-generated method stub

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();



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

        driver.findElement(By.xpath("//div[@title='New']")).click();
        driver.findElement(By.xpath("//input[@class=' input']")).sendKeys("Salesforce Project");
        driver.findElement(By.xpath("//textarea[@class=' textarea']")).sendKeys("Specimen");
        driver.findElement(By.xpath("//input[@role=\"combobox\"]")).click();
        driver.findElement(By.xpath("//span[@title='New Operating Hours']")).click();
        driver.findElement(By.xpath("(//input[@class=' input'])[2]")).sendKeys("UK Shift");
        driver.findElement(By.xpath("(//span[contains(text(),'Save')])[5]")).click();
        driver.findElement(By.xpath("(//input[@class=\"input uiInputSmartNumber\"])[1]")).sendKeys("7");
        driver.findElement(By.xpath("(//span[contains(text(),'Save')])[3]")).click();
        //Thread.sleep(500);
        String ActualText = driver.findElement(By.xpath("(//span[contains(text(),'Salesforce Project')])[3]")).getText();
        Assert.assertEquals("Salesforce Project", ActualText);

        Assert.assertEquals(true, driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']")).isDisplayed());
        String ActualText2 = driver.findElement(By.xpath("//span[@data-aura-class=\"forceActionsText\"]")).getText();
        System.out.println(ActualText2);
        Assert.assertEquals("Work Type \"Salesforce Project\" was created.", ActualText2);
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
7) Click on New
8) Enter Work Type Name as 'Salesforce Project'
9) Enter Description as 'Specimen'
10) Create new operating hours by Entering a name as 'UK Shift'
11) Enter Estimated Duration as '7'
12 Click on Save
13 Verify the Created message


		 *
		 * */

