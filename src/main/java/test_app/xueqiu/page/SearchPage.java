package test_app.xueqiu.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchPage extends BasePage {
    private By nameLocator = By.id("name");

    public SearchPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public SearchPage search(String keyword) {
        do {
            sendKeys(By.id("search_input_text"), keyword);
            System.out.println("sendkeys");
        } while (driver.findElements(nameLocator).size() <= 0);

//        MobileElement el5 = (MobileElement) driver.findElementById("com.xueqiu.android:id/search_input_text");
//        el5.sendKeys(keyword);
        return this;

    }

    public List<String> getSearchList() {
        List<String> nameList = new ArrayList<>();
//        for(Object element: driver.findElements(nameLocator)){
//            nameList.add(((WebElement)element).getText());
//        }
        driver.findElements(nameLocator).forEach(element -> nameList.add(element.getText()));

        //done: stream lamda优化

        return nameList;
    }


    public double getPrice() {
        //todo: 独立一个独立的po方法
        click(nameLocator);
        return Double.valueOf(find(By.id("current_price")).getText());
    }
}
