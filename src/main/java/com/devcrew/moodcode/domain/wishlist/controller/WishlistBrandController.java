package com.devcrew.moodcode.domain.wishlist.controller;

import com.devcrew.moodcode.domain.wishlist.service.WishlistBrandService;
import com.devcrew.moodcode.domain.wishlist.service.response.FindWishlistBrandsResponse;
import com.devcrew.moodcode.global.auth.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/api/v1")
public class WishlistBrandController implements WishlistController{

  private final WishlistBrandService wishlistBrandService;


  @PostMapping("/{brandId}/likes")
  @Override
  public ResponseEntity<Void> like(
      @LoginUser Long userId,
      @PathVariable Long brandId
  ) {
    wishlistBrandService.like(userId, brandId);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{brandId}/likes")
  @Override
  public ResponseEntity<Void> remove(
      @LoginUser Long userId,
      @PathVariable Long brandId
  ) {
    wishlistBrandService.remove(userId, brandId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/users/me/likes")
  @Override
  public ResponseEntity<FindWishlistBrandsResponse> getWishlist(
      @LoginUser Long userId
  ) {
    return ResponseEntity.ok(wishlistBrandService.getWishlist(userId));
  }

}
