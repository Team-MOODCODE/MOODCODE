package com.devcrew.moodcode.domain.product.dto;

import java.util.List;

public record ProductOptionListResponse(
        int count,
        List<ProductOptionResponse> options
) {}
