package com.devcrew.moodcode.domain.wishlist.repository;

import com.devcrew.moodcode.domain.wishlist.WishlistProduct;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistProductRepository extends JpaRepository<WishlistProduct, Long> {

  List<WishlistProduct> findAllByWishlistId(Long wishlistId);
  Optional<WishlistProduct> findByProductId(Long productId);
}
