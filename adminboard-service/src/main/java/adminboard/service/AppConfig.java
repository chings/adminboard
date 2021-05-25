package adminboard.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import springboard.mybatis.annotation.EnableMyBatisPersistence;

@Configuration("adminboard.service.AppConfig")
@EnableMyBatisPersistence("adminboard.dao")
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
