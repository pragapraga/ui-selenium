package com.govind;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC004_LegalEntityModifiedDate {

    WebDriver driver;
    String expectedLegalEntityName="Salesforce Automation by Govind";
    @Test
    public void findDriverName() {

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

        driver.findElement(By.xpath("//span[normalize-space()='Last Modified Date']//following::div[1]/button")).click();

        List<WebElement> recordRows = driver.findElements(By.xpath("//table[@data-aura-class='uiVirtualDataTable']/tbody/tr"));
        boolean isAscending = true;

        for (int i = 1; i < recordRows.size(); i++) {
            WebElement currentRow = recordRows.get(i);
            WebElement previousRow = recordRows.get(i - 1);

            // Extract the Last Modified timestamps from the current and previous rows
            String currentTimestamp = currentRow.findElement(By.xpath(".//td[3]")).getText();
            String previousTimestamp = previousRow.findElement(By.xpath(".//td[3]")).getText();

            System.out.println("currentTimestamp :" + currentTimestamp);
            System.out.println("previousTimestamp :" + previousTimestamp);

            if (currentTimestamp.compareTo(previousTimestamp) < 0) {
                isAscending = false;
                break;
            }
        }

        if (isAscending) {
            System.out.println("Records are displayed in ascending order by Last Modified timestamp.");
        } else {
            System.out.println("Records are not displayed in ascending order by Last Modified timestamp.");
        }

        // Close the browser
        driver.quit();
    }
}