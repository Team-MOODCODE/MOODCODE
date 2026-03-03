package com.devcrew.moodcode.domain.cart.repository;

import com.devcrew.moodcode.domain.cart.CartItem;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

  Optional<CartItem> findByProductOptionId(Long productOptionId);

  boolean existsByCartIdAndProductOptionId(Long cartId, Long productOptionId);

}
