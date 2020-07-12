package test_service.mock;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.Har;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MockTest {

    private static BrowserMobProxy proxy;

    @BeforeAll
    static void beforeAll() {
        proxy = new BrowserMobProxyServer();
        proxy.start(8082);
    }

    @BeforeEach
    void before() {
        proxy.newHar("click");
    }


    @AfterEach
    void after() {
        Har har = proxy.endHar();
    }

    @Test
    void click() {
        //自动化测试
    }
}
