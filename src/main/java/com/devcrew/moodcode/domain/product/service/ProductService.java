package com.devcrew.moodcode.domain.product.service;

import com.devcrew.moodcode.domain.product.Product;
import com.devcrew.moodcode.domain.product.dto.ProductResponseDto;
import com.devcrew.moodcode.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private static final Set<String> ALLOWED_CATEGORIES = Set.of(
            "OUTER", "TOP", "BOTTOM", "ONEPIECE", "ACC"
    );

    public List<ProductResponseDto> getProducts(String category, String keyword) {

        if (StringUtils.hasText(category) && StringUtils.hasText(keyword)) {
            throw new IllegalArgumentException("category와 keyword는 동시에 사용할 수 없습니다.");
        }

        List<Product> products;

        if (StringUtils.hasText(category)) {
            String normalizedCategory = category.trim().toUpperCase();

            if (!ALLOWED_CATEGORIES.contains(normalizedCategory)) {
                throw new IllegalArgumentException("유효하지 않은 category 값입니다.");
            }

            products = productRepository
                    .findByCategoryAndIsDeletedFalse(normalizedCategory);

        } else if (StringUtils.hasText(keyword)) {

            products = productRepository
                    .findByProductNameContainingAndIsDeletedFalse(keyword);

        } else {

            products = productRepository.findByIsDeletedFalse();
        }

        // ⭐ Entity → DTO 변환
        return products.stream()
                .map(ProductResponseDto::new)
                .toList();
    }
}
