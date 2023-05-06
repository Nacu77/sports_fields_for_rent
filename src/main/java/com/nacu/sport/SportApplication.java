package com.nacu.sport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.nacu.sport"})
public class SportApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SportApplication.class, args);
    }
}
