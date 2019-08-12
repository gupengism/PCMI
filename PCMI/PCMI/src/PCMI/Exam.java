//package PCMI;
//
//public class Exam {
//
//}


package PCMI;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;


public class Exam  {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        
        driver.get("https://qa-alpha-auto.pcrsdev.com/");
        
        driver.findElement(By.xpath("//span[text()='Email:']//ancestor::tr[1]//input")).sendKeys("tester@pcmi.com");
        driver.findElement(By.xpath("//span[text()='Login']")).click();      
        
        WebElement errorMessage = driver.findElement(By.xpath("//*[@id='dialogContentDiv']//label"));
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        Assert.assertEquals(errorMessage.getText(), "Password is required.");
        driver.findElement(By.xpath("//button//span[text()='OK']")).click();
                
        WebElement passwordField = driver.findElement(By.xpath("//span[text()='Password:']//ancestor::tr[1]//input"));
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys("qQadfi4$2");        
        driver.findElement(By.xpath("//span[text()='Login']")).click();
      
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wdriver) {
                return ((JavascriptExecutor) driver).executeScript(
                    "return document.readyState"
                ).equals("complete");
            }
        });
           

        WebElement addContract = (new WebDriverWait(driver, 10))
        		   .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Add Contract']")));
        addContract.click();
             
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wdriver) {
                return ((JavascriptExecutor) driver).executeScript(
                    "return document.readyState"
                ).equals("complete");
            }
        });
        
        ArrayList<String> tabs_windows = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs_windows.get(1));
        
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wdriver) {
                return ((JavascriptExecutor) driver).executeScript(
                    "return document.readyState"
                ).equals("complete");
            }
        });
        
        WebElement saleOdometer = driver.findElement(By.xpath("//label[text()='Sale Odometer']//parent::*//input"));
        wait.until(ExpectedConditions.visibilityOf(saleOdometer));
        saleOdometer.sendKeys("100");
        WebElement vin = driver.findElement(By.xpath("//label[text()='VIN']//parent::*//input"));
        vin.sendKeys("3N4BB41D7W2794739");        
        
        WebElement financeType = driver.findElement(By.xpath("//label[contains(.,'Finance Type')]//parent::*//input"));
        financeType.sendKeys("Loan");               

        WebElement amountFinanced = driver.findElement(By.xpath("//label[contains(.,'Amount Financed')]//parent::*//input"));
        amountFinanced.click();
        amountFinanced.sendKeys("10000");
        
        WebElement financeLeaseTeam = driver.findElement(By.xpath("//label[contains(.,'Finance/Lease Term')]//parent::*//input"));
        financeLeaseTeam.click();
        financeLeaseTeam.sendKeys("26");
        
        WebElement apr = driver.findElement(By.xpath("//label[contains(.,'APR')]//parent::*//input"));
        apr.click();
        apr.sendKeys("1");
        
        WebElement lenderSearch = driver.findElement(By.xpath("//label[contains(.,'Lender Search')]//parent::*//input"));
        lenderSearch.sendKeys("LN00000012");
        lenderSearch.sendKeys(Keys.TAB);
        

        
        String vinVal = driver.findElement(By.xpath("//label[text()='VIN']//parent::*//input//parent::*//parent::*//following::div[3]")).getText();
        System.out.println(vinVal);
//        assertTrue(vinVal.contains("LN00000012"));
        
//        Assert.assertEquals(lenderSearch.getText(), "LN00000012");

//        driver.quit();
    }
}
