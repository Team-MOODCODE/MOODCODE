package com.devcrew.moodcode.domain.wishlist.service;

import com.devcrew.moodcode.domain.wishlist.Brand;
import com.devcrew.moodcode.domain.wishlist.Wishlist;
import com.devcrew.moodcode.domain.wishlist.WishlistBrand;
import com.devcrew.moodcode.domain.wishlist.repository.BrandRepository;
import com.devcrew.moodcode.domain.wishlist.repository.WishlistBrandRepository;
import com.devcrew.moodcode.domain.wishlist.repository.WishlistRepository;
import com.devcrew.moodcode.domain.wishlist.service.response.FindWishlistBrandsResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishlistBrandService implements WishlistService {

  private final BrandRepository brandRepository;
  private final WishlistRepository wishlistRepository;
  private final WishlistBrandRepository wishlistBrandRepository;

  @Override
  public void like(Long userId, Long brandId) {
    Wishlist wishlist = findWishlistByUserIdWithThrow(userId);

    WishlistBrand wishlistBrand = findWishlistBrandByWishlistIdWithThrow(
        wishlist.getId());

    // 사용자가 요청한(좋아요) 브랜드 인스턴스
    Brand brand = findBrandByIdWithThrow(brandId);

    wishlistBrand.addBrand(brand, wishlist);
    wishlistRepository.save(wishlist);
  }

  @Override
  public void remove(Long userId, Long brandId) {
    Wishlist wishlist = findWishlistByUserIdWithThrow(userId);

    WishlistBrand wishlistBrand = findWishlistBrandByWishlistIdWithThrow(
        wishlist.getId());

    // 사용자가 요청한(좋아요) 브랜드 인스턴스
    Brand brand = findBrandByIdWithThrow(brandId);

    wishlistBrand.removeBrand(brand);
    wishlistBrandRepository.delete(wishlistBrand);
  }

  @Override
  public FindWishlistBrandsResponse getWishlist(Long userId) {
    // 로그인한 자신의 id로 자신의 위시리스트(좋아요 목록)을 조회
    Wishlist wishlist = findWishlistByUserIdWithThrow(userId);

    // 위시리스트 id로 위시리스트에 "담겨 있는" 상품 목록 조회
    WishlistBrand wishlistBrand = findWishlistBrandByWishlistIdWithThrow(wishlist.getId());

    List<Brand> brands = wishlistBrand.getBrands();

    return FindWishlistBrandsResponse.from(brands);
  }

  private Wishlist findWishlistByUserIdWithThrow(Long userId) {
    return wishlistRepository.findByUserId(userId)
        .orElseThrow(() -> new IllegalArgumentException());
  }

  private WishlistBrand findWishlistBrandByWishlistIdWithThrow(Long wishlistId) {
    return wishlistBrandRepository.findById(wishlistId)
        .orElseThrow(() -> new IllegalArgumentException());
  }

  private Brand findBrandByIdWithThrow(Long brandId) {
    return brandRepository.findById(brandId)
        .orElseThrow(() -> new IllegalArgumentException());
  }
}
