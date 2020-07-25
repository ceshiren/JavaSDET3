package test_framework_service;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseApi {
    /**
     * 保存了所有的api对象
     */
    List<ApiObjectModel> apis=new ArrayList<>();


    /**
     * 加载所有的api object对象，并保存到一个列表里
     * @param dir
     */
    public void load(String dir){

        Arrays.stream(new File(dir).list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                //todo: filter
                return true;
            }
        })).forEach(path-> {
            try {
                apis.add(ApiObjectModel.load(dir+"/"+path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 根据测试用例中提供的api object和对应的action，从自己的数据中检索对应的api，并调用对应的方法。
     * @param name
     * @param action
     */
    public void run(String name, String action){
        apis.stream().filter(api -> api.name.equals(name)).forEach(api->{
            api.methods.get(action).run();
        });
    }
}
