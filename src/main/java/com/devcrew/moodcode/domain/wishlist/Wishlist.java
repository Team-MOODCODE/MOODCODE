package com.devcrew.moodcode.domain.wishlist;

import com.devcrew.moodcode.domain.cart.User;
import com.devcrew.moodcode.global.common.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;

@Entity
@Getter
public class Wishlist extends BaseTimeEntity {
  @Id @Column(name = "wishlist_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "wishlist_product_id", nullable = true)
  private WishlistProduct wishlistProduct;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "brand_id", nullable = true)
  private Brand brand;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

}
