package test_service.framework;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class EncodeTest {
    @BeforeAll
    static void beforeAll(){
        RestAssured.filters((req, res, ctx)->{
            if(req.getURI().contains("encode.json")) {
                System.out.println(req.getURI());
                //返回的Response不具备set方法，无法修改body
                Response originResponse = ctx.next(req, res);
                //ResponseBuilder的作用主要是在Response的基础上建设出来一个新的可以修改body的对象
                ResponseBuilder responseBuilder = new ResponseBuilder().clone(originResponse);
                System.out.println(res.getStatusCode());

//            return originResponse;
                String encodeBody = originResponse.getBody().asString();
                System.out.println("encodeBody");
                System.out.println(encodeBody);

                //解密过程
                byte[] decodeBody = Base64.getDecoder().decode(encodeBody.replace("\n", "").trim());
                System.out.println("decode");
                System.out.println(new String(decodeBody));
                //Response无法直接修改body，所以间接的通过ResponseBuilder构建
                responseBuilder.setBody(decodeBody);

                //ResponseBuilder在最后通过build方法直接创建一个用于返回的不可修改的Response
                Response responseNew = responseBuilder.build();
                return responseNew;
            }else{
                return ctx.next(req, res);
            }
        });
    }
    @Test
    void ceshiren() {
        given().get("https://ceshiren.com/categories.json")
                .then().body("category_list.categories[0].name", equalTo("霍格沃兹测试学院公众号"));
    }


    @Test
    void ceshiren_raw() {
        given().get("http://shell.ceshiren.com:8000/raw.json")
                .then().body("category_list.categories[0].name", equalTo("霍格沃兹测试学院公众号"));
    }

    @Test
    void ceshiren_encode() {
        given().log().all().get("http://shell.ceshiren.com:8000/encode.json")
                .then().body("category_list.categories[0].name", equalTo("霍格沃兹测试学院公众号"));
    }

    //返回的Response无法修改，需要新类型，或者依旧使用response builder，会比较复杂
    Response categories(){
        Response response = given().get("http://shell.ceshiren.com:8000/encode.json").then().extract().response();
        byte[] decode = Base64.getDecoder().decode(response.getBody().asString().replace("\n", ""));
        return new ResponseBuilder().clone(response).setBody(decode).build();
//        return response;
    }




}

