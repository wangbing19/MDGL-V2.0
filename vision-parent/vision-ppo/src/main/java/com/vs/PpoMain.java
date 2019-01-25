package com.vs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.vs.ppo.mapper")
public class PpoMain {
    public static void main(String[] args){
        SpringApplication.run(PpoMain.class,args);
    }

}
