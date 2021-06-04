package com.example.demo.article;
import com.example.demo.category.Category;
import com.example.demo.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Transient;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, CategoryRepository categoryRepository) {
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
    }

    //search for Id in List
    public boolean isArticleInList(List<Article> articleListInput, Article articleCheck ){
        Boolean IdFoundInList = false;
        for (Article articleInput : articleListInput){
            if (articleInput.getArticleId() == articleCheck.getArticleId()){
                IdFoundInList = true;
            }
        }
        return IdFoundInList;
    }

    //GET with Query
    public List<Article> getArticlesQuery(String category, String author, LocalDateTime fromCreateDateTime, LocalDateTime toCreateDateTime, String title, String text, String fullText, String orderBy) {
        List<Article> articleList = new ArrayList<Article>();

        // add all if no filter is attached
        if (category == null && author == null && fromCreateDateTime == null && toCreateDateTime == null && title == null && text == null && fullText == null){
            articleList = articleRepository.findAll();
            return articleList;
        }
        else {

            //add articles with selected category
            if (category != null && category.length() > 0){
                for (Article article : articleRepository.findArticleByCategory(category)){
                    //add found Articles to articleList
                    if (!isArticleInList(articleList, article)){
                        articleList.add(article);
                    }
                }
            }

            //add articles with selected author
            if (author != null && author.length() > 0){
                for (Article article : articleRepository.findArticleByAuthor(author)){
                    //add found Articles to articleList
                    if (!isArticleInList(articleList, article)){
                        articleList.add(article);
                    }
                }
            }

            //find all articles in between
            if (fromCreateDateTime != null && toCreateDateTime!=null){
                for (Article article : articleRepository.findAllWithCreationDateTimeBetween(fromCreateDateTime,toCreateDateTime)){
                    //add found Articles to articleList
                    if (!isArticleInList(articleList, article)){
                        articleList.add(article);
                    }
                }
            }
            else {
                //add articles with fromCreateDateTime (Von Stichtag bis Zukunft)
                if (fromCreateDateTime != null){
                    for (Article article : articleRepository.findAllWithCreationDateTimeAfter(fromCreateDateTime)){
                        //add found Articles to articleList
                        if (!isArticleInList(articleList, article)){
                            articleList.add(article);
                        }
                    }
                }

                //add articles with toCreateDateTime (Von Vergangenheit bis Stichtag)
                if (toCreateDateTime != null){
                    for (Article article : articleRepository.findAllWithCreationDateTimeBefore(toCreateDateTime)){
                        //add found Articles to articleList
                        if (!isArticleInList(articleList, article)){
                            articleList.add(article);
                        }
                    }
                }
            }

            //order by
            if (orderBy == "CREATE_TIME_ASCENDING"){

            }

            else if(orderBy == "CREATE_TIME_DESCENDING"){

            }

            return articleList;
        }
    }

    public Article getArticlesById(Long articleId) {
        Optional<Article> optionalArticle = articleRepository.findById(articleId);
        if (!optionalArticle.isPresent()){
            throw new IllegalStateException("Article with Id" + articleId + " does not exists");
        }
        else {
            return optionalArticle.get();
        }
    }

    //post
    public void addNewArticle(Article article) {
        //further checks on Input values?
        Optional<Article> articleOptional_ByTitle = articleRepository.findArticleByTitle(article.getTitle());

        //Check if Category is valid
        Optional<Category> categoryOptional = categoryRepository.findCategoryByName(article.getCategory());
        if (categoryOptional.isPresent()){
            articleRepository.save(article);
        }
        else {
            throw new IllegalStateException("Category " + article.getCategory() + " not available");
        }
    }

    @Transactional
    public void updateArticleEfficient(Article article){
        Optional<Article> articleFound = articleRepository.findById(article.getArticleId());
        if (articleFound.isPresent()){
            //overrides Article with existing Id
            article.setLastModifiedDateTime(LocalDateTime.now());
            articleRepository.save(article);
        }
        else {
            throw new IllegalStateException("Article with Id" + article.getArticleId() + " does not exists");
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