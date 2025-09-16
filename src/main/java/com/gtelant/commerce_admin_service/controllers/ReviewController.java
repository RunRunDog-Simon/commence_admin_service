package com.gtelant.commerce_admin_service.controllers;

import com.gtelant.commerce_admin_service.models.Review;
import com.gtelant.commerce_admin_service.requests.CreateReviewRequest;
import com.gtelant.commerce_admin_service.requests.UpdateReviewRequest;
import com.gtelant.commerce_admin_service.responses.GetReviewResponse;
import com.gtelant.commerce_admin_service.service.PosterService;
import com.gtelant.commerce_admin_service.service.ReviewService;
import com.gtelant.commerce_admin_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(("/reviews"))
public class ReviewController {


    private ReviewService reviewService;
    private UserService userService;
    private PosterService posterService;
    @Autowired
    public ReviewController(ReviewService reviewService, UserService userService, PosterService posterService){
        this.reviewService = reviewService;
        this.userService = userService;
        this.posterService = posterService;
    }

    @Operation(summary = "get reviews pagination")
    @GetMapping
    public ResponseEntity<Map<String, Object>> findReviewPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "date") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {
        Map<String, String> sortMap = Map.of( // 1. 對應前端欄位 → 後端實體屬性
                "date",     "createdAt",
                "customer", "customer.lastName",
                "product",  "product.name",
                "rating",   "rating",
                "comment",  "comment",
                "status",   "status"
        );
        // 2. 排序方向
        String sortProp = sortMap.getOrDefault(sortBy, "createdAt");
        Sort sort = "asc".equalsIgnoreCase(direction)
                ? Sort.by(sortProp).ascending()
                : Sort.by(sortProp).descending();

        PageRequest pageRequest = PageRequest.of(page, size, sort);

        // 3. 查詢資料
        Page<Review> paged = reviewService.findReviewsPage(pageRequest);
        List<GetReviewResponse> dtoList = paged.getContent()
                .stream()
                .map(GetReviewResponse::new) // 4. 用建構子 把 Review entity 轉成 GetReviewResponse
                .toList();
        // 5. 包裝成 Map 回傳
        Map<String, Object> responses = new HashMap<>();
        responses.put("content", dtoList);
        responses.put("page", paged.getNumber());
        responses.put("size", paged.getSize());
        responses.put("totalElements", paged.getTotalElements());
        responses.put("totalPages", paged.getTotalPages());
        responses.put("first", paged.isFirst());
        responses.put("last", paged.isLast());

        return ResponseEntity.ok(responses);
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
