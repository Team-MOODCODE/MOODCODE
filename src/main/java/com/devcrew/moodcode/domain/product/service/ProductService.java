package com.devcrew.moodcode.domain.product.service;

import com.devcrew.moodcode.domain.product.Product;
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

    // 허용된 카테고리 코드 (DB 기준)
    private static final Set<String> ALLOWED_CATEGORIES =
            Set.of("TOP", "OUTER", "PANTS", "DRESS", "GOODS");

    public List<Product> getProducts(String category, String keyword) {

        // category + keyword 동시 사용 불가
        if (StringUtils.hasText(category) && StringUtils.hasText(keyword)) {
            throw new IllegalArgumentException("category와 keyword는 동시에 사용할 수 없습니다.");
        }

        // 카테고리 조회
        if (StringUtils.hasText(category)) {
            String normalizedCategory = category.trim().toUpperCase();

            if (!ALLOWED_CATEGORIES.contains(normalizedCategory)) {
                throw new IllegalArgumentException("유효하지 않은 category 값입니다.");
            }

            return productRepository
                    .findByCategoryAndIsDeletedFalse(normalizedCategory);
        }

        // 상품명 검색
        if (StringUtils.hasText(keyword)) {
            return productRepository
                    .findByProductNameContainingAndIsDeletedFalse(keyword);
        }

        // 전체 조회
        return productRepository.findByIsDeletedFalse();
    }
}
