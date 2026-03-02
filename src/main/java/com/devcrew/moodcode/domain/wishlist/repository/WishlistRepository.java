package com.devcrew.moodcode.domain.wishlist.repository;

import com.devcrew.moodcode.domain.wishlist.Wishlist;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

  Optional<Wishlist> findByUserId(Long userId);

}
