package com.devcrew.moodcode.domain.cart;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@Table(
//    name = "cart",
//    uniqueConstraints = {
//        @UniqueConstraint(
//            name = "uk_user_product_option", // 제약 조건 이름
//            columnNames = {"user_id", "product_option_id"}
//        )
//    }
//)
public class Cart {
  @Id
  @Column(name = "cart_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cartId;

  // Default 1
  @Column(nullable = false, columnDefinition = "int default 1")
  private int count = 1;

  @UpdateTimestamp // 변경 시 자동 업데이트.
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  // User와 ProductOption 클래스가 구현되면 작성.
  // @OneToOne
  // private User userId;
  // @OneToMany
  // private List<ProductOption> productOptions;

  // 장바구니에 상품 추가
  public void add(Long productOptionId) {
    productOptions.add(productOptionId);
    updatedAt = LocalDateTime.now();
  }

  // 장바구니 상품 삭제
  public void remove(Long productOptionId) {
    productOptions.remove(productOptionId);
    updatedAt = LocalDateTime.now();
  }

  public void addCount() {
    count++;
  }
}
