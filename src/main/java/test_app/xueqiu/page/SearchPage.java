package test_app.xueqiu.page;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchPage {
    private AndroidDriver driver;
    private By nameLocator=By.id("name");

    public SearchPage() {
    }

    public SearchPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public SearchPage search(String keyword){
        MobileElement el5 = (MobileElement) driver.findElementById("com.xueqiu.android:id/search_input_text");
        el5.sendKeys(keyword);
        return this;

    }

    public List<String> getSearchList(){
        List<String> nameList=new ArrayList<>();
        for(Object element: driver.findElements(nameLocator)){
            nameList.add(((WebElement)element).getText());
        }

        //todo: stream lamda优化

        return nameList;
    }

    public double getPrice(){
        driver.findElement(nameLocator).click();
        return Double.valueOf(driver.findElement(By.id("current_price")).getText());
    }
}
