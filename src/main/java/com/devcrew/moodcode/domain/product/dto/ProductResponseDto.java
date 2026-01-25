package com.devcrew.moodcode.domain.product.dto;

import com.devcrew.moodcode.domain.product.Product;
import lombok.Getter;

@Getter
public class ProductResponseDto {

    private final Long productId;
    private final String productName;
    private final String category;
    private final Long originalPrice;
    private final String thumbnailImageUrl;

    public ProductResponseDto(Product product) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.category = product.getCategory();
        this.originalPrice = product.getOriginalPrice();
        this.thumbnailImageUrl = product.getThumbnailImageUrl();
    }
}
