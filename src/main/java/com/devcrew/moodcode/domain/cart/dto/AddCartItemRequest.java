package com.devcrew.moodcode.domain.cart.dto;

import com.devcrew.moodcode.domain.cart.service.command.AddCartItemCommand;
import jakarta.validation.constraints.NotNull;

public record AddCartItemRequest(
    @NotNull(message = "id는 필수 입력값입니다.") Long productOptionId,
    @NotNull(message = "수량은 필수 입력값입니다.") Integer count) {

  public AddCartItemCommand toCommand() {
    return AddCartItemCommand.builder()
        .productOptionId(productOptionId)
        .count(count)
        .build();
  }
}
