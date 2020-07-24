package test_service.mock;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;

public class ExampleTransformer extends ResponseDefinitionTransformer {

    @Override
    public ResponseDefinition transform(Request request, ResponseDefinition responseDefinition, FileSource files, Parameters parameters) {
        String content=responseDefinition.getTextBody().replace("霍格沃兹", "mock");
        System.out.println(responseDefinition.getTextBody());
        System.out.println(content);
        return new ResponseDefinitionBuilder()
                .withHeader("MyHeader", "Transformed")
                .withStatus(200)
                .withBody(content)
                .build();
    }

    @Override
    public String getName() {
        return "example";
    }
}