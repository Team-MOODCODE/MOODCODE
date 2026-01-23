package com.devcrew.moodcode.domain.cart.controller.command;

public record RemoveCartItemCommand(Long userId, Long cartItemId) {

  public static RemoveCartItemCommand of(
      Long userId,
      Long cartItemId) {
    return new RemoveCartItemCommand(
        userId,
        cartItemId
    );
  }
}
