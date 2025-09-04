package com.gtelant.commerce_admin_service.service;

import com.gtelant.commerce_admin_service.models.Segment;
import com.gtelant.commerce_admin_service.repositories.SegmentRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SegmentService {
    private final SegmentRepo segmentRepo;

    public SegmentService(SegmentRepo segmentRepo){
        this.segmentRepo = segmentRepo;
    }

    public Page<Segment> findAllSegments(PageRequest pageRequest) {
        return segmentRepo.findAll(pageRequest);
    }

}
