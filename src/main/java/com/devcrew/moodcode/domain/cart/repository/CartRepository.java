package com.devcrew.moodcode.domain.cart.repository;

import com.devcrew.moodcode.domain.cart.Cart;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

  Optional<Cart> findByUserId(Long user);

}
