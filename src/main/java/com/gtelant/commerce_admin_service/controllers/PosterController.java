package com.gtelant.commerce_admin_service.controllers;

import com.gtelant.commerce_admin_service.models.Poster;
import com.gtelant.commerce_admin_service.requests.CreatePosterRequest;
import com.gtelant.commerce_admin_service.requests.UpdatePosterRequest;
import com.gtelant.commerce_admin_service.responses.GetPosterResponse;
import com.gtelant.commerce_admin_service.service.PosterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posters")
public class PosterController {

    private final PosterService posterService;

    public PosterController (PosterService posterService){
        this.posterService = posterService;
    }

    @GetMapping
    public ResponseEntity<List<GetPosterResponse>> findAllPosters(){
        List <Poster> posters = posterService.findAllPosters();
        return ResponseEntity.ok(posters.stream().map(GetPosterResponse::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetPosterResponse> findPosterById(@PathVariable long id){
        Optional<Poster> poster = posterService.findPosterById(id);
        if(poster.isPresent()){
            GetPosterResponse response = new GetPosterResponse(poster.get());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<GetPosterResponse> createPoster(@RequestBody CreatePosterRequest request){
        GetPosterResponse response = posterService.createPoster(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetPosterResponse> updatePosterById(@PathVariable long id, @RequestBody UpdatePosterRequest request){
        Optional<Poster> poster = posterService.findPosterById(id);
        if(poster.isPresent()){
            GetPosterResponse response = posterService.updatePosterById(id, request);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }  //return GetUserResponse or UpdateUserRequest ?

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePosterById(@PathVariable long id){
        return posterService.deletePosterById(id);
    }

}
