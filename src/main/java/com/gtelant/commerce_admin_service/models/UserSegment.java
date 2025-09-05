package com.gtelant.commerce_admin_service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_segment",
        uniqueConstraints = @UniqueConstraint(name = "uk_user_segment",
                columnNames = {"user_id", "segment_id"}) //確保 user_id + segment_id 不重複
)
@SQLDelete(sql = "UPDATE user_segment SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
@Data
@NoArgsConstructor
@AllArgsConstructor //包含此行以上三行可以取代建構子跟getter setter
public class UserSegment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // ()裡面是啥要確認
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "segment_id", nullable = false)
    private Segment segment;
//    下面兩行等同上面三行  上面是設計一個物件去做關聯，下面是用id去做關聯
//    @Column(name = "segment_id", nullable = false)
//    private long segmentId;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
