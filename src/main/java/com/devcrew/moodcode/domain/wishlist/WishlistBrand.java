package com.devcrew.moodcode.domain.wishlist;

import com.devcrew.moodcode.global.common.BaseTimeEntity;
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
import lombok.Getter;

@Entity
@Getter
@Table(name = "wishlist_brand")
public class WishlistBrand extends BaseTimeEntity {

  @Id
  @Column(name = "wish_brand_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "brand_id")
  private List<Brand> brands;

  @OneToOne(mappedBy = "wishlist_brand", cascade = CascadeType.ALL, orphanRemoval = true)
  private Wishlist wishlist;

  public void addBrand(Brand brand, Wishlist wishlist) {
    this.brands.add(brand);
    this.wishlist = wishlist;
  }

  public void removeBrand(Brand brand) {
    this.brands.remove(brand);
  }
}
