package com.devcrew.moodcode.domain.cart.controller.command;

public record FindCartItemCommand(Long userId) {
  public static FindCartItemCommand from(Long userId) {
    return new FindCartItemCommand(userId);
  }
}
