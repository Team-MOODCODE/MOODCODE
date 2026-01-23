package com.devcrew.moodcode.domain.cart.controller.command;


public record AddCartItemCommand(Long userId, Long productOptionId) {

  public static AddCartItemCommand of(
      Long userId,
      Long productOptionId) {
    return new AddCartItemCommand(
        userId,
        productOptionId
    );
  }

}
