package com.cibr.vectorservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cibr.vectorservice.dao")
public class VectorserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VectorserviceApplication.class, args);
    }

}
