package com.salesforce;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;


public class SalesforceTask {

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
        WebElement menu = driver.findElement(By.cssSelector(".slds-icon-waffle"));
        menu.click();
        driver.findElement(By.xpath("//lightning-button")).click();
        driver.findElement(By.xpath("//input[@part='input']")).sendKeys("Tasks");
        WebElement dashLink = driver.findElement(By.xpath("//lightning-formatted-rich-text//mark"));
        dashLink.click();
        driver.findElement(By.xpath("(//div[@class='uiMenu']//a[contains(@class,'slds-button')])[3]")).click();
        // driver.findElement(By.xpath("//div[@title='New Task']")).click(); Faced  org.openqa.selenium.ElementClickInterceptedException
        WebElement newTaskMenu = driver.findElement(By.xpath("//div[contains(@class,'actionMenu')]//a"));
        //JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.executeScript("arguments[0].click()", newTaskMenu);
        Random random = new Random();
        driver.findElement(By.xpath("(//div[@class='slds-form-element__control']//input[@role='combobox' and @type='text'])[2]")).sendKeys("Bootcamp" + random.nextInt());
        WebElement dropDown = driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Task.Status']//a"));
        driver.executeScript("arguments[0].click()", dropDown);
        WebElement dropDownInput = driver.findElement(By.xpath("//div[@class='select-options']//a[contains(text(),'Waiting')]"));
        driver.executeScript("arguments[0].click()", dropDownInput);
        //div[contains(@class,'forceVirtualRecordList')]//span[@data-aura-class='uiOutputText' and contains(text(),'Bootcamp')]
        driver.findElement(By.xpath("//button/span[text()='Save']")).click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        WebElement toastMessage = driver.findElement(By.xpath("//div[@role='alertdialog' and @data-key='success']"));
        wait.until(ExpectedConditions.visibilityOf(toastMessage));
        boolean isSuccessFlag = toastMessage.isDisplayed();
        Assert.assertTrue(isSuccessFlag);
    }
//setTimeout(function(){debugger;},5000);

    @Test
    public void EditTask() throws InterruptedException{
        WebElement menu = driver.findElement(By.cssSelector(".slds-icon-waffle"));
        menu.click();
        driver.findElement(By.xpath("//lightning-button")).click();
        driver.findElement(By.xpath("//input[@part='input']")).sendKeys("Tasks");
        WebElement dashLink = driver.findElement(By.xpath("//lightning-formatted-rich-text//mark"));
        dashLink.click();
        driver.findElement(By.xpath("//span[contains(@class,'slds-grow')]//span[@class='uiOutputText']")).click();
        driver.findElement(By.xpath("//button[@title='Edit Due Date']")).click();
        driver.findElement(By.xpath("//lightning-datepicker//input")).sendKeys("27.8.2023");
        WebElement priorityDropDown = driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Task.Priority']//a"));
        driver.executeScript("arguments[0].click()", priorityDropDown);
        WebElement priorityDropDownOption_low = driver.findElement(By.xpath("//div[@class='select-options']//a[contains(text(),'Low')]"));
        driver.executeScript("arguments[0].click()", priorityDropDownOption_low);
        driver.findElement(By.xpath("//button/span[text()='Save']")).click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        WebElement priority = driver.findElement(By.xpath("//span[contains(text(),'Low')]"));
        wait.until(ExpectedConditions.visibilityOf(priority));
        boolean isLowFlag = priority.isDisplayed();
        Assert.assertTrue(isLowFlag);
    }

    @Test
    public void deleteTask(){
        WebElement menu = driver.findElement(By.cssSelector(".slds-icon-waffle"));
        menu.click();
        driver.findElement(By.xpath("//lightning-button")).click();
        driver.findElement(By.xpath("//input[@part='input']")).sendKeys("Tasks");
        driver.manage().window().maximize();
        WebElement dashLink = driver.findElement(By.xpath("//lightning-formatted-rich-text//mark"));
        dashLink.click();
        WebElement deleteMenu = driver.findElement(By.xpath("//li[contains(@data-target-reveals,'sfdc:StandardButton.Task.Delete')]"));
        deleteMenu.click();
        WebElement deleteMenuItem = driver.findElement(By.xpath("//a[@data-target-selection-name='sfdc:StandardButton.Task.Delete']"));
        //driver.executeScript("arguments[0].click()",deleteMenuItem);
        deleteMenuItem.click();
        driver.findElement(By.xpath("//div[contains(@class,'modal-footer')]//button[@title='Delete']")).click(); //Handle Popup
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        WebElement toastContent = driver.findElement(By.xpath("//div[contains(@class,'toastContent')]//span"));
        wait.until(ExpectedConditions.visibilityOf(toastContent));
        boolean isLowFlag = toastContent.isDisplayed();
        Assert.assertTrue(isLowFlag);
    }

    @Test
    public void loginAndTestCreateTaskWithComments() {
        WebElement menu = driver.findElement(By.cssSelector(".slds-icon-waffle"));
        menu.click();
        driver.findElement(By.xpath("//lightning-button")).click();
        driver.findElement(By.xpath("//input[@part='input']")).sendKeys("Tasks");
        WebElement dashLink = driver.findElement(By.xpath("//lightning-formatted-rich-text//mark"));
        dashLink.click();
        driver.findElement(By.xpath("(//div[@class='uiMenu']//a[contains(@class,'slds-button')])[3]")).click();
        // driver.findElement(By.xpath("//div[@title='New Task']")).click(); Faced  org.openqa.selenium.ElementClickInterceptedException
        WebElement newTaskMenu = driver.findElement(By.xpath("//div[contains(@class,'actionMenu')]//a"));
        //JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.executeScript("arguments[0].click()", newTaskMenu);
        Random random = new Random();
        driver.findElement(By.xpath("(//div[@class='slds-form-element__control']//input[@role='combobox' and @type='text'])[2]")).sendKeys("Bootcamp" + random.nextInt());
        WebElement dropDown = driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Task.Status']//a"));
        driver.executeScript("arguments[0].click()", dropDown);
        WebElement dropDownInput = driver.findElement(By.xpath("//div[@class='select-options']//a[contains(text(),'Waiting')]"));
        driver.executeScript("arguments[0].click()", dropDownInput);
        //div[contains(@class,'forceVirtualRecordList')]//span[@data-aura-class='uiOutputText' and contains(text(),'Bootcamp')]
        driver.findElement(By.xpath("//textarea")).sendKeys("SALES FORCE Automation Using Selenium");
        driver.findElement(By.xpath("//button/span[text()='Save']")).click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        WebElement toastMessage = driver.findElement(By.xpath("//div[@role='alertdialog' and @data-key='success']"));
        wait.until(ExpectedConditions.visibilityOf(toastMessage));
        boolean isSuccessFlag = toastMessage.isDisplayed();
        Assert.assertTrue(isSuccessFlag);
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
