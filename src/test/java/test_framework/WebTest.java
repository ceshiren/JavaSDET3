package test_framework;

import org.junit.jupiter.api.Test;

public class WebTest {
    @Test
    void classic(){
        BasePage web=UIAutoFactory.create("web");
        UIAuto uiAuto=web.load("/test_framework/webauto.yaml");
        web.run(uiAuto);
    }
}
