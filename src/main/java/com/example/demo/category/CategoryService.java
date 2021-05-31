package com.example.demo.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) { this.categoryRepository = categoryRepository; }

    public List<Category> getCategorys(){
        //return categoryRepository.findAll();
        //Category c1 = new Category();
        //return List.of(c1);
        return categoryRepository.findAll();

    }

    public void addNewCategory(Category category){
        Optional<Category> categoryOptional = categoryRepository.findCategoryByName(category.getName());
        if (categoryOptional.isPresent()){
            throw new IllegalStateException("Category taken");
        }
        else {
            categoryRepository.save(category);
        }


    }

    public void deleteCategory(String CategoryName) {
        Optional<Category> categoryOptional = categoryRepository.findCategoryByName(CategoryName);

        if (!categoryOptional.isPresent()){
            throw new IllegalStateException("Category with CategoryName" + CategoryName + " does not exists");
        }
        else {
            Long Categoryid = categoryOptional.get().getCategoryId();
            categoryRepository.deleteById(Categoryid);
        }

    }
}
