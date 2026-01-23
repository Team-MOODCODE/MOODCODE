package com.devcrew.moodcode.domain.cart;

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
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@Table(
    name = "cartItem",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_cart_product_option", // 제약 조건 이름
            columnNames = {"cart_id", "product_option_id"}
        )
    }
)
@NoArgsConstructor
public class CartItem {
  @Id @Column(name = "cart_item_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, columnDefinition = "int default 1")
  private int count = 1;

  @UpdateTimestamp // 변경 시 자동 업데이트.
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cart_id")
  private Cart cart;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_option_id")
  private ProductOption productOption;

  public void addCount() {
    count++;
  }

  public void updateOption(ProductOption productOption, int count) {
    this.productOption = productOption;
    this.count = count;
  }

  public void updateAt() {
    this.updatedAt = LocalDateTime.now();
  }

  public CartItem(ProductOption productOption, int count, Cart cart) {
    this.productOption = productOption;
    this.count = count;
    this.updatedAt = LocalDateTime.now();
    this.cart = cart;
  }

  public static CartItem createCartItem(ProductOption productOption, int count, Cart cart) {
    return new CartItem(productOption, count, cart);
  }

}
