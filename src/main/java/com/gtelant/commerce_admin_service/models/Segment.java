package com.gtelant.commerce_admin_service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "segments",
        uniqueConstraints = {
        @UniqueConstraint (name = "uk_segments_name", columnNames = "segment_name")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor //包含此行以上三行可以取代建構子跟getter setter
public class Segment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "segment_id")
    private long segmentId;

    @Column(name = "segment_name", nullable = false, length = 100)
    private String segmentName;

    @Column(name = "description",nullable = false, columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "segment", fetch = FetchType.LAZY)
    private List<UserSegment> userSegments = new ArrayList<>();
}
