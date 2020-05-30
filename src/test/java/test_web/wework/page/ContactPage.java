package test_web.wework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactPage {
    By addMember=By.linkText("添加成员");
    By username=By.name("username");
    By delete=By.linkText("删除");

    public ContactPage addMember(String username, String acctid, String mobile) {
        //todo:
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        new WebDriverWait(MainPage.driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.visibilityOfElementLocated(addMember));
//        //todo: 就算可点击，仍然有一定的概率是点击不成功的
//        new WebDriverWait(MainPage.driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.elementToBeClickable(addMember));


        while(MainPage.driver.findElements(this.username).size()==0){
            MainPage.driver.findElement(addMember).click();
        }

        MainPage.driver.findElement(this.username).sendKeys(username);
        MainPage.driver.findElement(By.name("acctid")).sendKeys(acctid);
        MainPage.driver.findElement(By.name("mobile")).sendKeys(mobile);
        MainPage.driver.findElement(By.cssSelector(".js_btn_save")).click();
        return this;
    }

    public ContactPage search(String keyword){
        MainPage.driver.findElement(By.id("memberSearchInput")).sendKeys(keyword);
        new WebDriverWait(MainPage.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(delete));
        return this;
    }

    public ContactPage delete(){
        MainPage.driver.findElement(delete).click();
        MainPage.driver.findElement(By.linkText("确认")).click();
        MainPage.driver.findElement(By.id("clearMemberSearchInput")).click();
        return this;

    }
}
