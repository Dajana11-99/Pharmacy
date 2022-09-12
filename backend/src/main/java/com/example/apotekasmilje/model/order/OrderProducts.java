package com.example.apotekasmilje.model.order;

import com.example.apotekasmilje.model.products.BasketProductId;
import com.example.apotekasmilje.model.products.Product;
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
public class OrderProducts  implements Serializable {
    @EmbeddedId
    private OrderProductId id;

    @ManyToOne
    @JoinColumn(name = "order_product_id",insertable = false,updatable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_order_id",insertable = false,updatable = false)
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;
}
