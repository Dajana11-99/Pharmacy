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
public  class ProductSaleId implements Serializable {
    @Column(name = "sale_product_id")
    protected Long saleId;

    @Column(name = "product_sale_id")
    protected Long productId;

}