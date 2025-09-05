package com.gtelant.commerce_admin_service.responses;

import com.gtelant.commerce_admin_service.models.User;
import com.gtelant.commerce_admin_service.models.UserSegment;

import java.util.ArrayList;
import java.util.List;

public class GetUserResponse {
    private Long userId;
    private String firstName;
    private String lastName;
    private String role;

    // ✅ 新增：此使用者擁有的 Segment 名稱（例如 ["Compulsive", "Reviewer"]）
    // 不用物件:直接把 JPA 實體 List<UserSegment> 放到回傳 DTO
    //風險：序列化循環（User → UserSegment → User → …）、把資料庫技術欄位（deletedAt 等）一併曝露、之後欄位一變 API 就壞。
    //LAZY 載入問題
    //如果查詢沒把關聯撈出來（或沒有開 OSIV / 不在同一個交易裡），user.getUserSegmentList() 可能空或觸發 LazyInitializationException。
    private List<GetUserSegmentResponse> userSegments;

    public GetUserResponse() {
    }

    public  GetUserResponse(User user){
        this.userId = user.getUserId();
        this.lastName = user.getLastName();
        this.firstName = user.getFirstName();
        this.role = user.getRole();
        this.userSegments = user.getUserSegmentList().stream().map(GetUserSegmentResponse::new).toList();
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<GetUserSegmentResponse> getUserSegments() {
        return userSegments;
    }

    public void setUserSegments(List<GetUserSegmentResponse> userSegments) {
        this.userSegments = userSegments;
    }
}
