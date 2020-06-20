package test_app.wework.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class 日程Page extends AppBasePage {
    //todo:多版本app、多平台的app 定位符通常有差别
    private final By taskName = By.id("b0e");
    private final By save = byText("保存");
    private final By taskList = By.id("gg_");
    private By add =By.id("gq0");

    public 日程Page(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public 日程Page 添加(String name, String time){
        click(add);
        sendKeys(taskName, name);
        click(save);
        return this;
    }

    public List<String> 获取日程(String day){
        if(day!=null){
            //todo:选择日期
        }
        return driver.findElements(taskList)
                .stream()
                .map(x->x.getText())
                .collect(Collectors.toList());
    }
}
