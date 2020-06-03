package test_web.wework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BasePage {
    RemoteWebDriver driver;
    WebDriverWait wait;

    public BasePage() {
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        selenium 4.0 use duration
//        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait=new WebDriverWait(driver, 10);
    }

    public BasePage(RemoteWebDriver driver) {
        this.driver = driver;
//        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait=new WebDriverWait(driver,10);

    }


    public void quit() {
        driver.quit();
    }


    public void click(By by){
        //todo: 异常处理
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }

    public void sendKeys(By by, String content){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).sendKeys(content);
    }

    public void upload(By by, String path){
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        driver.findElement(by).sendKeys(path);
    }
}
