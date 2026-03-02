package com.devcrew.moodcode.domain.wishlist.service;

import org.springframework.stereotype.Service;

@Service
public interface WishlistService {

  public void like(Long userId, Long itemId);

  public void remove(Long userId, Long itemId);

  public Object getWishlist(Long userId);

}
