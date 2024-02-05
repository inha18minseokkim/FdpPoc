package com.example.fdppoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FdpPocApplication {

    public static void main(String[] args) {
        SpringApplication.run(FdpPocApplication.class, args);
    }

}
