package com.assessment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class SurpriseAssessment {

    WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Test
    public void jiraTest(){
        driver.get("https://api-training.atlassian.net/");
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("hari.radhakrishnan@testleaf.com");
        driver.findElement(By.xpath("//button[@id='login-submit']")).click();
        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(password));
        password.sendKeys("India@123");
        driver.findElement(By.xpath("//button[@id='login-submit']")).click();
        WebElement project = driver.findElement(By.xpath("//a[contains(@href,'https://api-training.atlassian.net/browse/SDET')]"));
        wait.until(ExpectedConditions.visibilityOf(project));
        project.click();
        WebElement createBtn = driver.findElement(By.xpath("//button[@id='createGlobalItem']"));
        wait.until(ExpectedConditions.visibilityOf(createBtn));
        createBtn.click();
        Random ran = new Random();
        String storyName = "Praga SDET5 New Story"+ran.nextInt();
        driver.findElement(By.xpath("//input[@id='summary-field']")).sendKeys(storyName);
        WebElement ModalCreateBtn = driver.findElement(By.xpath("//button[@form='issue-create.ui.modal.create-form']"));
        wait.until(ExpectedConditions.visibilityOf(ModalCreateBtn));
        driver.findElement(By.xpath("//span[contains(text(),'Backlog')]")).click();
        driver.findElement(By.xpath("//input[@name='search']")).sendKeys(storyName);
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@aria-label,'Praga')]")).isDisplayed());
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }


}
