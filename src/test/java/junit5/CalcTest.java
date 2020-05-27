package junit5;

import io.qameta.allure.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalcTest {

    @Test
    @Tag("xxx")
    @Link(name = "allure", type = "mylink", url = "https://home.testing-studio.com")
    void div() {
        Allure.step("第一步");
        Calc calc = new Calc();
        Allure.step("第二步");
        Allure.addAttachment("xxx", "ddddddd");
        Allure.addAttachment("demo", "image/png",
                this.getClass().getResourceAsStream("/斯内普.jpg"), ".jpg");
        assertEquals(calc.div(2, 1), 2);
    }
}