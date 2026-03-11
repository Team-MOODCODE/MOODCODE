package com.devcrew.moodcode.domain.cart.dto;

import com.devcrew.moodcode.domain.cart.CartItem;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

public record FindCartItemsResponse(
    List<FindCartItemResponse> findCartItemResponse,
    Integer totalPrice
) {

  public static FindCartItemsResponse from(
      List<CartItem> cartItems) {
    return new FindCartItemsResponse(
        cartItems.stream().map(cartItem -> {
          return FindCartItemResponse.builder()
              .cartItemId(cartItem.getId())
              .optionName(cartItem.getProductOption().getOptionName())
              .count(cartItem.getCount())
              .updatedAt(cartItem.getUpdatedAt())
              .createdAt(cartItem.getCreatedAt())
              .productOptionId(cartItem.getProductOption().getId())
              .cartId(cartItem.getCart().getId()).build();
        }).toList(),
        null
    );
  }
  @Builder
  public record FindCartItemResponse(
      Long cartItemId,
      String optionName,
      int count,
      LocalDateTime updatedAt,
      LocalDateTime createdAt,
      Long productOptionId,
      Long cartId
  ) {

  }
}


