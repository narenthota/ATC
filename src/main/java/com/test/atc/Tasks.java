package com.test.atc;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import junit.framework.Assert;

public class Tasks {
	
	static String Chromedriverpath = "path"; /* enter the path of the chromedriver*/
	static WebDriver driver;
	
	public static void Login(){
		
		System.setProperty("webdriver.chrome.driver", Chromedriverpath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
        driver.findElement(By.className("login")).click();
        driver.findElement(By.id("email")).sendKeys("mailtest@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("test123");
        driver.findElement(By.id("SubmitLogin")).click();
        String AcntName = driver.findElement(By.xpath("//a[@class='account']/span[1]")).getText();
        Assert.assertEquals("Account signed", "Naren Thota", AcntName);
	}
	
	public static void AddAddress(){
		
    	driver.findElement(By.xpath("//a[@title='Addresses']")).click();
        driver.findElement(By.xpath("//a[@title='Add an address']")).click();
        driver.findElement(By.id("firstname")).clear();
        driver.findElement(By.id("firstname")).sendKeys("testname");
        driver.findElement(By.id("lastname")).clear();
        driver.findElement(By.id("lastname")).sendKeys("lastname");
        driver.findElement(By.id("company")).sendKeys("testcompany");
        driver.findElement(By.id("address1")).sendKeys("test house");
        driver.findElement(By.id("address2")).sendKeys("test street");
        driver.findElement(By.id("city")).sendKeys("test city");            
        Select state = new Select(driver.findElement(By.id("id_state")));
        state.selectByIndex(9);
        driver.findElement(By.id("postcode")).sendKeys("32440");
        Select country = new Select(driver.findElement(By.id("id_country")));
        country.selectByValue("21");
        driver.findElement(By.id("phone")).sendKeys("1234567878");
        driver.findElement(By.id("phone_mobile")).sendKeys("1234568787");
        driver.findElement(By.id("other")).sendKeys("test"); 
        driver.findElement(By.id("alias")).clear();
        String alias = "TESTADDRESS";
        driver.findElement(By.id("alias")).sendKeys("TESTADDRESS");  
        driver.findElement(By.id("submitAddress")).click();
        String NewAddress = driver.findElement(By.xpath("//h3[text()='TESTADDRESS']")).getText();
        Assert.assertEquals("Address created", alias, NewAddress); 
    }
	
	public static void AddToCart(String Dress){
		
		WebElement Women = driver.findElement(By.xpath("//a[text()='Women']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(Women);
        WebElement Category = driver.findElement(By.xpath("//a[text()='Summer Dresses']"));
        actions.moveToElement(Category);
        actions.click().build().perform();    
        driver.findElement(By.id("list")).click();
        driver.findElement(By.xpath(Dress)).click();      
        for (int i=1;i<=5;i++){
        	String quantity = driver.findElement(By.id("quantity_wanted")).getAttribute("value");
            int q =Integer.parseInt(quantity); 
            if (q<i){
            	 driver.findElement(By.className("icon-plus")).click();
            }           
        }
        Select size = new Select(driver.findElement(By.id("group_1")));
        size.selectByValue("3");
        driver.findElement(By.id("color_16")).click();
        driver.findElement(By.name("Submit")).click();
		
	}
	
	public static void ContinueShop(){
		
		driver.findElement(By.xpath("//span[@title='Continue shopping']")).click();
	}
	
	public static void Checkout(){
		driver.findElement(By.xpath("//a[@title='Proceed to checkout']")).click();
		driver.findElement(By.xpath("//span[text()='Proceed to checkout']")).click();
		driver.findElement(By.xpath("//span[text()='Proceed to checkout']")).click();
		driver.findElement(By.id("cgv")).click();
		driver.findElement(By.name("processCarrier")).click();
		driver.findElement(By.className("bankwire")).click();
		driver.findElement(By.xpath("//span[text()='I confirm my order']")).click();
		
	}
	
	public static void Screenshot(){
		driver.findElement(By.className("account")).click();
		driver.findElement(By.xpath("//span[text()='Order history and details']")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("path"));/* enter the path of the location where you want to save screenshot*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void Signout(){
		driver.findElement(By.className("logout")).click();
		driver.findElement(By.className("page-heading")).isDisplayed();
		driver.quit();
	}

}
