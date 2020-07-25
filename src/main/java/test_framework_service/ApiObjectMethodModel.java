package test_framework_service;

import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

/**
 * 代表了一个单一的http接口
 */
public class ApiObjectMethodModel {
    public String method="get";
    public String url;
    public HashMap<String, Object> query;
    public String save;
    public HashMap<String, Object> json;
    public String post;
    public String get;

    /**
     * 发送http请求
     * @return
     */
    public Response run() {
        //多环境支持
        String url="";


        if(post!=null){
            url=post;
            method="post";
        }
        if(get!=null){
            url=get;
            method="get";
        }
        //读取配置文件，获得域名与ip对应关系，在此替换
        url=url.replaceAll("domain", "ip");

        return given().log().all().queryParams(query).request(method, url)
                .then().log().all().extract().response();
    }
}
