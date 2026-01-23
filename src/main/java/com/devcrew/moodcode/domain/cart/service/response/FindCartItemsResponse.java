package com.devcrew.moodcode.domain.cart.service.response;

import com.devcrew.moodcode.domain.cart.CartItem;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

public record FindCartItemsResponse(List<FindCartItemResponse> findCartItemResponse, Integer totalPrice) {

  public static FindCartItemsResponse from(
      List<CartItem> cartItems) {
    return new FindCartItemsResponse(
        cartItems.stream().map(cartItem -> {
          return FindCartItemResponse.builder()
              .cartItemId(cartItem.getId())
              .optionName(cartItem.getProductOption().getOptionName())
              .count(cartItem.getCount())
              .updatedAt(cartItem.getUpdatedAt())
              .productOptionId(cartItem.getProductOption().getId())
              .cartId(cartItem.getCart().getId()).build();
        }).toList(),
        null
    );
  }
  @Builder
  public record FindCartItemResponse(Long cartItemId, String optionName, int count, LocalDateTime updatedAt, Long productOptionId, Long cartId) {

  }
}


