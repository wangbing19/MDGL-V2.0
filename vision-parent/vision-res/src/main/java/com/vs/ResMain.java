package com.vs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com/vs/res/mapper")
public class ResMain {

    public static void main(String[] args){
        SpringApplication.run(ResMain.class,args);
    }

}
