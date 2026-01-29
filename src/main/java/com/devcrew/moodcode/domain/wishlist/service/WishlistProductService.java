package com.devcrew.moodcode.domain.wishlist.service;

import com.devcrew.moodcode.domain.wishlist.Product;
import com.devcrew.moodcode.domain.wishlist.Wishlist;
import com.devcrew.moodcode.domain.wishlist.WishlistProduct;
import com.devcrew.moodcode.domain.wishlist.repository.ProductRepository;
import com.devcrew.moodcode.domain.wishlist.repository.WishlistProductRepository;
import com.devcrew.moodcode.domain.wishlist.repository.WishlistRepository;
import com.devcrew.moodcode.domain.wishlist.service.response.FindWishlistProductsResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishlistProductService implements WishlistService{

  private final ProductRepository productRepository;
  private final WishlistRepository wishlistRepository;
  private final WishlistProductRepository wishlistProductRepository;

  @Override
  public void like(Long userId, Long productId) {
    Wishlist wishlist = findWishlistByUserIdWithThrow(userId);

    WishlistProduct wishlistProduct = findWishlistProductByWishlistIdWithThrow(wishlist.getId());

    // 사용자가 요청한(좋아요) 상품 인스턴스
    Product product = findProductByIdWithThrow(productId);

    wishlistProduct.addProduct(product, wishlist);
    wishlistRepository.save(wishlist);
  }

  @Override
  public void remove(Long userId, Long productId) {
    Wishlist wishlist = findWishlistByUserIdWithThrow(userId);

    WishlistProduct wishlistProduct = findWishlistProductByWishlistIdWithThrow(wishlist.getId());

    // 사용자가 요청한(좋아요) 상품 인스턴스
    Product product = findProductByIdWithThrow(productId);

    wishlistProduct.removeProduct(product);
    wishlistProductRepository.delete(wishlistProduct); // 연관된 레코드 싹다 삭제됨. cascade

  }

  @Override
  public FindWishlistProductsResponse getWishlist(Long userId) {
    // 로그인한 자신의 id로 자신의 위시리스트(좋아요 목록)을 조회
    Wishlist wishlist = findWishlistByUserIdWithThrow(userId);

    // 위시리스트 id로 위시리스트에 "담겨 있는" 상품 목록 조회
    WishlistProduct wishlistProduct = findWishlistProductByWishlistIdWithThrow(wishlist.getId());

    List<Product> products = wishlistProduct.getProducts();

    return FindWishlistProductsResponse.from(products);
  }

  private WishlistProduct findWishlistProductByWishlistIdWithThrow(Long wishlistId) {
    return wishlistProductRepository.findById(wishlistId)
        .orElseThrow(() -> new IllegalArgumentException());
  }

  private Wishlist findWishlistByUserIdWithThrow(Long userId) {
    return wishlistRepository.findByUserId(userId)
        .orElseThrow(() -> new IllegalArgumentException());
  }

  private Product findProductByIdWithThrow(Long productId) {
    return productRepository.findById(productId)
        .orElseThrow(() -> new IllegalArgumentException());
  }
}

