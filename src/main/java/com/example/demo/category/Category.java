package com.example.demo.category;

import javax.persistence.*;

@Entity
@Table
public class Category {
    @Id
    @SequenceGenerator(
            name = "category_sequence",
            sequenceName = "category_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "article_sequence"
    )

    private Long categoryId;
    private String name;

    public Category() {
    }
    public Category(String name){
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{"+
                "CategoryId" + categoryId +
                ", name='" + name + '\'' +
                '}';
    }

}
