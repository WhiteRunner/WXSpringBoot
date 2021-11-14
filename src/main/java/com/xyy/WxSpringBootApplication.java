package com.xyy;

import cn.dev33.satoken.SaManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xyy.mapper")
public class WxSpringBootApplication {

    public static void main(String[] args) throws JsonProcessingException {
        SpringApplication.run(WxSpringBootApplication.class, args);
    }

}
