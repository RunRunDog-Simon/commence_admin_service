package com.gtelant.commerce_admin_service.controllers;

import com.gtelant.commerce_admin_service.requests.CreatePosterRequest;
import com.gtelant.commerce_admin_service.requests.UpdatePosterRequest;
import com.gtelant.commerce_admin_service.responses.GetPosterResponse;
import com.gtelant.commerce_admin_service.service.CategoryService;
import com.gtelant.commerce_admin_service.service.PosterService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/posters")
public class PosterController {

    private final PosterService posterService;
    private final CategoryService categoryService;

    @Autowired
    public PosterController (PosterService posterService, CategoryService categoryService){
        this.posterService = posterService;
        this.categoryService = categoryService;
    }

    //下面照搬kermit
    @Operation(summary = "Get all product pagination", description = "Returns a page of products")
    @GetMapping("/page")
    public Page<GetPosterResponse> findPostersPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String query,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Integer stockFrom,
            @RequestParam(required = false) Integer stockTo
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return posterService.searchPosters(query, categoryId, stockFrom, stockTo, pageRequest)
                .map(GetPosterResponse::new);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetPosterResponse> findPosterById(@PathVariable long id){
        return ResponseEntity.ok(posterService.findPosterById(id));
    }

    @PostMapping
    public ResponseEntity<GetPosterResponse> createPoster(@RequestBody CreatePosterRequest request){
        GetPosterResponse response = posterService.createPoster(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetPosterResponse> updatePosterById(
            @PathVariable long id,
            @RequestBody UpdatePosterRequest request){
            GetPosterResponse response = posterService.updatePosterById(id, request);
            return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePosterById(@PathVariable long id){
        posterService.deletePosterById(id);
        return ResponseEntity.noContent().build();
    }

}
