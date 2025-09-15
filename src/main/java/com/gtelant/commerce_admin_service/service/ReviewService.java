package com.gtelant.commerce_admin_service.service;

import com.gtelant.commerce_admin_service.enums.ReviewStatus;
import com.gtelant.commerce_admin_service.models.Poster;
import com.gtelant.commerce_admin_service.models.Review;
import com.gtelant.commerce_admin_service.models.User;
import com.gtelant.commerce_admin_service.repositories.PosterRepo;
import com.gtelant.commerce_admin_service.repositories.ReviewRepo;
import com.gtelant.commerce_admin_service.repositories.UserRepo;
import com.gtelant.commerce_admin_service.requests.CreateReviewRequest;
import com.gtelant.commerce_admin_service.responses.GetReviewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Locale;
import java.util.Optional;

@Service
public class ReviewService {
    private ReviewRepo reviewRepo;
    private UserRepo userRepo;
    private PosterRepo posterRepo;
    @Autowired
    public ReviewService (ReviewRepo reviewRepo, UserRepo userRepo, PosterRepo posterRepo){
        this.reviewRepo = reviewRepo;
        this.userRepo = userRepo;
        this.posterRepo = posterRepo;
    }

    public GetReviewResponse createReview(CreateReviewRequest request) {
        User user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException(("User")));
        Poster poster = posterRepo.findById(request.getPosterId())
                .orElseThrow(() -> new RuntimeException("Poster Not Found!"));

        Review review = new Review();
        review.setUser(user);
        review.setPoster(poster);
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setStatus(ReviewStatus.PENDING); // 由後端決定，非由評論者決定
        Review savedReview = reviewRepo.save(review);
        return new GetReviewResponse(savedReview);
    }

    public Optional<Review> findReviewById(long id) {
        Optional<Review> review = reviewRepo.findById(id);
        return review;
    }
}
