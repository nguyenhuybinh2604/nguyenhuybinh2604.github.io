package com.example.day03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class Config {

    @Bean
    public Random random() {
        return new Random();
    }
}
