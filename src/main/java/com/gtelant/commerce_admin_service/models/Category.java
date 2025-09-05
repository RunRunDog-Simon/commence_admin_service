package com.gtelant.commerce_admin_service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "segments",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_segments_name", columnNames = "segment_name")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor //包含此行以上三行可以取代建構子跟getter setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private long categoryId;

    @Column(name = "category_name", nullable = false, length = 100)
    private String categoryName;

    @Column(name = "description",nullable = false, columnDefinition = "TEXT")
    private String description;
}
