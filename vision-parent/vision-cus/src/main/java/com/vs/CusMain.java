package com.vs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.vs.cus.mapper")
public class CusMain {

    public static void main(String[] args){
        SpringApplication.run(CusMain.class,args);
    }

}
