package com.devcrew.moodcode.domain.product.dto;

import com.devcrew.moodcode.domain.product.Category;
import com.devcrew.moodcode.domain.product.Product;
import lombok.Builder;

@Builder

public record ProductResponse(
        Long productId,
        String productName,
        Category category,
        Long originalPrice,
        String thumbnailImageUrl,
        boolean isDeleted
) {

    public static ProductResponse from (Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .category(product.getCategory())
                .originalPrice(product.getOriginalPrice())
                .thumbnailImageUrl(product.getThumbnailImageUrl())
                .isDeleted(product.isDeleted())
                .build();

    }
}
