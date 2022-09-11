package com.example.apotekasmilje.model.products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class BasketProductId implements Serializable {

    @Column(name = "basket_product_id")
    private Long basketId;


    @Column(name = "product_basket_id")
    private Long productId;

}