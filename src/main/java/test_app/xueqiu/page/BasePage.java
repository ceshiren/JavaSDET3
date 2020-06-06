package test_app.xueqiu.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BasePage {
//    AndroidDriver<MobileElement> driver;
    AppiumDriver<MobileElement> driver;
    //    IOSDriver
    WebDriverWait wait;

    public BasePage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public BasePage() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "hogwarts");
        desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");
        desiredCapabilities.setCapability("noReset", "true");
        desiredCapabilities.setCapability("udid", "");
//        desiredCapabilities.setCapability("dontStopAppOnReset", "true");

        URL remoteUrl = null;
        try {
            remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.exit(1);
        }

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        //todo: 等待优化
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }


    public void quit() {
        driver.quit();
    }


    public MobileElement find(By by) {
        return driver.findElement(by);
    }

    public void click(By by) {
        //todo: 异常处理
        driver.findElement(by).click();
    }


    public void sendKeys(By by, String content) {
        driver.findElement(by).sendKeys(content);
    }

    //todo:
    public void waitElement() {

    }
}
