package com.vs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.vs.vision.mapper")
public class ExpMain {

    public static void main(String[] args){
        SpringApplication.run(ExpMain.class,args);
    }

}
