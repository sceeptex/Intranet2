package com.example.demo.article;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//API Layer (Get, Post, Put, Delete)

@RestController
@RequestMapping(path = "/article")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired //Injection into Constructor articlewired -> no = new articleService
    public ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    @GetMapping
    public List<Article> getArticlesQuery(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String author,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromCreateDateTime,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toCreateDateTime,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String text,
            @RequestParam(required = false) String fullText,
            @RequestParam(required = false) String orderBy
    ) {
        return articleService.getArticlesQuery(category, author, fromCreateDateTime, toCreateDateTime, title, text, fullText, orderBy);
    }
    @GetMapping("/{articleId}")
    public Article getArticlesById(@PathVariable("articleId") Long articleId) {
        return articleService.getArticlesById(articleId);
    }
    @PostMapping
    public void registerNewArticle(@RequestBody Article article){ //Take requestbody and mapp it to article
        articleService.addNewArticle(article);
    }

    @PatchMapping("/{articleId}")
    public void updateArticleEfficient(@RequestBody Article article){
        articleService.updateArticleEfficient(article);
    }

    @DeleteMapping("/{articleId}")
    public void deleteArticle(@PathVariable("articleId") Long articleId) {
        articleService.deleteArticle(articleId);
    }
}