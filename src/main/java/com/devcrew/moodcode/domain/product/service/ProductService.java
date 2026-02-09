package com.devcrew.moodcode.domain.product.service;

import com.devcrew.moodcode.domain.product.Category;
import com.devcrew.moodcode.domain.product.Product;
import com.devcrew.moodcode.domain.product.ProductOption;
import com.devcrew.moodcode.domain.product.dto.ProductDetailResponse;
import com.devcrew.moodcode.domain.product.dto.ProductOptionResponse;
import com.devcrew.moodcode.domain.product.dto.ProductResponse;
import com.devcrew.moodcode.domain.product.repository.ProductOptionRepository;
import com.devcrew.moodcode.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;

    /**
     * 상품 목록 조회
     * - category + keyword 동시 사용 불가
     * - category 없으면 전체 조회
     * - 항상 is_deleted = false
     */
    public List<ProductResponse> getProducts(String category, String keyword) {

        if (StringUtils.hasText(category) && StringUtils.hasText(keyword)) {
            throw new IllegalArgumentException("category와 keyword는 동시에 사용할 수 없습니다.");
        }

        List<Product> products;

        if (StringUtils.hasText(category)) {
            Category categoryEnum;

            try {
                categoryEnum = Category.valueOf(category.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("유효하지 않은 category 값입니다.");
            }

            products = productRepository.findByCategoryAndIsDeletedFalse(categoryEnum);

        } else if (StringUtils.hasText(keyword)) {

            products = productRepository.findByProductNameContainingAndIsDeletedFalse(keyword);

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
                .orElseThrow(() ->
                        new IllegalArgumentException("존재하지 않거나 삭제된 상품입니다.")
                );

        List<ProductOption> options =
                productOptionRepository.findByProductId(productId);

        List<ProductOptionResponse> optionResponses = options.stream()
                .map(ProductOptionResponse::from)
                .toList();

        return ProductDetailResponse.from(product, optionResponses);
    }
}
