package com.devcrew.moodcode.domain.cart.dto;

import com.devcrew.moodcode.domain.cart.service.command.UpdateCartItemCommand;
import jakarta.validation.constraints.NotNull;

public record UpdateCartItemRequest(
    @NotNull(message = "id는 필수 입력값입니다.") String optionName,
    @NotNull(message = "수량은 필수 입력값입니다.") Integer count) {

  public UpdateCartItemCommand toCommand() {
    return UpdateCartItemCommand.builder()
      .optionName(optionName)
      .count(count)
      .build();
  }

}
