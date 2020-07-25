package test_framework_service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import test_framework.BasePage;
import test_framework.UIAuto;
import test_framework.UIAutoFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * 管理测试用例的运行
 */
public class ApiDDTest {

    private static BaseApi baseApi;


    @ParameterizedTest(name = "{index} {1}")
    @MethodSource
    void apiTest(ApiTestCaseModel apiTestCaseModel, String name) {
        apiTestCaseModel.run(baseApi);

    }

    static List<Arguments> apiTest() {

        //加载所有的api object
        baseApi = new BaseApi();

        if(System.getProperty("api")!=null){
            baseApi.load(System.getProperty("api"));
        }else{
//            baseApi.load("src/main/resources/test_framework_service/api");
        }


        //用来传递给参数化用例
        List<Arguments> testcases = new ArrayList<>();

        //读取所有的测试用例
        String testcaseDir = "";
        if(System.getProperty("test")!=null){
            testcaseDir=System.getProperty("test");
        }

        String finalTestcaseDir = testcaseDir;
        Arrays.stream(new File(testcaseDir).list())
                .forEach(name -> {
                    String path = finalTestcaseDir + "/" + name;
                    try {
                        ApiTestCaseModel apiTestCase = ApiTestCaseModel.load(path);
                        testcases.add(arguments(apiTestCase, apiTestCase.name));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        return testcases;
    }
}
