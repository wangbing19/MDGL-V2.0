package com.vs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.vs.pre.mapper")
public class PreMain {
    public static void main(String[] args){
        SpringApplication.run(PreMain.class,args);
    }

}
