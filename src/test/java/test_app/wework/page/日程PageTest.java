package test_app.wework.page;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class 日程PageTest {

    private static Wework wework;

    @BeforeAll
    static void beforeAll() {
        wework = new Wework();

    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void 添加() {
        assertTrue(
                wework.日程()
                        .添加("上班打卡", null)
                        .获取日程(null)
                        .contains("上班打卡")
        );
    }

    @Test
    void 获取日程() {
    }
}