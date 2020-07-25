package test_framework_service;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DemoTest {
    @Test
    void demo(){
        assertThat(1, equalTo(2));
    }
}
