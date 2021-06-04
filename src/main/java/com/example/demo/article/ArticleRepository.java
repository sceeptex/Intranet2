package com.example.demo.article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;
import java.time.LocalDateTime;

//Data Access Layer
public interface ArticleRepository
        extends JpaRepository<Article, Long> { //Long = ID

    //check if Title exists
    Optional<Article> findArticleByTitle(String title);

    List<Article> findArticleByCategory(String category);

    List<Article> findArticleByAuthor(String author);


    @Query("select a from Article a where a.createDateTime >= :fromCreateDateTime")
    List<Article> findAllWithCreationDateTimeAfter(
            @Param("fromCreateDateTime") LocalDateTime fromCreateDateTime
    );

    @Query("select a from Article a where a.createDateTime <= :toCreateDateTime")
    List<Article> findAllWithCreationDateTimeBefore(
            @Param("toCreateDateTime") LocalDateTime toCreateDateTime
    );

    @Query("select a from Article a where a.createDateTime >= :fromCreateDateTime and a.createDateTime <= :toCreateDateTime")
    List<Article> findAllWithCreationDateTimeBetween(
            @Param("fromCreateDateTime") LocalDateTime fromCreateDateTime,
            @Param("toCreateDateTime") LocalDateTime toCreateDateTime
    );
}