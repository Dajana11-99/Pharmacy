package com.example.apotekasmilje.model.products; /***********************************************************************
 * Module:  ProductSale.java
 * Author:  dajan
 * Purpose: Defines the Class ProductSale
 ***********************************************************************/



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
public class ProductSale {
    @EmbeddedId
    private ProductSaleId id;

    @ManyToOne
    @JoinColumn(name = "sale_id", insertable = false, updatable = false)
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @Column(name = "discount")
    private Integer discount;

    @Embeddable
    public static class ProductSaleId implements Serializable {
        @Column(name = "sale_id")
        protected Long saleId;

        @Column(name = "product_id")
        protected Long productId;

    }

}