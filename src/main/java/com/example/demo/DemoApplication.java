package com.example.demo;

import com.example.demo.article.Article;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

@SpringBootApplication





public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/*@GetMapping
	public Article hello() {
		Article test = new Article();
		test.setAuthor("Deine Mudda");
		return test;
	}*/


}
