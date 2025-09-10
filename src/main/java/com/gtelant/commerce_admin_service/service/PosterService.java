package com.gtelant.commerce_admin_service.service;

import com.gtelant.commerce_admin_service.models.Poster;
import com.gtelant.commerce_admin_service.repositories.PosterRepo;
import com.gtelant.commerce_admin_service.requests.CreatePosterRequest;
import com.gtelant.commerce_admin_service.requests.UpdatePosterRequest;
import com.gtelant.commerce_admin_service.responses.GetPosterResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PosterService {

    private final PosterRepo posterRepo;

    public PosterService (PosterRepo posterRepo){
        this.posterRepo = posterRepo;
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
        poster.setCategory(request.getCategory());
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
}
