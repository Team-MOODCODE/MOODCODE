package com.devcrew.moodcode.domain.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "product_option")
public class ProductOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_option_id")
    private Long productOptionId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "option_name", nullable = false)
    private String optionName;

    @Column(nullable = false)
    private int stock;

    @Version
    private Long version;
}
