package com.gtelant.commerce_admin_service.controllers;

import com.gtelant.commerce_admin_service.models.Review;
import com.gtelant.commerce_admin_service.requests.CreateReviewRequest;
import com.gtelant.commerce_admin_service.requests.UpdateReviewRequest;
import com.gtelant.commerce_admin_service.responses.GetReviewResponse;
import com.gtelant.commerce_admin_service.service.PosterService;
import com.gtelant.commerce_admin_service.service.ReviewService;
import com.gtelant.commerce_admin_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(("/reviews"))
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    private UserService userService;
    private PosterService posterService;
    public ReviewController(ReviewService reviewService, UserService userService, PosterService posterService){
        this.reviewService = reviewService;
        this.userService = userService;
        this.posterService = posterService;
    }

    @PostMapping
    public ResponseEntity<GetReviewResponse> createReview(@RequestBody CreateReviewRequest request){
        GetReviewResponse response = reviewService.createReview(request);
        return ResponseEntity.ok(response);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<GetReviewResponse> updateReviewById(@PathVariable long id, @RequestBody UpdateReviewRequest request){
//        Optional<Review> review = reviewService.findReviewById(id);
//        if(review.isPresent()){
//            GetReviewResponse response = reviewService.updateReviewById(id, request);
//            return ResponseEntity.ok(response);
//        }
//        return ResponseEntity.notFound().build();
//    }
}
