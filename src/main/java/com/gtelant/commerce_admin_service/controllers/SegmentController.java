package com.gtelant.commerce_admin_service.controllers;

import com.gtelant.commerce_admin_service.responses.GetSegmentResponse;
import com.gtelant.commerce_admin_service.service.SegmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/segments")
@CrossOrigin("*")
@Tag(name = "Posters Galore 分群管理")
public class SegmentController {
    private final SegmentService segmentService;

    @Autowired
    public SegmentController (SegmentService segmentService){
        this.segmentService = segmentService;
    }

    @GetMapping("/page")
    public Page<GetSegmentResponse> findAllSegmentsPage(
            @RequestParam(defaultValue="0") int page,
            @RequestParam(defaultValue="20") int size
    ){
        PageRequest pageRequest = PageRequest.of(page,size);
        return segmentService.findAllSegments(pageRequest).map(GetSegmentResponse::new);
    }

}
