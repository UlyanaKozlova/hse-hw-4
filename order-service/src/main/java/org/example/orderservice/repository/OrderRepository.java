package org.example.orderservice.repository;

import org.example.orderservice.entity.order.Order;
import org.example.orderservice.entity.order.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o.status FROM Order o WHERE o.id = :id")
    Status findStatusById(@Param("id") Long id);

    Order findOrderById(Long id);
}
