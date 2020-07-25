package test_framework_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * 对标了每个api object
 */
public class ApiObjectModel {
    public String name;
    public HashMap<String, ApiObjectMethodModel> methods;

    /**
     * api object支持从yaml中读取
     * @param path
     * @return
     * @throws IOException
     */
    public static ApiObjectModel load(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(new File(path), ApiObjectModel.class);

    }

    /**
     * 运行这个api object中的某个封装的方法
     * @param method
     */
    public void run(ApiObjectMethodModel method) {
        method.run();
    }

}
