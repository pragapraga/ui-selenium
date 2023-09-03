package com.mari;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Dashboard {

    ChromeDriver driver;
    WebDriverWait wait;
    String name="Salesforce Automation by Vignesh";

    @BeforeMethod
    public void Before() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver=new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://login.salesforce.com/");
        driver.findElement(By.id("username")).sendKeys("mgovindarajm@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Qwerty@5016");
        driver.findElement(By.id("Login")).click();
        WebElement toggle = driver.findElement(By.xpath("//div[@class='slds-icon-waffle']"));
        wait.until(ExpectedConditions.visibilityOf(toggle));
        //Click on the toggle menu button from the left corner
        driver.executeScript("arguments[0].click();", toggle);

        WebElement ViewAll = driver.findElement(By.xpath("//button[text()='View All']"));
        wait.until(ExpectedConditions.visibilityOf(ViewAll));
        ViewAll.click();
        driver.findElement(By.xpath("//button[contains(@class,'section-control slds-button')]//lightning-primitive-icon")).click();
        //Click View All and click Dashboards from App Launcher
        WebElement targetElement = driver.findElement(By.xpath("//p[text()='Dashboards']"));
        // Scroll to the target element using JavaScript
        driver.executeScript("arguments[0].scrollIntoView();", targetElement);
        wait.until(ExpectedConditions.visibilityOf(targetElement));
        // Click on the target element
        targetElement.click();
    }
    @Test(priority=1)
    public void create_New_Dashboard() throws InterruptedException
    {
        //Click on the New Dashboard option
        driver.findElement(By.xpath("//div[@title='New Dashboard']")).click();

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='dashboard']")));
        //Enter Name as 'Salesforce Automation by Your Name ' and Clickon Create.
        WebElement dashboardName = driver.findElement(By.xpath("//input[@id='dashboardNameInput']"));
        dashboardName.sendKeys(name);
        driver.findElement(By.xpath("//button[@id='submitBtn']")).click();
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='dashboard']")));
        driver.findElement(By.xpath("//button[text()='Save']")).click();
        driver.findElement(By.xpath("//button[text()='Done']")).click();
        driver.switchTo().defaultContent();
        WebElement toggle = driver.findElement(By.xpath("//span[text()='Dashboards']"));
        driver.executeScript("arguments[0].click();", toggle);
        String text = driver.findElement(By.xpath("//table[contains(@class,'slds-table slds-table_header-fixed')]//tbody//a")).getText();
        System.out.println(text);
        if (text.equals(name)) {
            System.out.println("The New Dashboard is created Successfully");
        }
        else {
            System.out.println("The New Dashboard is not created Successfully");
        }
    }
    @Test(priority=2)
    public void Edit_Dashboard() throws InterruptedException
    {
        String description="SalesForce";
        driver.findElement(By.xpath("//table[contains(@class,'slds-table slds-table_header-fixed')]//tr[1]//td[6]//button")).click();
        driver.findElement(By.xpath("//span[text()='Edit']")).click();
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='dashboard']")));
        Thread.sleep(2000);
        WebElement Properties = driver.findElement(By.xpath("(//button[@title='Edit Dashboard Properties'])[1]"));
        wait.until(ExpectedConditions.visibilityOf(Properties));
        Properties.click();
        WebElement namesss = driver.findElement(By.id("dashboardNameInput"));
        namesss.clear();
        namesss.sendKeys(name+" Updated");
        WebElement SalesForce = driver.findElement(By.xpath("//label[text()='Description']/following::input"));
        SalesForce.clear();
        SalesForce.sendKeys("SalesForce");
        driver.findElement(By.xpath("//button[@id='submitBtn']")).click();
        WebElement Done = driver.findElement(By.xpath("//button[text()='Done']"));
        wait.until(ExpectedConditions.visibilityOf(Done));
        driver.executeScript("arguments[0].click();", Done);
        WebElement save = driver.findElement(By.xpath("(//button[text()='Save'])[2]"));
        driver.executeScript("arguments[0].click();", save);
        String text = driver.findElement(By.xpath("//p[text()='"+description+"']")).getText();
        driver.switchTo().defaultContent();
        System.out.println(text);
        if (text.equals(description)) {
            System.out.println("The Dashboard is Edited Successfully");
        }
        else {
            System.out.println("The Dashboard is not Edited Successfully");
        }
    }
    @Test(priority=3)
    public void verifySubscribe() throws InterruptedException
    {
        driver.findElement(By.xpath("//input[contains(@class,'search-text-field slds-input')]")).sendKeys("Updated",Keys.ENTER);
        Thread.sleep(2000);
        WebElement button =driver.findElement(By.xpath("//table[contains(@class,'slds-table slds-table_header-fixed')]//tr[1]//td[6]//button"));
        button.click();
        wait.until(ExpectedConditions.elementToBeClickable(button));
        WebElement Subscribe = driver.findElement(By.xpath("//span[text()='Subscribe']"));
        wait.until(ExpectedConditions.visibilityOf(Subscribe));
        Subscribe.click();
        driver.findElement(By.xpath("//span[text()='Daily']")).click();
        driver.findElement(By.xpath("//span[text()='Save']")).click();
        Thread.sleep(2000);
        String text = driver.findElement(By.xpath("//table[contains(@class,'slds-table slds-table_header-fixed')]//tbody//a[contains(@title,'"+name+"')]//following::td[5]//span[1]")).getText();
        System.out.println(text);
        if (text.equals("True")) {
            System.out.println("Subscribed Successfully");
        }
        else {
            System.out.println("Not Subscribed");
        }
    }
    @Test(priority=4)
    public void sortOrder()
    {
        String Beforesort = driver.findElement(By.xpath("//span[text()='Dashboard Name']//following::span[1]")).getText();

        if (!Beforesort.contains("Descending")) {
            driver.findElement(By.xpath("//span[@title='Dashboard Name']")).click();
        }

        String Aftersort = driver.findElement(By.xpath("//span[text()='Dashboard Name']//following::span[1]")).getText();
        if (Aftersort.contains("Ascending")) {
            System.out.println("Sorted Ascending Successfully");
        }
        else {
            System.out.println("The Dashboard is not Ascending Sorted");
        }
    }
    @Test(priority=5)
    public void delete_Dashboard()
    {
        driver.findElement(By.xpath("//table[contains(@class,'slds-table slds-table_header-fixed')]//tbody//a[contains(@title,'"+name+"')]//following::td[6]//button")).click();

        driver.findElement(By.xpath("//span[text()='Delete']")).click();
        driver.findElement(By.xpath("(//span[text()='Delete'])[2]")).click();
        driver.findElement(By.xpath("//label[text()='Search recent dashboards...']/following::input")).sendKeys(name);

        String text = driver.findElement(By.xpath("//span[text()='No results found']")).getText();
        System.out.println(text);
        if (text.equals("No results found")) {
            System.out.println("The Dashboard is deleted Successfully");
        }
        else {
            System.out.println("The Dashboard is not deleted Successfully");
        }
    }
    @AfterMethod
    public void After() {
        driver.quit();
    }

}