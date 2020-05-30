package test_web.wework.testcase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import test_web.wework.page.MainPage;

public class TestContact {
    @Test
    void testAddMember(){
        MainPage main=new MainPage();
        main.toContact().addMember("3", "3", "15600534763");
        //todo: assert

    }

    @AfterAll
    static void afterAll(){
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MainPage.driver.quit();
    }
}
