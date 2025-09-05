package com.gtelant.commerce_admin_service.responses;

import com.gtelant.commerce_admin_service.models.UserSegment;

import java.util.List;

public class GetUserSegmentResponse {
    private Long id;         // user_segment 的主鍵
    private Long userId;     // 使用者ID
    private Long segmentId;  // Segment ID
    private String segmentName;     // Segment 名稱

    public GetUserSegmentResponse(UserSegment userSegment) { // ??這邊卡住了
        this.id = userSegment.getId();
        this.userId = userSegment.getUser().getUserId();
        this.segmentId = userSegment.getSegment().getSegmentId();
        this.segmentName = userSegment.getSegment().getSegmentName();
    }

    public GetUserSegmentResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(Long segmentId) {
        this.segmentId = segmentId;
    }

    public String getSegmentName() {
        return segmentName;
    }

    public void setSegmentName(String name) {
        this.segmentName = name;
    }
}
