package com.devcrew.moodcode.domain.cart;

import com.devcrew.moodcode.global.common.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
    name = "cartItem",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_cart_product_option", // 제약 조건 이름
            columnNames = {"cart_id", "product_option_id"}
        )
    }
)
public class CartItem extends BaseTimeEntity {
  @Id @Column(name = "cart_item_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private int count;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cart_id")
  private Cart cart;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_option_id")
  private ProductOption productOption;

  public void addCount(int count) {
    this.count += count;
  }

  public void updateOption(ProductOption productOption, int count) {
    this.productOption = productOption;
    this.count = count;
  }

  public static CartItem of(ProductOption productOption, int count, Cart cart) {
    return CartItem.builder()
        .productOption(productOption)
        .count(count)
        .cart(cart)
        .build();
  }

}
