package storm.falcon.springboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HelloService {

    @Value("${name:World}")
    private String name;

    public String getHelloMsg() {
        return "Hello " + name;
    }
}
