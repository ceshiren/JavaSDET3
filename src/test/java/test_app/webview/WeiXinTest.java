package test_app.webview;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class WeiXinTest {

    private AndroidDriver<WebElement> driver;
    private int index = 0;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "测试人社区 ceshiren.com");
        desiredCapabilities.setCapability("appPackage", "com.tencent.mm");
        desiredCapabilities.setCapability("appActivity", "com.tencent.mm.ui.LauncherUI");
        desiredCapabilities.setCapability("unicodeKeyboard", "true");
        desiredCapabilities.setCapability("resetKeyboard", "true");
        //高危操作，如果设置错误，聊天记录会被清空，建议使用小号测试
        desiredCapabilities.setCapability("noReset", "true");

        //第一步：设置正确的chromedriver
        //简单粗暴的解决方案
        desiredCapabilities.setCapability("chromedriverExecutable",
                "/Users/seveniruby/projects/chromedriver/chromedrivers/chromedriver_78.0.3904.11");
//      desiredCapabilities.setCapability("chromedriverExecutable",
//        "/Users/seveniruby/projects/chromedriver/chromedrivers/chromedriver_2.23");
        //完善的版本选择方案，不过会优先找android webview默认实现
//        desiredCapabilities.setCapability("chromedriverExecutableDir",
//                "/Users/seveniruby/projects/chromedriver/chromedrivers");
//        desiredCapabilities.setCapability("chromedriverChromeMappingFile",
//                "/Users/seveniruby/projects/Java3/src/main/resources/mapping.json");
        //打印更多chromedriver的log方便定位问题
        desiredCapabilities.setCapability("showChromedriverLog", true);

        //第二步：设置chromeoption传递给chromedriver
        //因为小程序的进程名跟包名不一样，需要加上这个参数
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("androidProcess", "com.tencent.mm:appbrand0");
        desiredCapabilities.setCapability("goog:chromeOptions", chromeOptions);
        //必须得加上，因为默认生成browserName=chrome的设置，需要去掉
        desiredCapabilities.setCapability("browserName", "");

        //第三步：设置adb proxy
        //通过自己的adb代理修复chromedriver的bug并解决@xweb_devtools_remote的问题
        desiredCapabilities.setCapability("adbPort", "5038");

        //加速
        desiredCapabilities.setCapability("skipLogcatCapture", "true");
        //用于快速测试
//        desiredCapabilities.setCapability("dontStopAppOnReset", "true");


        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver<>(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@text='通讯录']"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test
    public void wxmicroApplication() {
        Dimension size = driver.manage().window().getSize();
        new TouchAction<>(driver)
                .longPress(
                        LongPressOptions.longPressOptions()
                                .withDuration(Duration.ofSeconds(2))
                                .withPosition(PointOption.point(size.width / 2, size.height / 2)))
                .moveTo(PointOption.point(size.width / 2, size.height / 10 * 9))
                .release()
                .perform();

        driver.findElement(By.className("android.widget.EditText")).click();
        driver.findElement(By.xpath("//*[@text='取消']"));
        driver.findElement(By.className("android.widget.EditText")).sendKeys("雪球");
        screenshot();
        driver.findElement(By.className("android.widget.Button")).click();
        driver.findElement(By.xpath("//*[@text='自选']"));

        driver.getContextHandles().forEach(context -> {
            System.out.println(context.toString());
        });

        String webview = driver.getContextHandles().stream()
                .filter(context -> context.toString().contains("WEBVIEW_xweb"))
                .findFirst().get().toString();
        System.out.println(webview);
        driver.context(webview);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        findTopWindow();
        screenshot();
        driver.findElement(By.cssSelector("[src*=stock_add]")).click();
//        driver.findElement(By.cssSelector("[src=\"/static/img/stock_add@3x.png\"]")).click();

        new WebDriverWait(driver, 10).until((driver) -> driver.getWindowHandles().size() > 2);
        findTopWindow();
        screenshot();
        WebElement input = driver.findElement(By.cssSelector("._input"));
        //html输入不生效
//        input.sendKeys("xiaomi");

        //js输入方法不可行
//        String keyword = "alibaba";
//        String js = String.format("arguments[0].setAttribute('value','%s')", keyword);
//        System.out.println(js);
//        driver.executeScript(js, input);

        //native原生输入可以
        driver.context("NATIVE_APP");
        new Actions(driver).sendKeys("xiaomi").perform();
        screenshot();
        driver.context("WEBVIEW_xweb");
        driver.findElement(By.cssSelector(".stock__item")).click();
        //截图
        findTopWindow();
        screenshot();
    }

    public void screenshot() {
        //截图
        index += 1;
        String path = "/tmp";
        try {
            FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE),
                    new File(String.format("%s/wx_%s.png", path, index)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void findTopWindow() {
        for (String win : driver.getWindowHandles()) {
            if (driver.getTitle().contains(":VISIBLE")) {
                System.out.println(driver.getTitle());
                System.out.println(driver.findElement(By.cssSelector("body")).getAttribute("is"));
            } else {
                driver.switchTo().window(win);
            }
        }
        System.out.println(driver.getPageSource());
    }


    @After
    public void tearDown() throws InterruptedException {
        driver.quit();
    }
}
