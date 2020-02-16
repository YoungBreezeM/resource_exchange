package com.fw;

import com.fw.config.entity.FileProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileProperties.class
})
@MapperScan("com.fw.dao")
public class ResourceExchangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceExchangeApplication.class, args);
    }

}
