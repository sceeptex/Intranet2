package com.example.demo.article;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//test

import java.util.List;

@Configuration
public class ArticleConfig {

    @Bean //Access to Repository
    CommandLineRunner commandLineRunner(ArticleRepository repository){
        return args -> {
            Article article1 = new Article(
                    "firstArticle",
                    "Check it out",
                    "wohoho",
                    "GDrive/test2/test",
                    "NEWS",
                    "ich"
            );
            Article article2 = new Article(
                    "secondArticle",
                    "Check it out",
                    "wohoho",
                    "GDrive/test1/test",
                    "FINANCE",
                    "ich"
            );
            Article article3 = new Article(
                    "thirdArticle",
                    "Check it out",
                    "wohoho",
                    "GDrive/test2/test",
                    "NEWS",
                    "ich"
            );

            //save to db
            repository.saveAll(
                    List.of(article1, article2,article3)
            );
        };
    }
}