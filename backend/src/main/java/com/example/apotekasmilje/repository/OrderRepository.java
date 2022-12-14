package com.example.apotekasmilje.repository;

import com.example.apotekasmilje.model.enums.OrderStatus;
import com.example.apotekasmilje.model.order.Order;
import com.example.apotekasmilje.model.products.Product;
import com.example.apotekasmilje.model.users.AuthenticatedUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository  extends PagingAndSortingRepository<Order, Long> {

    @Query("SELECT m FROM Order m  WHERE  m.status = 0 or m.status = 1 or m.status=3 or m.status=4 and m.authenticatedUser.personEmail=:email")
    List<Order> findAllByUser(@Param("email")String email);

    @Query("SELECT m FROM Order m  WHERE m.status = 0")
    List<Order> findAllCurrentOrders(Pageable paging);

    @Query("SELECT m FROM Order m" +
            "  WHERE   UPPER(m.authenticatedUser.firstName)  LIKE  UPPER(CONCAT(:name, '%')) ")
    List<Order> searchByUser(@Param("name")String name);

    @Query("SELECT m FROM Order m" +
            "  WHERE   m.status =:status ")
    List<Order> searchByStatus(@Param("status") OrderStatus name);


    @Query("SELECT m FROM Order m  WHERE m.status = 1 or m.status=3 or m.status=4")
    List<Order> findAllAcceptedOrders(Pageable paging);

    @Query("SELECT m FROM Order m  WHERE m.status = 5")
    List<Order> findAllDeliveredOrders(Pageable paging);

    @Query("SELECT m FROM Order m  WHERE m.status = 2 or m.status=5 and m.authenticatedUser.personEmail=:email")
    Page<Order> findHistory(String email, Pageable paging);

    @Query("SELECT m FROM Order m join OrderProducts " +
            " o on m.id=o.order.id WHERE  m.status = 0 or m.status = 1 or m.status=3 " +
            "or m.status=4 and o.product.id= :id")
    List<Order> checkDidUserOrderProduct(@Param("id")Long id);

}
