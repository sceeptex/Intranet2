package com.example.demo.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/category")
public class CategoryController {
    private final  CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) { this.categoryService = categoryService; }

    @GetMapping
    public List<Category> getCategorys() {
        return categoryService.getCategorys();
    }

    @PostMapping
    public void  registerNewCategory(@RequestBody Category category) { categoryService.addNewCategory(category);}

    @DeleteMapping(path = "{CategoryName}")
    public void deleteCategory(@PathVariable("CategoryName") String CategoryName){
        categoryService.deleteCategory(CategoryName);
    }

}