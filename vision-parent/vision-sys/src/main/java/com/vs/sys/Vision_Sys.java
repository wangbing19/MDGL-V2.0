package com.vs.sys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.vs.sys.mappers")
public class Vision_Sys {
    public static void main(String[] args) {
        SpringApplication.run(Vision_Sys.class, args);
    }
}
