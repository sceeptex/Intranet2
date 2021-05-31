package com.example.demo.article;
import com.example.demo.category.Category;
import com.example.demo.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Transient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service //could be @Component
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, CategoryRepository categoryRepository) {
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
    }

    //GET with Query
    public List<Article> getArticles(String category, String author, LocalDateTime fromCreateDateTime, LocalDateTime toCreateDateTime, String title, String text, String fullText, String orderBy) {
        List<Article> articleList = articleRepository.findAll();


        //Restriction 1 category
        List<Article> removeList1 = new ArrayList<Article>();
        if (category != null && category.length() > 0){
            for (Article article : articleList) {
                if (!article.getCategory().equals(category)){
                    removeList1.add(article);
                }
            }
            articleList.removeAll(removeList1);
        }


        //Restriction 2 author
        List<Article> removeList2 = new ArrayList<Article>();
        if (author != null && author.length() > 0){
            for (Article article : articleList) {
                if (!article.getAuthor().equals(author)){
                    removeList2.add(article);
                }
            }
            articleList.removeAll(removeList2);
        }


        //Restriction 3 From Time
        List<Article> removeList3 = new ArrayList<Article>();
        if (fromCreateDateTime != null ){
            for (Article article : articleList) {
                if (fromCreateDateTime.isAfter(article.getCreateDateTime())){
                    removeList3.add(article);
                }
            }
            articleList.removeAll(removeList3);
        }

        //Restriction 3 toCreateDateTime
        List<Article> removeList4 = new ArrayList<Article>();
        if (toCreateDateTime != null ){
            for (Article article : articleList) {
                if (toCreateDateTime.isBefore(article.getCreateDateTime())){
                    removeList4.add(article);
                }
            }
            articleList.removeAll(removeList4);
        }



        return articleList;
    }

    public Optional<Article> getArticlesById(Long articleId) {
        Optional<Article> optionalArticle = articleRepository.findById(articleId);
        if (!optionalArticle.isPresent()){
            throw new IllegalStateException("Article with Id" + articleId + " does not exists");
        }
        else {
            return optionalArticle;
        }

    }

    //post
    public void addNewArticle(Article article) {
        //further checks on Input values?
        Optional<Article> articleOptional_ByTitle = articleRepository.findArticleByTitle(article.getTitle());

        //Check if Category is valid
        Optional<Category> categoryOptional = categoryRepository.findCategoryByName(article.getCategory());
        if (categoryOptional.isPresent()) {
            System.out.printf("save Article", article.getTitle());
            articleRepository.save(article);
        }
        else {
            throw new IllegalStateException("Category " + article.getCategory() + " not available");
        }


        System.out.println(article);
    }


    @Transactional
    public void updateArticle(Long articleId, String title, String teaser, String text, String picture, String category, String author) {
        System.out.println("Put");
        Article article = articleRepository.getById(articleId);

        //check if article exists
        if (article == null){
            throw new IllegalStateException("Article with Id" + articleId + " does not exists");
        }

        //indicator for changed
        article.setLastModifiedDateTime(LocalDateTime.now());

        //Business Logic
        if (title != null &&
            title.length() > 0 &&
                !Objects.equals(article.getTitle(), title)) { //if title is not the same
            article.setTitle(title);
        }

        if (teaser != null &&
                teaser.length() > 0 &&
                !Objects.equals(article.getTeaser(), teaser)) {
            article.setTeaser(teaser);
        }

        if (text != null &&
                text.length() > 0 &&
                !Objects.equals(article.getText(), text)) { //if text is not the same
            article.setText(text);
        }

        if (picture != null &&
                picture.length() > 0 &&
                !Objects.equals(article.getPicture(), picture)) { //if text is not the same
            article.setPicture(picture);
        }

        if (category != null &&
                category.length() > 0 &&
                !Objects.equals(article.getCategory(), category)) { //if text is not the same
            //Check if Category is valid
            Optional<Category> categoryOptional = categoryRepository.findCategoryByName(article.getCategory());
            if (categoryOptional.isPresent()) {
                article.setCategory(category);
            }
        }

        if (author != null &&
                author.length() > 0 &&
                !Objects.equals(article.getAuthor(), author)) { //if text is not the same
            article.setAuthor(author);
        }

    }

    public void deleteArticle(Long articleId) {
        boolean exists = articleRepository.existsById(articleId);
        if (!exists){
            throw new IllegalStateException("Article with Id" + articleId + " does not exists");
        }
        else {
            articleRepository.deleteById(articleId);
        }

    }



}
