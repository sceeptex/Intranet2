package com.example.demo.article;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table
public class Article{
    @Id //JAVA AP
    @SequenceGenerator(
            name = "article_sequence",
            sequenceName = "article_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "article_sequence"
    )

    private Long articleId;
    private String title;
    private String teaser;
    private String text;
    private String picture; //Todo
    private String category;
    private String author; //Todo
    private LocalDateTime createDateTime;
    private LocalDateTime lastModifiedDateTime;
    private Boolean isActive;

    public Article() {
    }

    public Article(String title, String teaser, String text, String picture, String category, String author) {
        this.title = title;
        this.teaser = teaser;
        this.text = text;
        this.picture = picture;
        this.category = category;
        this.author = author;
        this.createDateTime = LocalDateTime.now();
        this.lastModifiedDateTime = LocalDateTime.now();
        this.isActive = true;
    }

    public Article(String title) {
        this.title = title;
        this.createDateTime = LocalDateTime.now();
        this.lastModifiedDateTime = LocalDateTime.now();
        this.isActive = true;
    }

    // Getter
    public Long getArticleId() {
        return articleId;
    }

    public String getTitle() {
        return title;
    }

    public String getTeaser() {
        return teaser;
    }

    public String getText() {
        return text;
    }

    public String getPicture() {
        return picture;
    }

    public String getCategory() {
        return category;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public LocalDateTime getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    //Setter

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public void setLastModifiedDateTime(LocalDateTime lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", title='" + title + '\'' +
                ", teaser='" + teaser + '\'' +
                ", text='" + text + '\'' +
                ", picture='" + picture + '\'' +
                ", category='" + category + '\'' +
                ", author='" + author + '\'' +
                ", createDateTime=" + createDateTime +
                ", lastModifiedDateTime=" + lastModifiedDateTime +
                '}';
    }
}