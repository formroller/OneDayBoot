package com.example.onedayboot;

import jakarta.persistence.EntityListeners;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OnedaybootApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnedaybootApplication.class, args);
    }

}
