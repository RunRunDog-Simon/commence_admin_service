package com.gtelant.commerce_admin_service.service;

import com.gtelant.commerce_admin_service.models.Category;
import com.gtelant.commerce_admin_service.models.Poster;
import com.gtelant.commerce_admin_service.models.User;
import com.gtelant.commerce_admin_service.models.UserSegment;
import com.gtelant.commerce_admin_service.repositories.CategoryRepo;
import com.gtelant.commerce_admin_service.repositories.PosterRepo;
import com.gtelant.commerce_admin_service.requests.CreatePosterRequest;
import com.gtelant.commerce_admin_service.requests.UpdatePosterRequest;
import com.gtelant.commerce_admin_service.responses.GetPosterResponse;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PosterService {

    private final PosterRepo posterRepo;
    private final CategoryRepo categoryRepo;

    public PosterService (PosterRepo posterRepo, CategoryRepo categoryRepo){
        this.posterRepo = posterRepo;
        this.categoryRepo = categoryRepo;
    }

    public List<Poster> findAllPosters() {
        return posterRepo.findAll();
    }

    public Optional<Poster> findPosterById(long id) {
        Optional<Poster> poster = posterRepo.findById(id);
        return poster;
    }

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
        poster.setCategory(categoryRepo.findById(request.getCategoryId()).get());
        Poster savedPoster = posterRepo.save(poster);
        GetPosterResponse response = new GetPosterResponse(savedPoster);
        return  response;
    }

    public GetPosterResponse createPosterWithCategory(CreatePosterRequest request, Category category) {
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
        poster.setCategory(category);
        Poster savedPoster = posterRepo.save(poster);
        GetPosterResponse response = new GetPosterResponse(savedPoster);
        return  response;
    }


    public GetPosterResponse updatePosterById(long id, UpdatePosterRequest request) {
        Optional <Poster> poster = posterRepo.findById(id);
        poster.get().setPosterName(request.getPosterName());
        poster.get().setWidth(request.getWidth());
        poster.get().setHeight(request.getHeight());
        poster.get().setPrice(request.getPrice());
        poster.get().setStock(request.getStock());
        poster.get().setImageUrl(request.getImageUrl());
        poster.get().setThumbnailUrl(request.getThumbnailUrl());
        poster.get().setReference(request.getReference());
        poster.get().setDescription(request.getDescription());
        poster.get().setCategory(request.getCategory());
        Poster updatedPoster = posterRepo.save(poster.get());
        GetPosterResponse response = new GetPosterResponse(updatedPoster);
        return response;
    }

    public ResponseEntity<Void> deletePosterById(long id) {
        posterRepo.deleteById(id);
        return ResponseEntity.noContent().build();
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
                predicates.add(criteriaBuilder.equal(root.get("category").get("id"), categoryId));
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

    public Page<Poster> findAllPostersPage(PageRequest pageRequest) {
        return posterRepo.findAll(pageRequest);
    }
}
