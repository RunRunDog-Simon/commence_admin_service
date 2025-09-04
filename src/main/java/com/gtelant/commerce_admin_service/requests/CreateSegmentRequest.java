package com.gtelant.commerce_admin_service.requests;

public class CreateSegmentRequest {
    private String segmentName;
    private String description;

    public CreateSegmentRequest() {
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
