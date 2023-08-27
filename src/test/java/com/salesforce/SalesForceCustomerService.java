package com.salesforce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class SalesForceCustomerService {
    ChromeDriver driver;
    ChromeOptions Coptions;

    @BeforeMethod
    public void setUp() {

        Coptions = new ChromeOptions().addArguments("--disable-notifications");
        driver = new ChromeDriver(Coptions);
        driver.get("https://login.salesforce.com");
        //driver.get("https://qeagle-d-dev-ed.develop.my.salesforce.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        //WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("Login"));

        username.sendKeys("praga@praga.com");
        password.sendKeys("An$5bawled");
        loginButton.click();

    }

    @Test
    public void loginAndTestCreateTask() {
        driver.findElement(By.xpath("//button[@title='Learn More']")).click();
        System.out.println(driver.getTitle());
        Set<String> windows = driver.getWindowHandles();
        List<String> listOfWindows = new ArrayList<>(windows);
        driver.switchTo().window(listOfWindows.get(1));
        driver.findElement(By.xpath("//button[text()='Confirm']")).click();
    }
}
