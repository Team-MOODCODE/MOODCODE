package com.devcrew.moodcode.domain.cart.controller;

import com.devcrew.moodcode.domain.cart.controller.request.AddProductRequest;
import com.devcrew.moodcode.domain.cart.service.CartService;
import com.devcrew.moodcode.domain.cart.service.response.FindItemsResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {
  private final CartService cartService;

  /*
  UserDTO는 사용자가 로그인한 계정의 정보를 가지고 있는 DTO입니다. 임시로 작성한 것.
  User쪽 구현이 완료되면 수정할 예정입니다.
  클래스를 따로 만들지 않았습니다.
   */

  @PostMapping("/items")
  public void add(
      @AuthenticationPrincipal UserDTO userDTO,
      @RequestBody @Validated AddProductRequest addProductRequest) {

    cartService.add(userDTO.getId(), addProductRequest.productOptionId());
  }

  @DeleteMapping("/items/{itemId}")
  public void remove(
      @AuthenticationPrincipal UserDTO userDTO,
      @PathVariable Long itemId) {
    cartService.remove(userDTO.getId(), itemId);
  }

  @GetMapping("/items")
  public FindItemsResponse getCartItems(
      @AuthenticationPrincipal UserDTO userDTO) {
    return cartService.getCartItems(userDTO.getId());
  }


}
