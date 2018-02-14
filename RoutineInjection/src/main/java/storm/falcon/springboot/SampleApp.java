package storm.falcon.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SampleApp implements CommandLineRunner {

    @Autowired
    private HelloService helloService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(helloService.getHelloMsg());
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleApp.class, args);
    }
}
