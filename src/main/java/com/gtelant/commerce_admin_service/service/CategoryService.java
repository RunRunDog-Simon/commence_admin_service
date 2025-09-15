package com.gtelant.commerce_admin_service.service;

import com.gtelant.commerce_admin_service.models.Category;
import com.gtelant.commerce_admin_service.repositories.CategoryRepo;
import com.gtelant.commerce_admin_service.requests.CreateCategoryRequest;
import com.gtelant.commerce_admin_service.requests.UpdateCategoryRequest;
import com.gtelant.commerce_admin_service.responses.GetCategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired //放哪裡??
    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo){
        this.categoryRepo = categoryRepo;
    }

    public List<Category> findAllCategories() {
        return categoryRepo.findAll();
    }

    public Optional<Category> findCategoryById(long id) {
        Optional<Category> category = categoryRepo.findById(id);
        return category;
    }

    public GetCategoryResponse createCategory(CreateCategoryRequest request) {
        Category category = new Category();
        category.setCategoryName(request.getCategoryName());
        Category createdCategory = categoryRepo.save(category);
        GetCategoryResponse response = new GetCategoryResponse(createdCategory);
        return response;
    }

    //這邊要用void? 還是回Category
    public GetCategoryResponse updateCategoryById(long id, UpdateCategoryRequest request) {
        Optional<Category> category = categoryRepo.findById(id);
        category.get().setCategoryName(request.getCategoryName());
        Category updatedCategory = categoryRepo.save(category.get());
        GetCategoryResponse response = new GetCategoryResponse(updatedCategory);
        return  response;
    }

    public ResponseEntity<Void> deleteCategoryById(long id) {
        if(categoryRepo.existsById(id)){
            categoryRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
