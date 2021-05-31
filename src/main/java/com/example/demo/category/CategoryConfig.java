package com.example.demo.category;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CategoryConfig {
    @Bean
    CommandLineRunner commandLineRunner2(CategoryRepository repository){
        return args -> {
            Category c1 = new Category(
                    "FINANCE"
            );
            Category c2 = new Category(
                    "NEWS"
            );
            Category c3 = new Category(
                    "PR"
            );
            Category c4 = new Category(
                    "HR"
            );
            Category c5 = new Category(
                    "OTHER"
            );

            repository.saveAll(
                    List.of(c1, c2, c3, c4, c5)
            );
        };
    }

}
