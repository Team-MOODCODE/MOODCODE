package com.devcrew.moodcode.domain.cart.service;

import com.devcrew.moodcode.domain.cart.Cart;
import com.devcrew.moodcode.domain.cart.repository.CartRepository;
import com.devcrew.moodcode.domain.cart.service.response.FindItemsResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartService {

  private final CartRepository cartRepository;
  private final ProductOptionRepository productOptionRepository;

  // 장바구니 추가 기능
  public void add(Long userId, Long productOptionId) {
    Cart cart = findCartByUserId(userId);

    if (increaseCountIfDuplicate(userId, productOptionId, cart)) {
      return;
    }

    cart.add(productOptionId);
    cartRepository.save(cart);
  }

//  // 장바구니 상품 조회 기능
//  public FindItemsResponse getCartItems(Long userId) {
//    Cart cart = findCartByUserId(userId);
//
//  }

  // 장바구니 상품 삭제 기능
  public void remove(Long userId, Long productOptionId) {
    Cart cart = findCartByUserId(userId);

    cart.remove(productOptionId);
    cartRepository.save(cart);
  }

  private Cart findCartByUserId(Long userId) {
    Cart cart = cartRepository.findByUserId(userId)
        .orElseThrow(() -> new IllegalArgumentException());

    return cart;
  }

  private boolean increaseCountIfDuplicate(Long userId, Long productOptionId, Cart cart) {
    Boolean isDuplicate = cartRepository.existsByUserIdAndProductOptionId(userId, productOptionId);

    if (isDuplicate.equals(true)) {
      cart.addCount();
      cartRepository.save(cart);
      return true;
    }
    return false;
  }
}
