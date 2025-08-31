package com.gtelant.commerce_admin_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_segment",
        uniqueConstraints = @UniqueConstraint(name = "uk_user_segment",
                columnNames = {"user_id", "segment_id"}) //確保 user_id + segment_id 不重複
)
@Data
@NoArgsConstructor
@AllArgsConstructor //包含此行以上三行可以取代建構子跟getter setter
public class UserSegment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "segment_id", nullable = false)
    private Segment segment;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
