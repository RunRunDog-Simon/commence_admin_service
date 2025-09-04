package com.gtelant.commerce_admin_service.responses;

import com.gtelant.commerce_admin_service.models.Segment;
import jakarta.persistence.Column;

public class GetSegmentResponse {
    private long segmentId;
    private String segmentName;
    private String description;

    public GetSegmentResponse() {
    }

    public GetSegmentResponse(Segment segment){
        this.segmentId = segment.getSegmentId();
        this.segmentName = segment.getSegmentName();
        this.description = segment.getDescription();
    }

    public long getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(long segmentId) {
        this.segmentId = segmentId;
    }

    public String getSegmentName() {
        return segmentName;
    }

    public void setSegmentName(String segmentName) {
        this.segmentName = segmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
