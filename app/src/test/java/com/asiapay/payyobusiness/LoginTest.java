package com.asiapay.payyobusiness;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "ZY223FRBR6");
        desiredCapabilities.setCapability("appPackage", "com.asiapay.payyobusiness");
        desiredCapabilities.setCapability("appActivity", ".login.LoginActivity");

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void loginTest() {
        MobileElement el1 = (MobileElement) driver.findElementById("com.asiapay.payyobusiness:id/etUsername");
        el1.sendKeys("kandroid");
        MobileElement el2 = (MobileElement) driver.findElementById("com.asiapay.payyobusiness:id/etPassword");
        el2.sendKeys("kandroid");
        MobileElement el3 = (MobileElement) driver.findElementById("com.asiapay.payyobusiness:id/btnLogin");
        el3.click();
        MobileElement el4 = (MobileElement) driver.findElementById("com.asiapay.payyobusiness:id/et1");
        el4.sendKeys("1");
        MobileElement el5 = (MobileElement) driver.findElementById("com.asiapay.payyobusiness:id/et2");
        el5.sendKeys("2");
        MobileElement el6 = (MobileElement) driver.findElementById("com.asiapay.payyobusiness:id/et3");
        el6.sendKeys("3");
        MobileElement el7 = (MobileElement) driver.findElementById("com.asiapay.payyobusiness:id/et4");
        el7.sendKeys("4");
        MobileElement el8 = (MobileElement) driver.findElementById("com.asiapay.payyobusiness:id/btnOTPSubmit");
        el8.click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}