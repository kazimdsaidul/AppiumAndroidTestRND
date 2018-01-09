package com.example.lenovo.appiumstudio;

import java.net.MalformedURLException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.Assert.assertEquals;

/**
 * Created by LENOVO on 15/11/2017.
 */

public class WaitTests {

    WebDriver driver;

    String appPackagename = "currency.springrainit.com.currencyconverter.debug";

    @Before
    public void setUp() throws MalformedURLException {
        // Created object of DesiredCapabilities class.
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Set android deviceName desired capability. Set your device name.
        capabilities.setCapability("deviceName", "SM-G615F");

        // Set BROWSER_NAME desired capability. It's Android in our case here.
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");

        // Set android VERSION desired capability. Set your mobile device's OS version.
        capabilities.setCapability(CapabilityType.VERSION, "7.0.0");

        // Set android platformName desired capability. It's Android in our case here.
        capabilities.setCapability("platformName", "Android");

        // Set android appPackage desired capability. It is
        // com.android.calculator2 for calculator application.
        // Set your application's appPackage if you are using any other app.
        capabilities.setCapability("appPackage", ""+appPackagename);

        // Set android appActivity desired capability. It is
        // com.android.calculator2.Calculator for calculator application.
        // Set your application's appPackage if you are using any other app.
        capabilities.setCapability("appActivity", "currency.springrainit.com.currencyconverter.ui.splash.SplashActivity");

        // Created object of RemoteWebDriver will all set capabilities.
        // Set appium server address and port number in URL string.
        // It will launch calculator app in android device.
        driver = new RemoteWebDriver(new URL("http://192.168.1.18:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);


    }

    @Test
    public void test_First_Input_field() {

        driver.findElement(By.id("currency.springrainit.com.currencyconverter.debug:id/edit_text_from_currency_value")).clear();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("currency.springrainit.com.currencyconverter.debug:id/edit_text_from_currency_value")).sendKeys("90");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @Test
    public void test_ClearButton() {

        driver.findElement(By.id("currency.springrainit.com.currencyconverter.debug:id/edit_text_from_currency_value")).clear();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("currency.springrainit.com.currencyconverter.debug:id/edit_text_from_currency_value")).sendKeys("1000");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @Test
    public void test_0_input() {

        driver.findElement(By.id("currency.springrainit.com.currencyconverter.debug:id/edit_text_from_currency_value")).clear();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("currency.springrainit.com.currencyconverter.debug:id/edit_text_from_currency_value")).sendKeys("1000");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }







    @After
    public void End() {
        driver.quit();
    }
}