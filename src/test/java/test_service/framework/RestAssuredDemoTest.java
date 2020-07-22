package test_service.framework;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseBuilder;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredDemoTest {
    @Test
    void demo(){
        given().get("https://ceshiren.com/categories.json")
                .then().body("category_list.draft_key", equalTo("new_topic"));
    }

    @Test
    void filter(){
        RestAssured.filters((req, res, ctx) -> {
            Response originResponse = ctx.next(req, res);
            ResponseBuilder newResponse = new ResponseBuilder().clone(originResponse);
            String textResponse = originResponse.getBody().asString();
            byte[] decodeContent = Base64.getDecoder().decode(textResponse.trim());
            return newResponse.setBody(decodeContent).build();
        });

        given().get("http://127.0.0.1:8000/base64.json")
                .then().body("category_list.draft_key", equalTo("new_topic"));
    }
}
