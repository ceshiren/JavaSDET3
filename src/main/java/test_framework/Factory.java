package test_framework;

import test_app.wework.page.AppBasePage;
import test_web.wework.page.WebBasePage;

public class Factory {
    public static BasePage create(String driverName){
        if(driverName=="web" || driverName=="selenium"){
            return new WebBasePage();
        }

        if(driverName=="app" || driverName=="appium"){
            return new AppBasePage();
        }

        if(driverName=="uiautomator"){
//            return new AppBasePage();
        }

        if(driverName=="atx"){
//            return new AppBasePage();
        }

        if(driverName=="macaca"){
//            return new AppBasePage();
        }

        return null;
    }
}
