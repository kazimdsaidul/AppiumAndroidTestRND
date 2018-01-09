package com.example.lenovo.appiumstudio;

import android.support.annotation.NonNull;

import com.google.common.base.Verify;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by LENOVO on 15/11/2017.
 */

public class CurrencyAppTest {

    WebDriver driver;
    String platform;

   // String devicName = "SM-G615F";
//      String osVersion = "7.0.0";


    String devicName = "emulator-5554";
    String osVersion = "6.0.0";




    String appPackagename = "currency.springrainit.com.currencyconverter.debug";
    String firstInputBox = appPackagename +":id/edit_text_from_currency_value";
    String secoundInputBox = appPackagename+":id/edit_text_to_currency_value";


    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilitiesAndroid = getAndroidDesiredCapabilities();

        String platform = getStringPlatform();

        if (platform.equals("android")) {
            driver = new RemoteWebDriver(new URL("http://192.168.1.62:4723/wd/hub"), capabilitiesAndroid);
            System.out.println("android");
        } else{
           System.out.println("ios");
        }
//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);

    }

    @NonNull
    private String getStringPlatform() {
        String platform = System.getProperty("os.name");
        if (platform.contains("Windows")){
            platform="android";
        }else {
            platform = "ios";
        }
        return platform;
    }

    @NonNull
    private DesiredCapabilities getAndroidDesiredCapabilities() {
        // Created object of DesiredCapabilities class.
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Set android deviceName desired capability. Set your device name.
        capabilities.setCapability("deviceName", devicName);

        // Set BROWSER_NAME desired capability. It's Android in our case here.
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");

        // Set android VERSION desired capability. Set your mobile device's OS version.
        capabilities.setCapability(CapabilityType.VERSION, ""+osVersion);

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
        return capabilities;
    }

    @Test
    public void Frist_input_test() throws InterruptedException {
        // setup and input
        driver.findElement(By.id(firstInputBox)).clear();

        // process
        driver.findElement(By.id(firstInputBox)).sendKeys("100");
        driver.navigate().back();
        Thread.sleep(10000);

        // verify
        String result = driver.findElement(By.id(secoundInputBox)).getText();
        if (result.length() <0){
            Assert.fail();
        }
    }


    @Test
    public void Secound_input_test() throws InterruptedException {
        // setup and input
        driver.findElement(By.id(secoundInputBox)).clear();

        // process
        driver.findElement(By.id(secoundInputBox)).sendKeys("100");
        driver.navigate().back();
        Thread.sleep(10000);

        // verify
        String result = driver.findElement(By.id(firstInputBox)).getText();
        if (result.length() <0){
            Assert.fail();
        }
    }

    @Test
    public void Verify_the_occurrence_if_0_input_value_firstInputField()  throws InterruptedException {
        // setup and input
        driver.findElement(By.id(firstInputBox)).clear();

        // process
        driver.findElement(By.id(firstInputBox)).sendKeys("0");
        driver.navigate().back();
        Thread.sleep(10000);

        // verify
        String result = driver.findElement(By.id(secoundInputBox)).getText();
        if (!result.equals("0")){
            Assert.fail();
        }
    }


    @Test
    public void Verify_the_occurrence_if_0_input_value_secondInputField() throws InterruptedException {
        // setup and input
        driver.findElement(By.id(secoundInputBox)).clear();

        // process
        driver.findElement(By.id(secoundInputBox)).sendKeys("0");
        driver.navigate().back();
        Thread.sleep(10000);

        // verify
        String result = driver.findElement(By.id(firstInputBox)).getText();
        if (!result.equals("0")){
            Assert.fail();
        }
    }


    @Test
    public void Verify_that_input_won_not_allow_any_Negative_value_secound_input_field() throws InterruptedException {
        // setup and input
        driver.findElement(By.id(secoundInputBox)).clear();

        // process
        driver.findElement(By.id(secoundInputBox)).sendKeys("-");
        driver.navigate().back();
        Thread.sleep(10000);

        // verify
        String result1 = driver.findElement(By.id(firstInputBox)).getText();
        if (!result1.equals("")){
            Assert.fail();
        }

        // verify
        String result2 = driver.findElement(By.id(secoundInputBox)).getText();
        if (!result2.equals("")){
            Assert.fail();
        }

    }

    @Test
    public void Verify_that_input_won_not_allow_any_Negative_value_first_input_field() throws InterruptedException {
        // setup and input
        driver.findElement(By.id(firstInputBox)).clear();

        // process
        driver.findElement(By.id(firstInputBox)).sendKeys("-");
        driver.navigate().back();
        Thread.sleep(10000);

        // verify
        String result1 = driver.findElement(By.id(firstInputBox)).getText();
        if (!result1.equals("")){
            Assert.fail();
        }

        String result2 = driver.findElement(By.id(secoundInputBox)).getText();
        if (!result2.equals("")){
            Assert.fail();
        }

    }



    @Test
    public void Verify_that_Character_not_allow_as_all_input_field() throws InterruptedException {
        // setup and input
        driver.findElement(By.id(firstInputBox)).clear();

        // process
        driver.findElement(By.id(firstInputBox)).sendKeys("A");
        Thread.sleep(1000);
        driver.navigate().back();

        driver.findElement(By.id(secoundInputBox)).sendKeys("B");
        driver.navigate().back();
        Thread.sleep(1000);

        // verify
        String result1 = driver.findElement(By.id(firstInputBox)).getText();
        if (!result1.equals("")){
            Assert.fail();
        }
        Thread.sleep(1000);

        String result2 = driver.findElement(By.id(secoundInputBox)).getText();
        if (!result2.equals("")){
            Assert.fail();
        }

    }

    @After
    public void End() {
        driver.quit();
    }
}