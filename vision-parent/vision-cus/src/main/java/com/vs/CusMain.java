package com.vs;

import com.vs.cus.pojo.CusConsultation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class CusMain {

    public static void main(String[] args){
        SpringApplication.run(CusMain.class,args);
    }

}
