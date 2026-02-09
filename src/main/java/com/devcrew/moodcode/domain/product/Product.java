package com.devcrew.moodcode.domain.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    private String productName;

    @Enumerated(EnumType.STRING)
    private Category category; // TOP, OUTER ...

    @Column(name = "original_price", nullable = false)
    private Long originalPrice;

    @Column(name = "thumbnail_image_url")
    private String thumbnailImageUrl;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;
}
