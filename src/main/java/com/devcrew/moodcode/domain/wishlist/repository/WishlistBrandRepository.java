package com.devcrew.moodcode.domain.wishlist.repository;

import com.devcrew.moodcode.domain.wishlist.WishlistBrand;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistBrandRepository extends JpaRepository<WishlistBrand, Long> {

  Optional<WishlistBrand> findByBrandId(Long brandId);
}
