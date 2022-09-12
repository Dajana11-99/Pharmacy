package com.example.apotekasmilje.model.products;

import com.example.apotekasmilje.model.users.Basket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasketProducts implements Serializable {
    @EmbeddedId
    private BasketProductId id;

    @ManyToOne
    @JoinColumn(name = "basket_product_id",insertable = false,updatable = false)
    private Basket basket;

    @ManyToOne
    @JoinColumn(name = "product_basket_id",insertable = false,updatable = false)
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;




}
