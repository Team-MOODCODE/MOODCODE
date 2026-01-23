package com.devcrew.moodcode.domain.cart.service;

import com.devcrew.moodcode.domain.cart.Cart;
import com.devcrew.moodcode.domain.cart.CartItem;
import com.devcrew.moodcode.domain.cart.ProductOption;
import com.devcrew.moodcode.domain.cart.controller.command.AddCartItemCommand;
import com.devcrew.moodcode.domain.cart.controller.command.FindCartItemCommand;
import com.devcrew.moodcode.domain.cart.controller.command.RemoveCartItemCommand;
import com.devcrew.moodcode.domain.cart.controller.command.UpdateItemCommand;
import com.devcrew.moodcode.domain.cart.repository.CartItemRepository;
import com.devcrew.moodcode.domain.cart.repository.CartRepository;
import com.devcrew.moodcode.domain.cart.repository.ProductOptionRepository;
import com.devcrew.moodcode.domain.cart.service.response.FindCartItemsResponse;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartService {

  private final CartRepository cartRepository;
  private final CartItemRepository cartItemRepository;
  private final ProductOptionRepository productOptionRepository;

  // 장바구니 추가 기능
  @Transactional
  public void addCartItem(AddCartItemCommand command) {
    Cart cart = findCartByUserId(command.userId());
    List<CartItem> cartItems = cart.getCartItems();

    ProductOption productOption = findProductOption(command.productOptionId());

    cartItems.add(CartItem.createCartItem(productOption, 1, cart));

    if (increaseCountIfDuplicate(cart.getId(), command.productOptionId())) {
      return;
    }

    cartRepository.save(cart);
  }


  // 장바구니 상품 조회 기능
  public FindCartItemsResponse getCartItems(FindCartItemCommand command) {
    Cart cart = findCartByUserId(command.userId());
    List<CartItem> cartItems = cart.getCartItems();
    return FindCartItemsResponse.from(cartItems);
  }

  // 장바구니 상품 옵션 변경
  @Transactional
  public void updateCartItem(UpdateItemCommand command) {
    CartItem cartItem = findCartItemById(command.cartItemId());

    Long productId = cartItem.getProductOption().getProductId();
    String optionName = command.optionName();

    ProductOption productOption = findByProductIdAndOptionName(productId, optionName);

    cartItem.updateOption(productOption, command.count());
    cartItem.updateAt();
    cartItemRepository.save(cartItem);
  }

  // 장바구니 상품 삭제 기능
  @Transactional
  public void removeCartItem(RemoveCartItemCommand command) {
    CartItem cartItem = findCartItemById(command.cartItemId());

    cartItemRepository.delete(cartItem);
  }

  // 같은 상품의 다른 옵션 조회 (옵션을 변경 하려면, 다른 옵션도 조회를 해야 함) 상품 서비스 역할인듯.
//  public FindOptionResponse getOption(Long productId) {
//    List<ProductOption> productOptions = findAllByProductId(productId);
//
//    return FindOptionResponse.
//  }

  private Cart findCartByUserId(Long userId) {
    Cart cart = cartRepository.findByUserId(userId)
        .orElseThrow(() -> new IllegalArgumentException("장바구니에 상품이 없음"));

    return cart;
  }

  private ProductOption findByProductIdAndOptionName(Long productId, String optionName) {
    return productOptionRepository.findByProductIdAndOptionName(productId, optionName)
        .orElseThrow(() -> new IllegalArgumentException("해당하는 상품 옵션은 없음"));
  }

  private ProductOption findProductOption(Long productOptionId) {
    return productOptionRepository.findById(productOptionId)
        .orElseThrow(() -> new IllegalArgumentException("선택한 상품 옵션이 없습니다."));
  }

  private CartItem findCartItemById(Long cartItemId) {
    return cartItemRepository.findById(cartItemId)
        .orElseThrow(() -> new IllegalArgumentException(""));
  }

  private CartItem findCartItemByPrdOId(Long productOptionId) {
    return cartItemRepository.findByProductOptionId(productOptionId)
        .orElseThrow(() -> new IllegalArgumentException("내 장바구니에 선택한 상품 옵션이 없음."));
  }

  private List<ProductOption> findAllByProductId(Long productId) {
    return productOptionRepository.findAllByProductId(productId);
  }

  private boolean increaseCountIfDuplicate(Long cartId, Long productOptionId) {
    Boolean isDuplicate = cartItemRepository.existsByCartIdAndProductOptionId(cartId, productOptionId);

    if (isDuplicate.equals(true)) {
      CartItem cartItem = findCartItemByPrdOId(productOptionId);
      cartItem.addCount();
      cartItemRepository.save(cartItem);
      return true;
    }
    return false;
  }
}
