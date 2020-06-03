package test_app.xueqiu.page;

import java.util.Arrays;
import java.util.List;

public class SearchPage {
    public SearchPage search(String keyword){
        return this;

    }

    public List<String> getSearchList(){
        return Arrays.asList("1", "2", "3");
    }

    public double getPrice(){
        return 1.1;
    }
}
