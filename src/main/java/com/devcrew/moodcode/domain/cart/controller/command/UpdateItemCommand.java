package com.devcrew.moodcode.domain.cart.controller.command;

public record UpdateItemCommand(Long userId, Long cartItemId, String optionName, Integer count) {
  public static UpdateItemCommand of(Long userId, Long cartItemId, String optionName, Integer count) {
    return new UpdateItemCommand(userId, cartItemId, optionName, count);
  }
}
