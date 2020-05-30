package test_web.wework.testcase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import test_web.wework.page.ContactPage;
import test_web.wework.page.MainPage;

public class TestContact {
    static MainPage main;
    static ContactPage contact;
    @BeforeAll
    static void beforeAll(){
        main=new MainPage();
        contact=main.toContact();
    }
    @Test
    void testAddMember(){

        contact.addMember("3", "3", "15600534763");
        //todo: assert
    }

    @Test
    void testSearch(){
        contact.search("3").delete();
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
