package com.devcrew.moodcode.domain.wishlist;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wishlist_product")
public class WishlistProduct {

  @Id @Column(name = "wish_product_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  private List<Product> products;

  @OneToOne(mappedBy = "wishlist_product", cascade = CascadeType.ALL, orphanRemoval = true)
  private Wishlist wishlist;

  public void addProduct(Product product, Wishlist wishlist) {
    products.add(product);
    this.wishlist = wishlist;
  }

  public void removeProduct(Product product) {
    products.remove(product);
  }


}
