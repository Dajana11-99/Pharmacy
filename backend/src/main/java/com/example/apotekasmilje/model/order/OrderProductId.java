package com.example.apotekasmilje.model.order;

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
public class OrderProductId implements Serializable {

    @Column(name = "order_product_id")
    private Long orderId;

    @Column(name = "product_order_id")
    private Long productId;

}
