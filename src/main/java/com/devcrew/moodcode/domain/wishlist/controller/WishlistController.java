package com.devcrew.moodcode.domain.wishlist.controller;

import com.devcrew.moodcode.global.auth.LoginUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1")
public interface WishlistController {

  public ResponseEntity<Void> like(
      @LoginUser Long userId,
      Long itemId
  );


  public ResponseEntity<Void> remove(
      @LoginUser Long userId,
      Long itemId
  );

  public ResponseEntity<?> getWishlist(
      @LoginUser Long userId
  );

}
