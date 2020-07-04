package test_app.webview;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebViewTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "hogwarts");
        desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");
        desiredCapabilities.setCapability("noReset", "true");
//        desiredCapabilities.setCapability("adbPort", "5038");
//        desiredCapabilities.setCapability("skipLogcatCapture", "true");
        desiredCapabilities.setCapability("dontStopAppOnReset", "true");

        desiredCapabilities.setCapability("chromedriverExecutable", "/Users/seveniruby/projects/chromedriver/72/chromedriver");

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        //todo: 等待优化
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@text='交易']"));
    }

    @Test
    public void webview_native() {
        driver.findElement(By.xpath("//*[@text='交易']")).click();
        driver.findElement(By.xpath("//*[@text='基金开户']")).click();
    }

    @Test
    public void webview_web() throws InterruptedException {
        driver.findElement(By.xpath("//*[@text='交易']")).click();
        for (int i = 0; i < 2; i++) {
            driver.getContextHandles().forEach(context -> System.out.println(context.toString()));
            Thread.sleep(500);
        }
        driver.context(driver.getContextHandles().toArray()[1].toString());

        driver.getWindowHandles().forEach(window -> {
            System.out.println(window);
            System.out.println(driver.getTitle());
            driver.switchTo().window(window);
            System.out.println(driver.getPageSource());
        });

//        driver.getWindowHandles().stream().filter(win->{
//            driver.switchTo().window(win);
//            return driver.getTitle().contains("实盘交易");
//        });

        Object[] array = driver.getWindowHandles().toArray();
        driver.switchTo().window(array[array.length - 1].toString());

        driver.findElement(By.cssSelector(".trade_home_info_3aI")).click();

    }

    @Test
    public void wxmicroApplication(){
        
    }

    @Test
    public void sampleTest() {
        MobileElement el4 = (MobileElement) driver.findElementById("com.xueqiu.android:id/home_search");
        el4.click();
        MobileElement el5 = (MobileElement) driver.findElementById("com.xueqiu.android:id/search_input_text");
        el5.sendKeys("alibaba");
        MobileElement el6 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView[1]");
        el6.click();
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(20000);
        driver.quit();
    }
}
