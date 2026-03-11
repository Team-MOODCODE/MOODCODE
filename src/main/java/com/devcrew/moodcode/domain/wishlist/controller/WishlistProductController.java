package com.devcrew.moodcode.domain.wishlist.controller;

import com.devcrew.moodcode.domain.wishlist.service.WishlistProductService;
import com.devcrew.moodcode.domain.wishlist.service.response.FindWishlistProductsResponse;
import com.devcrew.moodcode.global.auth.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class WishlistProductController implements WishlistController {

  private final WishlistProductService wishlistProductService;

  @PostMapping("/{productId}/likes")
  @Override
  public ResponseEntity<Void> like(
      @LoginUser Long userId,
      @PathVariable Long productId
  ) {
    wishlistProductService.like(userId, productId);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{productId}/likes")
  @Override
  public ResponseEntity<Void> remove(
      @LoginUser Long userId,
      @PathVariable Long productId
  ) {
    wishlistProductService.remove(userId, productId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/users/me/likes")
  @Override
  public ResponseEntity<FindWishlistProductsResponse> getWishlist(
      @LoginUser Long userId
  ) {
    FindWishlistProductsResponse response = wishlistProductService.getWishlist(userId);
    return ResponseEntity.ok(response);
  }
}
