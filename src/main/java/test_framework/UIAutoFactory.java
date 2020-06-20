package test_framework;

import test_app.wework.page.AppBasePage;
import test_web.wework.page.WebBasePage;

public class UIAutoFactory {
    public static BasePage create(String driverName){
        if(driverName.equals("web") || driverName.equals("selenium")){
            return new WebBasePage();
        }

        if(driverName.equals("app") || driverName.equals("appium")){
            return new AppBasePage();
        }

        if(driverName.equals("uiautomator")){
//            return new AppBasePage();
        }

        if(driverName.equals("atx")){
//            return new AppBasePage();
        }

        if(driverName.equals("macaca")){
//            return new AppBasePage();
        }

        return null;
    }
}
