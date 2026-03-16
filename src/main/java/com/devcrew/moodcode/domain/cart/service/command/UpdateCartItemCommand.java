package com.devcrew.moodcode.domain.cart.service.command;

import lombok.Builder;

@Builder
public record UpdateCartItemCommand(
    Long userId,
    String optionName,
    Integer count
) {
}
