package com.gtelant.commerce_admin_service.controllers;

import com.gtelant.commerce_admin_service.models.Category;
import com.gtelant.commerce_admin_service.requests.CreateCategoryRequest;
import com.gtelant.commerce_admin_service.requests.UpdateCategoryRequest;
import com.gtelant.commerce_admin_service.responses.GetCategoryResponse;
import com.gtelant.commerce_admin_service.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
//@CrossOrigin("*")
public class CategoryController {
    private final CategoryService categoryService;
    private CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @Operation(summary = "Get all categories", description = "Return a list of all categories")
    @GetMapping
    public ResponseEntity<List<GetCategoryResponse>> findAllCategories(){
        List <Category> categories = categoryService.findAllCategories();
        return ResponseEntity.ok(categories.stream().map(GetCategoryResponse::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCategoryResponse> findUserById(@PathVariable long id){
        Optional<Category> category = categoryService.findCategoryById(id);
        if(category.isPresent()){
            GetCategoryResponse response = new GetCategoryResponse(category.get());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<GetCategoryResponse> createCategory(@RequestBody CreateCategoryRequest request){
        GetCategoryResponse response = categoryService.createCategory(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetCategoryResponse> updateCategoryById(@PathVariable long id, @RequestBody UpdateCategoryRequest request){
        Optional<Category> category = categoryService.findCategoryById(id);
        if(category.isPresent()){
            GetCategoryResponse response = categoryService.updateCategoryById(id, request);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable long id){
        return categoryService.deleteCategoryById(id);
    }
}
