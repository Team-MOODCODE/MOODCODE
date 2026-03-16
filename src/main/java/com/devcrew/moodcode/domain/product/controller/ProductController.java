package com.devcrew.moodcode.domain.product.controller;

import com.devcrew.moodcode.domain.product.dto.*;
import com.devcrew.moodcode.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ProductListResponse> getProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String keyword
    ) {
        List<ProductResponse> products =
                productService.getProducts(category, keyword);

        ProductListResponse response = new ProductListResponse(products.size(), products);

        // ResponseEntity.ok()로 감싸서 반환 (HTTP 200)
        return ResponseEntity.ok(response);
    }

    /**
     * 상품 상세 조회
     */
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetailWrapperResponse> getProductDetail(
            @PathVariable Long productId
    ) {
        ProductDetailResponse detail =
                productService.getProductDetail(productId);

        ProductDetailWrapperResponse response = new ProductDetailWrapperResponse(detail);

        // ResponseEntity.ok()로 감싸서 반환 (HTTP 200)
        return ResponseEntity.ok(response);
    }

    /**
     * 상품 옵션 목록 조회
     */
    @GetMapping("/{productId}/options")
    public ResponseEntity<ProductOptionListResponse> getOption(
            @PathVariable Long productId
    ) {
        ProductOptionListResponse options = productService.getOption(productId);
        return ResponseEntity.ok(options);
    }
}
