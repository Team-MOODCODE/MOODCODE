package com.devcrew.moodcode.domain.cart.controller;

import com.devcrew.moodcode.domain.cart.controller.command.AddCartItemCommand;
import com.devcrew.moodcode.domain.cart.controller.command.FindCartItemCommand;
import com.devcrew.moodcode.domain.cart.controller.command.RemoveCartItemCommand;
import com.devcrew.moodcode.domain.cart.controller.command.UpdateItemCommand;
import com.devcrew.moodcode.domain.cart.controller.request.AddItemRequest;
import com.devcrew.moodcode.domain.cart.controller.request.UpdateItemRequest;
import com.devcrew.moodcode.domain.cart.service.CartService;
import com.devcrew.moodcode.domain.cart.service.response.FindCartItemsResponse;
import com.devcrew.moodcode.global.api.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {
  private final CartService cartService;

  /*
  UserDTO는 사용자가 로그인한 계정의 정보를 가지고 있는 DTO입니다. 임시로 작성한 것.
  User쪽 구현이 완료되면 수정할 예정입니다.
  클래스를 따로 만들지 않았습니다.
   */

  @PostMapping("/items")
  public void addCartItem(
//      @AuthenticationPrincipal UserDTO userDTO,
      @RequestBody @Validated AddItemRequest request) {
    Long userId = 1L; // 임시

    AddCartItemCommand command = AddCartItemCommand.of(
//        userDTO.getId(),
        userId,
        request.productOptionId());
    cartService.addCartItem(command);
  }

  @GetMapping("/items")
  public Api<FindCartItemsResponse> getCartItems(
//      @AuthenticationPrincipal UserDTO userDTO
  ) {

    Long userId = 1L; // 임시
    FindCartItemCommand command = FindCartItemCommand.from(
//        userDTO.getId(),
        userId);
    return Api.ok(cartService.getCartItems(command));
  }

  @PatchMapping("/items/{cartItemId}")
  public void updateCartItem(
//      @AuthenticationPrincipal UserDTO userDTO,
      @PathVariable Long cartItemId,
      @RequestBody UpdateItemRequest request) {

    Long userId = 1L; // 임시

    UpdateItemCommand command = UpdateItemCommand.of(
//        userDTO.getId(),
        userId,
        cartItemId,
        request.optionName(),
        request.count());
    cartService.updateCartItem(command);
  }

  @DeleteMapping("/items/{cartItemId}")
  public void removeCartItem(
//      @AuthenticationPrincipal UserDTO userDTO,
      @PathVariable Long cartItemId) {

    Long userId = 1L; // 임시

    RemoveCartItemCommand command = RemoveCartItemCommand.of(
//        userDTO.getId(),
        userId,
        cartItemId);
    cartService.removeCartItem(command);
  }

}
