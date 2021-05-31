package com.example.demo.article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

//Data Access Layer
public interface ArticleRepository
        extends JpaRepository<Article, Long> { //Long = ID

    //check if Title exists

    //SELECT * FROM article WHERE EMail = ?
    Optional<Article> findArticleByTitle(String title);

    //@Query("SELECT a FROM ARTICLE a WHERE a.title = ?1") //JPQL  not SQL
    // @ENTITIY (Class = Student) = SELECT FROM ???


}
