package com.globantu.automation.salvador_ortuno;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import paquete.HomePage;

public class TestingBasics {

    private WebDriver driver;
//comentario
        @BeforeMethod
        public void before(){
            String baseURL="http://www.travelocity.com/";
            System.setProperty("webdriver.gecko.driver","browser/geckodriver.exe");
            driver=new FirefoxDriver();
            driver.manage().window().maximize();
            driver.get(baseURL);
        }

        @Test
        public void test1BeginProcessOfBooking()throws Exception{
            HomePage homeP =  PageFactory.initElements(driver, HomePage.class);
            homeP.bookingProcess("LAS","LAX", 3, "20", "Duration (Shortest)", "1","0");
            homeP.validations();
        }
        @AfterMethod
        public void tearDown()throws Exception{
            Thread.sleep(6000);
            driver.quit();
        }

    }



