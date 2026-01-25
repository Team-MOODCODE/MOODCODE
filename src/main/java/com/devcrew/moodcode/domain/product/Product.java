package com.devcrew.moodcode.domain.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Getter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "brand_id", nullable = false)
    private Long brandId;

    @Column(name = "product_name", nullable = false, length = 100)
    private String productName;

    @Column(name = "category", nullable = false, length = 20)
    private String category;

    @Column(name = "original_price", nullable = false)
    private Long originalPrice;

    @Column(name = "thumbnail_image_url", length = 2083)
    private String thumbnailImageUrl;

    @Column(name = "product_like_count")
    private Long productLikeCount;

    @Column(name = "view_count")
    private Long viewCount;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;
}
