package com.govind;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class TC005_DeleteLegalEntity {

    WebDriver driver;
    String expectedLegalEntityName="Salesforce Automation by Govind";
    String status="Delete";

    @Test
    public void findDriverName() throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        driver=new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("https://login.salesforce.com/");
        driver.findElement(By.id("username")).sendKeys("mgovindarajm@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Qwerty@5016");
        driver.findElement(By.id("Login")).click();
        driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
        driver.findElement(By.xpath("//button[text()='View All']")).click();

        WebElement element=driver.findElement(By.xpath("//p[text()='Legal Entities']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);

        driver.findElement(By.xpath("//input[@aria-label='Search Recently Viewed list view.']")).sendKeys(expectedLegalEntityName,Keys.TAB,Keys.ENTER);

        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[normalize-space()='"+expectedLegalEntityName+"']/ancestor::tr/td[5]")).click();

        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[@title='Delete']")).click();
        driver.findElement(By.xpath("//span[normalize-space()='Delete']")).click();
        int size=driver.findElements(By.xpath("//a[normalize-space()='"+expectedLegalEntityName+"']")).size();
        if (size==0) {
            System.out.println("No legal entity is found ,its deleted");
        }

    }

}