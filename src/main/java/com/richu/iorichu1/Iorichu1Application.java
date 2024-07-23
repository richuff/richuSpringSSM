package com.richu.iorichu1;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.richu.iorichu1.Mapper")
@SpringBootApplication
public class Iorichu1Application {
    public static void main(String[] args) {
        SpringApplication.run(Iorichu1Application.class, args);
    }
}
