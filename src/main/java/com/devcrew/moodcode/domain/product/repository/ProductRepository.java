package com.devcrew.moodcode.domain.product.repository;

import com.devcrew.moodcode.domain.product.Category;
import com.devcrew.moodcode.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // 전체 상품 조회
    List<Product> findByIsDeletedFalse();

    // 카테고리별 조회
    List<Product> findByCategoryAndIsDeletedFalse(Category category);

    // 상품명 검색
    List<Product> findByProductNameContainingAndIsDeletedFalse(String keyword);
}
