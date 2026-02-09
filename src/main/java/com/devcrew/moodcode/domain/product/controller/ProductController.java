package com.devcrew.moodcode.domain.product.controller;

import com.devcrew.moodcode.domain.product.dto.ProductDetailResponse;
import com.devcrew.moodcode.domain.product.dto.ProductResponse;
import com.devcrew.moodcode.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    /**
     * 상품 목록 조회
     * - category
     * - keyword
     */
    @GetMapping
    public List<ProductResponse> getProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String keyword
    ) {
        return productService.getProducts(category, keyword);
    }

    /**
     * 상품 상세 조회
     */
    @GetMapping("/{productId}")
    public ProductDetailResponse getProductDetail(
            @PathVariable Long productId
    ) {
        return productService.getProductDetail(productId);
    }
}
