package com.gtelant.commerce_admin_service.service;

import com.gtelant.commerce_admin_service.exceptions.CategoryNotFoundException;
import com.gtelant.commerce_admin_service.exceptions.PosterNotFoundException;
import com.gtelant.commerce_admin_service.models.Category;
import com.gtelant.commerce_admin_service.models.Poster;
import com.gtelant.commerce_admin_service.repositories.CategoryRepo;
import com.gtelant.commerce_admin_service.repositories.PosterRepo;
import com.gtelant.commerce_admin_service.requests.CreatePosterRequest;
import com.gtelant.commerce_admin_service.requests.UpdatePosterRequest;
import com.gtelant.commerce_admin_service.responses.GetPosterResponse;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PosterService {

    private final PosterRepo posterRepo;
    private final CategoryRepo categoryRepo;

    @Autowired
    public PosterService (PosterRepo posterRepo, CategoryRepo categoryRepo){
        this.posterRepo = posterRepo;
        this.categoryRepo = categoryRepo;
    }

    public Page<Poster> findAllPostersPage(String queryName, Long categoryId, Integer stockFrom, Integer stockTo, PageRequest pageRequest){
        Specification<Poster> spec = posterSpecification(queryName, categoryId, stockFrom, stockTo);
        return posterRepo.findAll(spec, pageRequest);
    }

    public GetPosterResponse findPosterById(long id) {
        Poster poster = posterRepo.findById(id).orElseThrow(()-> new PosterNotFoundException(id));
        GetPosterResponse response = new GetPosterResponse(poster);
        return response;
    }

    @Transactional //這註解是啥?
    public GetPosterResponse createPoster(CreatePosterRequest request) {
        Poster poster = new Poster();
        poster.setPosterName(request.getPosterName());
        poster.setWidth(request.getWidth());
        poster.setHeight(request.getHeight());
        poster.setPrice(request.getPrice());
        poster.setStock(request.getStock());
        poster.setImageUrl(request.getImageUrl());
        poster.setThumbnailUrl(request.getThumbnailUrl());
        poster.setReference(request.getReference());
        poster.setDescription(request.getDescription());
        if(request.getCategoryId() != null){
            Category category = categoryRepo.findById(request.getCategoryId())
                    .orElseThrow(() -> new CategoryNotFoundException(request.getCategoryId()));
            poster.setCategory(category);
        }
        Poster savedPoster = posterRepo.save(poster);
        GetPosterResponse response = new GetPosterResponse(savedPoster);
        return  response;
    }


    public GetPosterResponse updatePosterById(long id, UpdatePosterRequest request) {
        Poster poster = posterRepo.findById(id).orElseThrow(()-> new PosterNotFoundException(id));
        if(request.getPosterName() != null){
            poster.setPosterName(request.getPosterName());
        }
        if(request.getWidth() != null){
            poster.setWidth(request.getWidth());
        }
        if(request.getHeight() != null){
            poster.setHeight(request.getHeight());
        }
        if(request.getPrice() != null){
            poster.setPrice(request.getPrice());
        }
        if(request.getStock() != null){
            poster.setStock(request.getStock());
        }
        if(request.getImageUrl() != null){
            poster.setImageUrl(request.getImageUrl());
        }
        if(request.getThumbnailUrl() != null){
            poster.setThumbnailUrl(request.getThumbnailUrl());
        }
        if(request.getReference() != null){
            poster.setReference(request.getReference());
        }
        if(request.getDescription() != null){
            poster.setDescription(request.getDescription());
        }
        if(request.getCategoryId() != null){
            Category category = categoryRepo.findById(request.getCategoryId())
                    .orElseThrow(()-> new CategoryNotFoundException(request.getCategoryId()));
            poster.setCategory(category);
        }
        Poster updatedPoster = posterRepo.save(poster);
        GetPosterResponse response = new GetPosterResponse(updatedPoster);
        return response;
    }

    public void deletePosterById(long id) {
        posterRepo.deleteById(id);
    }


    // 以下Kermit的
    private Specification<Poster> posterSpecification(String queryName, Long categoryId, Integer stockFrom, Integer stockTo) {
        return (((root, query, criteriaBuilder) ->  {
            List<Predicate> predicates = new ArrayList<>();
            if(queryName != null && !queryName.isEmpty()) {
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("reference")), "%"+ queryName.toLowerCase()+"%")
                ));
            }

            if(categoryId != null) {
//                Join<Product , Category> productCategoryJoin = root.join("category");
                predicates.add(criteriaBuilder.equal(root.get("category").get("categoryId"), categoryId));
            }

            if(stockFrom != null && stockTo != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("stock"), stockFrom));
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("stock"), stockTo));
            }

            Predicate[] predicateArray = predicates.toArray(new Predicate[0]);
            return criteriaBuilder.and(predicateArray);
        }));
    }

    public Page<Poster> searchPosters(String queryName, Long categoryId, Integer stockFrom, Integer stockTo, PageRequest pageRequest) {
        return posterRepo.findAll(posterSpecification(queryName, categoryId, stockFrom, stockTo), pageRequest);
    }

}
