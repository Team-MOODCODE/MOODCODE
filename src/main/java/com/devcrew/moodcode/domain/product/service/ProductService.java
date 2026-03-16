package com.devcrew.moodcode.domain.product.service;

import com.devcrew.moodcode.domain.product.Category;
import com.devcrew.moodcode.domain.product.Product;
import com.devcrew.moodcode.domain.product.dto.ProductDetailResponse;
import com.devcrew.moodcode.domain.product.dto.ProductOptionListResponse;
import com.devcrew.moodcode.domain.product.dto.ProductOptionResponse;
import com.devcrew.moodcode.domain.product.dto.ProductResponse;
import com.devcrew.moodcode.domain.product.repository.ProductRepository;
import com.devcrew.moodcode.global.error.ErrorCode;
import com.devcrew.moodcode.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * 상품 목록 조회
     * - category 없으면 전체 조회
     * - 항상 is_deleted = false
     */
    public List<ProductResponse> getProducts(String category, String keyword) {

        Category categoryEnum = null;

        if (StringUtils.hasText(category)) {
            try {
                categoryEnum = Category.valueOf(category.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                // 1. 카테고리 오류 발생 시 BusinessException으로 던짐
                throw new BusinessException(ErrorCode.INVALID_CATEGORY);
            }
        }

        List<Product> products;

        if (categoryEnum != null && StringUtils.hasText(keyword)) {

            // 둘 다 있을 때 (AND 검색)
            products = productRepository
                    .findByCategoryAndNameContainingAndIsDeletedFalse(
                            categoryEnum,
                            keyword
                    );

        } else if (categoryEnum != null) {

            products = productRepository
                    .findByCategoryAndIsDeletedFalse(categoryEnum);

        } else if (StringUtils.hasText(keyword)) {

            products = productRepository
                    .findByNameContainingAndIsDeletedFalse(keyword);

        } else {

            products = productRepository.findByIsDeletedFalse();
        }

        return products.stream()
                .map(ProductResponse::from)
                .toList();
    }

    /**
     * 상품 상세 조회
     * - 삭제된 상품 조회 불가
     * - 옵션(재고 포함) 함께 반환
     */
    public ProductDetailResponse getProductDetail(Long productId) {
        Product product = productRepository.findById(productId)
                .filter(p -> !p.isDeleted())
                .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND));

        List<ProductOptionResponse> optionResponses =
                product.getProductOptions().stream()
                        .map(ProductOptionResponse::from)
                        .toList();

        return ProductDetailResponse.from(product, optionResponses);
    }

    /**
     * 상품 옵션만 조회
     */

    public ProductOptionListResponse getOption(Long productId) {

        Product product = productRepository.findById(productId)
                .filter(p -> !p.isDeleted())
                .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND));

        List<ProductOptionResponse> optionResponses =
                product.getProductOptions().stream()
                        .map(ProductOptionResponse::from)
                        .toList();

        return new ProductOptionListResponse(optionResponses);
    }
}
