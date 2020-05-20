package junit5;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestYaml {
    @ParameterizedTest
    @MethodSource
    public void testddt(String raw) {
        assertTrue(raw.length() > 3);

    }

    static Stream<String> testddt() {
        return Stream.of("1", "12", "123", "1234");
    }


    @ParameterizedTest()
    @MethodSource
    public void testDDTFromYaml(User user) {
        assertTrue(user.name.length() > 3);

    }

    static List<User> testDDTFromYaml() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference typeReference = new TypeReference<List<User>>() {
        };
        List<User> users = mapper.readValue(
                TestYaml.class.getResourceAsStream("/user.yaml"),
                typeReference
        );
        return users;
    }


    @ParameterizedTest()
    @MethodSource
    public void testDDTFromJson(User user) {
        assertTrue(user.name.length() > 3);

    }

    static List<User> testDDTFromJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference typeReference = new TypeReference<List<User>>() {
        };
        List<User> users = mapper.readValue(
                TestYaml.class.getResourceAsStream("/user.json"),
                typeReference
        );
        return users;
    }
}
