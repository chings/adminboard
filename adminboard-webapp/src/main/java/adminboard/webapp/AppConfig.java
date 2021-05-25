package adminboard.webapp;

import org.springframework.context.annotation.Configuration;
import springboard.redis.annotation.EnableRedisEventBus;
import springboard.shiro.annotation.EnableShiroSecurity;
import springboard.swagger.annotation.EnableSwaggerDocumentation;

@Configuration("adminboard.webapp.AppConfig")
@EnableRedisEventBus
@EnableShiroSecurity
@EnableSwaggerDocumentation
public class AppConfig {

}
