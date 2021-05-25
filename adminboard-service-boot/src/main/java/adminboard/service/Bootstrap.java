package adminboard.service;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import springboard.dubbo.annotation.EnableDubboService;

@SpringBootApplication
@EnableDubboService("adminboard.service")
public class Bootstrap {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Bootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

}
